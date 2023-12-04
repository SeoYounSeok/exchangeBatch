-- SmsMessageDTO 테이블 생성
CREATE TABLE sms_message (
    id NUMBER PRIMARY KEY, -- PK
    sms_request_id NUMBER, -- sms_request 연결 ID
    to_number VARCHAR2(20), -- 수신번호
    message_content CLOB, -- 개별 메시지 내용
    CONSTRAINT fk_sms_request FOREIGN KEY (sms_request_id) REFERENCES sms_request(id)
);