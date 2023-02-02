package com.patientMicroservice.service;

import com.patientMicroservice.controller.PatientController;
import com.patientMicroservice.exception.PatientNotFoundException;
import com.patientMicroservice.model.Patient;
import com.patientMicroservice.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl {

    private static  final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> listAllPatients() {
        logger.info("Service return list all patients");
        return (List<Patient>) patientRepository.findAll();
    }

    public Optional<Patient> findByIdPatient(Integer id) {
        logger.info("Service get patient by id");
        return patientRepository.findById(id);
    }

    public Patient addPatient(Patient patient) {
        logger.info("Service add a new patient");
        return patientRepository.save(patient);
    }

    public Patient showEditForm(Integer id) throws PatientNotFoundException {
        logger.info("Service patient edit form ");
        Optional<Patient> result = patientRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new PatientNotFoundException("Could not find any user whith ID " + id);
    }

    public void updatePatient(Integer id, Patient patientUpdate) {
        logger.info("Service update patient infos");
        Optional<Patient> patient = patientRepository.findById(id);
        patient.get().setFirstName(patientUpdate.getFirstName());
        patient.get().setLastName(patientUpdate.getLastName());
        patient.get().setDob(patientUpdate.getDob());
        patient.get().setSex(patientUpdate.getSex());
        patient.get().setAddress(patientUpdate.getAddress());
        patient.get().setPhone(patientUpdate.getPhone());
        patientRepository.save(patient.get());
    }

    public void deleteByIdPatient(Integer id) throws PatientNotFoundException {
        logger.info("Service Delete patient");
        Optional<Patient> result = patientRepository.findById(id);
        if (result.isPresent()) {
            patientRepository.deleteById(id);
        }
        throw new PatientNotFoundException("Could not find any user whith ID " + id);
    }


}
