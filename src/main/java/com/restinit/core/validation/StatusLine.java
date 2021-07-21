package com.restinit.core.validation;

import com.restinit.core.library.RestInitImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Component
public class StatusLine {

    @Autowired
    public RestInitImpl restInit;

    public void statusLineToBe(String expectedStatusLine, String logMessage){
        assertEquals(restInit.getResponse().getStatusLine(),expectedStatusLine,logMessage);
    }

    public void statusLineNotToBe(String expectedStatusLine, String logMessage){
        assertNotEquals(restInit.getResponse().getStatusLine(),expectedStatusLine,logMessage);
    }

}
