package com.restinit.getapi;


import com.restinit.core.library.RestInitBase;
import com.restinit.core.pojos.Token;
import com.restinit.core.pojos.User;
import com.restinit.core.support.ReqSpecBuilderPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetAPITests5 extends RestInitBase {

    @Autowired
    ReqSpecBuilderPool reqSpecBuilderPool;

    @Test
    public void mySpringBoot7() {
        restInit.createNewConnection();
        restInit.performPostApi("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}",routes.getRegSuccess());
        User user = restInit.getResponseAsPOJO(User.class);
        System.out.println(user.toString());

        restInit.performPostApi("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}","/api/login");
        Token token = restInit.getResponseAsPOJO(Token.class);
        System.out.println(token.toString());

    }

}
