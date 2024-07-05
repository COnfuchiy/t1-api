package ru.t1.response;

import ru.t1.json.JsonDecodable;

import java.util.List;

public class GetRolesResponse implements JsonDecodable {
  public List<String> roles;

  public static GetRolesResponse decode(String json) throws Exception {
    return JsonDecodable.decode(json, GetRolesResponse.class);
  }
}
