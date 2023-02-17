package com.noteMicroservice.service;

import com.noteMicroservice.model.Note;
import com.noteMicroservice.repository.NoteRepository;
import jdk.dynalink.linker.LinkerServices;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {NoteService.class})
@ExtendWith(SpringExtension.class)
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @MockBean
    private NoteRepository noteRepository;

    @Test
    @DisplayName("Test findByIdNote")
    public  void findByIdNoteTest() throws Exception {
        Note note1 = new Note("1", 1, LocalDate.now(), "test1");
        when(noteRepository.findById(any(String.class))).thenReturn(Optional.of(note1));
        Note note2 = noteService.findByIdNote("1");
        assertEquals(note1, note2);
    }

    @Test
    @DisplayName("Test findNotesByPatientId")
    public void findNotesByPatientId() throws Exception {
        Note note1 = new Note("1", 1, LocalDate.now(), "test1");
        Note note2 = new Note("2", 1, LocalDate.now(), "test2");
        List<Note> listNote = new ArrayList<Note>();
        listNote.add(note1);
        listNote.add(note2);
        when(noteRepository.findAllNotesByPatientId(1)).thenReturn(listNote);
        noteService.findNotesByPatientId(1);
        verify(noteRepository, times(1)).findAllNotesByPatientId(1);
    }

    @Test
    @DisplayName("Test addNote")
    public void addNoteTest() throws Exception {
        Note note1 = new Note("1", 1, LocalDate.now(), "test1");
        when(noteRepository.insert(any(Note.class))).thenReturn(note1);
        noteService.addNote(note1);
        verify(noteRepository, times(1)).save(note1);
    }

    @Test
    @DisplayName("Test showEditForm")
    public void showEditFormTest() throws Exception {
        Note note1 = new Note("1", 1, LocalDate.now(), "test1");
        when(noteRepository.findById("1")).thenReturn(Optional.of(note1));
        noteService.showEditFormNote("1", 1);
        verify(noteRepository).findById("1");
    }

    @Test
    @DisplayName("Test deleteNote")
    public void deleteNoteTest() throws Exception {
        String noteId = "11";
        noteService.deleteNote(noteId);
        Optional<Note> optionalPatient = noteRepository.findById(noteId);
        Assertions.assertThat(optionalPatient).isNotPresent();
    }

}
