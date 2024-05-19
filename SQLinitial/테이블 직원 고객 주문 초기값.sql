-- 초기 데이터 삽입
INSERT INTO Customers (customer_id, customer_name, customer_email, customer_phone_number, customer_address, customer_grade)
VALUES ('john1234', '김조누', 'john31@example.com', 1010022999, '123 Main St', 'Silver');

INSERT INTO Customers (customer_id, customer_name, customer_email, customer_phone_number, customer_address, customer_grade)
VALUES ('jane1234', '김제인', 'jane12@sample.com', 1020011020, '456 Elm St', 'Gold');

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_phone_number, emp_salary)
VALUES ('admin123', 'Qwer!234', '신중우', NULL, 'admin', 1012341111, 70000);

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_phone_number, emp_salary)
VALUES ('server123', 'Qwer!234', '서중우', '신중우', 'server', 1012321123, 10000);

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_phone_number, emp_salary)
VALUES ('server321', 'Qwer!234', '서이우', '신중우', 'server', 1012342222, 10000);

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_phone_number, emp_salary)
VALUES ('chef123', 'Qwer!234', '세중우', '신중우', 'chef', 1012323411, 10000);

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_phone_number, emp_salary)
VALUES ('acc123', 'Qwer!234', '어중우', '신중우', 'account', 1012321241, 10000);

INSERT INTO rtable (table_no)
VALUES (1);

INSERT INTO rtable (table_no)
VALUES (2);

INSERT INTO rtable (table_no)
VALUES (3);

-- 주문 예제 1
INSERT INTO aOrder (table_no, customer_id, aorder_datetime, atotal_amount, aorder_status)
VALUES (3, 'john1234', SYSTIMESTAMP, 0, 'cook');

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000001', '프랜치 어니언 수프L', 1);

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000001', '그린 샐러드', 2);

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000001', '토마토 안심 파스타', 2);

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000001', '티라미수', 1);

-- 주문 예제 2
INSERT INTO aOrder (table_no, customer_id, aorder_datetime, atotal_amount, aorder_status)
VALUES (3, 'john1234', SYSTIMESTAMP, 0, 'cook');

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000002', '프랜치 어니언 수프S', 1);

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000002', '크림 소스 새우 파스타', 1);

-- a1, b1 주문 2개 초기값
INSERT INTO aOrder (table_no, customer_id, aorder_datetime, atotal_amount, aorder_status)
VALUES (1, 'john1234', SYSTIMESTAMP, 0, 'cook');

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000003', '크림 소스 새우 파스타', 1);

INSERT INTO aOrder (table_no, customer_id, aorder_datetime, atotal_amount, aorder_status)
VALUES (2, 'john1234', SYSTIMESTAMP, 0, 'cook');

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000004', '크림 소스 새우 파스타', 1);



COMMIT;

-- 조건 추가 (주석)
-- ALTER TABLE rtable
-- ADD CONSTRAINT ck_seat_status CHECK (seat_status IN ('empty', 'occupied', 'disabled'));

-- 테이블 조회
SELECT * FROM Order_Details;
SELECT * FROM aOrder;
SELECT * FROM Customers;
SELECT * FROM rtable;
SELECT * FROM Employee;

-- 관리자 로그인 정보 확인
SELECT emp_department FROM Employee WHERE emp_id = 'admin123' AND emp_password = 'Qwer!234';

-- 관리자 부서 정보 확인
SELECT emp_department FROM Employee WHERE emp_id = 'admin123';