package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Accident;
import ru.job4j.model.Rule;
import ru.job4j.service.AccidentService;
import ru.job4j.service.AccidentTypeService;
import ru.job4j.service.RuleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AccidentControl {

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public AccidentControl(AccidentService accidentService, AccidentTypeService accidentTypeService, RuleService ruleService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
        this.ruleService = ruleService;
    }

    @GetMapping("/accidents")
    public String accidents(Model model) {
        model.addAttribute("accidents", accidentService.getAllAccidents());
        return "accidents";
    }

    @GetMapping("/formAddAccident")
    public String formAddAccident(Model model) {
        model.addAttribute("types", accidentTypeService.getAllAccidentsType());
        model.addAttribute("rules", ruleService.getAllRule());
        return "addAccident";
    }

    @PostMapping("/saveAccident")
    public String saveAccident(@ModelAttribute Accident accident,
                               @RequestParam("accidentType.id") int idAccidentType,
                               @RequestParam("rule.id") List<Integer> idRules) {
        accident.setAccidentType(accidentTypeService.findById(idAccidentType));

        Set<Rule> rules = new HashSet<>();
        idRules.forEach(n -> rules.add(ruleService.findById(n)));
        accident.setRules(rules);
        accidentService.addAccident(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/formEditAccident/{accidentId}")
    public String formEditAccident(Model model, @PathVariable("accidentId") int id) {
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("types", accidentTypeService.getAllAccidentsType());
        model.addAttribute("rules", ruleService.getAllRule());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident,
                                 @RequestParam("accidentType.id") int idAccidentType,
                                 @RequestParam("rule.id") List<Integer> idRules) {
        accident.setAccidentType(accidentTypeService.findById(idAccidentType));
        Set<Rule> rules = new HashSet<>();
        idRules.forEach(n -> rules.add(ruleService.findById(n)));
        accident.setRules(rules);
        accidentService.update(accident);
        return "redirect:/accidents";
    }
}