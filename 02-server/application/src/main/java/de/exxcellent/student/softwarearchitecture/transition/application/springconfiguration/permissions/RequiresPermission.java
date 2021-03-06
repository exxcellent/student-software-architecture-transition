package de.exxcellent.student.softwarearchitecture.transition.application.springconfiguration.permissions;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.user.api.types.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO [AL] class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting & software gmbh
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermission {
    Permission[] value();
}
