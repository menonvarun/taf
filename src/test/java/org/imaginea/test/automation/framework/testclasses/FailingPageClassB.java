package org.imaginea.test.automation.framework.testclasses;

import org.imaginea.test.automation.framework.pagemodel.PageClass;

/**
 * Created by varunm on 10-03-2015.
 */
public class FailingPageClassB extends PageClass {

    @Override
    public boolean at(){
        return false;
    }
}
