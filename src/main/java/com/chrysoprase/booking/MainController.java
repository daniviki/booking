package com.chrysoprase.booking;

import com.chrysoprase.booking.appuser.AppUser;
import com.chrysoprase.booking.appuser.AppUserService;
import com.chrysoprase.booking.company.Company;
import com.chrysoprase.booking.company.CompanyService;
import com.chrysoprase.booking.exception.MissingParameterException;
import com.chrysoprase.booking.exception.WrongPasswordException;
import com.chrysoprase.booking.utility.Utility;
import com.chrysoprase.booking.utility.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

  private BCryptPasswordEncoder passwordEncoder;
  private AppUserService userService;
  private MainService mainService;
  private CompanyService companyService;
  private UtilityService utilityService;

  @Autowired
  public MainController(AppUserService userService, BCryptPasswordEncoder passwordEncoder,
                        MainService mainService, CompanyService companyService, UtilityService utilityService) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.mainService = mainService;
    this.companyService = companyService;
    this.utilityService = utilityService;
  }

  @GetMapping("/user/sign-up")
  public String userSignUpPage(Model model) {
    model.addAttribute("new_user", new AppUser());
    return "user_register_form";
  }

  @PostMapping("/user/sign-up")
  public String userRegister(@ModelAttribute(name = "new_user") AppUser user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.saveUser(user);
    return "redirect:/login";
  }

  @GetMapping("/company/sign-up")
  public String companySignUpPage(Model model) {
    model.addAttribute("new_company", new Company());
    model.addAttribute("new_utility", new Utility());

    return "company_register_form";
  }

  @PostMapping("/company/sign-up")
  public String companyRegister(@ModelAttribute(name = "new_utility") Utility utility,
          @ModelAttribute(name = "new_company") Company company) {
    utilityService.addUtility(utility);
    company.setPassword(passwordEncoder.encode(company.getPassword()));
    companyService.saveCompany(company);
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String loginPage() {

    return "login_form";
  }

  @PostMapping("/login")
  public String login(Model model, AppUser user) throws MissingParameterException,
          WrongPasswordException {
    model.addAttribute("username", user.getUsername());
    model.addAttribute("password", user.getPassword());
    mainService.login(user);
    return "login_form";
  }

  @GetMapping("/home")
  public String hii() {
    return "index";
  }
}
