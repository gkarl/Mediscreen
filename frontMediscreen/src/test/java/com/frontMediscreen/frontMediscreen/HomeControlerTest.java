package com.frontMediscreen.frontMediscreen;


import com.frontMediscreen.frontMediscreen.controller.HomePageController;
import com.frontMediscreen.frontMediscreen.controller.PatientController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
@ContextConfiguration(classes = {HomePageController.class})
public class HomeControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test showHomePage")
    public void showHomePageTest() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }
}
