package de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.requestfilters.filters;

import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.security.JwtTokenUtil;
import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.security.types.UserCredentials;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.api.UserComponent;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
  private static final Logger LOG = LoggerFactory.getLogger(JwtRequestFilter.class);

  private final UserComponent userComponent;
  private final JwtTokenUtil jwtTokenUtil;

  @Autowired
  public JwtRequestFilter(UserComponent userComponent, JwtTokenUtil jwtTokenUtil) {
    this.userComponent = userComponent;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
      // do not block OPTIONS requests with 403 "no permission"
      definePreflightRequestPlaceholder(request);

    } else {

      final String requestTokenHeader = request.getHeader("Authorization");

      String username = null;
      String jwtToken = null;
      // JWT Token is in the form "Bearer token". Remove Bearer word and get
      // only the Token
      if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
        jwtToken = requestTokenHeader.substring(7);
        try {
          username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
          System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
          System.out.println("JWT Token has expired");
        }
      } else {
        logger.warn("JWT Token does not begin with Bearer String");
      }

      // Once we get the token validate it.
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        var userDO = this.userComponent.findByName(username);
        var userDetails = new UserCredentials(userDO.getName());

        // if token is valid configure Spring Security to manually set
        // authentication
        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
          usernamePasswordAuthenticationToken
              .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          // After setting the Authentication in the context, we specify
          // that the current user is authenticated. So it passes the
          // Spring Security Configurations successfully.
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
      }
    }

    chain.doFilter(request, response);
  }

  private void definePreflightRequestPlaceholder(HttpServletRequest request) {
    var userDetails = new UserCredentials("CORS");
    var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
    usernamePasswordAuthenticationToken
        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
  }

}
