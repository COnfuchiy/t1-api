package ru.t1.json;

public interface JsonEncodable extends JsonConvertible {

  default String encode() throws Exception {
    return objectMapper.writeValueAsString(this);
  }
}
