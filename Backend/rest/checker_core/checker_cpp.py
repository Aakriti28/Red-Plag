import re
import os

from pygccxml import utils
from pygccxml import declarations
from pygccxml import parser

"""
Python library pygccxml is used.
This package provides functionality to extract and inspect declarations from C/C++ header files. 
This is accomplished by invoking an external tool like CastXML or GCC-XML, which parses a header file and dumps the declarations as a XML file. 
This XML file is then read by pygccxml and the contents are made available as appropriate Python objects.

To parse a set of C/C++ header files you use the parse function in the :mod:parser sub package which returns a tree that contains all declarations found in the header files. 
The root of the tree represents the main namespace :: and the children nodes represent the namespace contents such as other namespaces, classes, functions, etc. 
Each node in the tree is an object of a type derived from the declaration_t class. 
An inner node is always either a namespace declarations.namespace_t or a class declarations.class_t, which are both derived from declarations.scopedef_t class

Thus, we can obtain - 
1) a list of function and their arguments
2) classes and their variables, constructors, operators and methods
3) global variables
"""

scope_depth = 0 #global variable storing anintger value representing the scope
is_function = -1 #global variable storing the current function being processed

# Find out the c++ parser
generator_path, generator_name = utils.find_xml_generator()

# Configure the xml generator
xml_generator_config = parser.xml_generator_configuration_t(
    xml_generator_path=generator_path,
    xml_generator=generator_name)

declarations, global_namespace, std = None, None, None


def keywords(): 
    '''a list of cpp keywords'''
    keywords = [
    "break", "case", "catch", "word", "class", "const", "continue", "delete", "do", "else", "false", "for", "goto", "if", 
    "namespace", "not", "or", "private", "protected", "public", "return", "signed", "sizeof", "static", "struct", "switch", "true", "try", "unsigned", "void", "while",
    "endl", "cout", "cin", 'queue', 'stack', 'vector', 'array', 'list', 'forward_list', 'set' ,'map', 'deque', 'priority_queue', 'multiset', 'multimap',
    ]
    return keywords

def identifiers(): 
    '''a list of cpp identifiers'''
    identifiers = [
    "auto", "bool", "char", "double", "enum", "float", "int", "long", "short", "string" ]
    #print(len(identifiers)) = 10
    return identifiers

def operators():
    '''a dictionary of cpp operators'''
    operators = {
    "+": "PLUS", "-": "MINUS", "*": "MUL", "/": "DIV", "%": "MOD", "+=": "PLUSEQ", "-=": "MINUSEQ", "*=": "MULEQ", "/=": "DIVEQ", "++": "INC", "--": "DEC", "|": "OR", "&&": "AND", "&": "REF",
    }
    return operators

def delimiters():
    '''a dictionary of cpp delimiters'''
    delimiters = {
    "\t": "TAB", "\n": "NEWLINE","(": "LPAR", ")": "RPAR", "[": "LBRACE", "]": "RBRACE", "{": "LCBRACE", "}": "RCBRACE", "=": "ASSIGN",":": "COLON", ",": "COMMA", ";": "SEMICOL", "<<": "OUT", ">>": "IN",
    }
    #print(len(delimiters)) = 14
    return delimiters

def add_func(token, func_tokens):
    '''takes the dictionary of func_tokens and function name as argument and returns a list of tokens corresponding to the function
    Invoked whnever a function is called and token is name of the function'''
    new_list = []
    for t in func_tokens[token]:
        if t != token:
            if t in func_tokens:
                new_list = new_list + add_func(t, func_tokens)
            else:
                new_list.append(t)

    return new_list



