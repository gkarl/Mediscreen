package com.noteMicroservice.service;

import com.noteMicroservice.model.Note;
import com.noteMicroservice.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> findNotesByPatientId(Integer patientId) {
        logger.info("Service find notes for patient whith id " + patientId);
        return noteRepository.findAllNotesByPatientId(patientId);
    }

    public Optional<Note> findByIdNote(String id) {
        logger.info("Service return one note by id");
        return noteRepository.findById(id);
    }

    public Note addNote(Note note) {
        logger.info("Service add a note to one patient");
        return noteRepository.save(note);
    }

    public Optional<Note> showEditFormNote(String id, Integer patientId) {
        logger.info("Service note edit form");
        Optional<Note> note = noteRepository.findById(id);
        return Optional.of(note.get());
    }

    public void deleteNote(String id) {
        logger.info("Service delete note bu id");
        /*Optional<Note> note = noteRepository.findById(id);*/
        noteRepository.deleteById(id);
    }

}
