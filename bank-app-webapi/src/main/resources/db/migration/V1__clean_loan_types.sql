-- Drop existing tables in correct order
DROP TABLE IF EXISTS individual_customer_loan_types CASCADE;
DROP TABLE IF EXISTS corporate_customer_loan_types CASCADE;
DROP TABLE IF EXISTS loan_types CASCADE;

-- Create tables in correct order
CREATE TABLE loan_types (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    min_amount DECIMAL(19,2) NOT NULL,
    max_amount DECIMAL(19,2) NOT NULL,
    min_term INTEGER NOT NULL,
    max_term INTEGER NOT NULL,
    interest_rate DECIMAL(19,2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE individual_customer_loan_types (
    id BIGINT PRIMARY KEY REFERENCES loan_types(id),
    min_credit_score INTEGER NOT NULL,
    max_debt_to_income_ratio DECIMAL(19,2) NOT NULL,
    requires_collateral BOOLEAN NOT NULL,
    individual_customer_id BIGINT
);

CREATE TABLE corporate_customer_loan_types (
    id BIGINT PRIMARY KEY REFERENCES loan_types(id),
    min_years_in_business INTEGER NOT NULL,
    min_annual_revenue DECIMAL(19,2) NOT NULL,
    requires_business_plan BOOLEAN NOT NULL,
    requires_financial_statements BOOLEAN NOT NULL,
    corporate_customer_id BIGINT
); 