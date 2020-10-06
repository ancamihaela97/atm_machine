DROP TABLE IF EXISTS ACCOUNT;
CREATE TABLE ACCOUNT(
                        ID INT AUTO_INCREMENT  PRIMARY KEY,
                        CARD VARCHAR(16) NOT NULL ,
                        PIN VARCHAR(4) NOT NULL ,
                        BALANCE DOUBLE
);