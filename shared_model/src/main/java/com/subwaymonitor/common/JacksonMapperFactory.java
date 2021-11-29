package com.subwaymonitor.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.TimeZone;

/** Utility class to build a standard {@link ObjectMapper}. */
public class JacksonMapperFactory {

  private JacksonMapperFactory() {}

  /**
   * Creates an {@link ObjectMapper} instance with standard configurations.
   *
   * @return the configured mapper.
   */
  public static ObjectMapper create() {
    final ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.setVisibility(
        VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
    objectMapper.setDefaultSetterInfo(JsonSetter.Value.forContentNulls(Nulls.SKIP));
    objectMapper.setTimeZone(TimeZone.getDefault());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    final ZonedDateTimeSerializer zonedDateTimeSerializer =
        new ZonedDateTimeSerializer(new DateTimeFormatterBuilder().appendInstant(0).toFormatter());
    final JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(ZonedDateTime.class, zonedDateTimeSerializer);
    javaTimeModule.addDeserializer(ZonedDateTime.class, InstantDeserializer.ZONED_DATE_TIME);
    return objectMapper;
  }
}
