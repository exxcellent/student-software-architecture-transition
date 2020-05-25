package de.exxcellent.student.softwarearchitecture.transition.tests.integration.appointments;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import de.exxcellent.student.softwarearchitecture.transition.application.resources.appointments.types.AppointmentTO;
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
public class AppointmentsResourcesV1IT {

  private static final String PATH = "/v1/appointments";

  @LocalServerPort
  private int port;

  private TestRestTemplate restTemplate = new TestRestTemplate();
  private HttpHeaders headers;

  @Test
  @DatabaseSetup("classpath:data/planning_database.xml")
  void appointments_CRUD() throws JSONException {
    headers = AuthenticationUtil.headersWithAuthorization(headers, port);

    var actual = assert_CREATE();
    assert_READ(actual);
    assert_UPDATE(actual);
    assert_DELETE(actual);

    assert_GET_All(actual);
  }

  private void assert_GET_All(AppointmentTO unexpected) throws JSONException {
    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET, entity, String.class);

    System.out.println(response.getBody());

    String expected = "{\"appointments\":[{\"version\":0,\"appointmentId\":1,\"description\":null,\"date\":\"2020-05-09\",\"travelDurationInSeconds\":null,\"startTime\":null,\"durationInSeconds\":null,\"finished\":false,\"processId\":1}]}";

    JSONAssert.assertEquals(expected, response.getBody(), false);

    assertThat(response.getBody()).doesNotContain("\"appointmentId\":" + unexpected.getAppointmentId());
  }


  private AppointmentTO assert_CREATE() {
    AppointmentTO expected = getExpected();

    HttpEntity<AppointmentTO> entity = new HttpEntity<>(expected, headers);

    ResponseEntity<AppointmentTO> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST, entity, AppointmentTO.class);

    var actual = response.getBody();

    assertThat(actual).isNotNull();
    assertThat(actual.getAppointmentId()).isPositive();
    assertThat(actual.getVersion()).isNotNull();
    assertThat(actual.getDate()).isEqualTo(expected.getDate());
    assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
    assertThat(actual.getFinished()).isEqualTo(expected.getFinished());
    assertThat(actual.getProcessId()).isEqualTo(expected.getProcessId());
    assertThat(actual.getDurationInSeconds()).isEqualTo(expected.getDurationInSeconds());
    assertThat(actual.getStartTime()).isEqualTo(expected.getStartTime());
    assertThat(actual.getTravelDurationInSeconds()).isEqualTo(expected.getTravelDurationInSeconds());

    return actual;
  }
  private void assert_READ(AppointmentTO expected) {
    HttpEntity<Void> entity = new HttpEntity<>(null, headers);

    ResponseEntity<AppointmentTO> response = restTemplate.exchange(
        createURLWithPort(PATH, String.valueOf(expected.getAppointmentId())),
        HttpMethod.GET, entity, AppointmentTO.class);

    var actual = response.getBody();

    assertThat(actual).isNotNull();
    assertThat(actual.getAppointmentId()).isEqualTo(expected.getAppointmentId());
    assertThat(actual.getVersion()).isEqualTo(expected.getVersion());
    assertThat(actual.getDate()).isEqualTo(expected.getDate());
    assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
    assertThat(actual.getFinished()).isEqualTo(expected.getFinished());
    assertThat(actual.getProcessId()).isEqualTo(expected.getProcessId());
    assertThat(actual.getDurationInSeconds()).isEqualTo(expected.getDurationInSeconds());
    assertThat(actual.getStartTime()).isEqualTo(expected.getStartTime());
    assertThat(actual.getTravelDurationInSeconds()).isEqualTo(expected.getTravelDurationInSeconds());
  }
  private void assert_UPDATE(AppointmentTO expected) {
    expected.setDescription("UPDATED");

    HttpEntity<AppointmentTO> entity = new HttpEntity<>(expected, headers);

    ResponseEntity<AppointmentTO> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PUT, entity, AppointmentTO.class);

    var actual = response.getBody();

    assertThat(actual).isNotNull();
    assertThat(actual.getAppointmentId()).isEqualTo(expected.getAppointmentId());
    assertThat(actual.getVersion()).isEqualTo(expected.getVersion() + 1);
    assertThat(actual.getDate()).isEqualTo(expected.getDate());
    assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
    assertThat(actual.getFinished()).isEqualTo(expected.getFinished());
    assertThat(actual.getProcessId()).isEqualTo(expected.getProcessId());
    assertThat(actual.getDurationInSeconds()).isEqualTo(expected.getDurationInSeconds());
    assertThat(actual.getStartTime()).isEqualTo(expected.getStartTime());
    assertThat(actual.getTravelDurationInSeconds()).isEqualTo(expected.getTravelDurationInSeconds());
  }
  private void assert_DELETE(AppointmentTO expected) {
    HttpEntity<Void> entity = new HttpEntity<>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(PATH, String.valueOf(expected.getAppointmentId())),
        HttpMethod.DELETE, entity, String.class);

    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  private AppointmentTO getExpected() {
    var expected = new AppointmentTO();
    expected.setDate("2020-01-01");
    expected.setFinished(true);
    expected.setDescription("Description");
    expected.setDurationInSeconds(100L);
    expected.setTravelDurationInSeconds(200L);
    expected.setStartTime("10:00");
    expected.setProcessId(1L);
    return expected;
  }

  private String createURLWithPort(String... pathParams) {
    return "http://localhost:" + port + String.join("/", pathParams);
  }

}
