package com.frontMediscreen.frontMediscreen.controller;

import com.frontMediscreen.frontMediscreen.beans.NoteBean;
import com.frontMediscreen.frontMediscreen.beans.PatientBean;
import com.frontMediscreen.frontMediscreen.proxies.AssessmentsProxy;
import com.frontMediscreen.frontMediscreen.proxies.NoteProxy;
import com.frontMediscreen.frontMediscreen.proxies.PatientProxy;
import org.hamcrest.Matcher;
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
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ContextConfiguration(classes = {NoteController.class})
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientProxy patientProxy;

    @MockBean
    private NoteProxy noteProxy;

    @MockBean
    private AssessmentsProxy assessmentsProxy;

    @Test
    @DisplayName("Test listNoteByPatient")
    public void listNoteByPatientTest() throws Exception {
        NoteBean note1 = new NoteBean("1", 1, LocalDate.now(), "test1");
        when(noteProxy.listNoteByPatientId(any(Integer.class))).thenReturn(new ArrayList<>());
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(new PatientBean()));
        mockMvc.perform(get("/note/list/{patientId}", 1)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test showNewFormNote")
    public void showNewFormNoteTest() throws Exception {
        NoteBean note1 = new NoteBean("1", 1, LocalDate.now(), "test1");
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(new PatientBean()));
        mockMvc.perform(get("/note/newForm/{PatientId}", 1)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test addNote")
    public void addNoteTest() throws Exception {
        PatientBean patient = new PatientBean();
        when(patientProxy.addPatient(any(PatientBean.class))).thenReturn(patient);
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(new PatientBean()));
        NoteBean note1 = new NoteBean();
        when(noteProxy.addNote(any(NoteBean.class))).thenReturn(note1);
        mockMvc.perform(post("/note/add/").sessionAttr("note", note1)
                .param("id", "1")
                .param("patientId", "1")
                .param("date", "2020-10-30")
                .param("recommendation", "test"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("redirect:/patient/list/"));;
    }

    @Test
    @DisplayName("Test showEditNoteForm")
    public void showEditNoteForm() throws Exception {
        NoteBean note1 = new NoteBean("1", 1, LocalDate.now(), "test1");
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(new PatientBean()));
        when(noteProxy.getNoteById(any(String.class))).thenReturn(note1);
        mockMvc.perform(get("/note/showEditForm/{id}/{patientId}", "1", 1)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test deleteNote")
    public void deleteNoteTest() throws Exception {
        mockMvc.perform(get("/note/delete/1")).andExpect(status().isFound());
    }


}