def basicCheck(token, file_tokens, func_tokens, class_list):

    '''
    token is single token to be processed now
    file_tokens is the list to which the token might be added
    func_tokens is a dictionary with function names mapped to corresponding declarations and is used to add tokens corresponding to a function call
    class_list is a list of classes to identify object instances

    This fucntion examines the given token and determines whether it needs to be appended. Whitespaces, comments, delimiters/punctuation/literals are ignored.
    Tokens which are keywords/ identifiers/ operators are added as strings to the file_tokens list
    Variables names are assigned token with 'v' keyword
    Numbers of any type(int/ float) are assigned token with 'no' keyword
    Headers of any type are assigned token with 'he' keyword
    Objects/ instances of a class are assigned token woth 'obj' keyword
    For function calls, add_func is passes the function name and tokens corresponding to the function are added to file_tokens
    '''
    global scope_depth, is_function
    varPtrn = re.compile(r"[a-zA-Z_][a-zA-Z0-9_]")  # variables
    headerPtrn = re.compile(r"\w[a-zA-Z]+[.]h")  # header files
    digitPtrn = re.compile(r'\d')   #digits
    floatPtrn = re.compile(r'\d+[.]\d+')    #decimals

    if token in mysrc.delimiters():
        description = mysrc.delimiters()[token]
        if description == 'LCBRACE':
            scope_depth += 1
            
        elif description == 'RCBRACE':
            scope_depth -= 1
            if is_function != -1 and scope_depth == 0:
                is_function = -1
            
        else:
            pass
    elif token in mysrc.keywords():
        
        if is_function != -1:
            pass
        else:
            file_tokens.append(token)

    elif token in mysrc.identifiers():
        
        if is_function != -1:
            pass
        else:
            file_tokens.append(token)

    elif token in mysrc.operators().keys():
        
        if is_function != -1:
            pass
        else:
            file_tokens.append(token)
    
    elif re.search(headerPtrn, token):
        
        file_tokens.append('head')

    elif token in func_tokens.keys() and token != 'main':
        file_tokens.extend(add_func(token, func_tokens))
        

    elif token in class_list:
        file_tokens.append('obj' )

    elif token == 'head':
        file_tokens.append('he')

    elif token == 'num':
        file_tokens.append('no')

    elif token == 'obj':
        file_tokens.append('obj')

    elif re.match(varPtrn, token) or "'" in token or '"' in token:
        if is_function != -1:
            pass
        else:
            file_tokens.append('v')
    elif re.match(digitPtrn, token):

        if is_function != -1:
            pass
        else:
            file_tokens.append('no')

    return True

def funcCheck(token, func_tokens, func_list, class_list):

    '''
    token is single token to be processed now
    file_tokens is the list to which the token might be added
    func_tokens is a dictionary with function names mapped to corresponding declarations and is used to add tokens corresponding to a function call
    class_list is a list of classes to identify object instances

    Similar to basicChecker but works on processing tokens of a particular function. The name of current function being processed is stored in is_function var.
    It appends the generated token value to the dictionary func_tokens mapped to corresponding name
    '''

    global scope_depth, is_function
    varPtrn = re.compile(r"[a-zA-Z_][a-zA-Z0-9_]")  # variables
    headerPtrn = re.compile(r"\w[a-zA-Z]+[.]h")  # header files
    digitPtrn = re.compile(r'\d') #digits
    floatPtrn = re.compile(r'\d+[.]\d+') #decimals

    if token in mysrc.delimiters():
        description = mysrc.delimiters()[token]
        if description == 'LCBRACE':
            scope_depth += 1

        elif description == 'RCBRACE':
            scope_depth -= 1
            if is_function != -1 and scope_depth == 0:
                is_function = -1
            
        else:
            pass
    elif token in mysrc.keywords():
        
        if is_function != -1:
            func_tokens[is_function].append(token)
        else:
            pass
    elif token in mysrc.identifiers():
        
        if is_function != -1:
            func_tokens[is_function].append(token)
        else:
            pass

    elif token in mysrc.operators().keys():
        
        if is_function != -1:
            func_tokens[is_function].append(token)
        else:
            pass
    elif token in func_list and token != is_function and is_function!= -1:
        func_tokens[is_function].append(token)

    elif token in class_list and is_function!= -1:
        func_tokens[is_function].append('obj' )

    elif re.search(headerPtrn, token):

        pass
    elif re.match(varPtrn, token) or "'" in token or '"' in token:

        if is_function != -1:
            func_tokens[is_function].append('v')
        else:
            pass
        

    elif re.match(digitPtrn, token):

        if is_function != -1:
            func_tokens[is_function].append('no')
            pass
        else:
            pass

    return True

