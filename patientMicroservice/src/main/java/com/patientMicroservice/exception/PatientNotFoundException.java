package com.patientMicroservice.exception;

public class PatientNotFoundException extends Throwable{

    public PatientNotFoundException(String message) {
        super(message);
    }
}
