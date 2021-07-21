package com.restinit.postapi;


import com.aventstack.extentreports.ExtentTest;
import com.restinit.core.library.RestInitBase;
import com.restinit.core.library.RestInitListener;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostAPITests5 extends RestInitBase {

	@Test
	public void mySpringBoot6(){
		restInit.createNewConnection();

		String body="{\n" +
				"    \"name\": \"morpheus\",\n" +
				"    \"job\": \"leader\"\n" +
				"}";
		restInit.performPostApi(body,routes.getAllUsersEndPoint());

		String resBody=restInit.getResponse().getBody().asString();
		System.out.println(resBody);

		ExtentTest extentTest = RestInitListener.getLocalThreadExtentTest().get();
		extentTest.info("RESPONSE BODY IS : \n" + resBody);

		assertThat(restInit.getJsonPathDataValue("id")).isNotBlank();
		statusCode.statusCodeToBe(201,"post request");

	}

}
