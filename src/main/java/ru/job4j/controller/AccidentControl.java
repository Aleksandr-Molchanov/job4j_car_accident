package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;
import ru.job4j.service.AccidentService;
import ru.job4j.service.AccidentTypeService;

@Controller
public class AccidentControl {

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;

    public AccidentControl(AccidentService accidentService, AccidentTypeService accidentTypeService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
    }

    @GetMapping("/accidents")
    public String accidents(Model model) {
        model.addAttribute("accidents", accidentService.getAllAccidents());
        return "accidents";
    }

    @GetMapping("/formAddAccident")
    public String formAddAccident(Model model) {
        model.addAttribute("types", accidentTypeService.getAllAccidentsType());
        return "addAccident";
    }

    @PostMapping("/saveAccident")
    public String saveAccident(@ModelAttribute Accident accident, @RequestParam("accidentType.id") int idAccidentType) {
        accident.setAccidentType(accidentTypeService.findById(idAccidentType));
        accidentService.addAccident(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/formEditAccident/{accidentId}")
    public String formEditAccident(Model model, @PathVariable("accidentId") int id) {
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("types", accidentTypeService.getAllAccidentsType());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, @RequestParam("accidentType.id") int idAccidentType) {
        accident.setAccidentType(accidentTypeService.findById(idAccidentType));
        accidentService.update(accident);
        return "redirect:/accidents";
    }
}