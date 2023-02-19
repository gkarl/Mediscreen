package com.frontMediscreen.frontMediscreen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Display home page
 * @author Gavillot Karl
 * @version 1.0
 */
@Controller
public class HomePageController {

    /**
     *
     * @return html page display home page
     */
    @GetMapping("")
    public String showHomePage() {
        return "index";
    }
}
