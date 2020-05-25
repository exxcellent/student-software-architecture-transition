package de.exxcellent.student.softwarearchitecture.transition.tests.integration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import de.exxcellent.student.softwarearchitecture.transition.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.lang.annotation.*;

/**
 * This annotation summarizes all default test annotation of Spring boot we use.
 * Annotate your test class with \@RunWith(SpringBootJunitRunner.class) and
 * \@TestSubject and all annotations
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"TEST"})
@ContextConfiguration(classes = { Application.class })
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    DbUnitTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    MockitoTestExecutionListener.class })
@TestPropertySource(locations = "classpath:application-LOCAL.properties")
public @interface IntegrationTest {
  // Intentionally empty
}