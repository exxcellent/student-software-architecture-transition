package de.exxcellent.student.softwarearchitecture.transition.springconfiguration.requestfilters;

import de.exxcellent.student.softwarearchitecture.transition.springconfiguration.requestfilters.filters.CorsFilter;
import de.exxcellent.student.softwarearchitecture.transition.springconfiguration.requestfilters.filters.RequestResponseLoggingFilter;
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



    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        final FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;

    }
}