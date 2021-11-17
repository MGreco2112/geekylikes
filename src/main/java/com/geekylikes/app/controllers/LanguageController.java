package com.geekylikes.app.controllers;

import com.geekylikes.app.models.language.Language;
import com.geekylikes.app.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    @Autowired
    private LanguageRepository repository;

    @GetMapping
    public List<Language> getAllLanguages() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable Long id) {
        Optional<Language> langauge = repository.findById(id);

        if (langauge.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(langauge.get(), HttpStatus.OK);
    }

    @PostMapping
    public Language createOne(@RequestBody Language newLanguage) {
        return repository.save(newLanguage);
    }


}
