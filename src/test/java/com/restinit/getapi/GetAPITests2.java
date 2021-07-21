package com.restinit.getapi;


import com.restinit.core.library.RestInitBase;
import com.restinit.core.support.ReqSpecBuilderPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetAPITests2 extends RestInitBase {

	@Autowired
	ReqSpecBuilderPool reqSpecBuilderPool;

	@Test
	public void mySpringBoot2(){
		restInit.createNewConnection(reqSpecBuilderPool.getCommonSpecQueryParam());
		restInit.performGetApi(routes.getAllUsersEndPoint());
		assertThat(restInit.getJsonPathDataValue("data[0].email")).isEqualTo("michael.lawson1@reqres.in");
	}

}
