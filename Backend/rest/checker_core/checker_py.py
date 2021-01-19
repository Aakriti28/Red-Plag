"""Python module pygments is used to tokenize the code files. This module supports 
    most of the popular languages
http://pygments.org/languages/
Hence this program can be used to clean up source code
This program generates tokenized version of python source code files using pygments 
    to identify the token type"""

import pygments.token
import pygments.lexers
import os, re

def add_function_tokens(filename, name, func_text, func_tokens, class_list):
    """
    Tokens of a function are removed and stored separately in a dictionary func_tokens. 
    Whenever a function call is encountered as a token this function is called and tokens corresponding 
        to the function are inserted in the list of file_tokens.
    *filename is used while obtaining the lexer for python from pygments module
    *name is the name of the function for which tokens returned by this function
    * func_text is a dictionary which maps the name of the function to the entire body of the function in 
        textual form extracted from the source code file with unnecessary whitespaces and comments removed.
    In case the function is encountered for the first time and its tokens have yet not been generated, 
        func_text will be used to generate tokens and add to func_tokens dictionary
    * func_tokens dictionary maps name of the function to tokens generated from the function
    * class_list stores the list of class names and will be helpful while generating tokens to 
        identify objects/ instances of classes defined by the user
    """
    text = func_text[name] #extract text of the required function#
    lexer = pygments.lexers.guess_lexer_for_filename(filename, text) #obtain lexer from pygmnets #
    tokens = lexer.get_tokens(text) #generate tokens from the code#
    tokens = list(tokens) 
    lenT = len(tokens) #length of tokens#
    file_tokens = [] #list to store the tokens corresponding to fucntion if yet to be generated#

    for i in range(lenT):
        if tokens[i][0] == pygments.token.Name.Class and str(tokens[i][1]) not in class_list:  #identify instances of classes and update class_list#
            class_list.append(str(tokens[i][1])) 

    for i in range(lenT):
        
        if (tokens[i][0] == pygments.token.Name or tokens[i][0] in pygments.token.Name) and not i == lenT - 1 and not tokens[i + 1][1] == '(': #if the token is a name type#
            
            if tokens[i][0] == pygments.token.Name.Class or str(tokens[i][1]) in class_list:  #identify objects/ instances of user defined classes and assign 'class' token to it#
                file_tokens.append('class')

            elif tokens[i][0] in pygments.token.Name.Builtin or tokens[i][0] in pygments.token.Name.Function \
                    or tokens[i][0] in pygments.token.Name.Attribute or tokens[i][0] in pygments.token.Name.Decorator \
                    or tokens[i][0] in pygments.token.Name.Namespace:  #identify builtin methods of python, decorators and namespaces and add the token as string form#
                file_tokens.append(str(tokens[i][1]))

            else:
                file_tokens.append('v') #if the token does not satisfy any of the condition above, it is a variable name. So 'v' token is assigned to it#
            

        elif tokens[i][0] == pygments.token.Name.Class: #identify objects/ instances of builtin/other classes and assign 'class' token to it#
            class_list.append(str(tokens[i][1]))
            file_tokens.append('class')
            
        elif tokens[i][0] in pygments.token.Literal.String: #ignore values of string type#
            pass

        elif str(tokens[i][1]) in func_tokens.keys():       #if function call is encountered, check if tokens have already been generated corresponding to it and add them to file_tokens#
            file_tokens.extend(func_tokens[str(tokens[i][1])]) 

        elif str(tokens[i][1]) in func_text.keys():     #if function call is encountered and tokens corresponding to it have yet not been generated #

            if str(tokens[i][1]) != name:    #check if recursive call#
                func_tokens[str(tokens[i][1])] = add_function_tokens(filename, str(tokens[i][1]), func_text, func_tokens, class_list)
                file_tokens.extend(func_tokens[str(tokens[i][1])]) #generate tokens from text and add to the func_tokens dictionary#

            else:
                pass
        elif tokens[i][0] == pygments.token.Text or tokens[i][0] in pygments.token.Comment or tokens[i][0] in pygments.token.Punctuation:   
            pass   #ignore tabs, comments, punctuautions (,.[](){} etc) and other unnecessary text#

        else: 
            file_tokens.append(str(tokens[i][1]))  #remaining tokens are identifiers and keywords, so are appended to file_tokens#
    return file_tokens



