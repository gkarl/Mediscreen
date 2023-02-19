package com.patientMicroservice.service;

import com.patientMicroservice.controller.PatientController;
import com.patientMicroservice.exception.PatientNotFoundException;
import com.patientMicroservice.model.Patient;
import com.patientMicroservice.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class service operation CRUD on patient
 * @author Gavillot Karl
 * @version 1.0
 */
@Service
public class PatientServiceImpl {

    private static  final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Return all patient
     * @return patient list
     */
    public List<Patient> listAllPatients() {
        logger.info("Service return list all patients");
        return (List<Patient>) patientRepository.findAll();
    }

    /**
     * Return a patient by id
     * @param id of a patient
     * @return a patient from a id
     */
    public Optional<Patient> findByIdPatient(Integer id) {
        logger.info("Service get patient by id");
        return patientRepository.findById(id);
    }

    /**
     * Save a new or update patient
     * @param patient the patient we choose to create or update
     * @return a patient create or update
     */
    public Patient addPatient(Patient patient) {
        logger.info("Service add a new patient");
        return patientRepository.save(patient);
    }

    /**
     * Use to get form fill whith the data from patient to edit
     * @param id from the editing patient
     * @return patient data
     * @throws PatientNotFoundException exception if patient not found
     */
    public Patient showEditForm(Integer id) throws PatientNotFoundException {
        logger.info("Service patient edit form ");
        Optional<Patient> result = patientRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new PatientNotFoundException("Could not find any user whith ID " + id);
    }

    /**
     * Delete a patient by id
     * @param id patient use to delete them
     * @throws ChangeSetPersister.NotFoundException
     */
    public void deletePatient(Integer id) throws ChangeSetPersister.NotFoundException {
        patientRepository.deleteById(id);
    }


}
