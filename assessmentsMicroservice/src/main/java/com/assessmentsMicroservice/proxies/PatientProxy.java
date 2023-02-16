package com.assessmentsMicroservice.proxies;

import com.assessmentsMicroservice.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "Patient-microservice", url = "localhost:8081")
public interface PatientProxy {

    @GetMapping("/patient/{id}")
    Optional<PatientBean> getByIdPatient(@PathVariable("id") Integer id);

}
