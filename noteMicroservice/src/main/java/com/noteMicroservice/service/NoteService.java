package com.noteMicroservice.service;

import com.noteMicroservice.model.Note;
import com.noteMicroservice.repository.NoteRepository;
import jdk.dynalink.linker.LinkerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    private NoteRepository noteRepository;

    public Note findByIdNote(String id) {
        logger.info("Service return one note by id");
        return noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Note No found"));
    }

    public List<Note> findNotesByPatientId(Integer patientId) {
        logger.info("Service find notes for patient whith id " + patientId);
        return noteRepository.findAllNotesByPatientId(patientId);
    }

}
