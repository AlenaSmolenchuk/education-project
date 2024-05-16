package ru.mts.educationproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mts.educationproject.entity.Animal;
import ru.mts.educationproject.service.AnimalService;

@Controller
public class AnimalController {

    private static final Logger logger = LoggerFactory.getLogger(AnimalController.class);
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("allAnimals", animalService.findAll());
        return "index";
    }

    @GetMapping("/new")
    public String newAnimal(Model model) {
        model.addAttribute("animal", new Animal());
        return "new";
    }

    @PostMapping(value = "/new", params = "action=CREATE")
    public String create(@ModelAttribute("animal") Animal animal) {
        animalService.save(animal);
        logger.info("{} added successfully", animal);
        return "redirect:/index";
    }

    @PostMapping(value = "/new" ,params = "action=BACK")
    public String back(Model model, Animal animal) {
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        animalService.delete(id);
        logger.info("Animal with ID: {} deleted successfully", id);
        return "redirect:/index";
    }
}
