CREATE TABLE ADMIN
(
    ID            VARCHAR(256)  NOT NULL,
    FIRST_NAME    VARCHAR(100) NOT NULL,
    LAST_NAME     VARCHAR(100) NOT NULL,
    EMAIL         VARCHAR(100) NOT NULL,
    FK_ADDRESS_ID INTEGER NOT NULL,
    PHONE_NUMBER  VARCHAR(40)  NOT NULL,
    CONSTRAINT PK_ADMIN PRIMARY KEY (ID),
    CONSTRAINT FK_ADDRESS_ID_ADMIN FOREIGN KEY (FK_ADDRESS_ID) REFERENCES ADDRESS (ID)
);