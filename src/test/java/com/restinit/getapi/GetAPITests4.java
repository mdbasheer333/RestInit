package com.restinit.getapi;


import com.restinit.core.library.RestInitBase;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetAPITests4 extends RestInitBase {

	@Test
	public void mySpringBoot4(){
		restInit.createNewConnection();
		restInit.performGetApi("/api/users");
		restInit.setQueryParameters("page","2");
		assertThat(restInit.getJsonPathDataValue("data[0].email")).isEqualTo("george.bluth@reqres.in");
	}

}
