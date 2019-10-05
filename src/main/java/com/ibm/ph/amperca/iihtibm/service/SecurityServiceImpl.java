package com.ibm.ph.amperca.iihtibm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

  @Autowired
  UserDetailsService userDetailsService;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public boolean authenticate(String username, String password) throws UsernameNotFoundException {
    UserDetails userDetails = null;
    try {
      userDetails = userDetailsService.loadUserByUsername(username);
    } catch (UsernameNotFoundException ex) {
      return false;
    }

    boolean passwordsMatch = bCryptPasswordEncoder.matches(password, userDetails.getPassword());

    if (!passwordsMatch) {
      return passwordsMatch;
    }

    UsernamePasswordAuthenticationToken userPasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(userDetails, password,
            userDetails.getAuthorities());

    authenticationManager.authenticate(userPasswordAuthenticationToken);

    boolean isAuthenticated = userPasswordAuthenticationToken.isAuthenticated();

    if (isAuthenticated) {
      SecurityContextHolder.getContext().setAuthentication(userPasswordAuthenticationToken);
    }
    return isAuthenticated;
  }

  public Authentication invalidate() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth;
  }
}
