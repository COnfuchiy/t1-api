package ru.t1.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonDecodable<T> extends JsonConvertible {

  static <T extends JsonDecodable<T>> T decode(String json, Class<T> clazz) throws Exception {
    return objectMapper.readValue(json, clazz);
  }
}