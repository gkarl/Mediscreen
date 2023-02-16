package com.assessmentsMicroservice.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PatientBean {

    private Integer id;

    private String firstName;

    private String lastName;

    @DateTimeFormat( pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private char sex;

    private String address;

    private String phone;

    public PatientBean() {
    }

    public PatientBean(Integer id, String firstName, String lastName, LocalDate dob, char sex, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
