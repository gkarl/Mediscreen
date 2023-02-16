package com.patientMicroservice.service;

import com.patientMicroservice.exception.PatientNotFoundException;
import com.patientMicroservice.model.Patient;
import com.patientMicroservice.repository.PatientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {PatientServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class PatientsServiceTest {

    @Autowired
    private PatientServiceImpl patientService;

    @MockBean
    private PatientRepository patientRepository;

    @Test
    @DisplayName("Test listAllPatients")
    public void listAllPatientsTest()throws Exception {
        Patient patient1 = new Patient(1, "Pitt", "Brad", LocalDate.of(1995, 04, 16), 'M', "4 Route de la Reine", "0688888888");
        Patient patient2 = new Patient(2, "Marceau", "Sophie", LocalDate.of(1996, 04, 16), 'F', "8 Route de la Reine", "0688888877");
        Patient patient3 = new Patient(3, "Gavillot", "Karl", LocalDate.of(1970, 04, 16), 'M', "10 Route de la Reine", "0677777777");
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient1);
        patientList.add(patient2);
        patientList.add(patient3);
        when(patientRepository.findAll()).thenReturn(patientList);
        patientService.listAllPatients();
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test findByIdPatient")
    public void findByIdPatientTest() throws Exception {
        Patient patient1 = new Patient(1, "Pitt", "Brad", LocalDate.of(1995, 04, 16), 'M', "4 Route de la Reine", "0688888888");
        when(patientRepository.findById(any(Integer.class))).thenReturn(Optional.of(patient1));
        Optional<Patient> patient = patientService.findByIdPatient(patient1.getId());
        assertThat(patient).isNotEmpty().get().satisfies((p -> assertThat(p.getLastName()).isEqualTo(patient1.getLastName())));
    }

    @Test
    @DisplayName("Test addPatient")
    public void addPatientTest() throws Exception {
        Patient patient3 = new Patient(3, "Gavillot", "Karl", LocalDate.of(1970, 04, 16), 'M', "10 Route de la Reine", "0677777777");
        when(patientRepository.save(any(Patient.class))).thenReturn(patient3);
        patientService.addPatient(patient3);
        verify(patientRepository, times(1)).save(patient3);
    }

    @Test
    @DisplayName("Test showEditForm")
    public void showEditFormTest() throws Exception, PatientNotFoundException {
        Patient patient3 = new Patient(3, "Gavillot", "Karl", LocalDate.of(1970, 04, 16), 'M', "10 Route de la Reine", "0677777777");
        when(patientRepository.findById(3)).thenReturn(Optional.of(patient3));
        patientService.showEditForm(3);
        verify(patientRepository).findById(3);
    }

    @Test
    @DisplayName("Test deletePatient")
    public void deletePatientTest() throws Exception{
        Patient patient3 = new Patient(3, "Gavillot", "Karl", LocalDate.of(1970, 04, 16), 'M', "10 Route de la Reine", "0677777777");
        when(patientRepository.save(patient3)).thenReturn(patient3);
        when(patientRepository.findById(3)).thenReturn(Optional.of(patient3));
        patientService.deletePatient(3);
        verify(patientRepository).deleteById(3);
    }


}
