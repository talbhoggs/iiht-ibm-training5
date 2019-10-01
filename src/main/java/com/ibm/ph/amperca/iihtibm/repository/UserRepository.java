package com.ibm.ph.amperca.iihtibm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ibm.ph.amperca.iihtibm.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

    public User findByEmail(String email);

    @Query("SELECT t FROM User t where t.userName = :name")
    public User findByUserName(String name);

}
