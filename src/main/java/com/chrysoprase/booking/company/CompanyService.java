package com.chrysoprase.booking.company;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
  private CompanyRepository companyRepository;

  @Autowired
  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  public void saveCompany(Company company) {
    companyRepository.save(company);
  }

  private Company returnById(Long id) {
    return companyRepository.findCompanyById(id);
  }

  public void updateCompany(Company company) throws NotFoundException {
    Company updateCompany = returnById(company.getId());
    if (updateCompany != null) {
      updateCompany.setEmployees(company.getEmployees());
      updateCompany.setUtilities(company.getUtilities());
      updateCompany.setName(company.getName());
      saveCompany(updateCompany);
    }
    throw new NotFoundException("Yoyoyoyo, you'll've no company mate!");
  }
}
