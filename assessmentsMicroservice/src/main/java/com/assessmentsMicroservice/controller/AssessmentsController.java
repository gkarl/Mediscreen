package com.assessmentsMicroservice.controller;

import com.assessmentsMicroservice.model.Assessments;
import com.assessmentsMicroservice.service.AssessmentsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * API REST expose endpoint get age of patient, counter keyword trigger one notes, assessment diabetes level of risks patient
 * @author Gavillot Karl
 * @version 1.0
 */
@RestController
public class AssessmentsController {

    private static final Logger logger = LoggerFactory.getLogger(AssessmentsController.class);

    @Autowired
    AssessmentsService assessmentsService;

    /**
     * Get age of a patient
     * @param id patient id
     * @return age patient
     */
    @ApiOperation(value = "Get age of a patient")
    @GetMapping("/assessments/age/{id}")
    public int getAgePatient(@PathVariable ("id") Integer id) {
        logger.info("GET patient'age");
        return assessmentsService.agePatient(id);
    }

    /**
     * Get keyword counter in the set of notes of a patient which is one of the criteria of the assessment
     * @param id patient id
     * @return trigger counter
     */
    @ApiOperation(value = "Get keyword counter in the set of notes of a patient which is one of the criteria of the assessment")
    @GetMapping(value = "/assessments/triggers/{id}")
    public int getAssementsTrigger(@PathVariable ("id")Integer id) {
        logger.info("GET assessments triggers");
        return assessmentsService.getNumberOfTriggerWords(id);
    }

    /**
     * Get assessment of diabetes risk level based on age, gender, number of keywords found in patient's notes
     * @param id patient id
     * @return assesment level of risk
     */
    @ApiOperation(value = "Get assessment of the patient's risk level based on age, gender and the number of keywords found in the doctor's notes")
    @GetMapping("/assessments/{id}")
    public Assessments getAssessmentsLevelOfRisk(@PathVariable ("id") Integer id) {
        logger.info("GET assessments level of risks");
        return assessmentsService.getAssessmentsLevelOfRisks(id);
    }
}
