import pygments.token
import pygments.lexers
import os, re

"""Python module pygments is used to tokenize the code files. This module supports most of the popular languages
http://pygments.org/languages/
Hence this program can be used to clean up source code
This program generates tokenized version of java source code files using pygments to identify the token type"""


def tokenize_jav(filename):

    """
    This function takes filename as input and returns the tokenized version of source code as string.
    It first removes all extra whitespaces. Then it identifies classes and functions and prepares a list of them
    Subsequently the remaining files is tokenized and list of tokens stored in file_tokens
    Whenever a fucntion call/ class instance/ variable name is encountered, specific keywords are used as tokens ('function'/ 'class'/ 'var')
    Comments, punctutation, literals are ignored
    """

    file = open(filename, "r")
    if os.path.exists("work"): 
        os.remove("work")

    work = open('work', 'a')        #an auxillary file created to store the cource code tet with extra whitespace removed#
    in_func = -1

    for l in file:
        if l == '' or l.isspace():      #ignore whitespace#
            pass
        else:
            work.write(l.rstrip())      #remove trailing space and write to auxillary file#
            work.write('\n')
    
    file.close()
    work.close()

    file = open('work', 'r')
    text = file.read()      #read all text from auxillary file#

    lexer = pygments.lexers.guess_lexer_for_filename(filename, text)
    tokens = lexer.get_tokens(text)
    tokens = list(tokens)
    func_list = []      #list to store all the function names#
    lenT = len(tokens)
    file_tokens = []       #list to store the tokens corresponding to the entire source code file#
    class_list = []     #list to store all the user defined classes#


    #key_names stores dictionary of keywords which are not identified by pygments. They are assigned a shorter value as code to keep track of their weightage in tokenized string#
    key_names = {'String' : 'str', 'ArrayList' : 'array', 'List': 'list', 'LinkedList': 'linked', 'HashMap': 'hashma', 'HashSet':' hashse', 'BufferedReader': 'buffer', 
    'ArithmeticException' : 'arithmex', 'ArrayIndexOutOfBoundsException' : 'arrinoex', 'Iterator': 'iterat', 'Pattern': 'pater', 'Matcher': 'match', 'PatternSyntaxException': 'patsynex',
    'ClassNotFoundException': 'clasnoex', 'FileNotFoundException': 'filenoex', 'IOException': 'inpoutex', 'InterruptedException': 'intexex', 'NoSuchFieldException': 'nofileex', 
    'NoSuchMethodException': 'nomethex', 'NullPointerException': 'nulponex', 'NumberFormatException': 'numforex', 'RuntimeException': 'runtimex', 
    'StringIndexOutOfBoundsException': 'strioex', 'LocalDate': 'locdat', 'LocalTime': 'loctim', 'LocalDateTime': 'dattim', 'DateTimeFormatter': 'dtform',
    'Thread': 'thread', 'Main': 'main', 'Runnable': 'runble', 'Consumer': 'consum', 'private': 'scp', 'public': 'scp', 'protected': 'scp',
    'FileReader': 'filred', 'FileInputStream': 'fileinpstr', 'FileWriter': 'filewrit', 'BufferedWriter': 'bufwrit', 'FileOutputStream': 'filoutstr', 
    'abstract': 'abstract', 'implements': 'implement', 'enum': 'enum', 'interface': 'interface', 'final': 'final', 'extends': 'extends', 'forEach': 'forEa'}

    #list of java's inbuilt methods for files#
    file_methods = ['File', 'canRead', 'canWrite', 'createNewFile', 'delete', 'exists', 'getName', 'length', 'list', 'mkdir', 'getAbsolutePath', 'FileWriter', 'write', 'close']
    

    #list of java's inbuilt methods for strings#
    string_methods = ['charAt', 'codePointAt', 'codePointBefore', 'codePointCount', 'compareTo', 'compareToIgnoreCase', 'concat', 'contains', 'contentEquals', 'copyValueOf', 'endsWith', 'equals',
        'equalsIgnoreCase', 'format', 'getBytes', 'getChars', 'hashCode', 'indexOf', 'intern', 'isEmpty', 'lastIndexOf', 'length', 'matches', 'offsetByCodePoints', 'regionMatches', 'replace', 'replaceFirst',
        'substring', 'toCharArray', 'toLowerCase', 'toString', 'toUpperCase', 'trim', 'valueOf']
    

    #list of java's inbuilt methods for mathematical operations#
    math_methods = ['abs', 'acos', 'asin', 'atan', 'atan2', 'cbrt', 'ceil', 'copySign', 'cos', 'cosh', 'exp', 'expm1', 'floor', 'getExponent', 'hypot', 'log', 'log10', 'log1p', 'max', 
    'min', 'nextAfter', 'nextUp', 'pow', 'random', 'round', 'rint', 'signum', 'sin', 'sinh', 'sqrt', 'tan', 'tanh', 'toDegrees', 'toRadians', 'ulp']

    for i in range(lenT):
        if tokens[i][0] == pygments.token.Name.Function:    #identify functions and update func_list#
            func_list.append(str(tokens[i][1]))

        elif tokens[i][0] == pygments.token.Name.Class:     #identify classes and update class_list#
            class_list.append(str(tokens[i][1]))


    for i in range(lenT):
        if tokens[i][0] in pygments.token.Punctuation :  #punctuautions (,.[](){} etc) are ignored#
            pass
        
        elif str(tokens[i][1]) in func_list or tokens[i][0] == pygments.token.Name.Function:    #assign keyword 'function' to function calls and declarations and add to file_tokens#
            file_tokens.append('function')

        elif tokens[i][0] in class_list or tokens[i][0] == pygments.token.Name.Class:       #assign keyword 'class' to class declarations and its object instances#
            file_tokens.append('class')


        elif (tokens[i][0] == pygments.token.Name or tokens[i][0] in pygments.token.Name) and not i == lenT - 1 and not tokens[i + 1][1] == '(':    #the token is of type name#

            t = str(tokens[i][1])

            if tokens[i][0] == pygments.token.Name.Class or str(tokens[i][1]) in class_list:       #identify objects/ instances of user defined classes and assign 'class' token to it#
                file_tokens.append('class')

            elif tokens[i][0] in pygments.token.Name.Namespace:     #identify imports and obtain a short keyword for their type#
                toks = t.split('.')[-1]
                if toks in key_names.keys():
                    file_tokens.append(key_names[toks])

            elif tokens[i][0] in pygments.token.Name.Builtin or tokens[i][0] in pygments.token.Name.Decorator :
                file_tokens.append(t)       #identify builtin methods of java and decorators and add the token as string form to the list of file tokens#

            elif tokens[i][0] in pygments.token.Name.Attribute:
                file_tokens.append('fun')

            else:
                #check if the token is included in our defined vocabulary#

                if t in key_names.keys():
                    file_tokens.append(key_names[t])

                elif t in file_methods:
                    file_tokens.append(t)

                elif t in string_methods:
                    file_tokens.append(t)

                elif t in math_methods:
                    file_tokens.append(t)
                else:
                    file_tokens.append('var')   #if the token does not satisfy any of the condition above, it is a variable name. So 'var' token is assigned#

        elif tokens[i][0] == pygments.token.Name.Class:     #assign keyword 'class' to class declarations and its object instances#
            file_tokens.append('class')

        elif tokens[i][0] == pygments.token.Name or tokens[i][0] in pygments.token.Name:
            file_tokens.append('var')

        elif tokens[i][0] in pygments.token.Literal.String:
            pass      #ignore values of string type#

        elif tokens[i][0] == pygments.token.Text or tokens[i][0] in pygments.token.Comment:
            pass   #ignore tabs, comments and other unnecessary text#

        elif str(tokens[i][1]) == 'import':     #import keyword is ignored#
            pass

        else:
            t = str(tokens[i][1])       #remaining tokens are identifiers, operators and keywords, so are appended to file_tokens#
            if t in key_names.keys():
                file_tokens.append(key_names[t])
            else:
                file_tokens.append(t)

    if os.path.exists("work"):
        os.remove("work")
    return ''.join(file_tokens)

