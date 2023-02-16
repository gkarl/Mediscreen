package com.assessmentsMicroservice.model;

public enum Assessments {

    NONE ("None"),
    BORDERLINE ("Borderline"),
    IN_DANGER ("In-Danger"),
    EARLY_ONSET ("Early-onset");

    private final String assessments;

    Assessments(String assessments) {
        this.assessments = assessments;
    }

    public String getAssessments() {
        return assessments;
    }
}