def delimiterCorrection(line):

    '''Takes a line as input and splits it into tokens using whitespace as separator
    To ensure that delimiters are taken into account poistion of delimeters are identified and replaced with padding of spaces around them for effective splitting
    Returned is list of tokens generated from the line excluding whitespaces
    The tokens generated now are just words in the source code file, they need to be processed further'''

    for delim in mysrc.delimiters().keys():
        if delim in line:
            line = line.replace(delim, ' '+delim+' ') 
    
    tokens = line.split(" ")
    for delimiter in mysrc.delimiters().keys():
        for token in tokens:

            if token == delimiter:
                pass
            elif delimiter in token:

                pos = token.find(delimiter)
                tokens.remove(token)
                token = token.replace(delimiter, " ")
                extra = token[:pos]
                token = token[pos + 1 :]
                tokens.append(delimiter)
                tokens.append(extra)
                tokens.append(token)
            else:
                pass
    for token in tokens:
        if isWhiteSpace(token):
            tokens.remove(token)
        elif ' ' in token:
            tokens.remove(token)
            token = token.split(' ')
            for d in token:
                tokens.append(d)
    return tokens

def isWhiteSpace(word):
    '''
    takes token as input and return true if it comes under whitespace else false
    '''
    ptrn = [ " ", "\t", "\n"]
    for item in ptrn:
        if word == item:
            return True
        else:
            return False

def hasWhiteSpace(token):

    '''
    Checks if a token has a whitespace in it
    If it is present, it is interpreted as aliteral and returned with single quotes added
    Else return false
    '''
    ptrn = ['\t', '\n']
    if isWhiteSpace(token) == False:
        for item in ptrn:
            if item in token:
                result = "'" + item + "'"
                return result
            else:
                pass
    return False


def class_n_func_tokens(class_all_list, func_all_list):

    '''
    Takes as input list of all classes and functions in the file as identified by the pygccxml parser
    Generates new lists - func_list and class_list of user defined functions and classes
    For functions - the line number where function definition begins is identified using regex matching and stored in func_start with the same order as func_list
    For Classses - constructors, operators, variables and memeber functions corresponding to each user defined class are obtained from the parser and added to dictionary class_tokens mapped to class name
    Returns the above generated lists/dictionaries
    '''

    func_start = []
    func_list = []
    func_tokens = []
    class_tokens = {}
    f = open('work', 'r')
    txt = f.read()
    for func in func_all_list:
        pat = r"\s*"+str(func.name)+r"+\s*\("
        res = re.findall(pat, txt)
        if (len(res)>0):
            func_list.append(str(func.name))
            pat2 = r"\s*"+str(func.name)+r"+\s*\(([\w+\s+\w+])*\)\s*\{"
            pos = re.search(pat2, txt)
            if pos != None:
                line_no = len(re.findall('\n', txt[0:int(pos.start())]))
                func_start.append(line_no)

    class_list = []
    for class_ in reversed(class_all_list):
        pat = r"\s*"+str(class_.name)+r"+\s*\{"
        res = re.findall(pat, txt)
        if (len(res)>0):
            class_tokens[class_.name] = []
            for base in class_.bases:
                class_tokens[class_.name].extend(base.related_class.name.split())
                
            for derive in class_.derived:
                class_tokens[class_.name].extend(derive.related_class.name.spilt())

            for p in class_.constructors(allow_empty = True):
                if p is None:
                    break
                for a in p.argument_types:
                    class_tokens[class_.name].extend(str(a).split())
                
            for p in class_.operators(allow_empty = True):
                if p is not None:
                    p = re.sub(r'operator', r'ope', str(p.name))
                    class_tokens[class_.name].append(p)

            for p in class_.variables(allow_empty = True):
                class_tokens[class_.name].append(str(p.decl_type))

            for p in class_.member_functions(allow_empty = True):
                if p is None:
                    break
                for a in p.argument_types:
                    class_tokens[class_.name].append(str(a))

            class_list.append(str(class_.name))

    return func_list, func_start, class_list, class_tokens

