package com.restinit.core.library;

import com.aventstack.extentreports.ExtentTest;
import com.restinit.core.support.Routes;
import com.restinit.core.validation.StatusCode;
import com.restinit.core.validation.StatusLine;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

@SpringBootTest
public class RestInitBase extends AbstractTestNGSpringContextTests {

    @Autowired
    public RestInitImpl restInit;

    @Autowired
    public StatusCode statusCode;

    @Autowired
    public StatusLine statusLine;

    @Autowired
    public Routes routes;

    @Value("${logging}")
    Boolean logging;

    @BeforeSuite
    public void beforeSuite() {
    }

    @BeforeTest
    public void beforeTest() {
    }

    @BeforeClass
    public void beforeClass() {
    }

    @BeforeMethod
    public void beforeMethod() {
    }

    @AfterMethod
    public void afterMethod() {
        if(logging) {
            ExtentTest extentTest = RestInitListener.getLocalThreadExtentTest().get();
            RequestSpecification requestSpecification = restInit.getRequestSpecification();
            if(null==requestSpecification){
                extentTest.warning("REQUEST DETAILS not found");
                return;
            }
        }
        restInit.clearExistingConnection();
        restInit.clearThreads();
    }

    @AfterClass
    public void afterClass() {
    }

    @AfterTest
    public void afterTest() {
    }

    @AfterSuite
    public void afterSuite() {
    }

}
