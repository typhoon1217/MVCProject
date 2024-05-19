
DROP TABLE client_login_logs;
DROP TABLE client_ids;

DROP SEQUENCE logs_seq;
DROP SEQUENCE client_seq;

-- Ŭ���̾�Ʈ ���̵� ���̺� ����
CREATE TABLE client_ids (
    client_id VARCHAR2(20) PRIMARY KEY
);

-- Ŭ���̾�Ʈ �α��� �α� ���̺� ����
CREATE TABLE client_login_logs (
    log_id NUMBER PRIMARY KEY,
    client_id VARCHAR2(20),
    login_attempt_time TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES client_ids(client_id)
);

-- ������ ����
CREATE SEQUENCE client_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE logs_seq
    START WITH 1
    INCREMENT BY 1;


INSERT INTO client_ids (client_id) VALUES ('CID' || LPAD(client_seq.NEXTVAL, 17, '0'));


SELECT * FROM client_ids;
SELECT * FROM client_login_logs;

-- ���Ե� Ŭ���̾�Ʈ ���̵� ��ȸ
SELECT * FROM client_ids 
where client_id = 'CID00000000000000001';

-- Ŭ���̾�Ʈ�� �α��� �õ� Ƚ���� ������ �α��� �ð� ��ȸ
SELECT login_attempt_time
FROM client_login_logs
WHERE client_id = 'CID2';

commit;
