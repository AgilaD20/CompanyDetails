package com.estock.companyinfomanager.service;

import com.estock.companyinfomanager.Entity.Company;
import com.estock.companyinfomanager.exception.CompanyAlreadyPresentException;
import com.estock.companyinfomanager.exception.CompanyNotFoundException;
import com.estock.companyinfomanager.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CompanyRegistrationService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private StockService stockService;

    public Company registerCompany(Company company){
        Optional<Company> companyOptional = companyRepository.findByCompanyCode(company.getCompanyCode());
        if(!companyOptional.isPresent()){
            company.setCompanyCode(UUID.randomUUID().toString());
            return companyRepository.save(company);
        }
        else{
            throw new CompanyAlreadyPresentException("Company is already present exception");
        }

    }

    @Transactional
    public void deleteCompany(String companyCode){
        if(companyRepository.findByCompanyCode(companyCode).isPresent()){
            companyRepository.deleteByCompanyCode(companyCode);
            stockService.deleteAllByCompanyCode(companyCode);
        }
        else{
            throw new CompanyNotFoundException("Company with the company code is not present");
        }

    }



}
