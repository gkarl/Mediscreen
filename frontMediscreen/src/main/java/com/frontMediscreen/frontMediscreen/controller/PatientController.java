package com.frontMediscreen.frontMediscreen.controller;

import com.frontMediscreen.frontMediscreen.beans.PatientBean;
import com.frontMediscreen.frontMediscreen.proxies.PatientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientProxy patientProxy;

    @GetMapping("/patient/list")
    public String listAllPatients(Model model) {
        logger.info("Get patient list");
        List<PatientBean> listPatient = patientProxy.listAllPatients();
        model.addAttribute("patients", listPatient);
        return "patient/listPatient";
    }


}
