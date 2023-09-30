# de-055240-assigment2023-group13

# Introduction
This is SpringBoot Spring MVC project

# Intsall database


https://www.linkedin.com/pulse/introduce-orientdb-tung-vo

https://orientdb.org/docs/3.0.x/fiveminute/java.html

https://orientdb.org/docs/3.0.x/gettingstarted/Tutorial-Using-schema-with-graphs.html

go to \orientdb-community-version\bin, run server.bat
################################
<pre>
.\server.bat
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

# thymleaf
https://www.baeldung.com/thymeleaf-list


# OrientDB connect example
https://github.com/orientechnologies/orientdb/tree/develop

https://github.com/orientechnologies/orientdb-jdbc/tree/master




<pre>
CREATE CLASS  SCHOLAR_USER EXTENDS E

CREATE EDGE SCHOLAR_USER FROM ( SELECT FROM SCHOLAR ) TO ( SELECT FROM USER )

CREATE EDGE SCHOLAR_USER FROM ( SELECT FROM SCHOLAR WHERE USR_ID = 2) TO ( SELECT FROM USER WHERE USR_ID = 2)

CREATE EDGE SCHOLAR_USER FROM ( SELECT FROM SCHOLAR WHERE USR_ID = 3) TO ( SELECT FROM USER WHERE USR_ID = 3)

SELECT * FROM ( SELECT EXPAND( OUT('SCHOLAR_USER') ) FROM SCHOLAR)


</pre>