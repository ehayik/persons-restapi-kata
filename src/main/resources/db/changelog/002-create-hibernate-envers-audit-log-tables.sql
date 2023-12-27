-- liquibase formatted sql
-- changeset ehayik:create-hibernate-envers-audit-log-tables
CREATE
    SEQUENCE AUDIT_REVISION_LOG_SEQ
START WITH
    1 INCREMENT BY 1;

CREATE
    TABLE
        AUDIT_REVISIONS_LOG(
            REVISION_NUMBER NUMBER(10) CONSTRAINT AUDIT_REVISIONS_LOG_PK PRIMARY KEY,
            REVISION_TIMESTAMP NUMBER
        );

CREATE
    TABLE
        PERSONS_AUDIT_LOG(
            REVISION_NUMBER_START NUMBER NOT NULL,
            REVISION_NUMBER_END NUMBER,
            REVISION_TYPE SMALLINT,
            PERSON_ID NUMBER(10) NOT NULL,
            FIRST_NAME VARCHAR2(100) NOT NULL,
            LAST_NAME VARCHAR2(100) NOT NULL,
            BIRTH_DATE DATE NOT NULL,
            GENDER VARCHAR2(1) NOT NULL,
            PHONE VARCHAR2(20) NOT NULL,
            EMAIL VARCHAR2(50) NOT NULL,
            CREATED_ON TIMESTAMP NOT NULL,
            LAST_UPDATED_ON TIMESTAMP NOT NULL,
            ADDRESS_ID NUMBER(10) NOT NULL,
            CONSTRAINT PERSONS_AUDIT_REVISIONS_LOG_START_FK FOREIGN KEY(REVISION_NUMBER_START) REFERENCES AUDIT_REVISIONS_LOG,
            CONSTRAINT PERSONS_AUDIT_REVISIONS_LOG_END_FK FOREIGN KEY(REVISION_NUMBER_END) REFERENCES AUDIT_REVISIONS_LOG,
            CONSTRAINT PERSONS_AUDIT_LOG_PK PRIMARY KEY(
                PERSON_ID,
                REVISION_NUMBER_START
            )
        );

CREATE
    TABLE
        ADDRESSES_AUDIT_LOG(
            REVISION_NUMBER_START NUMBER NOT NULL,
            REVISION_NUMBER_END NUMBER,
            REVISION_TYPE SMALLINT,
            ADDRESS_ID NUMBER(10) NOT NULL,
            STREET VARCHAR2(250) NOT NULL,
            BUILDING_NUMBER VARCHAR2(100) NOT NULL,
            CITY VARCHAR2(100) NOT NULL,
            COUNTRY VARCHAR2(100) NOT NULL,
            ZIP_CODE VARCHAR2(20) NOT NULL,
            CREATED_ON TIMESTAMP NOT NULL,
            LAST_UPDATED_ON TIMESTAMP NOT NULL,
            CONSTRAINT ADDRESSES_AUDIT_REVISIONS_LOG_START_FK FOREIGN KEY(REVISION_NUMBER_START) REFERENCES AUDIT_REVISIONS_LOG,
            CONSTRAINT ADDRESSES_AUDIT_REVISIONS_LOG_END_FK FOREIGN KEY(REVISION_NUMBER_END) REFERENCES AUDIT_REVISIONS_LOG,
            CONSTRAINT ADDRESSES_AUDIT_LOG_PK PRIMARY KEY(
                ADDRESS_ID,
                REVISION_NUMBER_START
            )
        );