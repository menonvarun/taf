package org.imaginea.test.automation.framework.exceptions;

/**
 * Created by varunm on 18-12-2014.
 */
public class TafException extends Exception {
    public TafException(){
        super();
    }

    public TafException(Throwable t){
        super(t);
    }

    public TafException(String message){
        super(message);
    }

    public TafException(String message, Throwable t){
        super(message, t);
    }
}