def tokenize(path, file_tokens, func_tokens, class_all_list, func_all_list):

    '''
    path is the path of file to be processed
    file_tokens is the list which will store all the tokens generated from the file
    func_tokens is a dictionary with function names mapped to corresponding declarations and is used to add tokens corresponding to a function call
    class_all_list and func_all_list are lists of all classes and functions in the file as identified by the pygccxml parser

    This function first invokes class_n_func_tokens to generate information about functions and tokens corresponding to classes
    It then invokes funcCheck to generate tokens corresponding to a fucntion and store in Func_tokens
    Subsequently, basicCheck is called to tokenize the entire file and store tokens in file_tokens
    '''

    global is_function
    var_list = []
    try:
        file = open(path)
        f = file.read()
        

        lines = f.split("\n")
        file.close()

  
        # check if file exists 
        if os.path.exists("work"): 
            os.remove("work")
        file = open('work', 'a')

        for line in lines:
            line = line.strip()
            if line is not None and line is not '':
                file.write(line)
                file.write('\n')
        file.close()

        func_list, func_start, class_list, class_tokens = class_n_func_tokens(class_all_list, func_all_list)

        count = -1
        for line in lines:
            line = line.strip()
            if line is not None and line is not '':
                count +=1
                if count in func_start:
                    
                    is_function = func_list[func_start.index(count)]
                    
                    func_tokens[is_function] = []
                tokens = delimiterCorrection(line)

                for token in tokens:
                    funcCheck(token, func_tokens, func_list, class_list)

        for token in func_tokens['main']:
            basicCheck(token, file_tokens, func_tokens, class_list)

        for c in class_tokens.keys():
            for token in class_tokens[str(c)]:
                token = str(token)
                
                if (token[0:3] == 'ope'):
                    file_tokens.append(token)
                else:
                    basicCheck(token, file_tokens, func_tokens, class_list)

        return True
    except FileNotFoundError:
        print("\nInvald Path. Retry")
        run()

def run(path):

    '''
    Takes the path of file name as input 
    The parser and xml generator use the file to generate a list of declarations
    The global namespace is obtained and the list of declarations in the global namespace is examined for functions and classes which are identified by the parser
    This list slong woth the file path id passed to the tokenize function to produce tokens for the source code
    '''

    declarations = parser.parse([path], xml_generator_config)
    global_namespace = declarations.get_global_namespace(declarations)
    std = global_namespace.namespace("std")

    func_all_list = []
    class_all_list = []

    for d in global_namespace.declarations:
        if isinstance(d, declarations.class_declaration_t):
            pass

        if isinstance(d, declarations.class_t) and d.parent == global_namespace:
            class_all_list.append(d)

        if isinstance(d, declarations.free_function_t):
            func_all_list.append(d)
    
    file_tokens = []
    func_tokens = {}

    '''
    file_tokens is the list which will store all the tokens generated from the file
    func_tokens is a dictionary with function names mapped to corresponding declarations and is used to add tokens corresponding to a function call
    '''
    tokenize(path, file_tokens, func_tokens, class_all_list, func_all_list)
    return file_tokens, func_tokens


def tokenize_cpp(file):
    '''
    Takes file as argument and invokes run function to obtain the list of tokens generated from the file
    It returns a single string of all tokens joined together
    '''
    t1a, t1f = run(file)
    return ''.join(t1a)
