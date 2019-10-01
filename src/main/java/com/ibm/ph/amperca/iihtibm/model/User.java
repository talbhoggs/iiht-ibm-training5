package com.ibm.ph.amperca.iihtibm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Column(name = "USER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    @NotEmpty(message = "Name is required!")
    private String name;

    @Column(name = "USERNAME")
    @NotEmpty(message = "Username is required!")
    private String userName;

    @Column(name = "PASSWORD")
    @NotEmpty(message = "Password is required!")
    private String password;

    @NotEmpty(message = "Email is a field!")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    @Column(name = "EMAIL")
    private String email;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", username=" + userName + ", password=" + password + ", email="
                + email + "]";
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
