package com.example.gooddeeds.utils;

import com.example.gooddeeds.model.ApplicationUser;
import org.apache.catalina.User;

import java.util.List;

public class UserHelper {
    public static List<ApplicationUser> hidePassword(List<ApplicationUser> users){
        for (ApplicationUser user : users) {
            user.setPassword(null);
        }
        return users;
    }
}
