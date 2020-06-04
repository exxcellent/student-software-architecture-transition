package de.exxcellent.student.softwarearchitecture.transition.resources.common.validation;

import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.ErrorCode;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.BusinessException;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public final class ResponseCondition {

  private ResponseCondition() {
  }

  public static <T> T checkNotNull(final T reference, final String errorMessage, Object... params) {
    if (reference == null) {
      throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, String.format(errorMessage, params));
    }

    return reference;
  }
}
