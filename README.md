# de-055240-assigment2023-group13

# Introduction
This is SpringBoot Thymleaft project

# Intsall database


https://www.linkedin.com/pulse/introduce-orientdb-tung-vo

https://orientdb.org/docs/3.0.x/fiveminute/java.html

https://orientdb.org/docs/3.0.x/gettingstarted/Tutorial-Using-schema-with-graphs.html

https://orientdb.org/docs/3.1.x/sql/Pagination.html

go to \orientdb-community-version\bin, run server.bat
################################
<pre>
.\server.bat
</pre>

go to  http://172.168.98.82:2480/studio/index.html

login and create schema 

<pre>
de_assigment_group13
</pre>


# install backend

install java-17

install maven

install *IntelliJ Community* or *Visual Code*

check out the code
open cmd

<pre>
mvn clean install
</pre>

run SpringBoot project

go to
http://localhost:8080/


# thymleaf
https://www.baeldung.com/thymeleaf-list

# Spring boot form login
https://www.codeburps.com/post/spring-boot-form-login

# materializecss icon
https://materializecss.com/icons.html


# OrientDB connect example
https://github.com/orientechnologies/orientdb/tree/develop

https://github.com/orientechnologies/orientdb-jdbc/tree/master


# generate-data-api
http://localhost:8080/generate-rank

http://localhost:8080/truncate-academic-rank

http://localhost:8080/user/generate-user

http://localhost:8080/user/truncate


<pre>

CREATE CLASS PAPER EXTENDS V

CREATE CLASS USER EXTENDS V

CREATE CLASS ACADEMIC_RANK EXTENDS V

CREATE CLASS SCHOLAR EXTENDS V

CREATE CLASS  SCHOLAR_USER EXTENDS E

CREATE EDGE SCHOLAR_USER FROM ( SELECT FROM SCHOLAR ) TO ( SELECT FROM USER )

CREATE EDGE SCHOLAR_USER FROM ( SELECT FROM SCHOLAR WHERE USR_ID = 2) TO ( SELECT FROM USER WHERE USR_ID = 2)

CREATE EDGE SCHOLAR_USER FROM ( SELECT FROM SCHOLAR WHERE USR_ID = 3) TO ( SELECT FROM USER WHERE USR_ID = 3)

SELECT * FROM ( SELECT EXPAND( OUT('SCHOLAR_USER') ) FROM SCHOLAR)

select * from user;

select * from user where USR_ID=10;

DELETE vertex FROM user where usr_id > 0;

TRUNCATE CLASS ACADEMIC_RANK UNSAFE;

TRUNCATE CLASS USER UNSAFE;

</pre>
