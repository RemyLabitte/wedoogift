package com.backchallenge.wedoogift.Repositories;

import com.backchallenge.wedoogift.Entities.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Company findByCompanyName(String companyName);
}
