package com.patientMicroservice.controller;

import com.patientMicroservice.exception.PatientNotFoundException;
import com.patientMicroservice.model.Patient;
import com.patientMicroservice.service.PatientServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * API REST expose endpoints for CRUD opération for patient
 * @author Gavillot Karl
 * @version 1.0
 */
@Validated
@RestController
public class PatientController {

    private static  final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientServiceImpl patientService;

    /**
     * Get all patient
     * @return list of all patients
     */
    @ApiOperation(value = "Get all patients")
    @GetMapping("/patient/list")
    public List<Patient> listAllPatients() {
        logger.info("GET the list of all patients");
        return patientService.listAllPatients();
    }

    /**
     * Get patient's data by id
     * @param id use to display datas from this patient
     * @return patient datas
     */
    @ApiOperation(value = "Get one patient by id")
    @GetMapping("/patient/{id}")
    public Optional<Patient> getByIdPatient(@PathVariable("id") Integer id) {
        logger.info("GET one patient by id");
        return patientService.findByIdPatient(id);
    }

    /**
     * Post save or update patient's datas
     * @param patient we went to edit or create
     * @return new patient or update patient
     */
    @ApiOperation(value = "Save update patient")
    @PostMapping("/patient/add")
    public Patient addPatient(@RequestBody Patient patient) {
        logger.info("POST add one patient");
        return patientService.addPatient(patient);
    }

    /**
     * Display edit form for patient
     * @param id use to get data from patient to edit
     * @param patientId
     * @param model
     * @throws PatientNotFoundException exception if patient not found
     */
    @ApiOperation(value = "Display edit form for patient")
    @PostMapping("/patient/showEditForm/{id}")
    public void showEditForm(@PathVariable Integer id,Integer patientId, Model model) throws PatientNotFoundException {
        logger.info("POST one patient by is ID");
        Patient patient = patientService.showEditForm(id);
        model.addAttribute("patient", patient);
    }

    /**
     * Delete patient by id
     * @param id patient to delete
     * @throws PatientNotFoundException exception if patient not found
     * @throws ChangeSetPersister.NotFoundException
     */
    @ApiOperation(value = "Delete patient by id")
    @GetMapping("/patient/delete/{id}")
    public void deleteByIdPatient(@PathVariable ("id") Integer id) throws PatientNotFoundException, ChangeSetPersister.NotFoundException {
        logger.info("GET delete patient by id");
        patientService.deletePatient(id);
    }

}
