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

@RestController
public class AssessmentsController {

    private static final Logger logger = LoggerFactory.getLogger(AssessmentsController.class);

    @Autowired
    AssessmentsService assessmentsService;

    @ApiOperation(value = "Get age of a patient")
    @GetMapping("/assessments/age/{id}")
    public int getAgePatient(@PathVariable ("id") Integer id) {
        logger.info("GET patient'age");
        return assessmentsService.agePatient(id);
    }

    @ApiOperation(value = "Get keyword counter in the set of notes of a patient which is one of the criteria of the assessment")
    @GetMapping(value = "/assessments/triggers/{id}")
    public int getAssementsTrigger(@PathVariable ("id")Integer id) {
        logger.info("GET assessments triggers");
        return assessmentsService.getNumberOfTriggerWords(id);
    }

    @ApiOperation(value = "Get assessment of the patient's risk level based on age, gender and the number of keywords found in the doctor's notes")
    @GetMapping("/assessments/{id}")
    public Assessments getAssessmentsLevelOfRisk(@PathVariable ("id") Integer id) {
        logger.info("GET assessments level of risks");
        return assessmentsService.getAssessmentsLevelOfRisks(id);
    }
}
