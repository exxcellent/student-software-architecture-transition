package de.exxcellent.student.softwarearchitecture.transition.resources.common.errorhandling;


import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.ErrorCode;

/**
 * I´m used to show backend errors on the user interface
 *
 * @author Andre Lehnert, eXXcellent solutions consulting & software gmbh
 */
public class ErrorTO {
    private ErrorCode errorCode;
    private String[] param;
    private String errorMessage;


    /**
     * Constructor with all parameter
     *
     * @param errorCode    to identify the specific error and translate the error on the user interface
     * @param param        optional parameter to describe the error cause
     * @param errorMessage optional errorMessage of the exception (can be removed in a later version of the project)
     */
    public ErrorTO(ErrorCode errorCode, String[] param, String errorMessage) {
        this.errorCode = errorCode;
        this.param = param;
        this.errorMessage = errorMessage;
    }


    /**
     * Constructor with all parameter
     *
     * @param errorCode    to identify the specific error and translate the error on the user interface
     * @param errorMessage optional errorMessage of the exception (can be removed in a later version of the project)
     */
    public ErrorTO(ErrorCode errorCode, String errorMessage) {
        this.errorCode = errorCode;
        param = new String[0];
        this.errorMessage = errorMessage;
    }


    /**
     * Constructor with all parameter
     *
     * @param errorCode to identify the specific error and translate the error on the user interface
     */
    public ErrorTO(ErrorCode errorCode) {
        this.errorCode = errorCode;
        param = new String[0];
        errorMessage = null;
    }


    public ErrorCode getErrorCode() {
        return errorCode;
    }


    public String[] getParam() {
        return param;
    }


    public String getErrorMessage() {
        return errorMessage;
    }
}
