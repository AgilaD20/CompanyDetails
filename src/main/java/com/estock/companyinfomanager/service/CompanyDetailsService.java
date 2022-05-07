package com.estock.companyinfomanager.service;

import com.estock.companyinfomanager.Entity.Company;
import com.estock.companyinfomanager.dto.CompanyDetails;
import com.estock.companyinfomanager.exception.CompanyNotFoundException;
import com.estock.companyinfomanager.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyDetailsService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private ModelMapper modelMapper;

    public CompanyDetails getCompanyDetails(String companyCode){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Optional<Company> companyOptional = companyRepository.findByCompanyCode(companyCode);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            CompanyDetails companyDetails = modelMapper.map(company,CompanyDetails.class);
            return companyDetails;
        }
        else{
            throw new CompanyNotFoundException("No company found for the companyCode");
        }

    }

    public List<CompanyDetails> getAllCompaniesDetails() {
        List<CompanyDetails> companyDetailsList = new ArrayList<>();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        companyRepository.findAll().stream().forEach((company)->{
            CompanyDetails companyDetails = modelMapper.map(company,CompanyDetails.class);
            companyDetailsList.add(companyDetails);
        });
        return companyDetailsList;
    }

    public void addStock(String companyCode, Double stock) {
        Optional<Company> optionalCompany = companyRepository.findByCompanyCode(companyCode);
        if(optionalCompany.isPresent()){
            Company company = optionalCompany.get();
            company.setStockPrice(stock);
            companyRepository.save(company);
            stockService.sendStockDetails(companyCode,stock);
        }
        else{
            throw new CompanyNotFoundException("No company found for the companyCode");
        }

    }
}
