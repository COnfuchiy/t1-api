package ru.t1.response;

public class ResponseEntity<T> {
  private final String rawResponse;
  private final T response;

  public ResponseEntity(String rawResponse, T response) {
    this.rawResponse = rawResponse;
    this.response = response;
  }

  public String getRawResponse() {
    return rawResponse;
  }

  public T getResponse() {
    return response;
  }
}