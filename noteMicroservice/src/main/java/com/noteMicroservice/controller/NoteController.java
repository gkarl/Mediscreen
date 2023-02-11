package com.noteMicroservice.controller;

import com.noteMicroservice.model.Note;

import com.noteMicroservice.service.NoteService;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @GetMapping("/note/list/{patientId}")
    public List<Note> listNoteByPatientId(@PathVariable ("patientId") Integer patientId) {
        logger.info("Get all note for one patient by it's id");
        return noteService.findNotesByPatientId(patientId);
    }

    @GetMapping("/note/{id}")
    public Note getNoteById(@PathVariable("id") String id) {
        logger.info("Get one note by id");
        return noteService.findByIdNote(id);
    }

    @PostMapping("/note/add")
    public Note addNote(@Validated @RequestBody Note note) {
        logger.info("POST add note to a patient");
        return noteService.addNote(note);
    }

    @GetMapping("/note/showEditForm/{id}/{patientId")
    public void showEditNoteForm(@PathVariable("id") String id, Integer patientId, Model model) {
        logger.info("GET update note form");
        Note note = noteService.findByIdNote(id);
        model.addAttribute("note", note);
    }

    @PostMapping("/note/update/{id}")
    public Note updateNote(@PathVariable ("id") String id, @Valid @RequestBody Note note) {
        logger.info("POST update note");
        note.setId(id);
        return noteService.updateNote(id, note);
    }


}
