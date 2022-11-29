package com.backchallenge.wedoogift.Services;

import com.backchallenge.wedoogift.Dto.CompanyDto;
import com.backchallenge.wedoogift.Entities.Company;
import com.backchallenge.wedoogift.Repositories.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDto retrieveCompanyByName(String companyName) {
        return CompanyDto.entityToDto(companyRepository.findByCompanyName(companyName));
    }

    public void updateCompanyBalance(CompanyDto companyDto) {
        Company companyToUpdate = companyRepository.findByCompanyName(companyDto.getCompanyName());
        companyToUpdate.setBalance(companyDto.getBalance());
        companyRepository.save(companyToUpdate);
    }
}
