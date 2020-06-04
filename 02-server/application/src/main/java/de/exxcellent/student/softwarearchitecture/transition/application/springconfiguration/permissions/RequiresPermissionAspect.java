package de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.permissions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.BusinessException;
import de.exxcellent.student.softwarearchitecture.transition.planning.user.api.types.Permission;
import de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.security.JwtTokenUtil;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * I implement the validation logic of the {@link RequiresPermission} annotation.
 *
 * The logic will be wrapped around the annotated method.
 *
 * @author Andre Lehnert, eXXcellent solutions consulting & software gmbh
 */
@Aspect
@Component
public class RequiresPermissionAspect {
    private static Logger LOG = LoggerFactory.getLogger(RequiresPermissionAspect.class);

    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    public RequiresPermissionAspect(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * I validate the permissions of an user.
     * <p>
     * A JSON Web Token is used for http authentication. The token contains the permissions of the user. The required
     * permissions are defined as annotation parameter.
     * <p>
     * All required permissions have to be a part of the user permissions.
     *
     * @param joinPoint of the annotated method
     *
     * @return next {@link ProceedingJoinPoint}
     *
     * @throws Throwable         if the {@link ProceedingJoinPoint} throws an exception
     * @throws BusinessException if the user has not all required permissions
     */
    @Around("@annotation(de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.permissions.RequiresPermission)")
    public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {

        List<Permission> requiredPermissions = new ArrayList<>();
        List<String> requiredPermissionStrings = new ArrayList<>();
        String joinedRequiredPermissions = "";

        // get permissions from annotation
        if (getCurrentMethod(joinPoint).isAnnotationPresent(RequiresPermission.class)) {
            Permission[] roles = getCurrentMethod(joinPoint).getAnnotation(RequiresPermission.class).value();
            requiredPermissions = Arrays.asList(roles);

            requiredPermissionStrings = requiredPermissions.stream()
                    .map(Permission::name)
                    .collect(Collectors.toList());
            joinedRequiredPermissions = String.join(", ", requiredPermissionStrings);

            LOG.trace("Verify required permissions: [{}]", joinedRequiredPermissions);
        }

        // get current http request from thread
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;

            HttpServletRequest request = servletRequestAttributes.getRequest();

            // if request present
            // parse json web token with roles
            String jwt = resolveToken(request);

            // custom permission check
            String username = jwtTokenUtil.getUsernameFromToken(jwt);
            List<Permission> userPermissions = jwtTokenUtil.getPermissions(jwt);

            // verify all jwt permissions are part of the required permissions
            if (!userPermissions.containsAll(requiredPermissions)) {
                throw new BusinessException(ErrorCode.NO_PERMISSION_ERROR,
                        String.format("User '%s' has not all required permissions [%s]", username,
                                joinedRequiredPermissions), requiredPermissionStrings.toArray());
            }

        }


        return joinPoint.proceed();
    }

    private String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // remove "Bearer" prefix
            return bearerToken.substring(7);
        }
        return null;
    }


    Method getCurrentMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}
