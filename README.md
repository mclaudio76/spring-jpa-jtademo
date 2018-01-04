# spring-jpa-jtademo
Using JTA in Spring JPA project (multiple entity managers).

This demo project shows how to use different PersistenceManagers defined within the same project, and how to select programmatically which one to use. To achieve this, one needs to run JPA operation (like persist, merge, delete and so on) under a JTA transaction manager.
For this example, I used Narayana Transaction Manager (http://narayana.io/)

