package com.banking.business.constants;

public final class CustomerConstants {
    private CustomerConstants() {
        throw new IllegalStateException("Constants class");
    }

    // Validation Messages
    public static final String CUSTOMER_NOT_FOUND = "Customer not found";
    public static final String INDIVIDUAL_CUSTOMER_NOT_FOUND = "Individual customer not found";
    public static final String CORPORATE_CUSTOMER_NOT_FOUND = "Corporate customer not found";
    
    // Business Rules
    public static final int MINIMUM_INDIVIDUAL_AGE = 18;
    public static final int MINIMUM_COMPANY_AGE = 2;
    public static final double MINIMUM_ANNUAL_REVENUE = 1_000_000.0;
    
    // Customer Number Format
    public static final String INDIVIDUAL_CUSTOMER_PREFIX = "I";
    public static final String CORPORATE_CUSTOMER_PREFIX = "C";
    public static final int CUSTOMER_NUMBER_LENGTH = 8;
    
    // Credit Score Ranges
    public static final int MIN_CREDIT_SCORE = 0;
    public static final int MAX_CREDIT_SCORE = 1900;
    public static final int GOOD_CREDIT_SCORE = 1000;
    
    // Credit Rating Values
    public static final String CREDIT_RATING_A = "A";
    public static final String CREDIT_RATING_B = "B";
    public static final String CREDIT_RATING_C = "C";
    public static final String CREDIT_RATING_D = "D";
} 