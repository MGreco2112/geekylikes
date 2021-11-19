package com.geekylikes.app.controllers;

import com.geekylikes.app.models.avatar.Avatar;
import com.geekylikes.app.models.developer.Developer;
import com.geekylikes.app.repositories.AvatarRepository;
import com.geekylikes.app.repositories.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/developers")
public class DeveloperController {
    @Autowired
    private DeveloperRepository repository;
    @Autowired
    private AvatarRepository avatarRepository;


    @GetMapping
    public @ResponseBody List<Developer> getDevelopers() {
       return repository.findAll();
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Developer> createDeveloper(@RequestBody Developer newDeveloper) {

        return new ResponseEntity<Developer>(repository.save(newDeveloper), HttpStatus.CREATED);
    }

    @PostMapping("/photo")
    public ResponseEntity<Developer> addPhoto(@RequestBody Developer dev) {
        Developer selDev = repository.findById(dev.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Avatar newAvatar = avatarRepository.save(dev.getAvatar());

        if (selDev.getAvatar() != null) {
            Avatar oldAv = selDev.getAvatar();
            selDev.setAvatar(null);

            avatarRepository.delete(oldAv);
        }

        selDev.setAvatar(newAvatar);
        return new ResponseEntity<>(repository.save(selDev), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody Developer getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/language")
    public Developer addLanguage(@RequestBody Developer updates) {
        Developer developer = repository.findById(updates.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.languages != null) {
            developer.languages.addAll(updates.languages);
        }

        return repository.save(developer);

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
        if (updateInfo.languages != null) {
            developer.languages = updateInfo.languages;
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
