package com.qa.api.basicauth.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicAuthTest extends BaseTest{
	
	@Test
	public void basicAuthTest() {
		Response response = restClient.get(BASE_URL_BASICAUTH, BASICAUTH_ENDPOINT, ContentType.JSON, AuthType.BASIC_AUTH, null, null);
		Assert.assertTrue(response.getBody().asString().contains("Congratulations! You must have the proper credentials."));
	}

}
