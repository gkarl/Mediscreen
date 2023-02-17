package com.noteMicroservice.controller;

import com.noteMicroservice.model.Note;

import com.noteMicroservice.service.NoteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @ApiOperation(value = "Get all notes")
    @GetMapping("/note/list/{patientId}")
    public List<Note> listNoteByPatientId(@PathVariable ("patientId") Integer patientId) {
        logger.info("Get all note for one patient by it's id");
        return noteService.findNotesByPatientId(patientId);
    }

    @ApiOperation(value = "Get one note by it's id")
    @GetMapping("/note/{id}")
    public Note getNoteById(@PathVariable("id") String id) {
        logger.info("Get one note by id");
        return noteService.findByIdNote(id);
    }

    @ApiOperation(value = "Save update note")
    @PostMapping("/note/add")
    public Note addNote(@Validated @RequestBody Note note) {
        logger.info("POST add note to a patient");
        return noteService.addNote(note);
    }

    @ApiOperation(value = "Display edit form note")
    @GetMapping("/note/showEditForm/{id}/{patientId}")
    public void showEditNoteForm(@PathVariable("id") String id,@PathVariable Integer patientId, Model model) {
        logger.info("GET update note form");
        Optional<Note> note = noteService.showEditFormNote(id, patientId);
        model.addAttribute("note", note);
    }

    @ApiOperation(value = "Delete note")
    @GetMapping("/note/delete/{id}")
    public void deleteNote(@PathVariable ("id") String id) {
        noteService.deleteNote(id);
    }


}
