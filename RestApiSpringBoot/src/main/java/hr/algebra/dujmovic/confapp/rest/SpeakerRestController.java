/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dujmovic.confapp.rest;

import hr.algebra.dujmovic.confapp.data.SpeakerRepository;
import hr.algebra.dujmovic.confapp.model.Speaker;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author matij
 */
@RestController
@RequestMapping(path = "/api/speaker", produces = "application/json")
@CrossOrigin(origins = "*")
public class SpeakerRestController {

    @Autowired
    SpeakerRepository repository;

    @GetMapping
    public Iterable<Speaker> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Speaker> findOne(@PathVariable Long id) {
        Optional<Speaker> speaker = repository.findById(id);
        
        if(speaker.isPresent()){
            return new ResponseEntity<>(speaker.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public Speaker save(@RequestBody Speaker speaker) {
        return repository.save(speaker);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Speaker> update(@PathVariable Long id, @RequestBody Speaker speaker) {
        if (repository.findById(id).isPresent()) {
            speaker.setId(id);
            repository.save(speaker);
            return new ResponseEntity<>(speaker, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
