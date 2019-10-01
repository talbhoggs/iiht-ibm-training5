package com.ibm.ph.amperca.iihtibm.service;

import com.ibm.ph.amperca.iihtibm.model.User;

public interface UserService {
    public User saveUser( User user);
    public User getUserById(Long id);
    public void deleteUser(User user);
    public boolean isExistingEmail(String mail);
    public boolean isExistingUsername(String name);
}
