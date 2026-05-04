package com.qa.api.gorest.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AppConstants;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.ExcelUtil;
import com.qa.api.utils.ObjectMapperUtil;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {

	String body = "{\r\n" + "    \"name\": \"Meghnath Sharma\",\r\n"
			+ "    \"email\": \"meghnath_sharma@cummings-aufderhar1234.test\",\r\n" + "    \"gender\": \"male\",\r\n"
			+ "    \"status\": \"active\"\r\n" + "}";

	@Test(enabled = false)
	public void createUserTestWithJSONString() {
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, ContentType.JSON, body,
				AuthType.BEARER_TOKEN, null, null);
		Assert.assertTrue(response.statusLine().contains("Created"));
	}

	@Test
	public void createUserTest() {
		User user = new User(null, "viji", "female", "active", StringUtil.getRandomEmailId());

		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, ContentType.JSON, user,
				AuthType.BEARER_TOKEN, null, null);
		Assert.assertTrue(response.statusLine().contains("Created"));
	}

	@Test(enabled=false)
	public void createUserTestWithFile() {

		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, ContentType.JSON,
				new File("C:\\Users\\vijil\\eclipse-workspace\\Apr2026APIFW\\src\\test\\resources\\jsons\\user.json"),
				AuthType.BEARER_TOKEN, null, null);
		Assert.assertTrue(response.statusLine().contains("Created"));
	}

	@Test
	public void createUserTestWithFileAsString() {
		String userJsonString;
		try {
			userJsonString = new String(Files.readAllBytes(Paths
					.get("C:\\Users\\vijil\\eclipse-workspace\\Apr2026APIFW\\src\\test\\resources\\jsons\\user.json")));
			String updatedJson = userJsonString.replace("{{emailId}}", StringUtil.getRandomEmailId());
			Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, ContentType.JSON,
					updatedJson, AuthType.BEARER_TOKEN, null, null);
			Assert.assertTrue(response.statusLine().contains("Created"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][]{
			{"Priyanka", "female", "inactive"},
			{"Riya", "female", "active"},
			{"Priya", "female", "active"}
		};
	}
	
	@DataProvider
	public Object[][] getDataFromExcel(){
		return ExcelUtil.readDataFromExcel(AppConstants.CREATE_USER_SHEET);
	}
	
	@Test(dataProvider="getDataFromExcel")
	public void createUserTestWithPOJO(String name, String gender, String status) {
		User user = new User(null, name, gender, status, StringUtil.getRandomEmailId());

		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, ContentType.JSON, user,
				AuthType.BEARER_TOKEN, null, null);
		Assert.assertTrue(response.statusLine().contains("Created"));
		User userDeserialize = ObjectMapperUtil.deserialize(response, User.class);
		System.out.println(userDeserialize);
		

	}
	
	@Test(dataProvider="getUserData")
	public void createUserTestWithPOJOExcel(String name, String gender, String status) {
		User user = new User(null, name, gender, status, StringUtil.getRandomEmailId());

		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, ContentType.JSON, user,
				AuthType.BEARER_TOKEN, null, null);
		Assert.assertTrue(response.statusLine().contains("Created"));
		User userDeserialize = ObjectMapperUtil.deserialize(response, User.class);
		System.out.println(userDeserialize);

	}
}
