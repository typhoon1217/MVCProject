
DROP TABLE client_login_logs;
DROP TABLE client_ids;

DROP SEQUENCE logs_seq;
DROP SEQUENCE client_seq;

-- 클라이언트 아이디 테이블 생성
CREATE TABLE client_ids (
    client_id VARCHAR2(20) PRIMARY KEY
);

-- 클라이언트 로그인 로그 테이블 생성
CREATE TABLE client_login_logs (
    log_id NUMBER PRIMARY KEY,
    client_id VARCHAR2(20),
    login_attempt_time TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES client_ids(client_id)
);

-- 시퀀스 생성
CREATE SEQUENCE client_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE logs_seq
    START WITH 1
    INCREMENT BY 1;


INSERT INTO client_ids (client_id) VALUES ('CID' || LPAD(client_seq.NEXTVAL, 17, '0'));


SELECT * FROM client_ids;
SELECT * FROM client_login_logs;

-- 삽입된 클라이언트 아이디 조회
SELECT * FROM client_ids 
where client_id = 'CID00000000000000001';

-- 클라이언트의 로그인 시도 횟수와 마지막 로그인 시간 조회
SELECT login_attempt_time
FROM client_login_logs
WHERE client_id = 'CID2';

commit;
