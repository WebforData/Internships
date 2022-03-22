package org.ouroui.interns.controller;

import org.ouroui.interns.Dto.OrderResponce;
import org.ouroui.interns.model.Company;
import org.ouroui.interns.repository.CompanyRepository;
import org.ouroui.interns.responce.ResponceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/company")
public class CompanyController {
    CompanyRepository companyRepositry;

    public CompanyController(CompanyRepository companyRepositry) {
        this.companyRepositry = companyRepositry;
    }

    @GetMapping("/companies")
    public ResponseEntity<Object> getCompanies() {
        try {
            return ResponceHandler.generateResponce("All companies", HttpStatus.OK, companyRepositry.findAll());
        } catch (Exception e) {
            return ResponceHandler.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCompany(@Valid @RequestBody Company company) {
        try {
            return ResponceHandler.generateResponce("added successfully", HttpStatus.CREATED, companyRepositry.save(company));
        } catch (Exception e) {
            return ResponceHandler.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> gett(@PathVariable long id) {
        try {
            return ResponceHandler.generateResponce("the id :" + id, HttpStatus.OK, companyRepositry.findById(id));
        } catch (Exception e) {
            return ResponceHandler.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/Info")
    public ResponseEntity<Object> getInfo() {
        try {
            return ResponceHandler.generateResponce("infos by name, role and location", HttpStatus.OK, companyRepositry.getJoinInfo());
        } catch (Exception e) {
            return ResponceHandler.generateResponce(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
