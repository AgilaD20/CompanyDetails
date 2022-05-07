package com.estock.companyinfomanager.controller;

import com.estock.companyinfomanager.Entity.Company;
import com.estock.companyinfomanager.dto.CompanyDetails;
import com.estock.companyinfomanager.service.CompanyDetailsService;
import com.estock.companyinfomanager.service.CompanyRegistrationService;
import com.estock.companyinfomanager.service.StockService;
import com.estock.companyinfomanager.ui.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1.0/market/company")
public class CompanyController {

    private CompanyRegistrationService companyRegistrationService;

    private CompanyDetailsService companyDetailsService;

    private StockService stockService;

    public CompanyController(CompanyRegistrationService companyRegistrationService,CompanyDetailsService companyDetailsService,StockService stockService){
        this.companyRegistrationService = companyRegistrationService;
        this.companyDetailsService = companyDetailsService;
        this.stockService = stockService;
    }

    @PostMapping("/register")
    public ResponseEntity<Company> registerCompany(@Validated @RequestBody Company company){
        log.info("Request received for adding new Company");
        Company registeredCompany = companyRegistrationService.registerCompany(company);
        return new ResponseEntity<>(registeredCompany, HttpStatus.CREATED);
    }

    @PostMapping("/add/{companycode}")
    public ResponseEntity<Response> addStock(@RequestParam Double stock, @PathVariable("companycode") String companyCode){
        log.info("Request received for adding stock to a Company with companycode {}",companyCode);
        companyDetailsService.addStock(companyCode,stock);
        Response response = new Response("Stock was added successfully",HttpStatus.OK.value(), ZonedDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/info/{companycode}")
    public ResponseEntity<CompanyDetails> retrieveCompanyDetails(@PathVariable("companycode") String companyCode){
        log.info("Request received for retrieving company details with companycode as {}",companyCode);
        CompanyDetails companyDetails = companyDetailsService.getCompanyDetails(companyCode);
        return new ResponseEntity<CompanyDetails>(companyDetails,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CompanyDetails>> retrieveAllCompanyDetails(){
        log.info("Request received for retrieving all company details");
        List<CompanyDetails> companyDetailsList = companyDetailsService.getAllCompaniesDetails();
        return new ResponseEntity<List<CompanyDetails>>(companyDetailsList,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{companycode}")
    public String deleteCompany(@PathVariable("companycode") String companyCode){
        log.info("Request received to delete the company details with companycode as {}",companyCode);
        companyRegistrationService.deleteCompany(companyCode);
        return "Deleted Successfully";

    }



}
