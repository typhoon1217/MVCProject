DROP TABLE ACCOUNTING; 
DROP TABLE TOTALSALES;
--------------------------------------------------------------------------------
-- 회계 관리 테이블 생성
CREATE TABLE Accounting (
    transaction_id      VARCHAR2(10)   NOT NULL, -- 결제시 랜덤 생성 트리거 
    transaction_amount  NUMBER         NOT NULL,
    transaction_time    TIMESTAMP      NOT NULL, -- 거래 시간
    transaction_description VARCHAR2(90)    -- 거래 설명 한글30자
);

-- 총 월별매출 테이블 생성  (SYSDATE YYMM 로 월별 테이블 생성)
CREATE TABLE TotalSales(
    sales_month         NUMBER  NOT NULL, --yyyymm 날자없이 저장위해
    sales_income        NUMBER  ,
    sales_expense       NUMBER  ,
    sales_netProfit     NUMBER  
);


-- Accounting 테이블에 프라이머리 키 추가
ALTER TABLE Accounting
ADD CONSTRAINT accounting_pk PRIMARY KEY (transaction_id);

-- TotalSales 테이블에 프라이머리 키 추가
ALTER TABLE TotalSales
ADD CONSTRAINT totalsales_pk PRIMARY KEY (sales_month);

--트리거 결제 아이디 자동생성 --추후 입력방식 수정하면 좋을듯? 
CREATE OR REPLACE TRIGGER generate_transaction_id
BEFORE INSERT ON Accounting
FOR EACH ROW
BEGIN
    :NEW.transaction_id := DBMS_RANDOM.STRING('X', 10); -- 랜덤한 20자리 문자열 생성
END;
/

--트리거 월별매출입력시 월별매출 등록  
CREATE OR REPLACE TRIGGER cal_totalsales
AFTER INSERT ON Accounting
FOR EACH ROW
DECLARE
    v_year_month VARCHAR2(6);
BEGIN
    -- yyyy/mm 형태의 년도와 월을 문자열로 추출
    v_year_month := TO_CHAR(:NEW.transaction_time, 'YYMM');
    
    -- 해당 월에 대한 매출이 이미 존재하는지 확인
    -- 없으면 새로운 레코드를 삽입, 있으면 기존 레코드 업데이트
    BEGIN
        INSERT INTO TotalSales (sales_month, sales_income, sales_expense, sales_netProfit)
        VALUES (v_year_month, 
                CASE WHEN :NEW.transaction_amount > 0 THEN :NEW.transaction_amount ELSE 0 END, -- 수입이면 양수 값만 계산
                CASE WHEN :NEW.transaction_amount < 0 THEN :NEW.transaction_amount ELSE 0 END, -- 지출이면 음수 값만 계산
                :NEW.transaction_amount); -- 초기값으로 transaction_amount로 설정
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

--아래는 테스트 부분----------------
-- 초기값 어카운팅------------------
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (13000,SYSTIMESTAMP,'판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (-100000,SYSTIMESTAMP,'물품수리');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000,SYSTIMESTAMP,'판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000,SYSTIMESTAMP,'판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000,SYSTIMESTAMP,'판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000,SYSTIMESTAMP,'판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000,SYSTIMESTAMP,'판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000,SYSTIMESTAMP,'판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (-130000,SYSTIMESTAMP,'발주');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (6000,SYSTIMESTAMP,'파란 우산 두고 가셨어요');


-- 고정 값 몇 개 생성
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (13000, TIMESTAMP '2024-04-01 12:34:56', '판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (-100000, TIMESTAMP '2024-04-05 09:45:30', '물품수리');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000, TIMESTAMP '2024-04-10 14:20:00', '판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000, TIMESTAMP '2024-04-15 18:02:45', '판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000, TIMESTAMP '2024-04-20 08:30:15', '판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000, TIMESTAMP '2024-03-25 13:15:00', '판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (103000, TIMESTAMP '2024-03-30 17:45:30', '판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (43000, TIMESTAMP '2024-08-03 10:00:00', '판매');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (-130000, TIMESTAMP '2024-03-09 14:30:45', '발주');
INSERT INTO Accounting (transaction_amount, transaction_time, transaction_description)
VALUES (6000, TIMESTAMP '2024-10-13 09:15:30', '파란 우산 두고 가셨어요');

-- -----------------
SELECT * FROM Accounting;
SELECT * FROM TotalSales;


--DROP TABLE Accounting;
--DROP TABLE TotalSales;
