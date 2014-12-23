package org.imaginea.test.automation.framework.exceptions;

/**
 * Created by varunm on 18-12-2014.
 */
public class TafRuntimeException extends RuntimeException {
    public TafRuntimeException() {
        super();
    }

    public TafRuntimeException(Throwable t) {
        super(t);
    }

    public TafRuntimeException(String message) {
        super(message);
    }

    public TafRuntimeException(String message, Throwable t) {
        super(message, t);
    }
}
