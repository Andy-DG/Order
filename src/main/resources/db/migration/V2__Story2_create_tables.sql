CREATE TABLE ITEM
(
    ID          VARCHAR(36)  NOT NULL,
    NAME        VARCHAR(100) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    PRICE       FLOAT        NOT NULL,
    STOCK       INTEGER      NOT NULL,
    CONSTRAINT PK_ITEM PRIMARY KEY (ID)
);