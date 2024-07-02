package ru.t1.response;

import ru.t1.json.JsonDecodable;

public class GetRolesResponse implements JsonDecodable<GetRolesResponse> {
  public String[] roles;

  public static GetRolesResponse decode(String json) throws Exception {
    return JsonDecodable.decode(json, GetRolesResponse.class);
  }
}
