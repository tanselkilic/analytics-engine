CREATE TABLE IF NOT EXISTS page_context
(id BIGINT AUTO_INCREMENT,
 op_id BIGINT NOT NULL,
 op_type VARCHAR(10) NOT NULL,
 site_id VARCHAR(256) NOT NULL,
 user_id VARCHAR(256) NOT NULL,
 session_id VARCHAR(50) NOT NULL,
 page VARCHAR(500) NULL,
 event VARCHAR(500) NULL,
 referrer_type VARCHAR(20) NULL,    -- internal/external
 referrer_url VARCHAR(500) NULL,
 referrer_domain VARCHAR(100) NULL,
 page_title VARCHAR(500) NULL,
 page_url VARCHAR(500) NULL,
 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (id),
 FOREIGN KEY (user_id) REFERENCES users(user_id) ON UPDATE CASCADE,
 FOREIGN KEY (session_id) REFERENCES sessions(session_id) ON DELETE CASCADE,
 FOREIGN KEY (site_id) REFERENCES sites(site_id) ON DELETE CASCADE);
