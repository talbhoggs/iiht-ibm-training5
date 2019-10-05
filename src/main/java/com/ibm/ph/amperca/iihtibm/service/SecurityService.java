package com.ibm.ph.amperca.iihtibm.service;

import org.springframework.security.core.Authentication;

public interface SecurityService {
    boolean authenticate(String username, String password);
    Authentication invalidate();
}
