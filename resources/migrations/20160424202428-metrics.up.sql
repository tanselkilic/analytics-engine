CREATE TABLE IF NOT EXISTS metrics
(id BIGINT AUTO_INCREMENT,
 context VARCHAR(20) DEFAULT NULL,
 metric_type VARCHAR(10) DEFAULT '',
 date_prefix VARCHAR(20) DEFAULT NULL,
 dim_name NUMERIC,
 dim_name1 VARCHAR(50) DEFAULT NULL,
 dim_name2 VARCHAR(50) DEFAULT NULL,
 dim_name3 VARCHAR(50) DEFAULT NULL,
 dim_name4 VARCHAR(50) DEFAULT NULL,
 dim_value NUMERIC,
 dim_value1 VARCHAR(256) DEFAULT NULL,
 dim_value2 VARCHAR(256) DEFAULT NULL,
 dim_value3 VARCHAR(256) DEFAULT NULL,
 dim_value4 VARCHAR(256) DEFAULT NULL,
 metric_value double DEFAULT 0,
 updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (id),
 KEY metrics_index (context,metric_type,date_prefix,dim_name,dim_value));
