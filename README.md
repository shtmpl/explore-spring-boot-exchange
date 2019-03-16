
# Explore

An approach on persisting HTTP exchange details 
for inbound requests (received by the application) as well as 
for outbound requests (initiated by the application).

All the necessary auditing operations are performed in the intercepting layers.
That allows basic application layers (controllers, services, repositories, whatever) to 
focus on business domain logic workflows without requiring them to 
deal with a side (and usually overlooked) functionality.  

`$ dropdb -h localhost -p 5432 -U aspirant explore-spring-exchange`

`$ createdb -h localhost -p 5432 -U aspirant explore-spring-exchange`