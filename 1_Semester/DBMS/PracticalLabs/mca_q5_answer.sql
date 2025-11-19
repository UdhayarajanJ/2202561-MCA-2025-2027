use mca_lab_test;

CREATE TABLE salesman_master (
  Salesman_no VARCHAR(6) NOT NULL,
  Sal_name VARCHAR(20) NOT NULL,
  Address TEXT NULL,
  City VARCHAR(20) NULL,
  State VARCHAR(20) NULL,
  PinCode INT NULL,
  Sal_amt DECIMAL(8,2) NOT NULL,
  Tgt_to_get DECIMAL(6,2) NOT NULL,
  Ytd_sales DECIMAL(6,2) NOT NULL,
  Remark VARCHAR(30) NULL,
  
  PRIMARY KEY (Salesman_no)
 );
 
 
 CREATE TABLE Sales_order (
    S_order_no     VARCHAR(6) NOT NULL,
    S_order_date   DATE       NOT NULL,
    Client_no      VARCHAR(25) NOT NULL,
    Dely_add       VARCHAR(50),
    Salesman_no    VARCHAR(6),
    Dely_type      CHAR(1) DEFAULT 'f',
    Billed_yn      CHAR(1),
    Dely_date      DATE,
    Order_status   VARCHAR(10),

    -- Primary key
    PRIMARY KEY (S_order_no),

    -- Must start with 0
    CHECK (S_order_no REGEXP '^0'),

    -- Delivery type must be f or p
    CHECK (Dely_type IN ('F', 'P')),

    -- Order status allowed values
    CHECK (Order_status IN ('Ip', 'F', 'C')),

    -- Delivery date cannot be less than order date
    CHECK (Dely_date >= S_order_date),

    -- Foreign key: client_no → client_master
    FOREIGN KEY (Client_no)
        REFERENCES client_master (client_no),

    -- Foreign key: salesman_no → salesman_master
    FOREIGN KEY (Salesman_no)
        REFERENCES salesman_master (salesman_no)
);


CREATE TABLE Sales_Order_Details (
    S_order_no    VARCHAR(6) NOT NULL,
    Product_no    VARCHAR(6) NOT NULL,
    Qty_order     INT,
    Qty_disp      INT,
    Product_rate  DECIMAL(10,2),

    -- Composite Primary Key
    PRIMARY KEY (S_order_no, Product_no),

    -- Foreign Key referencing Sales_Order (S_order_no)
    FOREIGN KEY (S_order_no)
        REFERENCES Sales_Order (S_order_no),

    -- Foreign Key referencing Product_Master (Product_no)
    FOREIGN KEY (Product_no)
        REFERENCES Product_Master (Product_no)
);
