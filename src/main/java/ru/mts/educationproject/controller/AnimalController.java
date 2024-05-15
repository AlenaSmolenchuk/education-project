package ru.mts.educationproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.educationproject.entity.Animal;
import ru.mts.educationproject.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/animals/api")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/new")
    public String create(@RequestBody Animal animal) {
        animalService.save(animal);
        return "SUCCESSFULLY ADDED";
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Animal animal) {
        animalService.delete(animal);
        return "SUCCESSFULLY DELETED";
    }

    @PostMapping("/all")
    public List<Animal> getAll() {
        return  animalService.findAll();
    }
}
