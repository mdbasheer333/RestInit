package com.restinit.core.library;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

@SpringBootTest
public class RestInitBase extends AbstractTestNGSpringContextTests {

    @Autowired
    public RestInitImpl restInit;

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
            QueryableRequestSpecification queryable = SpecificationQuerier.query(requestSpecification);
            extentTest.info("REQUEST DETAILS getPathParams: " + queryable.getPathParams());
            extentTest.info("REQUEST DETAILS  getQueryParams: "+queryable.getQueryParams());
            extentTest.info("REQUEST DETAILS  getCookies: "+queryable.getCookies());
            extentTest.info("REQUEST DETAILS getHeaders: "+queryable.getHeaders());
            extentTest.info("REQUEST DETAILS getContentType: "+queryable.getContentType());
            extentTest.info("REQUEST DETAILS getBody: "+queryable.getBody());
        }
        restInit.clearExistingConnection();
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
