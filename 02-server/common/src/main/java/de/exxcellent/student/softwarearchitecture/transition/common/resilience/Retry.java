package de.exxcellent.student.softwarearchitecture.transition.common.resilience;

import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.ErrorCode;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.BusinessException;
import de.exxcellent.student.softwarearchitecture.transition.common.errorhandling.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;


public class Retry {
    
    private static Logger LOGGER = LoggerFactory.getLogger(Retry.class);

    private Retry() {}

    public static <T> T execute(Supplier<T> method) {
        try {
            return method.get();
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            }

            LOGGER.warn("Caught exception, retrying method", e);
            return method.get();
        }
    }

    /**
     * I am used to call a method several times. The retries are only executed
     * if the previous calls have failed by throwing a RuntimeException. As soon
     * as a call is successfully executed, or the maxRetryCount is reached there
     * are no more calls of the method and the result will be returned to the
     * calling method.
     *
     * @param method
     *            Method, that should be called up to a given number of times.
     * @param methodLoggingName
     *            The name of the method to call. It is used for the logging.
     * @param maxRetryCount
     *            The maximum number a retires, if the previous executions fail.
     * @param retryDelaySeconds
     *            Time in seconds to wait until the next execution will be tried
     *            if the previous call failed.
     * @param <T>
     *            Return type of the 'Method' that should be executed.
     * @return return value of the given 'Method'.
     */
    public static <T> T execute(Supplier<T> method, String methodLoggingName,
            int maxRetryCount, long retryDelaySeconds) {
        for (int counter = 1; counter <= maxRetryCount; counter++) {
            try {
                return method.get();
            } catch (RuntimeException e) {
                if (e instanceof BusinessException) {
                    throw e;
                } else {
                    handleFailure(methodLoggingName, maxRetryCount, retryDelaySeconds, counter, e);
                }
            }
        }
        return handleFinalFailure(methodLoggingName);
    }

    private static void handleFailure(String serviceName, int maxRetryCount, long retryDelay,
            int counter, RuntimeException e) {
        LOGGER.warn("Failed to call {} for the {}. time.", serviceName, counter);

        if (counter < maxRetryCount) {
            LOGGER.info("Sleep for {}s before next retry.", retryDelay);

            long startTime = System.currentTimeMillis();
            try {
                TimeUnit.SECONDS.sleep(retryDelay);
            } catch (InterruptedException iCouldntSleepWell) {
                LOGGER.warn("Failed to sleep for {}s.", retryDelay);
                Thread.currentThread().interrupt();

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Thread.sleep was interrupted after {}ms.", (System.currentTimeMillis() - startTime));
                }
            }
        }
    }

    private static <T> T handleFinalFailure(String serviceName) {
        var errorCode = ErrorCode.INTERNAL_ERROR;
        var message = String.format("No more retry will be executed for the call of %s", serviceName);
        throw new TechnicalException(errorCode, message);
    }
}
