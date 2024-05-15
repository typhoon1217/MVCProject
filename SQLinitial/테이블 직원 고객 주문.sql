
DROP TABLE Order_Details;
DROP TABLE aOrder;
DROP TABLE Customers;
--DROP TABLE Orders;
DROP TABLE rtable;
DROP TABLE Employee;

-- 고객 관리 테이블    --추후 추가용
CREATE TABLE Customers (
    customer_id              VARCHAR2(10)    NOT NULL, --PK
    customer_name            VARCHAR2(50)    NOT NULL,
    customer_email           VARCHAR2(100)   , --UK
    customer_phone_number    VARCHAR2(20)    , --UK
    customer_address         VARCHAR2(200)   ,
    customer_grade           VARCHAR2(20)    NOT NULL
);

-- 직원 관리 테이블
CREATE TABLE Employee (
    emp_id            VARCHAR2(10)    NOT NULL, --PK
    emp_password      VARCHAR2(10)    NOT NULL,
    emp_name          VARCHAR2(20)    NOT NULL,
    emp_manager       VARCHAR2(20)    ,--null=상사없음
    emp_department    VARCHAR2(10)    NOT NULL,
    emp_contact       VARCHAR2(20)    NOT NULL, --UK
    emp_salary        NUMBER          NOT NULL
--start_date      DATE            NOT NULL,
--work_hours      NUMBER          NOT NULL,
--commission      NUMBER          NOT NULL
);

--테이블관리 테이블
CREATE TABLE rtable(
    table_no                VARCHAR2(3) NOT NULL  --좌석y축2자리 알파벳1개 좌석 x축 2자리 숫자
--    seat_status             VARCHAR2(10) NOT NULL
-- 인원수 추가?
);

--테이블 당 주문들--  직원정보는 그냥 따로 저장안해도 될듯 너무 복잡해짐
--CREATE TABLE Orders (
--    orders_id               VARCHAR2(20)    NOT NULL, --pk
--    emp_id                  VARCHAR2(10)    NOT NULL,   --fk 직원(담당직원)    
--    table_no                VARCHAR2(3)     NOT NULL --fk테이블 
--);
--주문 테이블당 주문이 여러개이기 때문
CREATE TABLE aOrder ( 
    aorder_id               VARCHAR2(20)    NOT NULL, --pk
--    orders_id               VARCHAR2(20)    NOT NULL, --fk orders
    table_no                VARCHAR2(3)     NOT NULL, --fk테이블  추가됨
    customer_id             VARCHAR2(10)    , --fk 고객 null 고객정보 미입력
    aorder_datetime         TIMESTAMP       NOT NULL,
    atotal_amount           NUMBER          NOT NULL,
    aorder_status           VARCHAR2(20)    NOT NULL  --list cooking served payed 

);
--drop table orders;
-- 주문 상세 관리 테이블 조인용 주문하나당 음식 여러개를 보유하기 때문
CREATE TABLE Order_Details (
    order_detail_id VARCHAR2(20)    NOT NULL,    --pk
    aorder_id       VARCHAR2(20)    NOT NULL,   --fk aOrder
    food_name       VARCHAR2(50)    NOT NULL,   --fk menu
    --food_price      NUMBER          NOT NULL,   --조인으로 가져오기
    order_quantity  NUMBER          NOT NULL
);

--pk
ALTER TABLE Customers
ADD CONSTRAINT Customers_pk PRIMARY KEY (customer_id);

ALTER TABLE Employee
ADD CONSTRAINT Employee_pk PRIMARY KEY (emp_id);

--ALTER TABLE Orders
--ADD CONSTRAINT Orders_pk PRIMARY KEY (orders_id);

ALTER TABLE aOrder
ADD CONSTRAINT aOrder_pk PRIMARY KEY (aorder_id);

ALTER TABLE Order_Details
ADD CONSTRAINT Order_Details_pk PRIMARY KEY (order_detail_id);

ALTER TABLE rtable
ADD CONSTRAINT rtable_pk PRIMARY KEY (table_no);


--uk
ALTER TABLE Customers
ADD UNIQUE(customer_email);

ALTER TABLE Customers
ADD UNIQUE(customer_phone_number);

ALTER TABLE Employee
ADD UNIQUE(emp_contact);

--fk
--ALTER TABLE Orders
--ADD CONSTRAINT fk_Orders_emp_id FOREIGN KEY (emp_id) REFERENCES Employee(emp_id);

--ALTER TABLE Orders
--ADD CONSTRAINT fk_Orders_table_no FOREIGN KEY (table_no) REFERENCES rtable(table_no);


ALTER TABLE aOrder --추가됨
ADD CONSTRAINT fk_aOrders_table_no FOREIGN KEY (table_no) REFERENCES rtable(table_no);

--ALTER TABLE aOrder
--ADD CONSTRAINT fk_aOrder_orders_id FOREIGN KEY (orders_id) REFERENCES Orders(orders_id);

ALTER TABLE aOrder
ADD CONSTRAINT fk_aOrder_customer_id FOREIGN KEY (customer_id) REFERENCES Customers(customer_id);


ALTER TABLE Order_Details
ADD CONSTRAINT fk_Order_Details_aorder_id FOREIGN KEY (aorder_id) REFERENCES aOrder(aorder_id);

ALTER TABLE Order_Details
ADD CONSTRAINT fk_Order_Details_food_name FOREIGN KEY (food_name) REFERENCES menu(food_name);

--price는 조인으로 가져오기 

--조건 추가
--ALTER TABLE rtable
--ADD CONSTRAINT ck_seat_status CHECK (seat_status IN ('empty', 'occupied', 'disable'));

SELECT * FROM Order_Details;
SELECT * FROM aOrder;
SELECT * FROM Customers;
SELECT * FROM rtable;
SELECT * FROM Employee;

