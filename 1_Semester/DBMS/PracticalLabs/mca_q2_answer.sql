INSERT INTO `mca_lab_test`.`client_master`
(
	`client_no`,
	`name`,
	`address1`,
	`address2`,
	`city`,
	`state`,
	`pincode`,
	`bal_due`
)
VALUES
('0001','Ivan','','','Bombay','Maharastra',400054,15000.00),
('0002','Vandana','','','Madras','Tamilnadu',780001,0.00),
('0003','Pramada','','','Bombay','Maharastra',400057,5000.00),
('0004','Basu','','','Bombay','Maharastra',400056,0.00),
('0005','Ravi','','','Delhi','',100001,2000.00),
('0006','Rukmini','','','Bombay','Maharastra',400050,0.00);


INSERT INTO `mca_lab_test`.`product_master`
(
	`Product_no`,
	`Description`,
	`Profit_percent`,
	`Unit_measure`,
	`Qty_on_hand`,
	`Reorder1v1`,
	`Sell_price`,
	`Cost_price`
)
VALUES
('P00001','1.44floppies',5,'piece',100,20,525.00,500.00),
('P03453','Monitor',6,'piece',10,3,12000.00,11200.00),
('P06734','Mouse',5,'piece',20,5,1050.00,500.00),
('P07856','1.22floppies',5,'piece',100,20,525.00,500.00),
('P07868','Keyboards',2,'piece',10,3,3150.00,3050.00),
('P07885','CD Drive',2.5,'piece',10,3,5250.00,5100.00),
('P07965','540 HDD',4,'piece',10,3,8400.00,8000.00),
('P07975','1.44 Drive',5,'piece',10,3,1050.00,1000.00),
('P08865','1.22 Drive',5,'piece',2,3,1050.00,1000.00);

