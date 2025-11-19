-- 1. Find out the product which has been sold to ‘Ivan Sayross’.
select
	product.*
from	
	product_master product
    inner join sales_order_details sod on sod.Product_no = product.Product_no
    inner join sales_order so on so.S_order_no = sod.S_order_no
    inner join client_master cm on cm.client_no = so.Client_no
where
	cm.name = 'Ivan';

-- 2. Find out the product and their quantities that will have do delivered.

select
	product.*,
    sod.Qty_disp
from	
	product_master product
    inner join sales_order_details sod on sod.Product_no = product.Product_no
    inner join sales_order so on so.S_order_no = sod.S_order_no
    inner join client_master cm on cm.client_no = so.Client_no
where
	so.Order_status = 'F';


-- 3. Find the product_no and description of moving products.

select
	product.Product_no,
    product.Description
from	
	product_master product
    inner join sales_order_details sod on sod.Product_no = product.Product_no
    inner join sales_order so on so.S_order_no = sod.S_order_no
    inner join client_master cm on cm.client_no = so.Client_no
where
	so.Order_status = 'Ip';
    
    
-- 4. Find out the names of clients who have purchased ‘CD DRIVE’
select
	cm.name
from	
	product_master product
    inner join sales_order_details sod on sod.Product_no = product.Product_no
    inner join sales_order so on so.S_order_no = sod.S_order_no
    inner join client_master cm on cm.client_no = so.Client_no
where
	product.Description like '%CD DRIVE%';

-- 5. List the product_no and s_order_no of customers haaving qty ordered less than 5 from the
-- order details table for the product ‘1.44 floppie’.
    
select
	sod.S_order_no,
    sod.Product_no
from	
	product_master product
    inner join sales_order_details sod on sod.Product_no = product.Product_no
    inner join sales_order so on so.S_order_no = sod.S_order_no
    inner join client_master cm on cm.client_no = so.Client_no
where
	product.Description like '%1.44floppies%'
    and sod.Qty_order < 5;
    
-- 6. Find the products and their quantities for the orders placed by
-- ‘Vandan Saitwal’ and ‘Ivan Bayross’.

select
	product.*,
    sod.Qty_order
from	
	product_master product
    inner join sales_order_details sod on sod.Product_no = product.Product_no
    inner join sales_order so on so.S_order_no = sod.S_order_no
    inner join client_master cm on cm.client_no = so.Client_no
where
	cm.name in ('Ivan','Vandana');
    
-- 7. Find the products and their quantities for the orders placed by client_no ‘ C00001’ and
-- ‘C00002’

select
	product.*,
    sod.Qty_order
from	
	product_master product
    inner join sales_order_details sod on sod.Product_no = product.Product_no
    inner join sales_order so on so.S_order_no = sod.S_order_no
    inner join client_master cm on cm.client_no = so.Client_no
where
	cm.client_no in ('0001','0002');
    
-- 8. Find the order No,, Client No and salesman No. where a client has been received by more than
-- one salesman.

select
    so.S_order_no,
    so.client_no,
    sm.Salesman_no
from	
	salesman_master sm
    inner join sales_order so on so.Salesman_no = sm.Salesman_no
    inner join client_master cm on cm.client_no = so.Client_no
group by
	so.S_order_no,
    so.client_no,
    sm.Salesman_no
having
	count(sm.Salesman_no) > 1;

-- 9. Display the s_order_date in the format “dd-mm-yy” e.g. “12- feb-96”
SELECT 
    DATE_FORMAT(s_order_date, '%d-%b-%y') AS formatted_order_date
FROM 
    Sales_Order;
    
-- 10. Find the date , 15 days after date.
SELECT 
    s_order_date,
    DATE_ADD(s_order_date, INTERVAL 15 DAY) AS date_after_15_days
FROM 
    Sales_Order;




