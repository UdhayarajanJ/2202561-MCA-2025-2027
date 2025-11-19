use mca_lab_test;

-- i) Find out the names of all the clients.
select name from client_master;

-- ii) Retrieve the list of names and cities of all the clients.
select name,city from client_master;

-- iii) List the various products available from the product_master table.
select * from product_master;

-- iv) List all the clients who are located in Bombay.
select * from client_master where city = 'Bombay';

-- v) Display the information for client no 0001 and 0002.
select * from client_master where client_no in ('0001','0002');

-- vi) Find the products with description as ‘1.44 drive’ and ‘1.22 Drive’.
select * from product_master where Description in ('1.44 drive','1.22 Drive');

-- vii) Find all the products whose sell price is greater then 5000.
select * from product_master where Sell_price > 5000;

-- viii) Find the list of all clients who stay in in city ‘Bombay’ or city ‘Delhi’ or ‘Madras’.
select * from client_master where city = 'Bombay' or city = 'Delhi' or city = 'Madras';

-- ix) Find the product whose selling price is greater than 2000 and less than or equal to 5000.
select * from product_master where Sell_price between 2000 and 5000;

-- x) List the name, city and state of clients not in the state of ‘Maharashtra’.
select name,city,state from client_master where state <> 'Maharastra';



