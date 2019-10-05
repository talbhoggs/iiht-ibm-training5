package com.ibm.ph.amperca.iihtibm.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import com.ibm.ph.amperca.iihtibm.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) {
    com.ibm.ph.amperca.iihtibm.model.User user = userService.findByUserName(username);
    if (user == null) {
      throw new UsernameNotFoundException("User " + username + " not found");
    }
    return new org.springframework.security.core.userdetails.User(user.getUserName(),
        user.getPassword(), getAuthorities(user));
  }

  private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
    String[] userRoles =
        user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
    Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
    return authorities;
  }

}
