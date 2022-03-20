package org.ouroui.interns.controller;

import org.ouroui.interns.model.Company;
import org.ouroui.interns.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/company")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepositry;
    @GetMapping("/")
    public ResponseEntity<List<Company>> getCompanies(){
        return ResponseEntity.ok(companyRepositry.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Company> addCompany(@Valid @RequestBody Company company){
        return new ResponseEntity<>(companyRepositry.save(company), HttpStatus.CREATED);
    }
}
