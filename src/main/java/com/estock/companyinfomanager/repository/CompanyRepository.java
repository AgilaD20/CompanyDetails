package com.estock.companyinfomanager.repository;

import com.estock.companyinfomanager.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,String> {

    Optional<Company> findByCompanyCode(String companyCode);

    void deleteByCompanyCode(String companyCode);


}
