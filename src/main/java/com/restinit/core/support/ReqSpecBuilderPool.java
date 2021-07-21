package com.restinit.core.support;

import io.restassured.builder.RequestSpecBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReqSpecBuilderPool {

    @Value("${baseUri}")
    String baseUri;

    public RequestSpecBuilder getCommonSpecPathParam(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(baseUri);
        requestSpecBuilder.addPathParam("user","2");
        return requestSpecBuilder;
    }

    public RequestSpecBuilder getCommonSpecQueryParam(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(baseUri);
        requestSpecBuilder.addQueryParam("page","2");
        return requestSpecBuilder;
    }

}
