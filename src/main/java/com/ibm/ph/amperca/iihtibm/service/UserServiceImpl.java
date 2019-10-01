package com.ibm.ph.amperca.iihtibm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ph.amperca.iihtibm.model.User;
import com.ibm.ph.amperca.iihtibm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser( User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public boolean isExistingEmail(String mail) {
        User user = userRepository.findByEmail(mail);
        return (user!= null ? true : false);
    }

    @Override
    public boolean isExistingUsername(String name) {
        User user = userRepository.findByEmail(name);
        return (user!= null ? true : false);
    }

}
