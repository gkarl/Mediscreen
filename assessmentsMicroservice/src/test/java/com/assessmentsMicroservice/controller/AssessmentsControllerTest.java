package com.assessmentsMicroservice.controller;

import com.assessmentsMicroservice.beans.PatientBean;
import com.assessmentsMicroservice.service.AssessmentsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;

@WebMvcTest(AssessmentsController.class)
@ExtendWith(SpringExtension.class)
public class AssessmentsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AssessmentsService assessmentsService;

    @Test
    @DisplayName("Test getAgePatient")
    public void getAgePatientTest() throws  Exception {
        PatientBean patient3 = new PatientBean(3, "Gavillot", "Karl", LocalDate.of(1970, 04, 16), 'M', "10 Route de la Reine", "0677777777");
        mockMvc.perform(get("/assessments/age/" + patient3.getId())).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test getAssementsTrigger")
    public void getAssementsTriggerTest() throws Exception {
        PatientBean patient3 = new PatientBean(3, "Gavillot", "Karl", LocalDate.of(1970, 04, 16), 'M', "10 Route de la Reine", "0677777777");
        mockMvc.perform(get("/assessments/triggers/"+ patient3.getId())).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test getAssessmentsLevelOfRisk")
    public void getAssessmentsLevelOfRiskTest() throws Exception {
        PatientBean patient3 = new PatientBean(3, "Gavillot", "Karl", LocalDate.of(1970, 04, 16), 'M', "10 Route de la Reine", "0677777777");
        mockMvc.perform(get("/assessments/"+ patient3.getId())).andExpect(status().isOk());
    }

}
