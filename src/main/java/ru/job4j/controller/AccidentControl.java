package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Accident;
import ru.job4j.service.AccidentService;

@Controller
public class AccidentControl {

    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/accidents")
    public String accidents(Model model) {
        model.addAttribute("accidents", service.getAllAccidents());
        return "accidents";
    }

    @GetMapping("/formAddAccident")
    public String formAddAccident(Model model) {
        return "addAccident";
    }

    @PostMapping("/saveAccident")
    public String saveAccident(@ModelAttribute Accident accident) {
        service.addAccident(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/formEditAccident/{accidentId}")
    public String formEditAccident(Model model, @PathVariable("accidentId") int id) {
        model.addAttribute("accident", service.findById(id));
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident) {
        //System.out.println(model.getAttribute("accident"));
        service.update(accident);
        return "redirect:/accidents";
    }
}