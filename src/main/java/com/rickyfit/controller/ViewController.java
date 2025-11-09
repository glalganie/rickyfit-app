package com.rickyfit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

  /*  @GetMapping("/")
    public String homepage() {
        return "homepage";
    }*/

    @GetMapping("/homepage.html")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/abbonamenti.html")
    public String abbonamenti() {
        return "abbonamenti";
    }

    @GetMapping("/corsi_prenotazioni.html")
    public String corsiPrenotazioni(){
        return "corsi_prenotazioni";
    }

    @GetMapping("/progressi.html")
    public String progressi() {
        return "progressi";
    }

    @GetMapping("/admin.html")
    public String admin() {
        return "admin";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }
}