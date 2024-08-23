-- Create the database (PostgreSQL does not have `IF NOT EXISTS` for CREATE DATABASE)
-- CREATE DATABASE eazybank;

-- -- Switch to the database
-- \c eazybank;

-- Disable foreign key checks (not supported in PostgreSQL, but you can drop constraints if needed)
-- DO $$ BEGIN SET LOCAL disable_trigger ALL; END $$;

-- Table structure for table `customer`
DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
  customer_id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  mobile_number VARCHAR(20) NOT NULL,
  pwd VARCHAR(500) NOT NULL,
  role VARCHAR(100) NOT NULL,
  create_dt DATE DEFAULT NULL
);

-- Table structure for table `accounts`
DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
  customer_id INT NOT NULL,
  account_number INT NOT NULL,
  account_type VARCHAR(100) NOT NULL,
  branch_address VARCHAR(200) NOT NULL,
  create_dt DATE DEFAULT NULL,
  PRIMARY KEY (account_number),
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- Table structure for table `loans`
DROP TABLE IF EXISTS loans;
CREATE TABLE loans (
  loan_number SERIAL PRIMARY KEY,
  customer_id INT NOT NULL,
  start_dt DATE NOT NULL,
  loan_type VARCHAR(100) NOT NULL,
  total_loan INT NOT NULL,
  amount_paid INT NOT NULL,
  outstanding_amount INT NOT NULL,
  create_dt DATE DEFAULT NULL,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- Table structure for table `cards`
DROP TABLE IF EXISTS cards;
CREATE TABLE cards (
  card_id SERIAL PRIMARY KEY,
  card_number VARCHAR(100) NOT NULL,
  customer_id INT NOT NULL,
  card_type VARCHAR(100) NOT NULL,
  total_limit INT NOT NULL,
  amount_used INT NOT NULL,
  available_amount INT NOT NULL,
  create_dt DATE DEFAULT NULL,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- Table structure for table `authorities`
DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities (
  id SERIAL PRIMARY KEY,
  customer_id INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Table structure for table `account_transactions`
DROP TABLE IF EXISTS account_transactions;
CREATE TABLE account_transactions (
  transaction_id VARCHAR(200) NOT NULL,
  account_number INT NOT NULL,
  customer_id INT NOT NULL,
  transaction_dt DATE NOT NULL,
  transaction_summary VARCHAR(200) NOT NULL,
  transaction_type VARCHAR(100) NOT NULL,
  transaction_amt INT NOT NULL,
  closing_balance INT NOT NULL,
  create_dt DATE DEFAULT NULL,
  PRIMARY KEY (transaction_id),
  FOREIGN KEY (account_number) REFERENCES accounts(account_number) ON DELETE CASCADE,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- Table structure for table `contact_messages`
DROP TABLE IF EXISTS contact_messages;
CREATE TABLE contact_messages (
  contact_id VARCHAR(50) PRIMARY KEY,
  contact_name VARCHAR(50) NOT NULL,
  contact_email VARCHAR(100) NOT NULL,
  subject VARCHAR(500) NOT NULL,
  message VARCHAR(2000) NOT NULL,
  create_dt DATE DEFAULT NULL
);

-- Table structure for table `notice_details`
DROP TABLE IF EXISTS notice_details;
CREATE TABLE notice_details (
  notice_id SERIAL PRIMARY KEY,
  notice_summary VARCHAR(200) NOT NULL,
  notice_details VARCHAR(500) NOT NULL,
  notic_beg_dt DATE NOT NULL,
  notic_end_dt DATE NOT NULL,
  create_dt DATE DEFAULT NULL
);

-- Insert data into `customer`
INSERT INTO customer (name, email, mobile_number, pwd, role, create_dt) VALUES
('Happy', 'happy@example.com', '9876548337', '$2y$12$oRRbkNfwuR8ug4MlzH5FOeui.//1mkd.RsOAJMbykTSupVy.x/vb2', 'admin', '2024-05-23'),
('John Doe', 'john@example.com', '456747484', '$2y$12$oRRbkNfwuR8ug4MlzH5FOeui.//1mkd.RsOAJMbykTSupVy.x/vb2', 'user', '2024-05-24');

-- Insert data into `accounts`
INSERT INTO accounts (customer_id, account_number, account_type, branch_address, create_dt) VALUES
(1, 1865764534, 'Savings', '123 Main Street, New York', '2024-05-23');

-- Insert data into `loans`
INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt) VALUES
(1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13'),
(1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06'),
(1, '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14'),
(1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');

-- Insert data into `cards`
INSERT INTO cards (card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_dt) VALUES
('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, '2024-05-23'),
('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, '2024-05-23'),
('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, '2024-05-23');

-- Insert data into `authorities`
INSERT INTO authorities (customer_id, name) VALUES
(1, 'ROLE_USER'),
(1, 'ROLE_ADMIN');

-- Insert data into `account_transactions`
INSERT INTO account_transactions (transaction_id, account_number, customer_id, transaction_dt, transaction_summary, transaction_type, transaction_amt, closing_balance, create_dt) VALUES
('200b5685-18e7-11ef-ba5a-0242ac110002', 1865764534, 1, '2024-05-16', 'Coffee Shop', 'Withdrawal', 30, 34500, '2024-05-16'),
('2014353c-18e7-11ef-ba5a-0242ac110002', 1865764534, 1, '2024-05-17', 'Uber', 'Withdrawal', 100, 34400, '2024-05-17'),
('201d609b-18e7-11ef-ba5a-0242ac110002', 1865764534, 1, '2024-05-18', 'Self Deposit', 'Deposit', 500, 34900, '2024-05-18'),
('20236fc8-18e7-11ef-ba5a-0242ac110002', 1865764534, 1, '2024-05-19', 'Ebay', 'Withdrawal', 600, 34300, '2024-05-19'),
('2028052f-18e7-11ef-ba5a-0242ac110002', 1865764534, 1, '2024-05-21', 'OnlineTransfer', 'Deposit', 700, 35000, '2024-05-21'),
('202fa532-18e7-11ef-ba5a-0242ac110002', 1865764534, 1, '2024-05-22', 'Amazon.com', 'Withdrawal', 100, 34900, '2024-05-22');

-- Insert data into `contact_messages`
INSERT INTO contact_messages (contact_id, contact_name, contact_email, subject, message, create_dt) VALUES
('SR15519149', 'sfsaas', 'assads@gmai.com', 'asd', 'asdsa', '2024-06-18'),
('SR361436921', 'sfsaas', 'assads@remail.com', 'asfsasadsa', 'sdfsada', '2024-08-19'),
('SR699554793', 'Teste', 'sfsadsa@afafa.com', 'fafaf', 'asdad', '2024-06-18'),
('SR788581048', 'asfasdsa', 'asdsad@wrafa.com', 'asdsaddsfaf', 'assaas', '2024-06-18'),
('SR818795927', 'Test', 'zfafafa@fafaf.com', 'asfsaasf', 'safsafsafsa', '2024-06-18');

-- Insert data into `notice_details`
INSERT INTO notice_details (notice_summary, notice_details, notic_beg_dt, notic_end_dt, create_dt) VALUES
('Welcome', 'Welcome to EazyBank', '2024-08-21', '2025-08-21', '2024-08-21');

-- Drop the table if it exists
DROP TABLE IF EXISTS oauth2_registered_client;

-- Create the table
CREATE TABLE oauth2_registered_client (
  id VARCHAR(255) NOT NULL,
  client_id VARCHAR(255) NOT NULL,
  client_id_issued_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  client_secret VARCHAR(255),
  client_secret_expires_at TIMESTAMP,
  client_name VARCHAR(255) NOT NULL,
  client_authentication_methods VARCHAR(1000) NOT NULL,
  authorization_grant_types VARCHAR(1000) NOT NULL,
  redirect_uris VARCHAR(1000),
  scopes VARCHAR(1000) NOT NULL,
  client_settings VARCHAR(2000) NOT NULL,
  token_settings VARCHAR(2000) NOT NULL,
  PRIMARY KEY (id)
);

-- Insert data into the table
INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES
('1', 'oidc-client', '2024-08-09 08:33:46', NULL, NULL, 'OIDC Client', 'none', 'authorization_code', 'http://localhost:4200/login', 'openid', '{"requireAuthorizationConsent":false,"requireProofKey":true}', '{"accessTokenTimeToLive":"PT30M","refreshTokenTimeToLive":"P30D","reuseRefreshTokens":true}');

-- Re-enable foreign key checks (not needed in PostgreSQL)
-- DO $$ BEGIN SET LOCAL enable_trigger ALL; END $$;
