package com.chrysoprase.booking.appuser;

import com.chrysoprase.booking.exception.WrongEmailException;
import com.chrysoprase.booking.exception.WrongUsernameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MediaType;

@Service
public class AppUserService {

  private AppUserRepository appUserRepository;
  private static final String MAILGUN_API_KEY
          = "ca1bfa72f9fdf4b67283b33a6a91a4a9-52b0ea77-a9286eb3";
  private static final String MAILGUN_DOMAIN_NAME
          = "sandbox5bff1c70ecc4406591bffa656835e964.mailgun.org";

  @Autowired
  public AppUserService(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  public AppUser findAppUserById(Integer id) {
    return appUserRepository.findAppUserById(id);
  }

  public AppUser findUserByUsername(String userName) {
    return appUserRepository.findByUsername(userName);
  }

  public void saveUser(AppUser user) throws WrongEmailException, WrongUsernameException {
    if (appUserRepository.existsAppUserByUsername(user.getUsername())) {
      throw new WrongUsernameException("This username already exists.");
    } else if (appUserRepository.existsAppUserByEmail(user.getEmail())) {
      throw new WrongEmailException("This email is already in use");
    }
    sendSimpleMessage(user.getEmail(), user.getUsername());
    appUserRepository.save(user);
  }

  public boolean isDatabaseContainsUsername(String username) {
    return appUserRepository.existsAppUserByUsername(username);
  }

  public String missingParameter(AppUser user) {
    String parameters = "";
    if (user.getUsername() == null && user.getPassword() == null) {
      return parameters = "username, password";
    }
    if (user.getUsername() == null) {
      return parameters = "username";
    }
    if (user.getPassword() == null) {
      return parameters = "password";
    }
    return parameters;
  }

  private static ClientResponse sendSimpleMessage(String recipient, String name) {
    Client client = Client.create();
    client.addFilter(new HTTPBasicAuthFilter("api", MAILGUN_API_KEY));
    MultivaluedMapImpl formData = new MultivaluedMapImpl();
    formData.add("from", "Chrysoprase <chrysoprase@online-queuing.com>");
    formData.add("to", recipient);
    formData.add("subject", "Online-queuing");
    formData.add("text", "Welcome " + name + ", \n we are glad that you signed up! :) \n http://bit.do/eTeGo");
    WebResource webResource = client.resource("https://api.mailgun.net/v3/" + MAILGUN_DOMAIN_NAME
            + "/messages");
    return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
            formData);
  }
}
