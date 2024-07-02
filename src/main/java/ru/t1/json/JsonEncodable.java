package ru.t1.json;

public interface JsonEncodable<T>extends JsonConvertible {

  static <T extends JsonDecodable<T>> String encode(Class<T> clazz) throws Exception {
    return objectMapper.writeValueAsString(clazz);
  }
}
