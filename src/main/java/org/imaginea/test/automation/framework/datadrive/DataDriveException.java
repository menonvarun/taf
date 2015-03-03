package org.imaginea.test.automation.framework.datadrive;

import org.imaginea.test.automation.framework.exceptions.TafRuntimeException;

/**
 * Created by varunm on 26-02-2015.
 */
public class DataDriveException extends TafRuntimeException {
    public DataDriveException(){
        super();
    }

    public DataDriveException(String message){
        super(message);
    }

    public DataDriveException(Throwable t){
        super(t);
    }

    public DataDriveException(String message, Throwable t){
        super(message, t);
    }
}
