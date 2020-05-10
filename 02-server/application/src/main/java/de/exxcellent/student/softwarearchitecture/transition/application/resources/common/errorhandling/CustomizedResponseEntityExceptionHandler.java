package de.exxcellent.student.softwarearchitecture.transition.application.resources.common.errorhandling;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.errorhandling.exception.BusinessException;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.errorhandling.exception.TechnicalException;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.errorhandling.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * I handle all application exceptions globally and generate an error response with a suitable http status code and an
 * error message
 *
 * @author Andre Lehnert, eXXcellent solutions consulting & software gmbh
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger LOG = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    /**
     * Handles all {@link BusinessException}
     *
     * @param ex      {@link BusinessException}
     * @param request will be ignored
     *
     * @return response with error
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorTO> handleBusinessException(BusinessException ex, WebRequest request) {
        HttpStatus errorStatus;
        switch (ex.getErrorCode()) {
            case ENTITY_NOT_FOUND_ERROR:
            case NOT_FOUND_ERROR:
                errorStatus = HttpStatus.NOT_FOUND;
                break;
            case ENTITY_CONFLICT_ERROR:
                errorStatus = HttpStatus.CONFLICT;
                break;
            case INVALID_ARGUMENT_ERROR:
                errorStatus = HttpStatus.BAD_REQUEST;
                break;
            case NO_SESSION_ERROR:
                errorStatus = HttpStatus.UNAUTHORIZED;
                break;
            case NO_PERMISSION_ERROR:
                errorStatus = HttpStatus.FORBIDDEN;
                break;
            default:
                errorStatus = HttpStatus.BAD_REQUEST;

        }
        var errorDetails = new ErrorTO(ex.getErrorCode(), ex.getParameters(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, errorStatus);
    }


    /**
     * Handles all {@link TechnicalException}
     *
     * @param ex      {@link TechnicalException}
     * @param request will be ignored
     *
     * @return response with error
     */
    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ErrorTO> handleTechnicalException(TechnicalException ex,
                                                                   WebRequest request) {
        LOG.error("A TechnicalException occured.", ex);

        HttpStatus errorStatus;
        switch (ex.getErrorCode()) {
            case DEPRECATED_VERSION_ERROR:
                errorStatus = HttpStatus.GONE;
                break;
            case EXTERNAL_SERVICE_ERROR:
            case INTERNAL_ERROR:
            case UNEXPECTED_ERROR:
            case DATABASE_CONNECTION_ERROR:
            case DATABASE_TRANSACTION_ERROR:
            case DATABASE_ERROR:
            default:
                errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }
        var errorDetails = new ErrorTO(ex.getErrorCode(), ex.getParameters(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, errorStatus);
    }

    /**
     * Handles all {@link NullPointerException}
     *
     * @param ex      {@link NullPointerException}
     * @param request will be ignored
     *
     * @return response with error
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorTO> handleNullPointerException(NullPointerException ex,
                                                              WebRequest request) {
        LOG.error("A NullPointerException occurred.", ex);

        ErrorTO errorDetails = new ErrorTO(ErrorCode.UNEXPECTED_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Handles all {@link RuntimeException}
     *
     * @param ex      {@link RuntimeException}
     * @param request will be ignored
     *
     * @return response with error
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorTO> handleRuntimeException(RuntimeException ex,
                                                                WebRequest request) {
        LOG.error("A RuntimeException occurred.", ex);

        ErrorTO errorDetails = new ErrorTO(ErrorCode.UNEXPECTED_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        LOG.error("A MethodArgumentNotValidException occurred.", ex);

        List<ErrorTO> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(new ErrorTO(ErrorCode.INVALID_ARGUMENT_ERROR,
                    error.getField() + " = " + error.getRejectedValue() + " -> " + error.getField()
                            + " " + error.getDefaultMessage()));
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(new ErrorTO(ErrorCode.INVALID_ARGUMENT_ERROR,
                    error.getObjectName() + ": " + error.getDefaultMessage()));
        }

        return handleExceptionInternal(
                ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.error("A TypeMismatchException occurred.", ex);
        var error = new ErrorTO(ErrorCode.INVALID_ARGUMENT_ERROR,
            String.format("Wrong datatype for field: %s with value '%s'. Required datatype: %s",
                ex.getPropertyName(), ex.getValue(), ex.getRequiredType()));

        return handleExceptionInternal(
            ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }
}