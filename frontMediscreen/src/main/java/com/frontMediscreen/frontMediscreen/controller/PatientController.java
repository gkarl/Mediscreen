package com.frontMediscreen.frontMediscreen.controller;

import com.frontMediscreen.frontMediscreen.beans.PatientBean;
import com.frontMediscreen.frontMediscreen.proxies.PatientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientProxy patientProxy;

    @GetMapping("/patient/list")
    public String listAllPatients(Model model) {
        logger.info("Get patient list from frontMicroservice");
        List<PatientBean> listPatient = patientProxy.listAllPatients();
        model.addAttribute("patients", listPatient);
        return "patient/listPatient";
    }

    @GetMapping("/patient/newForm")
    public String showNewForm(Model model) {
        model.addAttribute("patient", new PatientBean());
        model.addAttribute("pageTitle", "Add New Patient");
        return "patient/patient_form";
    }

    @PostMapping("/patient/add")
    public String addPatient(PatientBean patient, RedirectAttributes redirectAttributes) {
        patientProxy.addPatient(patient);
        return "redirect:/patient/list";
    }


}
