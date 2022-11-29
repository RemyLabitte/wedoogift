package com.backchallenge.wedoogift.Dto;

import com.backchallenge.wedoogift.Entities.Company;

public class CompanyDto {

    private String companyName;

    private double balance;

    public static CompanyDto entityToDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setBalance(company.getBalance());
        companyDto.setCompanyName(company.getCompanyName());
        return companyDto;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
