package de.exxcellent.student.softwarearchitecture.transition.application.resources.common.validation;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.errorhandling.ErrorCode;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.errorhandling.exception.BusinessException;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class RequestCondition {

  private RequestCondition() {
  }

  public static <T> T checkNotNull(final T reference, final String errorMessage, Object... params) {
    if (reference == null) {
      throw new BusinessException(ErrorCode.INVALID_ARGUMENT_ERROR, String.format(errorMessage, params));
    }

    return reference;
  }
}
