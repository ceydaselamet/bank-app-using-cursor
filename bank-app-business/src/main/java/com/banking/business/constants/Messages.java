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
        public static final String INVALID_AMOUNT = "Loan amount must be between minimum and maximum amount";
        public static final String INVALID_TERM = "Loan term must be between minimum and maximum term";
        public static final String INVALID_CUSTOMER_TYPE = "Customer type does not match loan type";
        public static final String ALREADY_PROCESSED = "Loan application has already been processed";
    }
} 