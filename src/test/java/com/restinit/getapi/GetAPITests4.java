package com.restinit.getapi;


import com.restinit.core.library.RestInitBase;
import com.restinit.core.support.ReqSpecBuilderPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetAPITests4 extends RestInitBase {

	@Autowired
	ReqSpecBuilderPool reqSpecBuilderPool;

	@Test
	public void mySpringBoot4(){
		restInit.createNewConnection(reqSpecBuilderPool.getCommonSpecQueryParam());
		System.out.println("routes.getAllUsersEndPoint() ------- " + routes.getAllUsersEndPoint());
		restInit.performGetApi(routes.getAllUsersEndPoint());
		assertThat(restInit.getJsonPathDataValue("data[0].email")).isEqualTo("michael.lawson@reqres.in");
	}

}
