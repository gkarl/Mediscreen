package com.frontMediscreen.frontMediscreen.controller;

import com.frontMediscreen.frontMediscreen.beans.PatientBean;
import com.frontMediscreen.frontMediscreen.exception.NotFoundException;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Datas use for patients traitements expose in front end
 * @author Gavillot Karl
 * @version 1.0
 */
@Controller
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientProxy patientProxy;

    /**
     * Get List all patients
     * @param model get datas in front
     * @return html page to display all patients list
     */
    @ApiOperation(value = "List all patients")
    @GetMapping("/patient/list")
    public String listAllPatients(Model model) {
        logger.info("Get patient list front");
        List<PatientBean> listPatient = patientProxy.listAllPatients();
        model.addAttribute("patients", listPatient);
        return "patient/listPatient";
    }

    /**
     * Display form for create a new patient
     * @param model get datas in front
     * @return html form
     */
    @ApiOperation(value = "Display form when add new patient")
    @GetMapping("/patient/newForm")
    public String showNewForm(Model model) {
        logger.info("New patient form front");
        model.addAttribute("patient", new PatientBean());
        model.addAttribute("pageTitle", "Add New Patient");
        return "patient/patient_form";
    }

    /**
     * Post Save or update patient
     * @param patient
     * @param redirectAttributes
     * @return redirect html page after create new or edit patient
     */
    @ApiOperation(value = "Save update patient from from")
    @PostMapping("/patient/add")
    public String addPatient(PatientBean patient, RedirectAttributes redirectAttributes) {
        logger.info("Add new patient Front");
        patientProxy.addPatient(patient);
        return "redirect:/patient/list";
    }

    /**
     * Get form whith patient'data we went to edit
     * @param id patient id
     * @param patient datas to edit
     * @param model get datas in front
     * @param redirectAttributes
     * @return redirect html page
     */
    @ApiOperation(value = "Display edit form for one patient")
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

    /**
     * Delete one patient by id
     * @param id patient
     * @param redirectAttributes
     * @return html page after delete patient
     */
    @ApiOperation(value = "Delete one patient by id")
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
