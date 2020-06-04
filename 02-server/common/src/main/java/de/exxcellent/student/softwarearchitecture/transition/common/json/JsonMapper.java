package de.exxcellent.student.softwarearchitecture.transition.common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.ErrorCode;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.TechnicalException;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */

public class JsonMapper<T>  {

  private final ObjectMapper objectMapper;
  private final Class<T> clazz;

  public JsonMapper(Class<T> handleClass) {
    this.objectMapper = new ObjectMapper();
    this.clazz = handleClass;
  }

  public String toJson(T javaObject) {
    try {
      return objectMapper.writeValueAsString(javaObject);
    } catch (JsonProcessingException e) {
      throw new TechnicalException(ErrorCode.JSON_SERIALIZATION_ERROR, e);
    }
  }

  public T fromJson(String json) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (JsonProcessingException e) {
      throw new TechnicalException(ErrorCode.JSON_SERIALIZATION_ERROR, e);
    }
  }
}
