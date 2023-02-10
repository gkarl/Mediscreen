package com.frontMediscreen.frontMediscreen.controller;

import com.frontMediscreen.frontMediscreen.beans.NoteBean;
import com.frontMediscreen.frontMediscreen.beans.PatientBean;
import com.frontMediscreen.frontMediscreen.proxies.NoteProxy;
import com.frontMediscreen.frontMediscreen.proxies.PatientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/note/list/{patientId}")
    public String listNoteByPatient(@PathVariable("patientId") Integer patientId, Model model) {
        logger.info("Get list note by patient id");
        Optional<PatientBean> patient = patientProxy.getByIdPatient(patientId);
        List<NoteBean> noteListe = noteProxy.listNoteByPatientId(patientId);
        model.addAttribute("patientName", patient.get().getFirstName() + " " + patient.get().getLastName());
        model.addAttribute("date", LocalDateTime.now());
        model.addAttribute("patient", patient);
        model.addAttribute("notes", noteListe);
        return "note/listNote";
    }

}
