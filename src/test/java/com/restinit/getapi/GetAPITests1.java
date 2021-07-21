package com.restinit.getapi;


import com.restinit.core.library.RestInitBase;

import com.restinit.core.support.ReqSpecBuilderPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetAPITests1 extends RestInitBase {

    @Autowired
    ReqSpecBuilderPool reqSpecBuilderPool;

    @Test
    public void mySpringBoot1() {
        restInit.createNewConnection(reqSpecBuilderPool.getCommonSpecPathParam());
        restInit.performGetApi(routes.getUserEndPoint());
        assertThat(restInit.getJsonPathDataValue("data.email")).isEqualTo("janet.weaver@reqres.in");
    }

}
