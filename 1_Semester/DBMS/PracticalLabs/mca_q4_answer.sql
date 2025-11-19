use mca_lab_test;

-- i.Change the selling price of 1.44 floppy drive to Rs.1150.00
update product_master
set
	Sell_price = 1150.00
where
	Description = '1.44floppies';
    
-- ii. Delete the record with client 0001 from the client master table.
delete from client_master where client_no = '0001';

-- iii. Change the city of client_no ‘0005’ to Bombay.
update client_master
set
	city = 'Bombay'
where
	client_no = '0005';
    
-- iv. Change the bal_due of client_no 0004, to 1000.
update client_master
set
	bal_due = 1000.00
where
	client_no = '0004';
    
-- v. Find the products whose selling price is more than 1500 and also find the new selling price as original selling price *15.
select
	Sell_price * 15
from
	product_master
where
	Sell_price > 1500;
    
-- vi. Find out the clients who stay in a city whose second letter is a.
select
	*
from
	client_master
where
	city like  '_a%';
    
-- vii. Find out the name of all clients having ‘a’ as the second letter in their names.
select
	*
from
	client_master
where
	name like  '_a%';
    
-- viii. List the products in sorted order of their description.
select
	*
from
	product_master
order by Description;

-- ix. Count the total number of orders
select
	count(*)
from
	client_master;
    
-- x. Calculate the average price of all the products.
select
	avg(Sell_price)
from
	product_master;
    
-- xi. Calculate the minimum price of products.
select
	min(Sell_price)
from
	product_master;
    
-- xii. Determine the maximum and minimum prices . Rename the tittle as ‘max_price’ and min_price respectively.
select
	min(Sell_price) as 'min_price',
    max(Sell_price) as 'max_price'
from
	product_master;
    
-- xiii. Count the number of products having price greater than or equal to 1500.
select
	count(*)
from
	product_master
where
	Sell_price >= 1500;

	