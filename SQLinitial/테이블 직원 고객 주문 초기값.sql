INSERT INTO Customers (customer_id, customer_name, customer_email, customer_phone_number, customer_address, customer_grade)
VALUES ('john1234', 'John Doe', 'john31@example.com', '01010022999', '123 Main St', 'Sliver');

INSERT INTO Customers (customer_id, customer_name, customer_email, customer_phone_number, customer_address, customer_grade)
VALUES ('jane1234', 'Jane Smith', 'jane12@sample.com', '01020011020', '456 Elm St', 'Gold');


INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_contact, emp_salary)
VALUES ('admin', 'admin', '신중우', NULL, 'admin', '01012341111', 70000);

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_contact, emp_salary)
VALUES ('server', 'server', '서중우', '신중우', 'sever', '01012321123', 10000);

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_contact, emp_salary)
VALUES ('server2', 'server', '서이우', '신중우', 'sever', '01012342222', 10000);


INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_contact, emp_salary)
VALUES ('chef', 'chef', '세중우', '신중우', 'chef', '01012323411', 10000);


INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_contact, emp_salary)
VALUES ('account', 'account', '어중우', '신중우', 'account', '01012321241', 10000);



INSERT INTO rtable (table_no)
VALUES ('A01');   

INSERT INTO rtable (table_no)
VALUES ('B02');

INSERT INTO rtable (table_no)
VALUES ('C03');
       
-----------------------------주문1 예제--------------------------
--INSERT INTO Orders (orders_id, emp_id, table_no)
--VALUES ('C03N91293', 'server1234', 'C03');
--on pro(('table')+'N'+random5digit),emp_id,table_no)
INSERT INTO aOrder (aorder_id ,table_no, customer_id, aorder_datetime, atotal_amount,  aorder_status)
VALUES  ('C03N91291','C03','john1234',systimestamp,0,'cook');
--랜덤입력 실패시 재시도 5번

INSERT INTO Order_Details (order_detail_id, aorder_id, food_name, order_quantity)
VALUES  ('C03N91291101','C03N91291','프랜치 어니언 수프L',1);

INSERT INTO Order_Details (order_detail_id, aorder_id, food_name, order_quantity)
VALUES  ('C03N91291102','C03N91291','그린 샐러드',2);

INSERT INTO Order_Details (order_detail_id, aorder_id, food_name, order_quantity)
VALUES  ('C03N91291103','C03N91291','토마토 안심 파스타',2);

INSERT INTO Order_Details (order_detail_id, aorder_id, food_name, order_quantity)
VALUES  ('C03N91291104','C03N91291','티라미수',1);
------------------------------주문2 예제------------------------------
INSERT INTO aOrder (aorder_id ,table_no, customer_id, aorder_datetime, atotal_amount,  aorder_status)
VALUES  ('C03N91292','C03','john1234',systimestamp,0,'cook');
--on pro((order_id)+'N'+random5digit)
--랜덤입력 실패시 재시도 5번
INSERT INTO Order_Details (order_detail_id, aorder_id, food_name, order_quantity)
VALUES  ('C03N91292101','C03N91292','프랜치 어니언 수프S',1);

INSERT INTO Order_Details (order_detail_id, aorder_id, food_name, order_quantity)
VALUES  ('C03N91292102','C03N91292','크림 소스 새우 파스타',1);
-----------------------------a1 b1 주문 2개 초기값---------------------
INSERT INTO aOrder (aorder_id ,table_no, customer_id, aorder_datetime, atotal_amount,  aorder_status)
VALUES  ('A01N91292','A01','john1234',systimestamp,0,'cook');

INSERT INTO Order_Details (order_detail_id, aorder_id, food_name, order_quantity)
VALUES  ('A01N91292102','A01N91292','크림 소스 새우 파스타',1);

INSERT INTO aOrder (aorder_id ,table_no, customer_id, aorder_datetime, atotal_amount,  aorder_status)
VALUES  ('B02N91292','B02','john1234',systimestamp,0,'cook');

INSERT INTO Order_Details (order_detail_id, aorder_id, food_name, order_quantity)
VALUES  ('B02N91292102','B02N91292','크림 소스 새우 파스타',1);
