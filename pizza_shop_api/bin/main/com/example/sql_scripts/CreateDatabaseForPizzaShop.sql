create database pizza_shop; -- Creates the new database
create user 'pizzaAdmin'@'%' identified by 'PizzaIsDream'; -- Creates the user
grant all on pizza_shop.* to 'pizzaAdmin'@'%'; -- Gives all privileges to the new user on the newly created database




--Security 
revoke all on pizza_shop.* from 'pizzaAdmin'@'%';
-- Now the Spring application cannot do anything in the database.


-- The application must have some privileges, so use the following command to grant the minimum privileges the application needs:
grant select, insert, delete, update on pizza_shop.* to 'pizzaAdmin'@'%';

