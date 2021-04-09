package com.restinit.getapi;


import com.restinit.core.library.RestInitBase;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetAPITests2 extends RestInitBase {

	@Test
	public void mySpringBoot2(){
		restInit.createNewConnection();
		restInit.performGetApi("/api/users");
		restInit.setQueryParameters("page","2");
		assertThat(restInit.getJsonPathDataValue("data[0].email")).isEqualTo("george.bluth1@reqres.in");
	}

}
