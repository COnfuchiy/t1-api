package ru.t1.response;

public class ResponseEntity<T> {
  private final String rawResponse;
  private final T response;

  public ResponseEntity(String rawResponse, T jsonResponse) {
    this.rawResponse = rawResponse;
    this.response = jsonResponse;
  }

  public String getRawResponse() {
    return rawResponse;
  }

  public T getResponse() {
    return response;
  }
}