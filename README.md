# Restaurants Finder 


## Requirements: 
 - Java JDK 8
 - Apache Maven
 
 
## The program can work in two ways:

**Single Search**


To run a Console Interface with a loop, just run this command

`mvn compile exec:java`


<br>


**Console Interface with a loop**
 
 
To run a Single Search, we should pass 5 arguments, and they can be empty. Each argument should be separated with comas, following this order:
`"restaurantName,cuisine,distance,rating,price"`



+ empty search:
`mvn compile exec:java -Dexec.arguments=",,,,"`

+ restaurant name and price:
`mvn compile exec:java -Dexec.arguments="someName,,,,10"`

+ cuisine, distance, price: 
`mvn compile exec:java -Dexec.arguments=",Chinese,5,,30"`




<br>
<br>




**Some important things:**
- all searches that uses text are case insensitive.
- the program just integer numbers without decimals for price, distance and rating values.

