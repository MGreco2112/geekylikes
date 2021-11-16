package com.geekylikes.app.controllers;

import com.geekylikes.app.models.Geekout;
import com.geekylikes.app.repositories.GeekoutRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/geekouts")
public class GeekoutController {

    @Autowired
    private GeekoutRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Geekout>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/dev/{devId}")
    public ResponseEntity<List<Geekout>> getAllByDevId(@PathVariable Long devId) {
        return new ResponseEntity<>(repository.findByDeveloperId(devId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Geekout> createOne(@RequestBody Geekout geekout) {
        System.out.println(geekout.getDeveloper().getId());
        return new ResponseEntity<>(repository.save(geekout), HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Geekout> updateOne(@PathVariable Long id, @RequestBody Geekout update) {
//        similar to developer update
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable Long id) {
        repository.deleteById(id);

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


}