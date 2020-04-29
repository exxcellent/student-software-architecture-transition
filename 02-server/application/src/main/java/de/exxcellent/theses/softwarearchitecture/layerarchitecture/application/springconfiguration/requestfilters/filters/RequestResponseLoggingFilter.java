package de.exxcellent.theses.softwarearchitecture.layerarchitecture.application.springconfiguration.requestfilters.filters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.output.TeeOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.mock.web.DelegatingServletOutputStream;


/**
 * I wrap the REST request and response processing.
 *
 * I log the called REST resource and the response code for the request.
 *
 * @author Andre Lehnert, eXXcellent solutions consulting & software gmbh
 * @see <a href="https://www.baeldung.com/spring-boot-add-filter">How to Define a Spring Boot Filter?</a>
 */
// @Component // activate the filter globally
// The filter is registered by the {@link FilterRegistrationConfiguration} for specific url pattern
@Order(1)
public class RequestResponseLoggingFilter implements Filter {

    private static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter: {}", getClass().getCanonicalName());
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
       
        if (LOG.isDebugEnabled()) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;

            LOG.debug("Receive {}: {}", req.getMethod(), req.getRequestURI());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);

            chain.doFilter(req,new HttpServletResponseWrapper(res) {
                @Override
                public ServletOutputStream getOutputStream() throws IOException {
                    return new DelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), ps));
                }
                @Override
                public  PrintWriter getWriter() throws IOException {
                    return new PrintWriter(new DelegatingServletOutputStream(
                        new TeeOutputStream(super.getOutputStream(), ps)));
                }
            });

            LOG.debug("Send response for '{}: {}' with status code '{}' and body: '{}'",
                req.getMethod(), req.getRequestURI(), res.getStatus(), baos.toString());
        } else {
            chain.doFilter(request, response);
        }
    }


    @Override
    public void destroy() {
        LOG.warn("Destructing filter: {}", getClass().getCanonicalName());
    }
}
