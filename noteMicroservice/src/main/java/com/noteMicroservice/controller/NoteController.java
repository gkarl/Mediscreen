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

/**
 * API REST expose endpoints for CRUD opération for patient's notes
 * @author Gavillot Karl
 * @version 1.0
 */
@Validated
@RestController
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    /**
     * Get all notes list
     * @param patientId patient id use to get all it's notes
     * @return all list notes from patient id
     */
    @ApiOperation(value = "Get all notes")
    @GetMapping("/note/list/{patientId}")
    public List<Note> listNoteByPatientId(@PathVariable ("patientId") Integer patientId) {
        logger.info("Get all note for one patient by it's id");
        return noteService.findNotesByPatientId(patientId);
    }

    /**
     * Get one note by it's id
     * @param id note id
     * @return note by id
     */
    @ApiOperation(value = "Get one note by it's id")
    @GetMapping("/note/{id}")
    public Note getNoteById(@PathVariable("id") String id) {
        logger.info("Get one note by id");
        return noteService.findByIdNote(id);
    }

    /**
     * Save or update note
     * @param note datas use to create or update
     * @return new or update note
     */
    @ApiOperation(value = "Save update note")
    @PostMapping("/note/add")
    public Note addNote(@Validated @RequestBody Note note) {
        logger.info("POST add note to a patient");
        return noteService.addNote(note);
    }

    /**
     * Display edit form note whith old datas
     * @param id note id
     * @param patientId patient id
     * @param model front
     */
    @ApiOperation(value = "Display edit form note")
    @GetMapping("/note/showEditForm/{id}/{patientId}")
    public void showEditNoteForm(@PathVariable("id") String id,@PathVariable Integer patientId, Model model) {
        logger.info("GET update note form");
        Optional<Note> note = noteService.showEditFormNote(id, patientId);
        model.addAttribute("note", note);
    }

    /**
     * Delete note by id
     * @param id note to delete
     */
    @ApiOperation(value = "Delete note")
    @GetMapping("/note/delete/{id}")
    public void deleteNote(@PathVariable ("id") String id) {
        noteService.deleteNote(id);
    }


}
