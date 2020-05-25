package de.exxcellent.student.softwarearchitecture.transition.tests.integration.inspectors;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import de.exxcellent.student.softwarearchitecture.transition.tests.integration.AuthenticationUtil;
import de.exxcellent.student.softwarearchitecture.transition.tests.integration.IntegrationTest;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */

@RunWith(SpringRunner.class)
@IntegrationTest
public class InspectorsResourcesV1IT {

  private static final String PATH = "/v1/inspectors";

  @LocalServerPort
  private int port;

  private TestRestTemplate restTemplate = new TestRestTemplate();
  private HttpHeaders headers;

  @Test
  @DatabaseSetup("classpath:data/planning_database.xml")
  void inspectors_GET() throws JSONException {
    headers = AuthenticationUtil.headersWithAuthorization(headers, port);

    assertGETAll();

  }

  private void assertGETAll() throws JSONException {
    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/v1/inspectors"),
        HttpMethod.GET, entity, String.class);

    System.out.println(response.getBody());

    String expected = "{\"inspectors\":[{\"version\":0,\"inspectorId\":1,\"firstName\":\"Inspector\",\"lastName\":\"Integrationtest\"}]}";

    JSONAssert.assertEquals(expected, response.getBody(), false);
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

}
