package de.exxcellent.student.softwarearchitecture.transition.planning.routecalculation.connector;

import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.ErrorCode;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.BusinessException;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.TechnicalException;
import de.exxcellent.student.softwarearchitecture.transition.common.json.JsonMapper;
import de.exxcellent.student.softwarearchitecture.transition.planning.routecalculation.connector.types.Mode;
import de.exxcellent.student.softwarearchitecture.transition.planning.routecalculation.connector.types.TripResponseTO;
import de.exxcellent.student.softwarearchitecture.transition.planning.routecalculation.connector.types.TripTO;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.Unirest;
import kong.unirest.UnirestParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
@Component
public class RouteCalculationConnector {

  private static final Logger LOG = LoggerFactory.getLogger(RouteCalculationConnector.class);
  private static final String ENDPOINT = "http://localhost:5000/optimize";

  private final JsonMapper<TripTO> tripJsonMapper;
  private final JsonMapper<TripResponseTO> tripResponseJsonMapper;

  public RouteCalculationConnector() {
    this.tripJsonMapper = new JsonMapper<>(TripTO.class);
    this.tripResponseJsonMapper = new JsonMapper<>(TripResponseTO.class);
  }

  public TripResponseTO calculateTrip(TripTO tripTO, Mode mode) {
    String payload = tripJsonMapper.toJson(tripTO);
    LOG.trace("Send request to {} with payload '{}'", ENDPOINT, payload);

    var response = POST(ENDPOINT)
        .queryString("mode", mode.getValue())
        .body(payload)
        .asString();

    if (response.getStatus() < 300) {
        var responseBody = response.getBody();
        LOG.trace("Received response from {} with payload '{}'", ENDPOINT, responseBody);
        return tripResponseJsonMapper.fromJson(responseBody);
    } else {

      UnirestParsingException parsingError = null;
      if (response.getParsingError().isPresent()) {
        parsingError = response.getParsingError().get();
        var body = parsingError.getOriginalBody();
        LOG.warn("Route calculation service responses error: '{}'", body);
      }

      if (response.getStatus() < 500) {
        throw new BusinessException(ErrorCode.EXTERNAL_SERVICE_ERROR, "Failed to call route calculation service", parsingError);
      } else {
        throw new TechnicalException(ErrorCode.EXTERNAL_SERVICE_ERROR, "Failed to call route calculation service", parsingError);
      }
    }
  }

  private HttpRequestWithBody POST(String url) {
    return Unirest.post(url)
        .header("Content-Type", "application/json")
        .header("Accept", "application/json");
  }
}