def tokenize_py(filename):

    """
    This function takes filename as input and returns the tokenized version of source code as string.
    It first removes all extra whitespaces.Then all functions are identified and their text is code
         is removed and stored in a separate dictionary func_text
    Tokens corresponding to the functions are generated and their list is mapped to the function 
        name in another dictionary func_tokens
    Subsequently the remaining files is tokenized and list of tokens stored in file_tokens
    Whenever a fucntion call is encountered, tokens corresponding to the fucntion are appended 
    """

    file = open(filename, "r")
    if os.path.exists("work"): 
        os.remove("work")

    work = open('work', 'a') #an auxillary file created to store the cource code tet with extra whitespace removed#
    func_text = {}
    pat = r'^def +(\w)*\(.*?\):' #matches with python function declaration#
    line_no = 0
    func_pos = []
    in_func = -1

    for l in file:
        if l == '' or l.isspace():  #ignore whitespace#
            pass
        elif l[0] == '\t' and in_func != -1:
            func_text[name] += l   #if inside function, add code to the func_text dictionary corresponding to the function name#

        else:
            match = re.search(pat, l)  #check if line has function declaration#
            in_func = -1
        
            if match is not None:       
                name = match.string.split()[1]
                name = name.split('(')[0]
                func_pos.append(line_no)
                in_func = name
                func_text[name] = ''  #create a new value in dictionary func_text if new fucntion found#
        
            else:
                work.write(l.rstrip()) #remove trailing space and write to auxillary file#
                work.write('\n')
        line_no += 1
    
    file.close()
    work.close()
    file = open('work', 'r')
    text = file.read() #read all text from auxillary file#

    lexer = pygments.lexers.guess_lexer_for_filename(filename, text) #obtain lexer from pygmnets #
    tokens = lexer.get_tokens(text)
    tokens = list(tokens)
    lenT = len(tokens)
    file_tokens = []    #list to store the tokens corresponding to the entire source code file#
    func_tokens = {}    
    class_list = []     #list to store all the user defined classes#

    for i in range(lenT):
        if tokens[i][0] == pygments.token.Name.Class:    #identify instances of classes and update class_list#
            class_list.append(str(tokens[i][1]))

    for i in range(lenT):
        if tokens[i][0] == pygments.token.Name.Class:
            class_list.append(str(tokens[i][1]))
            file_tokens.append('class')

        elif (tokens[i][0] == pygments.token.Name or tokens[i][0] in pygments.token.Name) and not i == lenT - 1 and not tokens[i + 1][1] == '(':  #the token is of type name#
            
            if tokens[i][0] == pygments.token.Name.Class or str(tokens[i][1]) in class_list:    #identify objects/ instances of user defined classes and assign 'class' token to it#
                file_tokens.append('class')

            elif tokens[i][0] in pygments.token.Name.Builtin or tokens[i][0] in pygments.token.Name.Function \
                    or tokens[i][0] in pygments.token.Name.Attribute or tokens[i][0] in pygments.token.Name.Decorator \
                    or tokens[i][0] in pygments.token.Name.Namespace:      #identify builtin methods of python, decorators and namespaces and add the token as string form to the list of file tokens#
                file_tokens.append(str(tokens[i][1]))

            else:
                file_tokens.append('v')     #if the token does not satisfy any of the condition above, it is a variable name. So 'v' token is assigned to it#

        elif tokens[i][0] in pygments.token.Literal.String:     #ignore values of string type#
            pass

        elif tokens[i][0] == pygments.token.Name.Class: #identify objects/ instances of builtin/other classes and assign 'class' token to it#
            class_list.append(str(tokens[i][1]))
            file_tokens.append('class')

        elif str(tokens[i][1]) in func_tokens.keys():    #if function call is encountered, check if tokens have already been generated corresponding to it and add them to file_tokens#
            file_tokens.extend(func_tokens[str(tokens[i][1])])

        elif str(tokens[i][1]) in func_text.keys():     #if function call is encountered and tokens corresponding to it have yet not been generated #

            func_tokens[str(tokens[i][1])] = add_function_tokens(filename, str(tokens[i][1]), func_text, func_tokens, class_list)
            file_tokens.extend(func_tokens[str(tokens[i][1])])  #generate tokens from text and add to the func_tokens dictionary#

        elif tokens[i][0] == pygments.token.Text or tokens[i][0] in pygments.token.Comment or tokens[i][0] in pygments.token.Punctuation:
            pass   #ignore tabs, comments, punctuautions (,.[](){} etc) and other unnecessary text#

        else:
            file_tokens.append(str(tokens[i][1]))      #remaining tokens are identifiers, operators and keywords, so are appended to file_tokens#

    if os.path.exists("work"):
        os.remove("work")   #remove auxillary file#

    print(str(' '.join(file_tokens)))
    print('\n')
    return ' '.join(file_tokens)    #return all tokens concatenated as a single string#