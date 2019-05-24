package com.chrysoprase.booking.company;

import com.chrysoprase.booking.utility.Utility;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;

import java.util.List;

@Service
public class CompanyService {
  private CompanyRepository companyRepository;
  private static final String MAILGUN_API_KEY
          = "158d5ba190bcf4bd90afacf43ca22572-52b0ea77-23ee444c";
  private static final String MAILGUN_DOMAIN_NAME
          = "sandboxc16c1932d9384d5c974eb932752f791d.mailgun.org";

  @Autowired
  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  public void saveCompany(Company company) {
    sendSimpleMessage(company.getEmail(), company.getName(), company.getUtility());
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

  private static ClientResponse sendSimpleMessage(String recipient, String name, Utility utility) {
    Client client = Client.create();
    client.addFilter(new HTTPBasicAuthFilter("api", MAILGUN_API_KEY));
    MultivaluedMapImpl formData = new MultivaluedMapImpl();
    formData.add("from", "Chrysoprase <chrysoprase@online-queuing.com>");
    formData.add("to", recipient);
    formData.add("subject", "Online-queuing");
    formData.add("text", "Welcome " + name + ", \n we are glad that you signed up! :) \n"
            + "your utility is: " + utility.getType());
    WebResource webResource = client.resource("https://api.mailgun.net/v3/" + MAILGUN_DOMAIN_NAME
            + "/messages");
    return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
            formData);
  }

  public List<Company> getAllCompanies() {
    return (List<Company>)companyRepository.findAll();
  }

  public List<Company> companiesWithCertainUtility(String utiliy) {
    return companyRepository.findCompaniesByUtility_TypeIsContaining(utiliy);
  }
}
