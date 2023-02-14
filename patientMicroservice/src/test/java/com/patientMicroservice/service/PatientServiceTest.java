package com.patientMicroservice.service;

import com.patientMicroservice.model.Patient;
import com.patientMicroservice.repository.PatientRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void addNewPatienttest() {
        Patient patient = new Patient();
        patient.setFirstName("Karl");
        patient.setLastName("Gavillot");
        patient.setDob(LocalDate.of(1900, 02, 16));
        patient.setSex('M');
        patient.setAddress("2 Route de la Reine");
        patient.setPhone("0677777777");

        Patient savedPatient = patientRepository.save(patient);

        Assertions.assertThat(savedPatient).isNotNull();
        Assertions.assertThat(savedPatient.getId()).isGreaterThan(0);
    }

    @Test
    public void listAllPatientTest() {
        Iterable<Patient> patients = patientRepository.findAll();
        Assertions.assertThat(patients).hasSizeGreaterThan(0);

        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    @Test
    public void getPatientTest() {
        Integer patientId = 11;
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        Assertions.assertThat(optionalPatient).isPresent();
        System.out.println(optionalPatient.get());
    }

    @Test
    public void deletePatientTest() {
        Integer patientId = 11;
        patientRepository.deleteById(patientId);

        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        Assertions.assertThat(optionalPatient).isNotPresent();
    }

}
