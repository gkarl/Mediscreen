package com.frontMediscreen.frontMediscreen.proxies;

import com.frontMediscreen.frontMediscreen.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "Patient-microservice", url = "localhost:8081")
public interface PatientProxy {

    @GetMapping("/patient/list")
    List<PatientBean> listAllPatients();

    @GetMapping("/patient/{id}")
    Optional<PatientBean> getByIdPatient(@PathVariable("id") Integer id);

    @PostMapping("/patient/add")
    PatientBean addPatient(@RequestBody PatientBean patient);

    @PutMapping("/patient/update/{id}")
    PatientBean updatePatient(@PathVariable Integer id, @RequestBody PatientBean patient);

    @GetMapping("/patient/delete/{id}")
    void deleteByIdPatient(@PathVariable ("id") Integer id);

}
