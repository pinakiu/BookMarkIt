package com.bookTrackerProject.bookMarkIt.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String name(HttpServletRequest request) {
        return "Pinaki " + request.getSession().getId();
    }
}
