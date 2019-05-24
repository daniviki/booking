package com.chrysoprase.booking.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CurrentUserDetails implements UserDetails {
  private Long userID;
  private String emailAddress;
  private String password;
  private Role role;


  public CurrentUserDetails(Long userID, String emailAddress, String password, Role role) {
    super();
    this.userID = userID;
    this.emailAddress = emailAddress;
    this.password = password;
    this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

    list.add(new SimpleGrantedAuthority(role.toString()));

    return list;
  }

  @Override
  public String getPassword() {
    return this.password;
  }


  @Override
  public String getUsername() {
    return this.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
