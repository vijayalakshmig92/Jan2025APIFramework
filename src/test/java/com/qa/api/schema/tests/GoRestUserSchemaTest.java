package com.qa.api.schema.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.ObjectMapperUtil;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class GoRestUserSchemaTest extends BaseTest{
	
	@Test
	public void getUsersAPISchemaTest() {
			Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, ContentType.JSON, AuthType.BEARER_TOKEN, null, null);
			SchemaValidator.validateSchema(response, "schema/usersschema.json");
	}
	
	@Test
	public void createUserAPISchemaTest() {
		User user = new User(null, "viji", "female", "active", StringUtil.getRandomEmailId());

		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, ContentType.JSON, user,
				AuthType.BEARER_TOKEN, null, null);
		Assert.assertTrue(response.statusLine().contains("Created"));
		SchemaValidator.validateSchema(response, "schema/singleuserschema.json");
	}

}
