package com.estock.companyinfomanager.dto;

import lombok.Data;

@Data
public class CompanyDetails {

    private String companyCode;
    private String companyName;
    private String ceo;
    private Integer companyTurnOver;
    private String companyWebsiteUrl;
    private String stockExchange;
    private Double stockPrice;
}
