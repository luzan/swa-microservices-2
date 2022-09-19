package com.example.commonmodule.security;

import com.example.commonmodule.security.enums.UserRole;

import java.util.HashSet;
import java.util.Set;

public class AppServiceUtils {

    public static Set<UserRole> convertStringRolesSetToEnumSet(Set<String> roles) {
        Set<UserRole> userRoleSet = new HashSet<>();
        for (String role : roles) {
            userRoleSet.add(Enum.valueOf(UserRole.class, role));
        }
        return userRoleSet;
    }

}
