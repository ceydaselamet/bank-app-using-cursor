package com.banking.business.constants;

public final class Messages {
    private Messages() {
        throw new IllegalStateException("Constants class");
    }

    public static final class ValidationMessages {
        private ValidationMessages() {}
        
        public static final String CUSTOMER_NOT_FOUND = "Customer not found";
        public static final String INDIVIDUAL_CUSTOMER_NOT_FOUND = "Individual customer not found";
        public static final String CORPORATE_CUSTOMER_NOT_FOUND = "Corporate customer not found";
    }

    public static final class BusinessMessages {
        private BusinessMessages() {}

        public static final class Common {
            private Common() {}
            
            public static final String EMAIL_EXISTS = "Customer with this email already exists";
            public static final String CUSTOMER_NUMBER_EXISTS = "Customer with this customer number already exists";
        }

        public static final class Individual {
            private Individual() {}
            
            public static final String NATIONAL_ID_EXISTS = "Individual customer with this national ID already exists";
            public static final String MINIMUM_AGE_NOT_MET = "Customer must be at least 18 years old";
        }

        public static final class Corporate {
            private Corporate() {}
            
            public static final String TAX_NUMBER_EXISTS = "Corporate customer with this tax number already exists";
            public static final String MINIMUM_COMPANY_AGE = "Company must be at least 2 years old";
            public static final String MINIMUM_ANNUAL_REVENUE = "Company must have minimum 1,000,000 annual revenue";
        }
    }

    public static final class BusinessRules {
        private BusinessRules() {}
        
        public static final int MINIMUM_INDIVIDUAL_AGE = 18;
        public static final int MINIMUM_COMPANY_AGE = 2;
        public static final double MINIMUM_ANNUAL_REVENUE = 1_000_000.0;
    }

    public static final class CustomerFormat {
        private CustomerFormat() {}
        
        public static final String INDIVIDUAL_PREFIX = "I";
        public static final String CORPORATE_PREFIX = "C";
        public static final int NUMBER_LENGTH = 8;
    }

    public static final class CreditScoring {
        private CreditScoring() {}
        
        public static final int MIN_SCORE = 0;
        public static final int MAX_SCORE = 1900;
        public static final int GOOD_SCORE = 1000;

        public static final String RATING_A = "A";
        public static final String RATING_B = "B";
        public static final String RATING_C = "C";
        public static final String RATING_D = "D";
    }

    public static class LoanType {
        public static final String NOT_FOUND = "Loan type not found";
        public static final String PARENT_NOT_FOUND = "Parent loan type not found";
        public static final String INVALID_AMOUNT_RANGE = "Minimum amount cannot be greater than maximum amount";
        public static final String INVALID_TERM_RANGE = "Minimum term cannot be greater than maximum term";
    }

    public static class LoanApplication {
        public static final String NOT_FOUND = "Loan application not found";
        public static final String INVALID_AMOUNT = "Invalid loan amount";
        public static final String INVALID_TERM = "Invalid loan term";
        public static final String INVALID_CUSTOMER_TYPE = "Customer type does not match loan type";
        public static final String INSUFFICIENT_CREDIT_SCORE = "Insufficient credit score";
        public static final String EXCEEDS_DEBT_TO_INCOME_RATIO = "Debt to income ratio exceeds maximum limit";
        public static final String INSUFFICIENT_BUSINESS_HISTORY = "Insufficient years in business";
        public static final String INSUFFICIENT_ANNUAL_REVENUE = "Insufficient annual revenue";
        public static final String ALREADY_PROCESSED = "Loan application already processed";
    }

    public static final class User {
        private User() {}

        public static final String NOT_FOUND = "User not found";
        public static final String NOT_FOUND_BY_ID = "User not found with id: %d";
        public static final String NOT_FOUND_BY_USERNAME = "User not found with username: %s";
        public static final String INCORRECT_PASSWORD = "Current password is incorrect";
    }

    public static final class Auth {
        private Auth() {}

        public static final String USERNAME_TAKEN = "Username is already taken";
        public static final String EMAIL_TAKEN = "Email is already in use";
        public static final String DEFAULT_ROLE_NOT_FOUND = "Default role not found";
        public static final String INVALID_PASSWORD = "Password must be at least 6 characters";
        public static final String INVALID_EMAIL = "Invalid email format";
    }
} 