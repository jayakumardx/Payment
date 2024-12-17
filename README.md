Please follow the instructions to run the project in your local environment.
A. checkout the branch -main
B. Run the maven command - mvn clean install
C. Run the Project as Spring Boot Application.
D. Use Postman or any other REST client to Request the REST Application.
E. The following are the endpoints corresponding to the question numbers


   
     1.	All transactions for a given category - latest first -
          
          http://localhost:8080/payment/getTransactionsByCategory?category=Groceries
   
     2.	Total outgoing per category -
         
       	  http://localhost:8080/payment/getTotalOutgoingAmountByCategory
   
     3.	Monthly average spend in a given category
         
       	  http://localhost:8080/payment/findMonthlyAverageSpendByCategory?category=Groceries
   
     4.  Highest spend in a given category, for a given year
      
          http://localhost:8080/payment/findHighestSpendByCategoryForYear?category=Groceries&year=2020

     5.	Lowest spend in a given category, for a given year
    
          http://localhost:8080/payment/findLowestSpendByCategoryForYear?category=Groceries&year=2020
   
