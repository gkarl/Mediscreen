package com.frontMediscreen.frontMediscreen.controller;

import com.frontMediscreen.frontMediscreen.beans.NoteBean;
import com.frontMediscreen.frontMediscreen.beans.PatientBean;
import com.frontMediscreen.frontMediscreen.exception.NotFoundException;
import com.frontMediscreen.frontMediscreen.proxies.NoteProxy;
import com.frontMediscreen.frontMediscreen.proxies.PatientProxy;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class NoteController {

    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteProxy noteProxy;

    @Autowired
    private PatientProxy patientProxy;


    @ApiOperation(value = "List all notes for one patient id")
    @GetMapping("/note/list/{patientId}")
    public String listNoteByPatient(@PathVariable("patientId") Integer patientId, Model model) {
        logger.info("Get list note by patient id");
        Optional<PatientBean> patient = patientProxy.getByIdPatient(patientId);
        List<NoteBean> noteListe = noteProxy.listNoteByPatientId(patientId);
        model.addAttribute("patientName", patient.get().getFirstName() + " " + patient.get().getLastName());
        model.addAttribute("patientId", patient.get().getId());
        model.addAttribute("date", LocalDateTime.now());
        model.addAttribute("patient", patient);
        model.addAttribute("notes", noteListe);
        return "note/listNote";
    }

    @ApiOperation(value = "Display form when add new note for a patient")
    @GetMapping("/note/newForm/{PatientId}")
    public String showNewFormNote(@PathVariable("PatientId") Integer patientId, NoteBean note, Model model) {
        logger.info("New form note form front");
        Optional<PatientBean> patient = patientProxy.getByIdPatient(patientId);
        note.setPatientId(patientId);
        note.setDate(LocalDate.now());
        model.addAttribute("patientName", patient.get().getFirstName() + " " + patient.get().getLastName());
        model.addAttribute("patientId", patient.get().getId());
        model.addAttribute("note", new NoteBean());
        model.addAttribute("pageTitle", "Add New Note");
        return "note/note_form";
    }

    @ApiOperation(value = "Save update patient's note from from")
    @PostMapping("/note/add/")
    public String addNote(Integer patientId, @ModelAttribute("note") NoteBean note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("ERROR, for create new note");
            return "/patient/list/";
        } else {
            logger.info("Add new note for patient front");
            Optional<PatientBean> patient = patientProxy.getByIdPatient(patientId);
            model.addAttribute("patientId", patient.get().getId());
            model.addAttribute("note", note);
            note.setPatientId(patient.get().getId());
            note.setDate(LocalDate.now());
            note.setRecommendation(note.getRecommendation());
            noteProxy.addNote(note);
            return "redirect:/patient/list/" ;
        }
    }

    @ApiOperation(value = "Display edit form for one note")
    @GetMapping("/note/showEditForm/{id}/{patientId}")
    public String showEditNoteForm(@PathVariable("id") String id,@PathVariable Integer patientId, NoteBean note, Model model, RedirectAttributes redirectAttributes) {
        logger.info("GET edit note form");
        try {
            noteProxy.showEditNoteForm(id, patientId);
            Optional<PatientBean> patient = patientProxy.getByIdPatient(patientId);
            note.setPatientId(patientId);
            note.setDate(LocalDate.now());
            model.addAttribute("patientName", patient.get().getFirstName() + " " + patient.get().getLastName());
            model.addAttribute("patientId", patient.get().getId());
            Optional<NoteBean> note1 = Optional.ofNullable(noteProxy.getNoteById(id));
            model.addAttribute("note", note1);
            model.addAttribute("pageTitle", "Update New Note");
            return "note/note_edit";
        }   catch (NotFoundException e) {
        redirectAttributes.addFlashAttribute("message",  e.getMessage());
        return "redirect:/patient/list";
        }
    }

    @ApiOperation(value = "Delete one note by id")
    @GetMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable String id, RedirectAttributes redirectAttributes) {
        logger.info("GET delete note by id");
        noteProxy.deleteNote(id);
        return "redirect:/patient/list";
    }

}
