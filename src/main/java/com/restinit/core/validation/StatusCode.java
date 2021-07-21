package com.restinit.core.validation;

import com.restinit.core.library.RestInitImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Component
public class StatusCode {

    @Autowired
    public RestInitImpl restInit;

    public void statusCodeToBe(int expectedStatusCode, String logMessage){
        assertEquals(restInit.getResponse().getStatusCode(),expectedStatusCode,logMessage);
    }

    public void statusCodeNotToBe(int expectedStatusCode, String logMessage){
        assertNotEquals(restInit.getResponse().getStatusCode(),expectedStatusCode,logMessage);
    }

}
