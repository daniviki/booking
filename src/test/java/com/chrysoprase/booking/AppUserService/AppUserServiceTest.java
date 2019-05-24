package com.chrysoprase.booking.AppUserService;

import com.chrysoprase.booking.appuser.AppUser;
import com.chrysoprase.booking.appuser.AppUserRepository;
import com.chrysoprase.booking.appuser.AppUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class AppUserServiceTest {

  private AppUserService appUserService;
  private AppUser userWithoutUsernameAndPassword;
  private AppUser userWithoutUsername;
  private AppUser userWithoutPassword;
  private AppUser testUser;

  @Mock
  private AppUserRepository appUserRepository;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    appUserService = new AppUserService(appUserRepository);
    userWithoutUsernameAndPassword = new AppUser();
    userWithoutUsername = new AppUser();
    userWithoutUsername.setPassword("blabla");
    userWithoutPassword = new AppUser();
    userWithoutPassword.setUsername("Asd");
    testUser = new AppUser(1, "Decent", "mypass", "master", "gmail@gmail.com");
  }

  @Test
  public void appUserService_missingParameter_nameAndPwMissing() {
    String usernamePassword = "username, password";
    assertEquals(appUserService.missingParameter(userWithoutUsernameAndPassword), usernamePassword);
  }

  @Test
  public void appUserService_missingParameter_nameMissing() {
    String username = "username";
    assertEquals(appUserService.missingParameter(userWithoutUsername), username);
  }

  @Test
  public void appUserService_missingParameter_pwMissing() {
    String password = "password";
    assertEquals(appUserService.missingParameter(userWithoutPassword), password);
  }

  @Test
  public void appUserService_missingParameter_allOk() {
    assertEquals("", appUserService.missingParameter(testUser));
  }

  @Test
  public void appUserService_isDatabaseContainsUsername_true() {
    when(appUserRepository.existsAppUserByUsername(any(String.class))).thenReturn(true);
    assertTrue(appUserService.isDatabaseContainsUsername("Decent"));
  }

  @Test
  public void appUserService_findAppUserById_returnsAppUser() {
    when(appUserRepository.findAppUserById(any(Integer.class))).thenReturn(testUser);
    assertEquals(appUserService.findAppUserById(1), testUser);
  }

  @Test
  public void appUserService_findUserByUsername_returnsFalse() {
    when(appUserRepository.findByUsername(any(String.class))).thenReturn(null);
    assertNull(appUserService.findUserByUsername("ilyenNincsIs"));
  }

}