package de.exxcellent.student.softwarearchitecture.transition.tests.integration;

import de.exxcellent.student.softwarearchitecture.transition.application.resources.authentication.types.JwtRequest;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.authentication.types.JwtResponse;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class AuthenticationUtil {

  private AuthenticationUtil() {}

  public static HttpHeaders headersWithAuthorization(HttpHeaders existingHeaders, int port) {
    if (existingHeaders != null && existingHeaders.containsKey("Authorization")) {
      return existingHeaders;
    }

    var headers = new HttpHeaders();

    var token = authenticate(port);
    headers.add("Authorization", "Bearer " + token);

    return headers;
  }

  public static String authenticate(int port) {
    var request = new JwtRequest();
    request.setUserName("ADMIN");
    request.setPassword("thesis");

    HttpEntity<JwtRequest> entity = new HttpEntity<>(request, new HttpHeaders());

    ResponseEntity<JwtResponse> response = new TestRestTemplate().exchange(
        "http://localhost:" + port + "/v1/authenticate",
        HttpMethod.POST, entity, JwtResponse.class);

    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getJwtToken()).isNotNull();

    System.out.println(response.getBody().getJwtToken());

    return response.getBody().getJwtToken();
  }

  private static String createURLWithPort(String uri, int port) {
    return "http://localhost:" + port + uri;
  }
}
