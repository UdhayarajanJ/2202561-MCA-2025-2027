-- i. Create an index on the table client_master, field client_no.

ALTER TABLE `mca_lab_test`.`client_master` 
ADD INDEX `client_no_index` (`client_no` ASC);

-- ii. Create an index on the sales_order, field s_order_no.
ALTER TABLE `mca_lab_test`.`sales_order` 
ADD INDEX `S_OrderNo_index` (`S_order_no` ASC);

-- iii. Create an composite index on the sales_order_details table for the columns s_order_no and product_no.
CREATE INDEX idx_sorder_product 
ON sales_order_details (s_order_no, product_no);

-- iv. Create an composite index ch_index on challan_header table for the columns challan no and s_order_no.
-- challan_header table not mentioned.

-- v. Create an uniQuestion index on the table salesman_master, field salesman_no.
CREATE Unique INDEX uniQuestion 
ON salesman_master (salesman_no);

-- vi. Drop index ch_index on table challan_header.
-- challan_header table not mentioned.

-- vii. Create view on salesman_master whose sal_amt is less than 3500.
CREATE  OR REPLACE VIEW `salesmater_view_1` AS
	select * from salesman_master where Sal_amt < 3500;
    
-- viii. Create a view client_view on client_master and rename the columns as name, add1, add2, city, pcode, state respectively.
CREATE OR REPLACE VIEW `client_view` AS
	SELECT 
		client_no,
		name,
		address1 as 'add1',
		address2 as 'add2',
		city,
		state,
		pincode as 'pcode',
		bal_due
	FROM 
		client_master;
        
-- ix. Select the client names from client_view who lives in city „Bombay‟.
SELECT * FROM client_view where city = 'Bombay';

-- x. Drop the view client_view.
Drop view client_view;


