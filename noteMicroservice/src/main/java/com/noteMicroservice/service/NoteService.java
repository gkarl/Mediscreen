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

/**
 * Service CRUD opération on note model
 * @author Gavillot Karl
 * @version 1.0
 */
@Service
public class NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    private NoteRepository noteRepository;

    /**
     * Return all notes for patient
     * @param patientId Id of patient to get all it's notes
     * @return all notes for one patient
     */
    public List<Note> findNotesByPatientId(Integer patientId) {
        logger.info("Service find notes for patient whith id " + patientId);
        return noteRepository.findAllNotesByPatientId(patientId);
    }

    /**
     * Return one note by id
     * @param id to get a note from a patient
     * @return one note
     */
    public Note findByIdNote(String id) {
        logger.info("Service return one note by id");
        return noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Note note found"));
    }

    /**
     * Create or Update one note
     * @param note data to create or update
     * @return new note or update note
     */
    public Note addNote(Note note) {
        logger.info("Service add a note to one patient");
        return noteRepository.save(note);
    }

    /**
     * Return form whith note's datas to edit
     * @param id note id
     * @param patientId patient id
     * @return data use in edit form
     */
    public Optional<Note> showEditFormNote(String id, Integer patientId) {
        logger.info("Service note edit form");
        Optional<Note> note = noteRepository.findById(id);
        return Optional.of(note.get());
    }

    /**
     * Delete note by id
     * @param id note to delete
     */
    public void deleteNote(String id) {
        logger.info("Service delete note bu id");
        /*Optional<Note> note = noteRepository.findById(id);*/
        noteRepository.deleteById(id);
    }

}
