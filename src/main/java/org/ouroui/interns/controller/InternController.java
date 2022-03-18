package org.ouroui.interns.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.ouroui.interns.exception.ResourceNotFoundException;
import org.ouroui.interns.model.Internship;
import org.ouroui.interns.repository.InternsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InternController {
    InternsRepository internsRepository;
    public InternController(InternsRepository internsRepository){
        this.internsRepository=internsRepository;
    }
    @GetMapping("/Internships")
    public List<Internship> getAllInternship(){
        return internsRepository.findAll();
    }
    @PostMapping("/add")
    public Internship add(@Valid @RequestBody Internship internship){
        return internsRepository.save(internship);
    }
    @GetMapping("/Internships/id/{id}")
    public Internship getById(@PathVariable(value = "id") Long id){
        return internsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Internship","id",id));
    }
    @GetMapping("/Internships/role/{role}")
    public List<Internship> getByRole(@PathVariable String role){
        return internsRepository.findInternshipByRoleContaining(role); }
    @GetMapping("/Internships/duration/{duration}")
    public List<Internship> getBydura(@PathVariable int duration){
        return internsRepository.findInternshipByDuration(duration); }
    @PutMapping("/Internships/put/{id}")
    public Internship update(@PathVariable(value = "id") Long id,@Valid @RequestBody Internship internship){
        Internship i=internsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Internship","id",id));
        i.setCompany(internship.getCompany());
        i.setRole(internship.getRole());
        i.setDuration(internship.getDuration());
        i.setLocation(internship.getLocation());
        i.setUrl(internship.getUrl());
        return internsRepository.save(i);
    }
    @DeleteMapping("Internships/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable(value = "id") long id){
        Internship internship=internsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Internship","id",id));
        internsRepository.delete(internship);
        return ResponseEntity.ok().build();
    }
}
