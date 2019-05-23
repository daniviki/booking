package com.chrysoprase.booking;

import com.chrysoprase.booking.appuser.AppUser;
import com.chrysoprase.booking.appuser.AppUserService;
import com.chrysoprase.booking.exception.MissingParameterException;
import com.chrysoprase.booking.exception.WrongPasswordException;
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

  @Autowired
  public MainController(AppUserService userService, BCryptPasswordEncoder passwordEncoder, MainService mainService) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.mainService = mainService;
  }

  @GetMapping("/sign-up")
  public String signUpPage(Model model) {
    model.addAttribute("new_user", new AppUser());
    return "register_form";
  }

  @PostMapping("/sign-up")
  public String register(@ModelAttribute(name = "new_user") AppUser user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.saveUser(user);
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String loginPage() {

    return "login_form";
  }

  @PostMapping("/login")
  public String login(AppUser user) throws MissingParameterException, WrongPasswordException {
    mainService.login(user);
    return "login_form";
  }
  
  /*@RequestMapping(path = "/add", method = RequestMethod.POST)
  public String addPost(User new_user, HttpServletRequest request) {
    userService.addUser(new_user);
    return "redirect:/reddit/user/";
  }

  @RequestMapping(path = "/add", method = RequestMethod.GET)
  public String getUserPostForm(Model model) {
    model.addAttribute("new_user", new User());
    return "user_addform";*/
}
