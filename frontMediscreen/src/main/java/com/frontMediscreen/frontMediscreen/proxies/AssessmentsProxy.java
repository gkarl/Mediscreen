package com.frontMediscreen.frontMediscreen.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Assessments-microservice", url = "localhost:8083")
public interface AssessmentsProxy {

    @GetMapping("/assessments/age/{id}")
    int getAgePatient(@PathVariable("id") Integer id);

    @GetMapping(value = "/assessments/triggers/{id}")
    int getAssementsTrigger(@PathVariable ("id")Integer id);

    @GetMapping("/assessments/{id}")
    String getAssessmentsLevelOfRisk(@PathVariable ("id") Integer id);
}
