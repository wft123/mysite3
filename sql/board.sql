drop table board;
drop sequence board_no_seq;

CREATE TABLE BOARD
(
   NO                 NUMBER (8),
   TITLE              VARCHAR2 (200),
   CONTENT            VARCHAR2 (4000),
   MEMBER_NO          NUMBER (8),
   VIEW_CNT           NUMBER (10),
   REG_DATE           DATE,
   GROUP_NO           NUMBER (10) DEFAULT 0,
   ORDER_NO           NUMBER (10) DEFAULT 1,
   DEPTH              NUMBER (10) DEFAULT 0,
   FILENAME           VARCHAR2 (200),
   FILEORIGINALNAME   VARCHAR2 (200)
);

ALTER TABLE board
ADD ( CONSTRAINT board_no_pk PRIMARY KEY ( no ) );

CREATE SEQUENCE board_no_seq
 START WITH     1
 INCREMENT BY   1
 MAXVALUE       99999999
 NOCACHE
 NOCYCLE;