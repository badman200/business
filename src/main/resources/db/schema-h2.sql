DROP TABLE IF EXISTS transaction;

CREATE TABLE transaction
(
	id INT(11) IDENTITY PRIMARY KEY COMMENT '主键ID',
	trade_id INT(11) NOT NULL COMMENT '交易号',
	version INT(11) NOT NULL COMMENT '版本',
	security_code VARCHAR(50) NOT NULL COMMENT '安全码',
	quantity INT(11) NOT NULL COMMENT '数量',
	opera_type INT(11) NOT NULL COMMENT '操作类型1插入2更新3取消',
	trade_type INT(11) NOT NULL COMMENT '交易类型1买2卖'
);


DROP TABLE IF EXISTS position;
CREATE TABLE position
(
    id INT(11) IDENTITY PRIMARY KEY COMMENT '主键 trade_id',
    security_code VARCHAR(50) NOT NULL COMMENT '安全码',
    quantity INT(11) NOT NULL COMMENT '数量'
)



