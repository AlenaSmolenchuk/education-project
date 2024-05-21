package ru.mts.educationproject.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mts.educationproject.annotations.Logging;
import ru.mts.educationproject.entity.Animal;
import ru.mts.educationproject.service.AnimalService;

@Controller
@Log4j2
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @Logging(value = "Fetching index page",
            enter = true,
            exit = true,
            logParams = false,
            logResult = false,
            level = "INFO")
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("allAnimals", animalService.findAll());
        return "index";
    }

    @Logging(value = "Fetching new animal form",
            enter = true,
            exit = true,
            logParams = false,
            logResult = false,
            level = "INFO")
    @GetMapping("/new")
    public String newAnimal(Model model) {
        model.addAttribute("animal", new Animal());
        return "new";
    }

    @Logging(value = "Creating a new animal",
            enter = true,
            exit = true,
            logParams = true,
            logResult = true,
            level = "INFO")
    @PostMapping(value = "/new", params = "action=CREATE")
    public String create(@ModelAttribute("animal") Animal animal) {
        animalService.save(animal);
        return "redirect:/index";
    }

    @Logging(value = "Returning back from new animal form",
            enter = true,
            exit = true,
            logParams = false,
            logResult = false,
            level = "INFO")
    @PostMapping(value = "/new" ,params = "action=BACK")
    public String back(Model model, Animal animal) {
        return "redirect:/index";
    }

    @Logging(value = "Deleting an animal",
            enter = true,
            exit = true,
            logParams = true,
            logResult = true,
            level = "INFO")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        animalService.delete(id);
        return "redirect:/index";
    }
}
