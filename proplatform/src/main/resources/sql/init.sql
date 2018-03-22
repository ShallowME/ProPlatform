-- 数据库初始化脚本
-- ------------------------------------------------------ proplatform数据库 v1.0

-- 数据库一定要设置好编码！！
SET NAMES utf8;
SET character_set_database = utf8;
SET character_set_server = utf8;

-- 创建数据库
CREATE DATABASE proplatform;

-- 使用数据库
USE proplatform;

-- 创建表

-- 创建用户登录信息表
CREATE TABLE user(
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(255),
  user_phone_num VARCHAR(255),
  user_pasword VARCHAR(255),
  user_salt VARCHAR(255),
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE user  ADD UNIQUE KEY (user_name, user_phone_num);


-- 创建用户个人资料表
CREATE TABLE user_info(
  user_id INT,
  user_url VARCHAR(255),
  user_realname VARCHAR(255),
  user_sex VARCHAR(255),
  user_province VARCHAR(255),
  pro_city VARCHAR(255),
  user_school VARCHAR(255),
  user_major VARCHAR(255),
  user_mailbox VARCHAR(255),
  user_profession VARCHAR(255),
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE user_info ADD FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE;

-- 创建企业登录信息表
CREATE TABLE company(
  id INT PRIMARY KEY AUTO_INCREMENT,
  company_name VARCHAR(255),
  company_phone_num VARCHAR(255),
  company_password VARCHAR(255),
  comapny_salt VARCHAR(255),
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE company ADD UNIQUE KEY (company_name,company_phone_num);

-- 创建企业资料表
CREATE TABLE company_info(
  company_id INT,
  company_logo VARCHAR(255),
  company_realname VARCHAR(255),
  company_major VARCHAR(255),
  company_description VARCHAR(255),
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE company_info ADD FOREIGN KEY (`company_id`) REFERENCES company(`id`) ON UPDATE CASCADE;

-- 创建角色表
CREATE TABLE role(
  id INT PRIMARY KEY AUTO_INCREMENT,
  role_name VARCHAR(255),
  role_description VARCHAR(255),
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 创建权限表
CREATE TABLE permission(
  id INT PRIMARY KEY AUTO_INCREMENT,
  per_name VARCHAR(255),
  per_description VARCHAR(255),
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 创建用户角色对应表
CREATE TABLE user_role(
  user_id INT,
  role_id INT,
  PRIMARY KEY (user_id,role_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE user_role ADD FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE;
ALTER TABLE user_role ADD FOREIGN KEY (`role_id`) REFERENCES role(`id`) ON DELETE CASCADE;

-- 创建企业角色对应表
CREATE TABLE company_role(
  company_id INT,
  role_id INT,
  PRIMARY KEY (company_id,role_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE company_role ADD FOREIGN KEY (`company_id`) REFERENCES company(`id`) ON DELETE CASCADE;
ALTER TABLE company_role ADD FOREIGN KEY (`role_id`) REFERENCES role(`id`) ON DELETE CASCADE;

  -- 创建角色权限对应表
CREATE TABLE role_permission (
  role_id       INT,
  permission_id INT,
  PRIMARY KEY (role_id, permission_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE role_permission ADD FOREIGN KEY (`role_id`) REFERENCES role(`id`) ON DELETE CASCADE;
ALTER TABLE role_permission ADD FOREIGN KEY (`permission_id`) REFERENCES permission(`id`) ON DELETE CASCADE;


-- 创建简历表
CREATE TABLE resume(
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  resume_img VARCHAR(255),
  resume_realname VARCHAR(255),
  resume_sex VARCHAR(255),
  resume_address VARCHAR(255),
  resume_school VARCHAR(255),
  resume_major VARCHAR(255),
  resume_mailbox VARCHAR(255),
  resume_phonenum VARCHAR(255),
  resume_sch_experience TEXT,
  resume_work_experience TEXT,
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE resume ADD FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE;

-- 创建专利表
CREATE TABLE patent(
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  patent_img VARCHAR(255),
  patent_name VARCHAR(255),
  patent_owner VARCHAR(255),
  patent_certi_code VARCHAR(255),
  patent_apply_date BIGINT,
  patent_auth_date BIGINT,
  patent_contact VARCHAR(255),
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE patent ADD FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE;

-- 创建订阅表
CREATE TABLE subscribe(
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  sub_spot VARCHAR(255),
  sub_type VARCHAR(255),
  sub_max_pay INT,
  sub_min_pay INT,
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE subscribe ADD FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE;

-- 创建项目表
CREATE TABLE project(
  id INT PRIMARY KEY AUTO_INCREMENT,
  company_id INT,
  company_name VARCHAR(255),
  pro_name VARCHAR(255),
  pro_money VARCHAR(255),
  pro_type VARCHAR(255),
  pro_cycle INT,
  pro_pubtime BIGINT,
  pro_enrollment INT,
  pro_description TEXT,
  pro_request TEXT,
  pro_state INT,
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE project ADD FOREIGN KEY (`company_id`) REFERENCES company(`id`) ON DELETE CASCADE;

-- 创建项目阶段表
CREATE TABLE stage(
  id INT PRIMARY KEY AUTO_INCREMENT,
  pro_id INT,
  stage_num INT,
  stage_starttime BIGINT,
  stage_endtime BIGINT,
  stage_settletime BIGINT,
  stage_speed INT,
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE stage ADD FOREIGN KEY (`pro_id`) REFERENCES project(`id`) ON DELETE CASCADE;

-- 创建阶段目标表
CREATE TABLE target(
  id INT PRIMARY KEY AUTO_INCREMENT,
  stage_id INT,
  target_deadline BIGINT,
  target_detail TEXT,
  target_remarks TEXT,
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE target ADD FOREIGN KEY (`stage_id`) REFERENCES stage(`id`) ON DELETE CASCADE;

-- 创建项目文件表
CREATE TABLE file(
  id INT PRIMARY KEY AUTO_INCREMENT,
  stage_id INT,
  user_id INT,
  company_id INT,
  file_name VARCHAR(255),
  file_size FLOAT,
  file_uploader VARCHAR(255),
  file_modifydate BIGINT,
  file_url VARCHAR(255),
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE file ADD FOREIGN KEY (`stage_id`) REFERENCES stage(`id`) ON DELETE CASCADE;
ALTER TABLE file ADD FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE;
ALTER TABLE file ADD FOREIGN KEY (`company_id`) REFERENCES company(`id`) ON DELETE CASCADE;

-- 创建项目申请表
CREATE TABLE apply(
  id INT PRIMARY KEY AUTO_INCREMENT,
  resume_id INT,
  company_id INT,
  pro_id INT,
  apply_state INT,
  apply_mark INT,
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE apply ADD FOREIGN KEY (`resume_id`) REFERENCES resume(`id`) ON DELETE CASCADE;
ALTER TABLE apply ADD FOREIGN KEY (`company_id`) REFERENCES company(`id`) ON DELETE CASCADE;
ALTER TABLE apply ADD FOREIGN KEY (`pro_id`) REFERENCES project(`id`) ON DELETE CASCADE;

-- 创建项目邀请表
CREATE TABLE invite(
  id INT PRIMARY KEY AUTO_INCREMENT,
  resume_id INT,
  company_id INT,
  pro_id INT,
  invite_state INT,
  invite_mark INT,
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE invite ADD FOREIGN KEY (`resume_id`) REFERENCES resume(`id`) ON DELETE CASCADE;
ALTER TABLE invite ADD FOREIGN KEY (`company_id`) REFERENCES company(`id`) ON DELETE CASCADE;
ALTER TABLE invite ADD FOREIGN KEY (`pro_id`) REFERENCES project(`id`) ON DELETE CASCADE;

-- 消息表
CREATE TABLE message(
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  company_id INT,
  mes_content VARCHAR(255),
  mes_state INT,
  modification_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改的时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE message ADD FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE;
ALTER TABLE message ADD FOREIGN KEY (`company_id`) REFERENCES company(`id`) ON DELETE CASCADE;

-- 所有外键约束都为删除时cascade类型（级联删除）
ON DELETE CASCADE;

-- 数据表要统一编码
alter table album convert to character set utf8;

-- 插入独立约束时
INSERT INTO TABLE (a,c) VALUES (1,3),(1,7) ON DUPLICATE KEY UPDATE c=VALUES(c);
