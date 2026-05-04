package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.ObjectMapperUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest{
	
	@Test
	public void getAllUsersTest() {
		ChainTestListener.log("Go Rest API - Get All Users Test");
		Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, ContentType.JSON, AuthType.BEARER_TOKEN, null, null);
		Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
	@Test
	public void getAllUsersWithQueryParamTest() {
		ChainTestListener.log("Go Rest API - Get All Users with query param");
		Map<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("status", "active");
		queryParam.put("name", "naveen");
		
		Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, ContentType.JSON, AuthType.BEARER_TOKEN, queryParam, null);
		Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
	@Test
	public void getSingleUserTest() {
		ChainTestListener.log("Go Rest API - Get single user Test");
		Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/8332781", ContentType.JSON, AuthType.BEARER_TOKEN, null, null);
		Assert.assertTrue(response.statusLine().contains("OK"));
		Assert.assertEquals(response.jsonPath().getInt("id"), 8332781);
		
		
	}
	
}
