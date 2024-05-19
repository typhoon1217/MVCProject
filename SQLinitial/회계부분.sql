DROP TABLE Accounting;
DROP TABLE TotalSales;

--------------------------------------------------------------------------------
-- ȸ�� ���� ���̺� ����
CREATE TABLE Accounting (
    transaction_id      VARCHAR2(20)   NOT NULL, -- ������ ���� Ʈ���� 
    transaction_amount  NUMBER         NOT NULL,
    transaction_time    TIMESTAMP      NOT NULL, -- �ŷ� �ð�
    transaction_description NVARCHAR2(30)    -- �ŷ� ���� �ѱ�30��
);

-- �� �������� ���̺� ����  (SYSDATE YYMM �� ���� ���̺� ����)
CREATE TABLE TotalSales(
    sales_month         NUMBER  NOT NULL, --yyyymm ���ھ��� ��������
    sales_income        NUMBER  ,
    sales_expense       NUMBER  ,
    sales_netProfit     NUMBER  
);

-- Accounting ���̺� �����̸Ӹ� Ű �߰�
ALTER TABLE Accounting
ADD CONSTRAINT accounting_pk PRIMARY KEY (transaction_id);

-- TotalSales ���̺� �����̸Ӹ� Ű �߰�
ALTER TABLE TotalSales
ADD CONSTRAINT totalsales_pk PRIMARY KEY (sales_month);

-- ������ ����
CREATE SEQUENCE transaction_seq START WITH 1 INCREMENT BY 1;

-- Ʈ����: ���� ���̵� �ڵ�����
CREATE OR REPLACE TRIGGER generate_transaction_id
BEFORE INSERT ON Accounting
FOR EACH ROW
BEGIN
    :NEW.transaction_id := 'TID' || TO_CHAR(transaction_seq.NEXTVAL, 'FM00000000'); -- �������� ����Ͽ� ������ ID ����
END;
/

-- Ʈ����: ���� ���� ��� �� ������Ʈ
CREATE OR REPLACE TRIGGER cal_totalsales
AFTER INSERT ON Accounting
FOR EACH ROW
DECLARE
    v_year_month VARCHAR2(6);
BEGIN
    -- yyyy/mm ������ �⵵�� ���� ���ڿ��� ����
    v_year_month := TO_CHAR(:NEW.transaction_time, 'YYMM');
    
    -- �ش� ���� ���� ������ �̹� �����ϴ��� Ȯ��
    -- ������ ���ο� ���ڵ带 ����, ������ ���� ���ڵ� ������Ʈ
    BEGIN
        INSERT INTO TotalSales (sales_month, sales_income, sales_expense, sales_netProfit)
        VALUES (v_year_month, 
                CASE WHEN :NEW.transaction_amount > 0 THEN :NEW.transaction_amount ELSE 0 END, -- �����̸� ��� ���� ���
                CASE WHEN :NEW.transaction_amount < 0 THEN :NEW.transaction_amount ELSE 0 END, -- �����̸� ���� ���� ���
                :NEW.transaction_amount); -- �ʱⰪ���� transaction_amount�� ����
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            UPDATE TotalSales
            SET sales_income = sales_income + CASE WHEN :NEW.transaction_amount > 0 THEN :NEW.transaction_amount ELSE 0 END,
                sales_expense = sales_expense + CASE WHEN :NEW.transaction_amount < 0 THEN :NEW.transaction_amount ELSE 0 END,
                sales_netProfit = sales_netProfit + :NEW.transaction_amount
            WHERE sales_month = v_year_month;
    END;
END;
/

-- �ʱⰪ ��ī����------------------
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (13000, SYSTIMESTAMP, '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (-100000, SYSTIMESTAMP, '��ǰ����');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000, SYSTIMESTAMP, '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000, SYSTIMESTAMP, '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000, SYSTIMESTAMP, '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000, SYSTIMESTAMP, '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000, SYSTIMESTAMP, '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000, SYSTIMESTAMP, '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (-130000, SYSTIMESTAMP, '����');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (6000, SYSTIMESTAMP, '�Ķ� ��� �ΰ� ���̾��');

-- ���� �� �� �� ����
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (13000, TIMESTAMP '2024-04-01 12:34:56', '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (-100000, TIMESTAMP '2024-04-05 09:45:30', '��ǰ����');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000, TIMESTAMP '2024-04-10 14:20:00', '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000, TIMESTAMP '2024-04-15 18:02:45', '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000, TIMESTAMP '2024-04-20 08:30:15', '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000, TIMESTAMP '2024-03-25 13:15:00', '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000, TIMESTAMP '2024-03-30 17:45:30', '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000, TIMESTAMP '2024-08-03 10:00:00', '�Ǹ�');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (-130000, TIMESTAMP '2024-03-09 14:30:45', '����');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (6000, TIMESTAMP '2024-10-13 09:15:30', '�Ķ� ��� �ΰ� ���̾��');

-- �׽�Ʈ�� ��ȸ
SELECT * FROM Accounting;
SELECT * FROM TotalSales;



--DROP TABLE Accounting;
--DROP TABLE TotalSales;
