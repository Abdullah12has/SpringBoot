/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dujmovic.confapp.rest;

import hr.algebra.dujmovic.confapp.data.LectureRepository;
import hr.algebra.dujmovic.confapp.model.Lecture;
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
@RequestMapping(path = "/api/lecture", produces = "application/json")
@CrossOrigin(origins = "*")
public class LectureRestController {

    @Autowired
    LectureRepository repository;

    @GetMapping
    public Iterable<Lecture> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lecture> findOne(@PathVariable Long id) {
        Optional<Lecture> lecture = repository.findById(id);
        
        if(lecture.isPresent()){
            return new ResponseEntity<>(lecture.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public Lecture save(@RequestBody Lecture lecture) {
        return repository.save(lecture);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lecture> update(@PathVariable Long id, @RequestBody Lecture lecture) {
        if (repository.findById(id).isPresent()) {
            lecture.setId(id);
            lecture = repository.save(lecture);
            return new ResponseEntity<>(lecture, HttpStatus.OK);
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
