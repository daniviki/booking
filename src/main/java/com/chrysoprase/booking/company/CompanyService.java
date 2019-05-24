package com.chrysoprase.booking.company;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
      updateCompany.setUtility(company.getUtility());
      updateCompany.setName(company.getName());
      saveCompany(updateCompany);
    }
    throw new NotFoundException("Yoyoyoyo, you'll've no company mate!");
  }

  public List<Company> getAllCompanies() {
    return (List<Company>)companyRepository.findAll();
  }

  public List<Company> companiesWithCertainUtility(String utiliy) {
    return companyRepository.findCompaniesByUtility_TypeIsContaining(utiliy);
  }
}
