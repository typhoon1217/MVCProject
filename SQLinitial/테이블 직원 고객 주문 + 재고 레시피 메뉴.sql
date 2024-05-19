DROP TABLE Order_Details;
DROP TABLE aOrder;
DROP TABLE Customers;
DROP TABLE rtable;
DROP TABLE Employee;

DROP SEQUENCE aOrder_seq;
DROP SEQUENCE Order_Details_seq;

DROP TABLE requestOrder;
DROP TABLE Inventory;
DROP TABLE Recipe;
DROP TABLE iRecipe;
DROP TABLE Menu;
DROP TABLE Ingredient;

DROP SEQUENCE requestOrder_seq;
DROP SEQUENCE Inventory_seq;
DROP SEQUENCE Recipe_seq;
DROP SEQUENCE iRecipe_seq;

-- �� ���� ���̺�
CREATE TABLE Customers (
    customer_id              VARCHAR2(20)    NOT NULL, -- PK
    customer_name            NVARCHAR2(5)    NOT NULL, -- �ѱ� 5����
    customer_email           VARCHAR2(100),  -- UK
    customer_phone_number    NUMBER,         -- ����
    customer_address         NVARCHAR2(50),  -- �ѱ� 50��
    customer_grade           VARCHAR2(20)    NOT NULL
);

-- ���� ���� ���̺�
CREATE TABLE Employee (
    emp_id            VARCHAR2(20)    NOT NULL, -- PK
    emp_password      VARCHAR2(20)    NOT NULL,
    emp_name          NVARCHAR2(5)    NOT NULL, -- �ѱ� 5����
    emp_manager       NVARCHAR2(5),   -- �ѱ� 5����, null=��� ����
    emp_department    VARCHAR2(10)    NOT NULL,
    emp_phone_number  NUMBER,         -- ����
    emp_email         VARCHAR2(100),  -- UK
    emp_salary        NUMBER          NOT NULL
);

-- ���̺� ���� ���̺�
CREATE TABLE rtable (
    table_no                NUMBER NOT NULL  -- ���� ��Ʈ�� �¼� ���̺� �׸� ����
);

-- ���̺� �� �ֹ��� ���� ���̺�
CREATE TABLE aOrder (
    aorder_id               VARCHAR2(10)    NOT NULL, -- PK
    table_no                NUMBER          NOT NULL, -- FK rtable
    customer_id             VARCHAR2(20),   -- FK Customers, null ����
    aorder_datetime         TIMESTAMP       NOT NULL,
    atotal_amount           NUMBER          NOT NULL,
    aorder_status           VARCHAR2(20)    NOT NULL  -- list: cooking, served, paid
);

-- �ֹ� �� ���� ���̺�
CREATE TABLE Order_Details (
    order_detail_id         VARCHAR2(10)    NOT NULL, -- PK
    aorder_id               VARCHAR2(10)    NOT NULL, -- FK aOrder 
    food_name               NVARCHAR2(20)   NOT NULL, -- FK menu
    order_quantity          NUMBER          NOT NULL
);

-- �޴� ���̺�
CREATE TABLE Menu (
    food_name           NVARCHAR2(20) NOT NULL, -- PK
    food_price          NUMBER NOT NULL,
    food_description    NVARCHAR2(50),
    food_category       NVARCHAR2(15)
);

-- ��� ���̺�
CREATE TABLE Ingredient (           
    ingredient_name     NVARCHAR2(20) NOT NULL, -- PK
    ingredient_unit     NVARCHAR2(10) NOT NULL
);

-- ������ ���� ���̺�
CREATE TABLE Recipe (  
    recipe_content_id   VARCHAR2(10) NOT NULL, -- PK
    food_name           NVARCHAR2(20) NOT NULL, -- FK �޴� ���̺���� ������ ���� �ܷ� Ű
    ingredient_name     NVARCHAR2(20) NOT NULL, -- FK ���� DB  
    recipe_quantity     NUMBER NOT NULL
);

-- ���� ��� ����� ������ ���� ���̺�
CREATE TABLE iRecipe (
    i_recipe_content_id VARCHAR2(10) NOT NULL, -- PK
    r_ingredient_name   NVARCHAR2(20) NOT NULL, -- FK ���� DB
    i_ingredient_name   NVARCHAR2(20) NOT NULL, -- FK ���� DB  
    r_recipe_quantity   NUMBER NOT NULL, -- �������
    i_recipe_quantity   NUMBER NOT NULL
);

-- ��� ���� ���̺�
CREATE TABLE Inventory (
    inventory_id        VARCHAR2(10) NOT NULL, -- PK
    ingredient_name     NVARCHAR2(20) NOT NULL, -- FK ���� DB, ���� ���� ���� ������Ѻ�
    inventory_quantity  NUMBER NOT NULL,
    expiry_date         DATE -- ���� ǰ�� ����
);

-- ���� ��û ���̺�
CREATE TABLE requestOrder (
    request_id          NVARCHAR2(5) NOT NULL, -- PK
    ingredient_name     NVARCHAR2(50) NOT NULL,
    request_quantity    NUMBER
);

