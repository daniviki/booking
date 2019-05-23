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

  public void updateCompany(Company company) throws NotFoundException {
    boolean isUpdateAble = companyRepository.findById(company.getId()).isPresent();
    if (isUpdateAble) {
      Company updateCompany = companyRepository.findById(company.getId()).get();
      updateCompany.setEmployees(company.getEmployees());
      updateCompany.setUtilities(company.getUtilities());
      updateCompany.setName(company.getName());
      saveCompany(updateCompany);
    }
    throw new NotFoundException("Yoyoyoyo, you'll've no company mate!");
  }
}
