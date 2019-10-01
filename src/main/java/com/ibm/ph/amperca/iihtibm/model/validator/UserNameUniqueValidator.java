package com.ibm.ph.amperca.iihtibm.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.ph.amperca.iihtibm.service.UserService;

public class UserNameUniqueValidator implements ConstraintValidator<UniqueUserName, String> {
    
    @Autowired
    private UserService userService;
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        boolean val = userService.isExistingUsername(value);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> value " + val);
        return val;
    }

}
