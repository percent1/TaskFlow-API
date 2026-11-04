-- Create the user table
CREATE TABLE `user` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY UKob8kqyqqgmefl0aco34akdtpe (email)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;


-- Create the task table
CREATE TABLE task (
    id BIGINT NOT NULL AUTO_INCREMENT,
    completed BIT(1) NOT NULL,
    created_at DATETIME(6) DEFAULT NULL,
    description VARCHAR(500) DEFAULT NULL,
    due_date DATE DEFAULT NULL,
    tittle VARCHAR(100) NOT NULL,
    user_id BIGINT DEFAULT NULL,

    PRIMARY KEY (id),
    KEY FK2hsytmxysatfvt0p1992cw449 (user_id),

    CONSTRAINT FK2hsytmxysatfvt0p1992cw449
        FOREIGN KEY (user_id) REFERENCES `user` (id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;