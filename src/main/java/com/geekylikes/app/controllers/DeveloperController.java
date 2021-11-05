package com.geekylikes.app.controllers;

import com.geekylikes.app.models.Developer;
import com.geekylikes.app.repositories.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/developers")
public class DeveloperController {
    @Autowired
    private DeveloperRepository repository;


    @GetMapping
    public @ResponseBody List<Developer> getDevelopers() {
       return repository.findAll();
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Developer> createDeveloper(@RequestBody Developer newDeveloper) {

        return new ResponseEntity<Developer>(repository.save(newDeveloper), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody Developer getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public @ResponseBody Developer updateDeveloper(@PathVariable Long id, @RequestBody Developer updateInfo) {
        Developer developer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        if (updateInfo.getName() != null) {
            developer.setName(updateInfo.getName());
        }
        if (updateInfo.getEmail() != null) {
            developer.setEmail(updateInfo.getEmail());
        }
        if (updateInfo.getCohort() != null) {
            developer.setCohort(updateInfo.getCohort());
        }
        if (updateInfo.getLanguages() != null) {
            developer.setLanguages(updateInfo.getLanguages());
        }

        return repository.save(developer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyDeveloper(@PathVariable Long id) {
        repository.deleteById(id);

        return new ResponseEntity<String>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/cohort/{cohort}")
    public ResponseEntity<List<Developer>> getCohortDevelopers(@PathVariable Integer cohort) {
        return new ResponseEntity<>(repository.findAllByCohort(cohort, Sort.by("name")), HttpStatus.OK);
    }



}
