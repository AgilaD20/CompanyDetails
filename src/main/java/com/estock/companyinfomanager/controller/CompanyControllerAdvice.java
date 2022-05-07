package com.estock.companyinfomanager.controller;

import com.estock.companyinfomanager.exception.CompanyAlreadyPresentException;
import com.estock.companyinfomanager.exception.CompanyNotFoundException;
import com.estock.companyinfomanager.ui.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class CompanyControllerAdvice {

    @ExceptionHandler({CompanyNotFoundException.class})
    public ResponseEntity<Response> companyNotFoundException(){
        Response response = new Response("Company not found exception",HttpStatus.NOT_FOUND.value(), ZonedDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CompanyAlreadyPresentException.class})
    public ResponseEntity<Response> companyAlreadyPresentException(){
        Response response = new Response("Company already present exception",HttpStatus.OK.value(), ZonedDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