--pk
ALTER TABLE Customers ADD CONSTRAINT Customers_pk PRIMARY KEY (customer_id);
ALTER TABLE Employee ADD CONSTRAINT Employee_pk PRIMARY KEY (emp_id);
ALTER TABLE aOrder ADD CONSTRAINT aOrder_pk PRIMARY KEY (aorder_id);
ALTER TABLE Order_Details ADD CONSTRAINT Order_Details_pk PRIMARY KEY (order_detail_id);
ALTER TABLE rtable ADD CONSTRAINT rtable_pk PRIMARY KEY (table_no);
ALTER TABLE Menu ADD CONSTRAINT Menu_pk PRIMARY KEY (food_name);
ALTER TABLE Ingredient ADD CONSTRAINT Ingredient_pk PRIMARY KEY (ingredient_name);
ALTER TABLE Recipe ADD CONSTRAINT Recipe_pk PRIMARY KEY (recipe_content_id);
ALTER TABLE iRecipe ADD CONSTRAINT iRecipe_pk PRIMARY KEY (i_recipe_content_id);
ALTER TABLE Inventory ADD CONSTRAINT Inventory_pk PRIMARY KEY (inventory_id);
ALTER TABLE requestOrder ADD CONSTRAINT requestOrder_pk PRIMARY KEY (request_id);

--uk
ALTER TABLE Customers ADD CONSTRAINT customer_email_uk UNIQUE (customer_email);
ALTER TABLE Customers ADD CONSTRAINT customer_phone_number_uk UNIQUE (customer_phone_number);
ALTER TABLE Employee ADD CONSTRAINT emp_phone_number_uk UNIQUE (emp_phone_number);
ALTER TABLE requestOrder ADD CONSTRAINT requestOrder_ingredient_name_uk UNIQUE (ingredient_name);


--fk
ALTER TABLE aOrder ADD CONSTRAINT fk_aOrder_table_no FOREIGN KEY (table_no) REFERENCES rtable(table_no);
ALTER TABLE aOrder ADD CONSTRAINT fk_aOrder_customer_id FOREIGN KEY (customer_id) REFERENCES Customers(customer_id);
ALTER TABLE Order_Details ADD CONSTRAINT fk_Order_Details_aorder_id FOREIGN KEY (aorder_id) REFERENCES aOrder(aorder_id);
ALTER TABLE Order_Details ADD CONSTRAINT fk_Order_Details_food_name FOREIGN KEY (food_name) REFERENCES Menu(food_name);
ALTER TABLE Recipe ADD CONSTRAINT fk_recipe_menu FOREIGN KEY (food_name) REFERENCES Menu(food_name);
ALTER TABLE Recipe ADD CONSTRAINT fk_recipe_ingredient FOREIGN KEY (ingredient_name) REFERENCES Ingredient(ingredient_name);
ALTER TABLE iRecipe ADD CONSTRAINT fk_iRecipe_r_ingredient FOREIGN KEY (r_ingredient_name) REFERENCES Ingredient(ingredient_name);
ALTER TABLE iRecipe ADD CONSTRAINT fk_iRecipe_i_ingredient FOREIGN KEY (i_ingredient_name) REFERENCES Ingredient(ingredient_name);
ALTER TABLE Inventory ADD CONSTRAINT fk_inventory_ingredient FOREIGN KEY (ingredient_name) REFERENCES Ingredient(ingredient_name);
ALTER TABLE requestOrder ADD CONSTRAINT fk_requestorder_ingredient FOREIGN KEY (ingredient_name) REFERENCES Ingredient(ingredient_name);


--������ ����:
CREATE SEQUENCE aOrder_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE Order_Details_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE requestOrder_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE Inventory_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE Recipe_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE iRecipe_seq START WITH 1 INCREMENT BY 1;

-- aOrder ���̺��� Ʈ����
CREATE OR REPLACE TRIGGER generate_aorder_id
BEFORE INSERT ON aOrder
FOR EACH ROW
BEGIN
    :NEW.aorder_id := 'AOD' || TO_CHAR(aOrder_seq.NEXTVAL, 'FM0000000'); -- ��: AD00000001, AD00000002, ...
END;
/

CREATE OR REPLACE TRIGGER generate_order_detail_id
BEFORE INSERT ON Order_Details
FOR EACH ROW
BEGIN
    -- �ֹ� �� ID ����
    :NEW.order_detail_id := 'ODI' || LPAD(Order_Details_seq.NEXTVAL, 'FM0000000');
END;
/

-- iRecipe ���̺��� ������ ���� Ʈ����
CREATE OR REPLACE TRIGGER generate_i_recipe_content_id
BEFORE INSERT ON iRecipe
FOR EACH ROW
BEGIN
    :NEW.i_recipe_content_id := 'IRC'|| TO_CHAR(iRecipe_seq.NEXTVAL, 'FM0000000'); -- ��: IRECOnio000001, IRECOnio000002, ...
END;
/

-- requestOrder ���̺��� ������ ���� Ʈ����
CREATE OR REPLACE TRIGGER generate_request_id
BEFORE INSERT ON requestOrder
FOR EACH ROW
BEGIN
    :NEW.request_id := 'REQ' || TO_CHAR(requestOrder_seq.NEXTVAL, 'FM0000000'); -- ��: REQ1, REQ2, ...
END;
/

-- Inventory ���̺��� ������ ���� Ʈ����
CREATE OR REPLACE TRIGGER generate_inventory_id
BEFORE INSERT ON Inventory
FOR EACH ROW
BEGIN
    :NEW.inventory_id := 'INV' || TO_CHAR(Inventory_seq.NEXTVAL, 'FM0000000'); -- ��: INV1, INV2, ...
END;
/

-- Recipe ���̺��� ������ ���� Ʈ����
CREATE OR REPLACE TRIGGER generate_recipe_content_id
BEFORE INSERT ON Recipe
FOR EACH ROW
BEGIN
    :NEW.recipe_content_id := 'REC' || TO_CHAR(Recipe_seq.NEXTVAL, 'FM0000000'); -- ��: REC1, REC2, ...
END;
/
