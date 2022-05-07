package com.estock.companyinfomanager.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Company {
    @Id
    private String companyCode;
    @NotNull(message="companyName must not be null")
    private String companyName;
    @NotNull(message="ceo must not be null")
    private String ceo;
    @NotNull(message="companyTurnOver must not be null")
    @Min(value = 100000000, message = "Age should not be less than 18")
    private Integer companyTurnOver;
    @NotNull(message="companyWebsiteUrl must not be null")
    private String companyWebsiteUrl;
    @NotNull(message="stockExchange must not be null")
    private String stockExchange;
    private Double stockPrice;

}
