package com.talentboozt.edu_service.domains.edu.exception;

import org.springframework.http.HttpStatus;

public class EduInvalidCredentialsException extends EduBaseException {
    public EduInvalidCredentialsException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS");
    }
}
