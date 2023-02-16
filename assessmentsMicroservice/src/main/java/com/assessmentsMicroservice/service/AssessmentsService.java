package com.assessmentsMicroservice.service;

import com.assessmentsMicroservice.beans.NoteBean;
import com.assessmentsMicroservice.beans.PatientBean;
import com.assessmentsMicroservice.model.Assessments;
import com.assessmentsMicroservice.proxies.NoteProxy;
import com.assessmentsMicroservice.proxies.PatientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class AssessmentsService {

    private static final Logger logger = LoggerFactory.getLogger(AssessmentsService.class);

    @Autowired
    private PatientProxy patientProxy;

    @Autowired
    private NoteProxy noteProxy;

    public static final List<String> triggersList = List.of("hémoglobine A1C", "microalbumine", "taille", "poids", "fumeur", "anormal", "cholestérol", "vertige", "rechute", "réaction", "anticorps");
    public int agePatient(Integer id) {
        logger.info("Service Age Patient");
        Optional<PatientBean> patient = patientProxy.getByIdPatient(id);
        LocalDate birthDatePatient = patient.get().getDob();
        int agePatient = Period.between(birthDatePatient, LocalDate.now()).getYears();
        return agePatient;
    }

    public int getAgePatient(LocalDate birthPatient) {
        logger.info("Service get Age Patient");
        int agePatient = Period.between(birthPatient, LocalDate.now()).getYears();
        return agePatient;
    }

    public int getNumberOfTriggerWords(Integer id) {
        logger.info("Service Number of trigger for one patient");
        List<NoteBean> notes = noteProxy.listNoteByPatientId(id);
        int triggersCounter= 0;
        for (NoteBean note : notes) {
            for (String triggerWord : triggersList) {
                if (note.getRecommendation().contains(triggerWord)) {
                    triggersCounter++;
                }
            }
        }
        return triggersCounter;
    }

    public Assessments getAssessmentsLevelOfRisks(Integer id) {
        logger.info("Service get Assessments level of risks");
        Optional<PatientBean> patient = patientProxy.getByIdPatient(id);
        int agePatient = getAgePatient(patient.get().getDob());
        int numberTriggersWords = getNumberOfTriggerWords(id);
        char sexe = patient.get().getSex();
        if (sexe == 'M' && agePatient <= 30 && numberTriggersWords >= 5) {
            return Assessments.EARLY_ONSET;
        } else if (sexe == 'F' && agePatient <= 30 && numberTriggersWords >= 7) {
            return Assessments.EARLY_ONSET;
        } else if ((sexe == 'M' || sexe == 'F') && agePatient > 30 && numberTriggersWords >= 8) {
            return Assessments.EARLY_ONSET;
        } else if (sexe == 'M' && agePatient <= 30 && numberTriggersWords >= 3 && numberTriggersWords < 5) {
            return Assessments.IN_DANGER;
        } else if (sexe == 'F' && agePatient <= 30 && numberTriggersWords >= 4 && numberTriggersWords < 7) {
            return Assessments.IN_DANGER;
        } else if (agePatient > 30 && numberTriggersWords >= 6 && numberTriggersWords < 8) {
            return Assessments.IN_DANGER;
        } else if ((sexe == 'M' || sexe == 'F') && agePatient > 30 && numberTriggersWords >= 2 && numberTriggersWords < 6) {
            return Assessments.BORDERLINE;
        } else if ((sexe == 'M' || sexe == 'F') && numberTriggersWords == 0) {
            return Assessments.NONE;
        }
        return null;
    }


}
