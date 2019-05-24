package com.chrysoprase.booking.appuser;

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
          = "158d5ba190bcf4bd90afacf43ca22572-52b0ea77-23ee444c";
  private static final String MAILGUN_DOMAIN_NAME
          = "sandboxc16c1932d9384d5c974eb932752f791d.mailgun.org";

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

  public void saveUser(AppUser user) {
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
    formData.add("text", "Welcome " + name + ", \n we are glad that you signed up! :)");
    WebResource webResource = client.resource("https://api.mailgun.net/v3/" + MAILGUN_DOMAIN_NAME
            + "/messages");
    return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
            formData);
  }
}
