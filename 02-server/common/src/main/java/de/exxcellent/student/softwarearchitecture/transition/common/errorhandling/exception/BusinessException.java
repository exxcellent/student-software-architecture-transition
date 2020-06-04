package de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.errorhandling.exception;


import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.errorhandling.ErrorCode;

/**
 * Fachliche Fehler werden über die Facade durch diese CoreException transportiert.
 * Enthaltenede Exceptions werden als Text übertragen, um keine
 * Code-Abhängigkeiten im Aufrufer zu erzeugen.
 *
 * @author Alexander Jost
 */
public class BusinessException extends CoreException {

    /**
     * BusinessException
     *
     * @param errorCode specifies the detailed error
     * @param message   error message
     */
    public BusinessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }


    /**
     * BusinessException
     *
     * @param errorCode specifies the detailed error
     * @param cause     error cause
     */
    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }


    /**
     * BusinessException
     *
     * @param errorCode specifies the detailed error
     * @param message   error message
     * @param cause     error cause
     */
    public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }


    /**
     * BusinessException
     *
     * @param errorCode          specifies the detailed error
     * @param message            error message
     * @param cause              error cause
     * @param enableSuppression  whether or not suppression is enabled
     *                           or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     */
    public BusinessException(ErrorCode errorCode, String message, Throwable cause,
                             boolean enableSuppression, boolean writableStackTrace) {
        super(errorCode, message, cause, enableSuppression, writableStackTrace);
    }


    /**
     * BusinessException
     *
     * @param errorCode specifies the detailed error
     * @param message   error message
     * @param param     error parameter to be logged and displayed with the error code
     */
    public BusinessException(ErrorCode errorCode, String message, Object... param) {
        super(errorCode, message, param);
    }


    /**
     * BusinessException
     *
     * @param errorCode specifies the detailed error
     * @param param     error parameter to be logged and displayed with the error code
     * @param cause     error cause
     */
    public BusinessException(ErrorCode errorCode, Throwable cause, Object... param) {
        super(errorCode, cause, param);
    }


    /**
     * BusinessException
     *
     * @param errorCode specifies the detailed error
     * @param message   error message
     * @param param     error parameter to be logged and displayed with the error code
     * @param cause     error cause
     */
    public BusinessException(ErrorCode errorCode, String message, Throwable cause,
                             Object... param) {
        super(errorCode, message, cause, param);
    }
}
