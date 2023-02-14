package com.frontMediscreen.frontMediscreen.proxies;

import com.frontMediscreen.frontMediscreen.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
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

    @PostMapping("/patient/showEditForm/{id}")
    public void showEditForm(@PathVariable Integer id);

    @GetMapping("/patient/delete/{id}")
    void deleteByIdPatient(@PathVariable ("id") Integer id);

}
