package org.imaginea.test.automation.framework;

import org.imaginea.test.automation.framework.pagemodel.Browser;
import org.openqa.selenium.WebDriver;

import java.net.URISyntaxException;

/**
 * Created by varunm on 04-12-2014.
 */
public class TafTestClass {
    protected Browser browser;
    public TafTestClass (){
        initialize();
    }

    private void initialize(){
        this.browser = new Browser();
    }

    public String getHtmlFile(String fileName){
            return "file:/"+ClassLoader.getSystemResource("html/"+fileName).getPath();
    }

    public String getFilePathFromResource(String fileName){
        return ClassLoader.getSystemResource(fileName).getPath();
    }
}
