
CREATE SEQUENCE PUBLIC.ORCAMENTO_SEQUENCE START WITH 1 INCREMENT BY 1;

CREATE TABLE ORCAMENTO (
    ORCAMENTO_ID INTEGER NOT NULL PRIMARY KEY,
    ORCAMENTO_DATA TIMESTAMP NOT NULL
);