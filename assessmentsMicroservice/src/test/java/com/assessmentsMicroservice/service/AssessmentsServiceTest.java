package com.assessmentsMicroservice.service;

import com.assessmentsMicroservice.beans.NoteBean;
import com.assessmentsMicroservice.beans.PatientBean;
import com.assessmentsMicroservice.model.Assessments;
import com.assessmentsMicroservice.proxies.NoteProxy;
import com.assessmentsMicroservice.proxies.PatientProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AssessmentsService.class})
@ExtendWith(SpringExtension.class)
public class AssessmentsServiceTest {

    @Autowired
    private AssessmentsService assessmentsService;

    @MockBean
    private PatientProxy patientProxy;

    @MockBean
    private NoteProxy noteProxy;

    @Test
    @DisplayName("Test AgePatient")
    public void AgePatientTest() throws Exception {
        PatientBean patient1 = new PatientBean(1, "Gavillot", "Karl", LocalDate.of(1970, 04, 16), 'M', "2 Route de la Reine", "0677777777");
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(patient1));
        assertEquals(52, assessmentsService.agePatient(patient1.getId()));
    }

    @Test
    @DisplayName("Test getAgePatient")
    public void getAgePatientTest() throws Exception {
        PatientBean patient1 = new PatientBean(1, "Gavillot", "Karl", LocalDate.of(1970, 04, 16), 'M', "2 Route de la Reine", "0677777777");
        assertEquals(52, assessmentsService.getAgePatient(patient1.getDob()));
    }

    @Test
    @DisplayName("Test getNumberOfTriggerWords")
    public void getNumberOfTriggerWordsTest() throws Exception {
        NoteBean note1 = new NoteBean("1", 1, LocalDate.now(), "taille");
        NoteBean note2 = new NoteBean("2", 1, LocalDate.now(), "poids");
        List<NoteBean> noteList = new ArrayList<NoteBean>();
        noteList.add(note1);
        noteList.add(note2);
        when(noteProxy.listNoteByPatientId(any(Integer.class))).thenReturn(noteList);
        assertEquals(2, assessmentsService.getNumberOfTriggerWords(1));
        verify(noteProxy, times(1)).listNoteByPatientId(any(Integer.class));
    }

    @Test
    @DisplayName("Test getNumberOfTriggerWords 5 triggers EARLY_ONSET")
    void getAssessmentsLevelOfRisksMaleLessThan30TriggersNumber5M() throws Exception {
        PatientBean patient1 = new PatientBean(1, "Pitt", "Brad", LocalDate.of(1995, 04, 16), 'M', "4 Route de la Reine", "0688888888");
        NoteBean note1 = new NoteBean("1", 1, LocalDate.of(2023, 1, 10), "vertige");
        NoteBean note2 = new NoteBean("2", 1, LocalDate.of(2023, 1, 10), "rechute");
        NoteBean note3 = new NoteBean("3", 1, LocalDate.of(2023, 1, 10), "anticorps");
        NoteBean note4 = new NoteBean("4", 1, LocalDate.of(2023, 1, 10), "cholestérol");
        NoteBean note5 = new NoteBean("5", 1, LocalDate.of(2023, 1, 10), "poids");
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(patient1));
        when(noteProxy.listNoteByPatientId(1)).thenReturn(Arrays.asList(note1, note2, note3, note4, note5));
        assertEquals(Assessments.EARLY_ONSET, this.assessmentsService.getAssessmentsLevelOfRisks(1));
    }

    @Test
    @DisplayName("Test getNumberOfTriggerWords 7 triggers EARLY_ONSET")
    void getAssessmentsLevelOfRisksFemaleLessThan30TriggersNumber7F() throws Exception {
        PatientBean patient1 = new PatientBean(1, "Marceau", "Sophie", LocalDate.of(1995, 04, 16), 'F', "4 Route de la Reine", "0688888888");
        NoteBean note1 = new NoteBean("1", 1, LocalDate.of(2023, 1, 10), "microalbumine");
        NoteBean note2 = new NoteBean("2", 1, LocalDate.of(2023, 1, 10), "taille");
        NoteBean note3 = new NoteBean("3", 1, LocalDate.of(2023, 1, 10), "poids");
        NoteBean note4 = new NoteBean("4", 1, LocalDate.of(2023, 1, 10), "fumeur");
        NoteBean note5 = new NoteBean("5", 1, LocalDate.of(2023, 1, 10), "anormal");
        NoteBean note6 = new NoteBean("6", 1, LocalDate.of(2023, 1, 10), "cholestérol");
        NoteBean note7 = new NoteBean("7", 1, LocalDate.of(2023, 1, 10), "vertige");
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(patient1));
        when(noteProxy.listNoteByPatientId(1)).thenReturn(Arrays.asList(note1, note2, note3, note4, note5, note6, note7));
        assertEquals(Assessments.EARLY_ONSET, this.assessmentsService.getAssessmentsLevelOfRisks(1));
    }

    @Test
    @DisplayName("Test getNumberOfTriggerWords 8 triggers EARLY_ONSET")
    void getAssessmentsLevelOfRisksFemaleGreaterThan30TriggersNumber8X() throws Exception {
        PatientBean patient1 = new PatientBean(1, "Marceau", "Sophie", LocalDate.of(1970, 04, 16), 'F', "4 Route de la Reine", "0688888888");
        NoteBean note1 = new NoteBean("1", 1, LocalDate.of(2023, 1, 10), "microalbumine");
        NoteBean note2 = new NoteBean("2", 1, LocalDate.of(2023, 1, 10), "taille");
        NoteBean note3 = new NoteBean("3", 1, LocalDate.of(2023, 1, 10), "poids");
        NoteBean note4 = new NoteBean("4", 1, LocalDate.of(2023, 1, 10), "fumeur");
        NoteBean note5 = new NoteBean("5", 1, LocalDate.of(2023, 1, 10), "anormal");
        NoteBean note6 = new NoteBean("6", 1, LocalDate.of(2023, 1, 10), "cholestérol");
        NoteBean note7 = new NoteBean("7", 1, LocalDate.of(2023, 1, 10), "rechute");
        NoteBean note8 = new NoteBean("8", 1, LocalDate.of(2023, 1, 10), "réaction");
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(patient1));
        when(noteProxy.listNoteByPatientId(1)).thenReturn(Arrays.asList(note1, note2, note3, note4, note5, note6, note7, note8));
        assertEquals(Assessments.EARLY_ONSET, this.assessmentsService.getAssessmentsLevelOfRisks(1));
    }

    @Test
    @DisplayName("Test getNumberOfTriggerWords 3 triggers IN_DANGER")
    void getAssessmentsLevelOfRisksMaleLessThan30TriggersNumber3M() throws Exception {
        PatientBean patient1 = new PatientBean(1, "Pitt", "Brad", LocalDate.of(1995, 04, 16), 'M', "4 Route de la Reine", "0688888888");
        NoteBean note1 = new NoteBean("1", 1, LocalDate.of(2023, 1, 10), "poids");
        NoteBean note2 = new NoteBean("2", 1, LocalDate.of(2023, 1, 10), "fumeur");
        NoteBean note3 = new NoteBean("3", 1, LocalDate.of(2023, 1, 10), "anormal");
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(patient1));
        when(noteProxy.listNoteByPatientId(1)).thenReturn(Arrays.asList(note1, note2, note3));
        assertEquals(Assessments.IN_DANGER, this.assessmentsService.getAssessmentsLevelOfRisks(1));
    }

    @Test
    @DisplayName("Test getNumberOfTriggerWords 4 triggers IN_DANGER")
    void getAssessmentsLevelOfRisksFemaleLessThan30TriggersNumber4F() throws Exception {
        PatientBean patient1 = new PatientBean(1, "Marceau", "Sophie", LocalDate.of(1995, 04, 16), 'F', "4 Route de la Reine", "0688888888");
        NoteBean note1 = new NoteBean("1", 1, LocalDate.of(2023, 1, 10), "poids");
        NoteBean note2 = new NoteBean("2", 1, LocalDate.of(2023, 1, 10), "fumeur");
        NoteBean note3 = new NoteBean("3", 1, LocalDate.of(2023, 1, 10), "anormal");
        NoteBean note4 = new NoteBean("4", 1, LocalDate.of(2023, 1, 10), "cholestérol");
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(patient1));
        when(noteProxy.listNoteByPatientId(1)).thenReturn(Arrays.asList(note1, note2, note3,  note4));
        assertEquals(Assessments.IN_DANGER, this.assessmentsService.getAssessmentsLevelOfRisks(1));
    }

    @Test
    @DisplayName("Test getNumberOfTriggerWords 6 triggers IN_DANGER")
    void getAssessmentsLevelOfRisksFemaleGreaterThan30TriggersNumber6M() throws Exception {
        PatientBean patient1 = new PatientBean(1, "Marceau", "Sophie", LocalDate.of(1970, 04, 16), 'F', "4 Route de la Reine", "0688888888");
        NoteBean note1 = new NoteBean("1", 1, LocalDate.of(2023, 1, 10), "poids");
        NoteBean note2 = new NoteBean("2", 1, LocalDate.of(2023, 1, 10), "fumeur");
        NoteBean note3 = new NoteBean("3", 1, LocalDate.of(2023, 1, 10), "anormal");
        NoteBean note4 = new NoteBean("4", 1, LocalDate.of(2023, 1, 10), "cholestérol");
        NoteBean note5 = new NoteBean("5", 1, LocalDate.of(2023, 1, 10), "vertige");
        NoteBean note6 = new NoteBean("6", 1, LocalDate.of(2023, 1, 10), "rechute");
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(patient1));
        when(noteProxy.listNoteByPatientId(1)).thenReturn(Arrays.asList(note1, note2, note3,  note4, note5, note6));
        assertEquals(Assessments.IN_DANGER, this.assessmentsService.getAssessmentsLevelOfRisks(1));
    }

    @Test
    @DisplayName("Test getNumberOfTriggerWords 2 triggers BORDERLINE")
    void getAssessmentsLevelOfRisksMaleGreaterThan30TriggersNumber2() throws Exception {
        PatientBean patient1 = new PatientBean(1, "Pitt", "Brad", LocalDate.of(1970, 04, 16), 'M', "4 Route de la Reine", "0688888888");
        NoteBean note1 = new NoteBean("1", 1, LocalDate.of(2023, 1, 10), "anormal");
        NoteBean note2 = new NoteBean("2", 1, LocalDate.of(2023, 1, 10), "vertige");
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(patient1));
        when(noteProxy.listNoteByPatientId(1)).thenReturn(Arrays.asList(note1, note2));
        assertEquals(Assessments.BORDERLINE, this.assessmentsService.getAssessmentsLevelOfRisks(1));
    }


    @Test
    @DisplayName("Test getNumberOfTriggerWords 0 triggers")
    void getAssessmentsLevelOfRisksMaleLessThan30TriggersNumber0() throws Exception {
        PatientBean patient1 = new PatientBean(1, "Pitt", "Brad", LocalDate.of(1970, 04, 16), 'M', "4 Route de la Reine", "0688888888");
        when(patientProxy.getByIdPatient(any(Integer.class))).thenReturn(Optional.of(patient1));
        when(noteProxy.listNoteByPatientId(1)).thenReturn(Arrays.asList());
        assertEquals(Assessments.NONE, this.assessmentsService.getAssessmentsLevelOfRisks(1));
    }


}
