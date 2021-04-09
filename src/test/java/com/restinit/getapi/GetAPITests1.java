package com.restinit.getapi;


import com.restinit.core.library.RestInitBase;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetAPITests1 extends RestInitBase {

	@Test
	public void mySpringBoot1(){
		restInit.createNewConnection();
		restInit.setPathParameters("user","2");
		restInit.performGetApi("/api/users/{user}");
		assertThat(restInit.getJsonPathDataValue("data.email")).isEqualTo("janet.weaver@reqres.in");
	}

}
