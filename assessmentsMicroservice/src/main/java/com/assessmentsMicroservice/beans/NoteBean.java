package com.assessmentsMicroservice.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class NoteBean {

    private String id;

    private Integer patientId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String recommendation;

    public NoteBean() {
    }

    public NoteBean(String id, Integer patientId, LocalDate date, String recommendation) {
        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.recommendation = recommendation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
