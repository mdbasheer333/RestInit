package com.restinit.core.library;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RestInitImpl{

    @Value("${baseUri}")
    String baseUri;

    @Value("${logging}")
    Boolean logging;

    private final ThreadLocal<RequestSpecification> requestSpecification= new ThreadLocal<>();
    private final ThreadLocal<ResponseBodyExtractionOptions> responseBody= new ThreadLocal<>();
    private final ThreadLocal<Response> response= new ThreadLocal<>();
    private final ThreadLocal<RequestSpecBuilder> requestSpecBuilder= new ThreadLocal<>();

    //ExtentTest extentTest = RestInitListener.getLocalThreadExtentTest().get();

    public RequestSpecification getRequestSpecification(){
        return this.requestSpecification.get();
    }

    public void createNewConnection() {
        RestAssured.reset();
        RestAssured.baseURI = baseUri;
        RestInitListener.getLocalThreadExtentTest().get().info("baseURI: " + baseUri);
        this.requestSpecification.set(RestAssured.given());

    }

    public void createNewConnection(RequestSpecBuilder requestSpecBuilder) {
        RestAssured.reset();
        RestInitListener.getLocalThreadExtentTest().get().info("baseURI: " + baseUri);
        this.requestSpecification.set(RestAssured.given(requestSpecBuilder.build()));
    }

    public void clearExistingConnection() {
        RestAssured.reset();
    }

    public void setQueryParameters(Map<String, String> queryParams) {
        this.requestSpecification.get().queryParams(queryParams);
        RestInitListener.getLocalThreadExtentTest().get().info("queryParams: " + queryParams.toString());
    }

    public void setQueryParameters(String key, String value) {
        this.requestSpecification.get().queryParam(key,value);
        RestInitListener.getLocalThreadExtentTest().get().info("queryParams: " + key + ", "+value);
    }

    public void setPathParameters(String key, String value) {
        this.requestSpecification.get().pathParams(key,value);
        RestInitListener.getLocalThreadExtentTest().get().info("pathParams: " + key + ", "+value);
    }

    public void setPathParams(Map<String, String> pathParams) {
        this.requestSpecification.get().pathParams(pathParams);
        RestInitListener.getLocalThreadExtentTest().get().info("pathParams: " + pathParams.toString());
    }

    public void performGetApi(String endPoint) {
        logIfSetTrue();
        this.response.set(this.requestSpecification.get().get(endPoint));
        this.responseBody.set(this.response.get().getBody());
        RestInitListener.getLocalThreadExtentTest().get().info("GET Method and endpoint is : " + endPoint);
        RestInitListener.getLocalThreadExtentTest().get().info("Response body is "+ this.response.get().getBody().asString());
    }

    private void logIfSetTrue() {
        if(logging) {
            ExtentTest extentTest = RestInitListener.getLocalThreadExtentTest().get();
            RequestSpecification requestSpecification = getRequestSpecification();
            if(null==requestSpecification){
                extentTest.warning("REQUEST DETAILS not found");
                return;
            }
            logRequestSpecification(extentTest,requestSpecification);
        }
    }

    private void logRequestSpecification(ExtentTest extentTest,RequestSpecification requestSpecification){
        QueryableRequestSpecification queryable = SpecificationQuerier.query(requestSpecification);
        extentTest.info("------------REQUEST DETAILS------------");
        extentTest.info("REQUEST DETAILS PathParams: " + queryable.getPathParams());
        extentTest.info("REQUEST DETAILS  QueryParams: "+queryable.getQueryParams());
        extentTest.info("REQUEST DETAILS  Cookies: "+queryable.getCookies());
        extentTest.info("REQUEST DETAILS Headers: "+queryable.getHeaders());
        extentTest.info("REQUEST DETAILS ContentType: "+queryable.getContentType());
        extentTest.info("REQUEST DETAILS Body: "+queryable.getBody());
    }

    public void performPostApi(String requestBody, String endPoint) {
        this.response.set(this.requestSpecification.get().body(requestBody).post(endPoint));
        this.responseBody.set(this.response.get().getBody());
        logIfSetTrue();
        RestInitListener.getLocalThreadExtentTest().get().info("POST Method and endpoint is : " + endPoint);
        RestInitListener.getLocalThreadExtentTest().get().info("Response body is "+ this.response.get().getBody().asString());
    }

    public Response getResponse(){
        return this.response.get();
    }

    public String getJsonPathDataValue(String jsonPath) {
        String actual = this.responseBody.get().jsonPath().getString(jsonPath);
        RestInitListener.getLocalThreadExtentTest().get().info("Value of json path  "+ jsonPath + " is "+actual);
        return actual;
    }

    public String getHeader(String expectedHeader){
        return this.response.get().getHeader(expectedHeader);
    }

    public Headers getHeaders(){
        return this.response.get().getHeaders();
    }

    public void setHeaders(Map<String,String> headers){
        this.requestSpecification.get().headers(headers);
    }

    public void setHeaders(String header, String headerValue){
        this.requestSpecification.get().header(header, headerValue);
    }

    public int getStatusCode(){
        return this.response.get().getStatusCode();
    }

    public String getStatusLine(){
        return this.response.get().statusLine();
    }

    public ResponseSpecification getResponseSpecification() {
        return null;
    }

}
