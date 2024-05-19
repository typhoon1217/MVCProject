-- �ʱ� ������ ����
INSERT INTO Customers (customer_id, customer_name, customer_email, customer_phone_number, customer_address, customer_grade)
VALUES ('john1234', '������', 'john31@example.com', 1010022999, '123 Main St', 'Silver');

INSERT INTO Customers (customer_id, customer_name, customer_email, customer_phone_number, customer_address, customer_grade)
VALUES ('jane1234', '������', 'jane12@sample.com', 1020011020, '456 Elm St', 'Gold');

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_phone_number, emp_salary)
VALUES ('admin123', 'Qwer!234', '���߿�', NULL, 'admin', 1012341111, 70000);

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_phone_number, emp_salary)
VALUES ('server123', 'Qwer!234', '���߿�', '���߿�', 'server', 1012321123, 10000);

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_phone_number, emp_salary)
VALUES ('server321', 'Qwer!234', '���̿�', '���߿�', 'server', 1012342222, 10000);

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_phone_number, emp_salary)
VALUES ('chef123', 'Qwer!234', '���߿�', '���߿�', 'chef', 1012323411, 10000);

INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_phone_number, emp_salary)
VALUES ('acc123', 'Qwer!234', '���߿�', '���߿�', 'account', 1012321241, 10000);

INSERT INTO rtable (table_no)
VALUES (1);

INSERT INTO rtable (table_no)
VALUES (2);

INSERT INTO rtable (table_no)
VALUES (3);

-- �ֹ� ���� 1
INSERT INTO aOrder (table_no, customer_id, aorder_datetime, atotal_amount, aorder_status)
VALUES (3, 'john1234', SYSTIMESTAMP, 0, 'cook');

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000001', '����ġ ��Ͼ� ����L', 1);

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000001', '�׸� ������', 2);

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000001', '�丶�� �Ƚ� �Ľ�Ÿ', 2);

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000001', 'Ƽ��̼�', 1);

-- �ֹ� ���� 2
INSERT INTO aOrder (table_no, customer_id, aorder_datetime, atotal_amount, aorder_status)
VALUES (3, 'john1234', SYSTIMESTAMP, 0, 'cook');

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000002', '����ġ ��Ͼ� ����S', 1);

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000002', 'ũ�� �ҽ� ���� �Ľ�Ÿ', 1);

-- a1, b1 �ֹ� 2�� �ʱⰪ
INSERT INTO aOrder (table_no, customer_id, aorder_datetime, atotal_amount, aorder_status)
VALUES (1, 'john1234', SYSTIMESTAMP, 0, 'cook');

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000003', 'ũ�� �ҽ� ���� �Ľ�Ÿ', 1);

INSERT INTO aOrder (table_no, customer_id, aorder_datetime, atotal_amount, aorder_status)
VALUES (2, 'john1234', SYSTIMESTAMP, 0, 'cook');

INSERT INTO Order_Details (aorder_id, food_name, order_quantity)
VALUES ('AOD0000004', 'ũ�� �ҽ� ���� �Ľ�Ÿ', 1);



COMMIT;

-- ���� �߰� (�ּ�)
-- ALTER TABLE rtable
-- ADD CONSTRAINT ck_seat_status CHECK (seat_status IN ('empty', 'occupied', 'disabled'));

-- ���̺� ��ȸ
SELECT * FROM Order_Details;
SELECT * FROM aOrder;
SELECT * FROM Customers;
SELECT * FROM rtable;
SELECT * FROM Employee;

-- ������ �α��� ���� Ȯ��
SELECT emp_department FROM Employee WHERE emp_id = 'admin123' AND emp_password = 'Qwer!234';

-- ������ �μ� ���� Ȯ��
SELECT emp_department FROM Employee WHERE emp_id = 'admin123';