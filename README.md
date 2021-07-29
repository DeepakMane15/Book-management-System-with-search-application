# Book-management-System-with-search-application
Hello There.
To get started with the project you should have following softwares
java - jdk 1.8.1 & jre 1.8.1
netbeans (latest version would be comfortable)
mysql 8.0.22

firstly open mysql and create a database named "mpdatabase" by entering "create database mpdatabase;"
and then create 2 tables in it namely users and books (since these names are entered in the java program, so instead of changing names over there lets create the same database and table in mysql)
1st table users, enter "create table users (username varchar(30) primary key, password varchar(30) not null);"  <<copy paste code in mysql
2nd table books, enter "create table books (Name varchar(40) not null, ID int primary key auto_increment, Author varchar(40) not null, Price varchar(10) not null);" <<copy paste code in mysql

after completing all the above process with no errors,
you are good to go!!!
After opening java project, in MyConnection.java class dont forget to replace my mysql username and password by yours.

Happy Learning!! :)//
