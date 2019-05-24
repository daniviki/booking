package com.chrysoprase.booking.company;

import com.chrysoprase.booking.exception.WrongEmailException;
import com.chrysoprase.booking.exception.WrongUsernameException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;

@Service
public class CompanyService {
  private CompanyRepository companyRepository;
  private static final String MAILGUN_API_KEY
          = "ca1bfa72f9fdf4b67283b33a6a91a4a9-52b0ea77-a9286eb3";
  private static final String MAILGUN_DOMAIN_NAME
          = "sandbox5bff1c70ecc4406591bffa656835e964.mailgun.org";

  @Autowired
  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  public void saveCompany(Company company) throws WrongUsernameException, WrongEmailException {
    if (companyRepository.existsByName(company.getName())) {
      throw new WrongUsernameException("This name already exists.");
    } else if (companyRepository.existsByEmail(company.getEmail())) {
      throw new WrongEmailException("This email is already in use");
    }
    sendSimpleMessage(company.getEmail());
    companyRepository.save(company);
  }

  public void updateCompany(Company company) throws NotFoundException,
          WrongEmailException, WrongUsernameException {
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

  private static ClientResponse sendSimpleMessage(String recipient) {
    Client client = Client.create();
    client.addFilter(new HTTPBasicAuthFilter("api", MAILGUN_API_KEY));
    MultivaluedMapImpl formData = new MultivaluedMapImpl();
    formData.add("from", "Chrysoprase <chrysoprase@onlinequeuing.com>");
    formData.add("to", recipient);
    formData.add("subject", "Online-queuing");
    formData.add("text", "Welcome, we are glad that you signed up! :) \n http://bit.do/eTeGo");
    WebResource webResource = client.resource("https://api.mailgun.net/v3/" + MAILGUN_DOMAIN_NAME
            + "/messages");
    return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
            formData);
  }
}
