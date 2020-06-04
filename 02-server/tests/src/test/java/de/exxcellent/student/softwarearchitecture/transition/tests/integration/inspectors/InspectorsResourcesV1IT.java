package de.exxcellent.student.softwarearchitecture.transition.tests.integration.inspectors;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import de.exxcellent.student.softwarearchitecture.transition.planning.inspectors.resources.types.InspectorTO;
import de.exxcellent.student.softwarearchitecture.transition.tests.integration.AuthenticationUtil;
import de.exxcellent.student.softwarearchitecture.transition.tests.integration.IntegrationTest;
import org.json.JSONException;
import org.junit.Ignore;
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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */

@RunWith(SpringRunner.class)
@IntegrationTest
@Ignore // Skip JUnit 4 (Vintage) Test Engine
public class InspectorsResourcesV1IT {

  private static final String PATH = "/v1/inspectors";

  @LocalServerPort
  private int port;

  private TestRestTemplate restTemplate = new TestRestTemplate();
  private HttpHeaders headers;

  @Test
  @DatabaseSetup("classpath:data/planning_database.xml")
  void inspectors_CRUD() throws JSONException {
    headers = AuthenticationUtil.headersWithAuthorization(headers, port);

    var actual = assert_CREATE();
    assert_READ(actual);
    assert_UPDATE(actual);
    assert_DELETE(actual);

    assert_GET_All(actual);
  }

  private void assert_GET_All(InspectorTO unexpected) throws JSONException {
    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET, entity, String.class);

    System.out.println(response.getBody());

    String expected = "{\"inspectors\":[{\"version\":0,\"inspectorId\":1,\"firstName\":\"Inspector\",\"lastName\":\"Integrationtest\"}]}";

    JSONAssert.assertEquals(expected, response.getBody(), false);

    assertThat(response.getBody()).doesNotContain("\"inspectorId\":" + unexpected.getInspectorId());
  }


  private InspectorTO assert_CREATE() {
    InspectorTO expected = getExpected();

    HttpEntity<InspectorTO> entity = new HttpEntity<>(expected, headers);

    ResponseEntity<InspectorTO> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST, entity, InspectorTO.class);

    var actual = response.getBody();

    assertThat(actual).isNotNull();
    assertThat(actual.getInspectorId()).isPositive();
    assertThat(actual.getVersion()).isNotNull();
    assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
    assertThat(actual.getLastName()).isEqualTo(expected.getLastName());

    return actual;
  }
  private void assert_READ(InspectorTO expected) {
    HttpEntity<Void> entity = new HttpEntity<>(null, headers);

    ResponseEntity<InspectorTO> response = restTemplate.exchange(
        createURLWithPort(PATH, String.valueOf(expected.getInspectorId())),
        HttpMethod.GET, entity, InspectorTO.class);

    var actual = response.getBody();

    assertThat(actual).isNotNull();
    assertThat(actual.getInspectorId()).isPositive().isEqualTo(expected.getInspectorId());
    assertThat(actual.getVersion()).isNotNull().isEqualTo(0);
    assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
    assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
  }
  private void assert_UPDATE(InspectorTO expected) {
    expected.setFirstName("UPDATED");

    HttpEntity<InspectorTO> entity = new HttpEntity<>(expected, headers);

    ResponseEntity<InspectorTO> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PUT, entity, InspectorTO.class);

    var actual = response.getBody();

    assertThat(actual).isNotNull();
    assertThat(actual.getInspectorId()).isPositive().isEqualTo(expected.getInspectorId());
    assertThat(actual.getVersion()).isNotNull().isEqualTo(expected.getVersion() + 1);
    assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
    assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
  }
  private void assert_DELETE(InspectorTO expected) {
    HttpEntity<Void> entity = new HttpEntity<>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(PATH, String.valueOf(expected.getInspectorId())),
        HttpMethod.DELETE, entity, String.class);

    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  private InspectorTO getExpected() {
    var expected = new InspectorTO();
    expected.setFirstName("Firstname");
    expected.setLastName("Lastname");
    return expected;
  }

  private String createURLWithPort(String... pathParams) {
    return "http://localhost:" + port + String.join("/", pathParams);
  }

}
