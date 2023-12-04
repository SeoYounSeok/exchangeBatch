-- SmsRequestDTO 테이블 생성
CREATE TABLE sms_request (
    id NUMBER PRIMARY KEY, -- PK
    type VARCHAR2(255), -- SMS, LMS, MMS (소문자 가능)
    content_type VARCHAR2(255), -- COMM: 일반메시지, AD: 광고메시지 / default: COMM
    country_code VARCHAR2(10), -- SENS에서 제공하는 국가로의 발송만 가능 / default : 82
    from_number VARCHAR2(20), -- 사전 등록된 발신번호만 사용 가능
    content CLOB -- 기본 메시지 내용
);