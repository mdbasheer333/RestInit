package com.restinit.getapi;


import com.restinit.core.library.RestInitBase;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class JsonValidations extends RestInitBase {

	String jsonData="{  \n" +
			"   \"store\":{  \n" +
			"      \"book\":[  \n" +
			"         {  \n" +
			"            \"author\":\"Nigel Rees\",\n" +
			"            \"category\":\"reference\",\n" +
			"            \"price\":8.95,\n" +
			"            \"title\":\"Sayings of the Century\"\n" +
			"         },\n" +
			"         {  \n" +
			"            \"author\":\"Evelyn Waugh\",\n" +
			"            \"category\":\"fiction\",\n" +
			"            \"price\":12.99,\n" +
			"            \"title\":\"Sword of Honour\"\n" +
			"         },\n" +
			"         {  \n" +
			"            \"author\":\"Herman Melville\",\n" +
			"            \"category\":\"fiction\",\n" +
			"            \"isbn\":\"0-553-21311-3\",\n" +
			"            \"price\":8.99,\n" +
			"            \"title\":\"Moby Dick\"\n" +
			"         },\n" +
			"         {  \n" +
			"            \"author\":\"J. R. R. Tolkien\",\n" +
			"            \"category\":\"fiction\",\n" +
			"            \"isbn\":\"0-395-19395-8\",\n" +
			"            \"price\":22.99,\n" +
			"            \"title\":\"The Lord of the Rings\"\n" +
			"         }\n" +
			"      ]\n" +
			"   }\n" +
			"}";

	@Test(enabled = false)
	public void mySpringBoot5(){

		JsonPath jsonPath=JsonPath.from(jsonData);

		//1. the titles of the books with a price less than 10 are "Sayings of the Century" and "Moby Dick"
		List<String> list = jsonPath.getList("store.book.findAll{e->e.price<10}.title");
		System.out.println(list);

		//2. sum of the all books prices is 53.92
		float aFloat = jsonPath.getFloat("store.book.price.sum()");
		System.out.println(aFloat);

		//3. get the data of specific author 'Herman Melville' into map
		Map<String, String> map = jsonPath.getMap("store.book.findAll{e->e.author=='Herman Melville'}[0]");
		System.out.println(map);

	}

}
