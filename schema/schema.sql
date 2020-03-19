DROP DATABASE IF EXISTS secure_banking_system;

CREATE DATABASE secure_banking_system;

USE secure_banking_system;

CREATE TABLE `secure_banking_system`.`user` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(60) NOT NULL,
  status INT NOT NULL,
  incorrect_attempts INT DEFAULT 0,
  created_date DATETIME DEFAULT NOW(),
  modified_date DATETIME DEFAULT NOW(),
  role VARCHAR(100)
);

CREATE TABLE `secure_banking_system`.`user_details` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255) DEFAULT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(15) NOT NULL,
  address1 VARCHAR(255) NOT NULL,
  address2 VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  province VARCHAR(255) NOT NULL,
  zip INT NOT NULL,
  date_of_birth DATETIME NOT NULL,
  ssn VARCHAR(15) NOT NULL UNIQUE,
  question_1 VARCHAR(255) NOT NULL,
  question_2 VARCHAR(255) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES `secure_banking_system`.`user`(id) ON DELETE CASCADE
);

CREATE TABLE `secure_banking_system`.`account` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  account_number VARCHAR(255) NOT NULL,
  account_type VARCHAR(100) NOT NULL,
  current_balance DECIMAL(30, 5) DEFAULT 0.0,
  created_date DATETIME NOT NULL DEFAULT NOW(),
  status BOOLEAN NOT NULL,
  interest DECIMAL(10, 5) DEFAULT 0.0,
  approval_date DATETIME,
  FOREIGN KEY (user_id) REFERENCES `secure_banking_system`.`user`(id)
);

CREATE TABLE `secure_banking_system`.`transaction` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  approval_status BOOLEAN NOT NULL,
  amount DECIMAL(10, 5),
  is_critical_transaction BOOLEAN NOT NULL,
  requested_date DATETIME NOT NULL DEFAULT NOW(),
  decision_date DATETIME DEFAULT NULL,
  from_account INT NOT NULL,
  to_account INT NOT NULL,
  transaction_type VARCHAR(100) NOT NULL,
  request_assigned_to INT DEFAULT NULL,
  approval_level_required VARCHAR(100) NOT NULL,
  level_1_approval BOOLEAN DEFAULT NULL,
  level_2_approval BOOLEAN DEFAULT NULL,
  approved BOOLEAN DEFAULT NULL, /* Can be approved by merchant or bank employee depending on type of request */
  FOREIGN KEY (request_assigned_to) REFERENCES `secure_banking_system`.`user`(id),
  FOREIGN KEY (from_account) REFERENCES `secure_banking_system`.`account`(id),
  FOREIGN KEY (to_account) REFERENCES `secure_banking_system`.`account`(id)
);

CREATE TABLE `secure_banking_system`.`appointment` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  appointment_user_id INT NOT NULL,
  assigned_to_user_id INT NOT NULL,
  created_date DATETIME NOT NULL DEFAULT NOW(),
  appointment_status VARCHAR(25) NOT NULL,
  FOREIGN KEY (appointment_user_id) REFERENCES `secure_banking_system`.`user`(id),
  FOREIGN KEY (assigned_to_user_id) REFERENCES `secure_banking_system`.`user`(id)
);

CREATE TABLE `secure_banking_system`.`login_history` (
  id INT PRIMARY KEY AUTO_INCREMENT, 
  user_id INT NOT NULL,
  logged_in DATETIME NOT NULL DEFAULT NOW(),
  logged_out DATETIME DEFAULT NULL,
  ip_address VARCHAR(25) NOT NULL,
  device_type VARCHAR(25) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES `secure_banking_system`.`user`(id)
);
