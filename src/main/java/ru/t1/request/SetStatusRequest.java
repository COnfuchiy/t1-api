package ru.t1.request;

import ru.t1.json.JsonEncodable;

public class SetStatusRequest implements JsonEncodable {
    public String token;
    public String status;

    public SetStatusRequest(String token, String status) {
        this.token = token;
        this.status = status;
    }

}
