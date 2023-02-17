package com.noteMicroservice.controller;

import com.noteMicroservice.model.Note;
import com.noteMicroservice.service.NoteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {NoteController.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = NoteController.class)
public class NoteControllerTest {

    @Autowired
    private NoteController noteController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Test
    @DisplayName("Test listNoteByPatientId")
    public void listNoteByPatientIdTest() throws Exception {
        Note note1 = new Note("1", 1, LocalDate.now(), "test1");
        mockMvc.perform(get("/note/list/1")).andExpect(status().isOk());
        verify(noteService, times(1)).findNotesByPatientId(1);
    }

    @Test
    @DisplayName("Test getNoteById")
    public void getNoteByIdTest() throws Exception {
        Note note1 = new Note("1", 1, LocalDate.now(), "test1");
        mockMvc.perform(get("/note/1")).andExpect(status().isOk());
        verify(noteService, times(1)).findByIdNote("1");
    }

    @Test
    @DisplayName("Test addNote")
    public void addNoteTest() throws Exception {
        Note note1 = new Note();
        when(noteService.addNote(any(Note.class))).thenReturn(note1);
        mockMvc.perform(post("/note/add").contentType(MediaType.APPLICATION_JSON)
                .sessionAttr("note", note1)
                .content("{ \"id\":\"1\", \"patientId\":\"1\", \"date\":\"2023-01-09\", \"recommendation\":\"test\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test showEditNoteForm")
    public void showEditNoteFormTest() throws Exception {
        Note note1 = new Note("1", 1, LocalDate.now(), "test1");
        when(noteService.findByIdNote(any(String.class))).thenReturn(note1);
        mockMvc.perform(get("/note/showEditForm/{id}/{patientId}", 1, 1).sessionAttr("note", note1)
                .param("id", "1")
                .param("patientId", "1")
                .param("recommendation", "test1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test DeleteNote")
    public  void  DeleteNoteTest() throws Exception {
        Note note1 = new Note("1", 1, LocalDate.now(), "test1");
        mockMvc.perform(get("/note/delete/1")).andExpect(status().isOk());
    }
}


