package com.frontMediscreen.frontMediscreen;

import com.frontMediscreen.frontMediscreen.beans.PatientBean;
import com.frontMediscreen.frontMediscreen.controller.PatientController;
import com.frontMediscreen.frontMediscreen.proxies.PatientProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {PatientController.class})
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientProxy patientProxy;

    @Test
    @DisplayName("Test listAllPatients")
    public void listAllPatientsTest() throws Exception {
        mockMvc.perform(get("/patient/list")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test showNewForm")
    public void showNewForm() throws Exception {
        mockMvc.perform(get("/patient/newForm")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test addPatient")
    public void addPatientTest() throws Exception {
        PatientBean patient = new PatientBean();
        when(patientProxy.addPatient(any(PatientBean.class))).thenReturn(patient);
        mockMvc.perform(post("/patient/add").sessionAttr("patient", patient)
                .param("firstName", "Gavillot")
                .param("lastName", "Karl")
                .param("dob", String.valueOf(LocalDate.of(1900, 02, 16)))
                .param("sex", "M")
                .param("address", "2 Route de la Reine")
                .param("phone", "0677777777"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("redirect:/patient/list"));
    }

    @Test
    @DisplayName("Test showEditForm")
    public void showEditFormTest() throws Exception {
        PatientBean patient = new PatientBean(11, "Gavillot", "Karl", LocalDate.of(1900, 02, 16), 'M', "2 Route de la Reine", "0677777778");
        when(patientProxy.getByIdPatient(anyInt())).thenReturn(Optional.of(patient));
        mockMvc.perform(get("/patient/showEditForm/{id}", 11)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test deleteById")
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(get("/patient/delete/11")).andExpect(status().isFound());
    }


}
