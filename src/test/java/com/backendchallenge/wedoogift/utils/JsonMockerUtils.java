package com.backendchallenge.wedoogift.utils;

import com.google.gson.Gson;
import org.assertj.core.api.Assertions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

// Utils to allow to access json resource files from unit tests by passing name file only
public final class JsonMockerUtils {
  private static final Gson GSON = new Gson();

  public static <T> T getClassFromJson(Class<T> type, String fileName) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(JsonMockerUtils.class.getResourceAsStream("/json/" + fileName + ".json")),
                StandardCharsets.UTF_8))) {
      return GSON.fromJson(reader, type);
    } catch (IOException io) {
      Assertions.fail("Cannot retrieve " + fileName + ".json");
    }
    return null;
  }

  private JsonMockerUtils() {}
}
