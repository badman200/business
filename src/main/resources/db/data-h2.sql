
DELETE FROM transaction;

INSERT INTO transaction ( trade_id, version, security_code,quantity,opera_type,trade_type) VALUES
( 1, 1,'REL',50, 1,1),
( 2, 1,'ITC',40, 1,2),
( 3, 1,'INF',70, 1,1),
( 1, 2,'REL',60, 2,1),
( 2, 2,'ITC',30, 3,1),
( 4, 1,'INF',20, 1,2);

INSERT INTO position (security_code,quantity) values
('REL',-60),
('ITC',0),
('INF',50);

