package com.ibm.ph.amperca.iihtibm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ibm.ph.amperca.iihtibm.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  @Query("SELECT t FROM Role t where t.name = :name")
  Role findByRoleName(String name);

}
