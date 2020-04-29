package de.exxcellent.theses.softwarearchitecture.layerarchitecture.application.springconfiguration.requestfilters;

import de.exxcellent.theses.softwarearchitecture.layerarchitecture.application.springconfiguration.requestfilters.filters.RequestResponseLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * TODO [AL] class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting & software gmbh
 */
@Configuration
public class FilterRegistrationConfiguration {

    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
        final FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());

        registrationBean.addUrlPatterns("/*");

        return registrationBean;

    }
}