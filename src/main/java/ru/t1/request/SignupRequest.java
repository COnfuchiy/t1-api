package ru.t1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.t1.json.JsonEncodable;

public class SignupRequest implements JsonEncodable {

    public String lastName;
    public String firstName;
    public String email;
    public String role;

    public SignupRequest(String lastName, String firstName, String email, String role) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.role = role;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }
    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }
}
