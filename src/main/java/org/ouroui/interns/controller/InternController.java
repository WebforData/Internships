package org.ouroui.interns.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.ouroui.interns.exception.ResourceNotFoundException;
import org.ouroui.interns.model.Internship;
import org.ouroui.interns.repository.InternsRepository;
import org.ouroui.interns.responce.ResponceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InternController {
    InternsRepository internsRepository;

    public InternController(InternsRepository internsRepository) {
        this.internsRepository = internsRepository;
    }

    @GetMapping("/Internships")
    public ResponseEntity<Object> getAllInternship() {
        try {
            return ResponceHandler.generateResponce("all internships", HttpStatus.OK, internsRepository.findAll());
        } catch (Exception e) {
            return ResponceHandler.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("Internships/company/{company}")
    public ResponseEntity<Object> findCompany(@PathVariable String company) {
        try {
            return ResponceHandler.generateResponce("get :" + company, HttpStatus.OK, internsRepository.findByCompanyContaining(company));
        } catch (Exception e) {
            return ResponceHandler.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody Internship internship) {
        try {
            return ResponceHandler.generateResponce("added successfully", HttpStatus.CREATED, internsRepository.save(internship));
        } catch (Exception e) {
            return ResponceHandler.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/Internships/id/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id) {
        try {
            return ResponceHandler.generateResponce("id: "+id,HttpStatus.OK,internsRepository.findById(id));
        }catch (Exception e){
            return ResponceHandler.generateResponce(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/Internships/role/{role}")
    public ResponseEntity<Object> getByRole(@PathVariable String role) {
    try {
        return ResponceHandler.generateResponce("role: "+role,HttpStatus.OK,internsRepository.findInternshipByRoleContaining(role));
    }catch (Exception e){
        return ResponceHandler.generateResponce(e.getMessage(),HttpStatus.MULTI_STATUS,null);
    }
    }

    @GetMapping("/Internships/duration/{duration}")
    public ResponseEntity<Object> getBydura(@PathVariable int duration) {
        try {
            return ResponceHandler.generateResponce("duration: "+duration,HttpStatus.OK,internsRepository.findInternshipByDuration(duration));
        }catch (Exception e){
            return ResponceHandler.generateResponce(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/Internships/duration/plus/{duration}")
    public ResponseEntity<Object> getBydurationGreat(@PathVariable int duration) {
       try {
           return ResponceHandler.generateResponce("dduratin great than : "+duration,HttpStatus.OK,internsRepository.findByDurationGreaterThan(duration));
       }catch (Exception e){
           return ResponceHandler.generateResponce(e.getMessage(),HttpStatus.MULTI_STATUS,null);
       }
    }

    @GetMapping("/Internships/duration/less/{duration}")
    public ResponseEntity<Object> getBydurationLess(@PathVariable int duration) {
        try {
            return ResponceHandler.generateResponce("dduratin less than : "+duration,HttpStatus.OK,internsRepository.findByDurationLessThan(duration));
        }catch (Exception e){
            return ResponceHandler.generateResponce(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @PutMapping("/Internships/put/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Internship internship) {
        Internship i = internsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Internship", "id", id));
        i.setCompany(internship.getCompany());
        i.setRole(internship.getRole());
        i.setDuration(internship.getDuration());
        i.setLocation(internship.getLocation());
        i.setUrl(internship.getUrl());
       try {
           return ResponceHandler.generateResponce("modified successfully",HttpStatus.OK,internsRepository.save(i));
       }catch (Exception e){
           return ResponceHandler.generateResponce(e.getMessage(),HttpStatus.MULTI_STATUS,null);
       }
    }

    @DeleteMapping("Internships/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable(value = "id") long id) {
        Internship internship = internsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Internship", "id", id));
        try{
            internsRepository.delete(internship);
            return ResponceHandler.generateResponce("Deleted!!",HttpStatus.OK,id+" deleted");
        }catch (Exception e){
            return ResponceHandler.generateResponce(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }

    }
}
