package com.cookeasy.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = "/")
    public String retrieveIndexPage(@AuthenticationPrincipal UserDetails user) {
        if(user != null) return "redirect:/recipes";

        return "index";
    }
}