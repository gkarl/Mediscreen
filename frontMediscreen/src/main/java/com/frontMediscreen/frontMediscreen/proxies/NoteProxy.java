package com.frontMediscreen.frontMediscreen.proxies;

import com.frontMediscreen.frontMediscreen.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "Note-microservice", url = "localhost:8082")
public interface NoteProxy {

    @GetMapping("/note/list/{patientId}")
    List<NoteBean> listNoteByPatientId(@PathVariable ("patientId") Integer patientId);

    @GetMapping("/note/{id}")
    NoteBean getNoteById(@PathVariable("id") String id);

    @PostMapping("/note/add")
    NoteBean addNote(@RequestBody NoteBean note);

    @GetMapping("/note/showEditForm/{id}/{patientId}")
    void showEditNoteForm(@PathVariable("id") String id,@PathVariable Integer patientId);

    @PostMapping("/note/update/{patienId}")
    NoteBean updateNote(@PathVariable ("patienId") String id, @RequestBody NoteBean note);

    @GetMapping("/note/delete/{id}")
    void deleteNote(@PathVariable ("id") String id);


}
