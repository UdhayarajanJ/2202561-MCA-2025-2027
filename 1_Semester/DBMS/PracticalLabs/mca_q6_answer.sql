use mca_lab_test;

INSERT INTO salesman_master
(
	Salesman_no, 
    Sal_name, 
    Address, 
    City, 
    Pincode, 
    State, 
    Sal_amt, 
    Tgt_to_get, 
    Ytd_sales, 
    Remark
)
VALUES
('500001', 'Kiran',  'A/14 Worli',  'Bombay', 400002, 'Mah', 3000, 100,  50, 'Good'),
('500002', 'Manish', '65 Nariman', 'Bombay', 400001, 'Mah', 3000, 200, 100, 'Good'),
('500003', 'Ravi',   'P-7 Bandra', 'Bombay', 400032, 'Mah', 3000, 200, 100, 'Good'),
('500004', 'Ashish', 'A/5 Juhu',   'Bombay', 400044, 'Mah', 3500, 200, 150, 'Good');


INSERT INTO Sales_Order
(
	S_order_no, 
    S_order_date, 
    Client_no, 
    Dely_type, 
    Billed_yn, 
    Salesman_no, 
    Dely_date, 
    Order_status
)
VALUES
('019001', '1996-01-12', '0001', 'F', 'N', '50001',  '1996-01-20', 'Ip'),
('019002', '1996-01-25', '0002', 'P', 'N', '50002',  '1996-01-27', 'C'),
('016865', '1996-02-18', '0003', 'F', 'Y', '500003', '1996-02-20', 'F'),
('019003', '1996-04-03', '0001', 'F', 'Y', '500001', '1996-04-07', 'F'),
('046866', '1996-05-20', '0004', 'P', 'N', '500002', '1996-05-22', 'C'),
('010008', '1996-05-24', '0005', 'F', 'N', '500004', '1996-05-26', 'Ip');


INSERT INTO Sales_Order_Details
(S_order_no, Product_no, Qty_order, Qty_disp, Product_rate)
VALUES
('019001', 'P00001', 4, 4, 525),
('019001', 'P07965', 2, 1, 8400),
('019001', 'P07885', 2, 1, 5250),
('019002', 'P00001', 10, 0, 525),
('046865', 'P07868', 3, 3, 3150),
('046865', 'P07885', 10, 10, 5250),
('019003', 'P00001', 4, 4, 1050),
('019003', 'P03453', 2, 2, 1050),
('046866', 'P06734', 1, 1, 12000),
('046866', 'P07965', 1, 0, 8400),
('010008', 'P07975', 1, 0, 1050),
('010008', 'P00001', 10, 5, 525);

