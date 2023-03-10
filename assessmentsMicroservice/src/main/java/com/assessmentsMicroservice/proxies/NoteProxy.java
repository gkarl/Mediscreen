package com.assessmentsMicroservice.proxies;

import com.assessmentsMicroservice.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Note-microservice", url = "localhost:8082")
public interface NoteProxy {

    @GetMapping("/note/list/{patientId}")
    List<NoteBean> listNoteByPatientId(@PathVariable("patientId") Integer patientId);

    @GetMapping("/note/{id}")
    NoteBean getNoteById(@PathVariable("id") String id);
}
