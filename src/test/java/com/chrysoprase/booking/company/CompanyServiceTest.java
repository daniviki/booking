package com.chrysoprase.booking.company;

import com.chrysoprase.booking.exception.WrongEmailException;
import com.chrysoprase.booking.exception.WrongUsernameException;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CompanyServiceTest {

  private CompanyService companyService;

  @Mock
  private CompanyRepository companyRepository;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    companyService = new CompanyService(companyRepository);
  }

  @Test(expected = NotFoundException.class)
  public void companyService_updateCompany_throwsException()
          throws NotFoundException, WrongEmailException, WrongUsernameException {
    when(companyRepository.findCompanyById(any(Long.class))).thenReturn(null);
    companyService.updateCompany(new Company());
  }
}
