# RED PLAG - A SOURCE CODE PLAGIARISM CHECKER
Course project for CS251 Software Systems Lab

## Introduction and Flow

This projects aims at making a software for detecting plagiarism between source code files. Here implemented is a website which will allow users to interact with the tool. Any user who opens the website land on the `Home Page` where they are given two options - `Login` for existing user and `Registration` for new user. After doing either, the user is taken to their `Dashboard` where there are instrcutions on running the checker. They can select a `.tar/.tar.gz/.zip` file to upload for running through the plagiarism checker through the file selector available. There is also option to select boilerplate code to be excluded across all comparisons. Each user has a separate folder created at the backend where all their uploaded files are displayed. They can choose any uploaded file and then click at the button to run the plagiarism checker on it and generate results. The results generated will be a `.csv` file with n x n table of similarity between every 2 pair of files and an image with the graphical representation of the results in the form of a colormap. The user has the option to download the results or run the checker again. There is also option to delete any of the existing uploaded files and a `Logout` button to allow the user to logout and redirect to the `Home Page`. 

#### Here's our [documentation](https://docs.google.com/document/d/1QwUatFlK9yukOeORm25qPnayoxSLF8khmCQbObGzxOM/edit?usp=sharing) of the Core Logic of this project

## Steps to run the project 

`git clone https://github.com/jsum007/RedPlagCryptoFox`

(This won't work now as the repo is private but is added for later)

1. To get the Angular Frontend running - <br>
```
npm install
ng serve --open
````

2. To get the Django Backend running - <br>
```
cd backend
pip3 install -r requirements.txt
python3 manage.py migrate
python3 manage.py makemigrations
python3 manage.py runserver
```

### PDFs of the Documentation generated have been also been added to the repository here -
* [Full Documentation](./Final_Documentation_CryptoFox.pdf)
* [Documentation of Code of Core Logic](./Code-Documentation.pdf)




# Red Plag - Frontend <a name="front"></a>

The frontend of the project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 10.1.6.

### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

### Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

### Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

### Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

### Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

****** 

# Red Plag - Backend

The backend of the project was generated with Django and the APIs were generated using Django REST Framework. 

### Registration of new user

JSON Web Tokens are an industry standard mechanism for generating a token which can be passed in the HTTP headers of each request, authenticating the user. Any new entry assks for an email address which must not be registered already and a password atleast 8 characters long. These after validation create a new user instance and redirect to the dashboard page.


### Login and Authentication of existing user

To implement an authentication system, we used the email address and password model, where after validating this pair, you get a token that works as a credential to give you access to your resources on that service. In our case, the authentication method selected was the JWT. JWT stands for JSON Web Token, and it is an encoded JSON object defined by the RFC 7519 to perform access information exchange between two ends. `djangorestframework-jwt` is an extension to Django REST Framework which provides an authentication layer using JSON Web Tokens and has been used in our project.


## Further scope of improvements

Since, we have used tokenization and winnowing as our technique, a major scope of improvement is in tokenization. We have made separate language specific efficient tokenizers for C++, Python and Java which will handle changes such as addition of redundant code instead of functions, functions reordering and other major reordering of code.
In C++, since we are using parser, the tokenization is giving great results. In Python and Java, we have done Syntax based tokenization, which can further be improved, by adding special tokens corresponding to class/struct member variables and methods and functions(especially for Java). Further, this checker can be improved by adding support for more language specific support(by implementing more language specific tokenizers).
In winnowing, improvements can be made by tuning the value of ‘K’, i.e. the length of kgram, and making it unique for specific languages. Another possibility is by changing the hash function we are using. Currently, we are using SHA-1, we have not experimented with other hash functions, so we may obtain better results by testing other hash functions.

In the frontend and backend, a possible scope of improvement is by adding functionality to upload multiple code files and selecting them to compare at once. 
Further, tokenization and authentication can be made more robust. We can also use OAuth, instead of JWT Authentication.

