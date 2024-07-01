package ru.t1;

import java.util.Base64;
public class Main {
    public static void main(String[] args) {
        System.out.println(encodeToBase64("semm0202@yandex.ru", "d4333bb461c07a33a4cec69785ef13b8"));
    }

    public static String encodeToBase64(String email, String token) {
        return Base64.getEncoder().encodeToString((email+":"+token).getBytes());
    }
}