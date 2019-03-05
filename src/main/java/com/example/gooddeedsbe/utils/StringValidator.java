package com.example.gooddeedsbe.utils;

public class StringValidator {
    public static boolean checkIfNullOrWhitespace(String str){
        if(str == null)
            return true;
        if(str.trim().length() == 0)
            return true;
        return false;

    }
}
