package com.restinit.core.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.restinit.core.library.RestInitListener;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReqQueryLogging {

    @Value("${logging}")
    Boolean logging;

    public void logIfSetTrue(RequestSpecification requestSpecification) {
        if(logging) {
            ExtentTest extentTest = RestInitListener.getLocalThreadExtentTest().get();
            if(null==requestSpecification){
                extentTest.warning("REQUEST DETAILS not found");
                return;
            }
            logRequestSpecification(extentTest,requestSpecification);
        }
    }

    public void logRequestSpecification(ExtentTest extentTest,RequestSpecification requestSpecification){
        QueryableRequestSpecification queryable = SpecificationQuerier.query(requestSpecification);
        extentTest.info("------------REQUEST DETAILS------------");
        extentTest.info("REQUEST DETAILS PathParams: " + queryable.getPathParams());
        extentTest.info("REQUEST DETAILS  QueryParams: "+queryable.getQueryParams());
        extentTest.info("REQUEST DETAILS  Cookies: "+queryable.getCookies());
        extentTest.info("REQUEST DETAILS Headers: "+queryable.getHeaders());
        extentTest.info("REQUEST DETAILS ContentType: "+queryable.getContentType());
        extentTest.info("REQUEST DETAILS Body: "+queryable.getBody());
    }

}
