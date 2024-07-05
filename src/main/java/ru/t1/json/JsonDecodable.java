package ru.t1.json;

public interface JsonDecodable extends JsonConvertible {

  static <T extends JsonDecodable> T decode(String json, Class<T> clazz) throws Exception {
    return objectMapper.readValue(json, clazz);
  }
}