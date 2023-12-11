package com.example.projectjeu.ui.login;

public class Param {
    private String token;
    private static Param param;

    private Param() {
    }

    public static Param getInstance() {
        if (param == null) {
            param = new Param();
        }
        return param;
    }

    public void setToken(String token) { this.token = token; }

    public String getToken() { return token; }
}