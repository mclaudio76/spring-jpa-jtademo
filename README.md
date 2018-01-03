# spring-jpa-jtademo
Using JTA in Spring JPA project (multiple entity managers).

This demo project shows how to use different PersistenceManagers defined within the same project, and choose which one to use dinamically. To achieve this, one needs to run JPA operation (like persist, merge, delete and so on) under a JTA transaction manager.
In this case, I used Atomikos transaction manager (https://www.atomikos.com).

