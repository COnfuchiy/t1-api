package ru.t1.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class GetCodeResponse {
  String code;

  public GetCodeResponse(String code) {
    this.code = code;
  }

  @NotNull
  @Contract(value = "_ -> new", pure = true)
  public static GetCodeResponse fromHttpResponse(String response) {
    return new GetCodeResponse(response);
  }
}
