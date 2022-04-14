package com.subwaymonitor.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;

/** Utility functions to handle JSON serialization/deserialization. */
public final class JsonUtils {

  private JsonUtils() {}

  public static String writeValueAsString(final Object object) throws JsonProcessingException {
    return JacksonMapperFactory.create().writer().writeValueAsString(object);
  }
}
