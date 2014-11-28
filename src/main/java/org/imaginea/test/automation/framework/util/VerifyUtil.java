package org.imaginea.test.automation.framework.util;

import java.util.ArrayList;

/**
 * Created by varunm on 28-11-2014.
 */

/**
 * Utility to verify the test results. Should be used when there is a need of verification rather than
 * Assertion.
 *
 */
public class VerifyUtil {
    private ArrayList<String> results = new ArrayList<String>();

    /**
     * Utility that checks the truth condition and stores the given message for future verification.
     * @param truth true or false based on the verification condition
     * @param message message that needs to be stored in case the truth condition is false.
     * @return this object
     */
    public VerifyUtil verifyTrue(boolean truth, String message){
        if(!truth)
            results.add(message);
        return this;
    }

    /**
     * Utility to verify whether any verification is failed
     * @return true if there are any failures else false
     */
    public boolean isFailed(){
        return results.size() > 0;
    }

    /**
     * Utility to verify whether verification passed
     * @return true if there are no failures else false
     */
    public boolean isPassed(){
        return !isFailed();
    }

    /**
     * Get the stored results
     * @return ArrayList of the messages that with failed verifications
     */
    public ArrayList<String> getResults(){
        return results;
    }

    /**
     * Returns the failed messages
     * @return
     */
    public String getFailureMessage(){
        return results.toString();
    }
}
