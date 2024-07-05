package ru.t1.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class GetCodeResponse extends MessageResponse{

  public GetCodeResponse(String message) {
    super(message);
  }

  public String getCode(){
    return getMessage();
  }
}
