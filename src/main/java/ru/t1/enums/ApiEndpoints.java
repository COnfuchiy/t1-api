package ru.t1.enums;

public enum ApiEndpoints {
    GET_ROLES("GET_ROLES"),
    SIGNUP("SIGNUP"),
    GET_CODE("GET_CODE"),
    SET_STATUS("SET_STATUS");
    private final String name;

    ApiEndpoints(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
