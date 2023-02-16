package com.patientMicroservice.controller;



import com.patientMicroservice.model.Patient;
import com.patientMicroservice.service.PatientServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Autowired
    private PatientServiceImpl patientService;

    @Test
    @DisplayName("Test findAllPatient")
    public void findAllPatientTest() throws Exception{
        Patient patient = new Patient();
        List<Patient> listPatient = new ArrayList<>();
        patient.setFirstName("Karl");
        patient.setLastName("Gavillot");
        patient.setDob(LocalDate.of(1970, 04, 16));
        patient.setSex('M');
        patient.setAddress("2 Route de la Reine");
        patient.setPhone("0677777777");

        listPatient.add(patient);
        when(patientService.listAllPatients()).thenReturn(listPatient);
        mockMvc.perform(get("/patient/list")).andExpect(status().isOk());
        verify(patientService, times(1)).listAllPatients();
    }

    @Test
    @DisplayName(" Test getByIdPatient patient")
    public void getByIdPatient() throws Exception {
        mockMvc.perform(get("/patient/1")).andExpect(status().isOk());
    }

    @Test
    @DisplayName(" Test get addPatient")
    public void AddPatientTest() throws Exception {
        mockMvc.perform(post("/patient/add")
                .content("{ \"family\":\"Gavillot\", \"given\":\"Karl\", \"dob\":\"1970-04-16\", \"sex\":\"M\", \"address\":\"2 Route de la Reine\", \"phone\":\"0677777777\" }")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


    @Test
    @DisplayName(" Test deleteByIdPatient")
    public void deleteByIdPatientTest() throws Exception {
        mockMvc.perform(get("/patient/delete/1")).andExpect(status().isOk());
    }



}
