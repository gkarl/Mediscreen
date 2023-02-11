package com.noteMicroservice.service;

import com.noteMicroservice.model.Note;
import com.noteMicroservice.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import java.util.List;

@Service
public class NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> findNotesByPatientId(Integer patientId) {
        logger.info("Service find notes for patient whith id " + patientId);
        return noteRepository.findAllNotesByPatientId(patientId);
    }

    public Note findByIdNote(String id) {
        logger.info("Service return one note by id");
        return noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Note No found"));
    }

    public Note addNote(Note note) {
        logger.info("Service add a note to one patient");
        return noteRepository.insert(note);
    }

    public Note showEditFormNote(String id, Integer patientId, Model model) {
        logger.info("Service note edit form");
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Note No found"));
        model.addAttribute("note", note);
        return note;
    }

    public Note updateNote(String id, Note note) {
        logger.info("Service update note");
        return noteRepository.save(note);
    }

}
