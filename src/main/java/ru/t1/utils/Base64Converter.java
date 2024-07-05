package ru.t1.utils;

import java.util.Base64;

public class Base64Converter {
    public static String encode(String email, String token) {
        return Base64.getEncoder().encodeToString((email+":"+token).getBytes());
    }
}
