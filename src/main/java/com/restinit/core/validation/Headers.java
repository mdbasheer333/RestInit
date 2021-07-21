package com.restinit.core.validation;


import com.restinit.core.library.RestInitImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Component
public class Headers {

    @Autowired
    public RestInitImpl restInit;

    public void headerValueToBe(String expectedHeaderValue, String message){
        assertEquals(restInit.getResponse().getHeader(expectedHeaderValue),expectedHeaderValue,message);
    }

    public void headerValueNotToBe(String expectedHeaderValue, String message){
        assertNotEquals(restInit.getResponse().getHeader(expectedHeaderValue),expectedHeaderValue,message);
    }

}
