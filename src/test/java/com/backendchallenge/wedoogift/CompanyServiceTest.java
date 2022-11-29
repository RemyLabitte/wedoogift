package com.backendchallenge.wedoogift;


import com.backchallenge.wedoogift.Dto.CompanyDto;
import com.backchallenge.wedoogift.Entities.Company;
import com.backchallenge.wedoogift.Repositories.CompanyRepository;
import com.backchallenge.wedoogift.Services.CompanyService;
import com.backchallenge.wedoogift.WedoogiftApplication;
import com.backendchallenge.wedoogift.utils.JsonMockerUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = WedoogiftApplication.class)
public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    void should_returnCompany() {
        //GIVEN
        Company company = retrieveCompanyByName("appleEntity");
        when(companyRepository.findByCompanyName("appleEntity")).thenReturn(company);

        //WHEN
        CompanyDto resultCompany = companyService.retrieveCompanyByName("appleEntity");

        //THEN
        Assertions.assertNotNull(resultCompany);
        Assertions.assertEquals("Apple", resultCompany.getCompanyName());
        Assertions.assertEquals(50000, resultCompany.getBalance());
    }

    // No unit test done on method : updateCompanyBalance
    // Because method return void -> should be useful to do integration test

    private Company retrieveCompanyByName(String companyName) {
        return JsonMockerUtils.getClassFromJson(Company.class, companyName);
    }
}
