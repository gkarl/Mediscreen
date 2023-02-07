package com.frontMediscreen.frontMediscreen.controller;

import com.frontMediscreen.frontMediscreen.beans.PatientBean;
import com.frontMediscreen.frontMediscreen.exception.NotFoundException;
import com.frontMediscreen.frontMediscreen.proxies.PatientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        logger.info("Get patient list front");
        List<PatientBean> listPatient = patientProxy.listAllPatients();
        model.addAttribute("patients", listPatient);
        return "patient/listPatient";
    }

    @GetMapping("/patient/newForm")
    public String showNewForm(Model model) {
        logger.info("New patient form front");
        model.addAttribute("patient", new PatientBean());
        model.addAttribute("pageTitle", "Add New Patient");
        return "patient/patient_form";
    }

    @PostMapping("/patient/add")
    public String addPatient(PatientBean patient, RedirectAttributes redirectAttributes) {
        logger.info("Add new patient Front");
        patientProxy.addPatient(patient);
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/showEditForm/{id}")
    public String showEditForm(@PathVariable("id") Integer id, PatientBean patient, Model model, RedirectAttributes redirectAttributes) {
        logger.info("Show Edit Form front");
        try {
            patientProxy.showEditForm(id);
            Optional<PatientBean> patientB = patientProxy.getByIdPatient(id);
            model.addAttribute("patient", patientB);
            return "patient/patient_form_edit"; // template html
        }  catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message",  e.getMessage());
            return "redirect:/patient/list";
        }
    }

    @GetMapping("/patient/delete/{id}")
    public String deleteById(@PathVariable ("id") Integer id, RedirectAttributes redirectAttributes) {
        logger.info("delete patient front");
        try {
            patientProxy.deleteByIdPatient(id);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/patient/list";
    }


}
