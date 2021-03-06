package com.geekylikes.app.controllers;

import com.geekylikes.app.models.approve.Approve;
import com.geekylikes.app.models.developer.Developer;
import com.geekylikes.app.models.geekout.Geekout;
import com.geekylikes.app.repositories.ApproveRepository;
import com.geekylikes.app.repositories.GeekoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/geekouts")
public class GeekoutController {

    @Autowired
    private GeekoutRepository repository;

    @Autowired
    private ApproveRepository approveRepository;

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

    @PostMapping("/like/{id}")
    public ResponseEntity<Geekout> likeById(@PathVariable Long id, @RequestBody Developer developer) {
        Optional<Geekout> geekout = repository.findById(id);

        if (geekout.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Approve newApprove = new Approve(developer, geekout.get());

        approveRepository.save(newApprove);

        return new ResponseEntity<>(repository.save(geekout.get()), HttpStatus.CREATED);
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
