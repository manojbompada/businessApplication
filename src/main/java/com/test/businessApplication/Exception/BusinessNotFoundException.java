package com.test.businessApplication.Exception;

public class BusinessNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Invalid BusinessId, please check it again!";

    public BusinessNotFoundException() {
        super(MESSAGE);
    }
    
    public BusinessNotFoundException(Long businessId) {
        super(MESSAGE+"\nBusinessId:"+businessId);
    }
}
