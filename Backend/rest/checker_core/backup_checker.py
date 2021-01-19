"""
Python module pygments is used to tokenize the code files.
This module supports most of the popular languages
http://pygments.org/languages/
Hence this program can be used to clean up codes written in most languages
This program generates tokenized version of source code files 
    using pygments to identify the token type
This is a general checker with basic functionality for tokenization 
It will be invoked in case files are of any type other than C++/Pyhton/JAVA
    or the primary tokenizer for thses langugaes encounters an error
"""

import pygments.token
import pygments.lexers
import os, re

def backup_tokenize(filename):

    """
    This function takes filename as input and generates tokens based on the following rules - 
    1) 'funct' keyword is used for functions - Functions calls will be represented by this token.
    2) 'class' keyword is used for classes - Instances of classes/objects will be 
        replaced by this token.
    3) 'v' is the token used for variable declarations 
    4) Keywords, operators, indentifiers, builtin methods, attributes and decorators are added 
        as it is in string form
    5) Whitespaces, comments, punctuation and literals are ignored
    """
    file = open(filename, "r")

    if os.path.exists("work"): 
        os.remove("work")

    work = open('work', 'a')        #an auxillary file created to store the cource code tet with extra whitespace removed#

    for l in file:
        if l == '' or l.isspace():      #ignore whitespace#
            pass
        else:
            work.write(l.rstrip())
            work.write('\n')
    
    file.close()
    work.close()

    file = open('work', 'r')    #read all text from auxillary file#
    text = file.read()

    lexer = pygments.lexers.guess_lexer_for_filename(filename, text)        #obtain lexer from pygmnets #
    tokens = lexer.get_tokens(text)
    tokens = list(tokens)
    result = []
    lenT = len(tokens)
    file_tokens = []
    class_list = []



    for i in range(lenT):

        if tokens[i][0] == pygments.token.Name.Function:
            file_tokens.append('funct')

        elif tokens[i][0] == pygments.token.Name.Class or str(tokens[i][1]) in class_list:      #identify instances of classes and update class_list#
            class_list.append(str(tokens[i][1]))                                                #identify objects/ instances of user defined classes and assign 'class' token to it#
            file_tokens.append('class')

        elif (tokens[i][0] == pygments.token.Name or tokens[i][0] in pygments.token.Name) and not i == lenT - 1 and not tokens[i + 1][1] == '(':
            
            if str(tokens[i][1]) in class_list:                                                 #identify objects/ instances of user defined classes and assign 'class' token to it#
                file_tokens.append('class')

            elif tokens[i][0] in pygments.token.Name.Namespace:                                 #identify namespaces and add them#
                file_tokens.extend(str(tokens[i][0]).split())

            elif tokens[i][0] in pygments.token.Name.Builtin or tokens[i][0] in pygments.token.Name.Attribute or tokens[i][0] in pygments.token.Name.Decorator :
                file_tokens.append(str(tokens[i][1]))                       #identify builtin methods, decorators and attributes and add the token as string form to the list of file tokens#

            else:
                file_tokens.append('v')     #if the token does not satisfy any of the condition above, it is a variable name. So 'v' token is assigned to it#

        elif tokens[i][0] in pygments.token.Literal.String:
            pass

        elif tokens[i][0] == pygments.token.Text or tokens[i][0] in pygments.token.Comment or tokens[i][0] in pygments.token.Punctuation:
            pass   #whitespaces and comments ignored

        else:
            file_tokens.append(str(tokens[i][1]))           #remaining tokens are identifiers, operators and keywords, so are appended to file_tokens#

    if os.path.exists("work"):
        os.remove("work")

    return ''.join(file_tokens)