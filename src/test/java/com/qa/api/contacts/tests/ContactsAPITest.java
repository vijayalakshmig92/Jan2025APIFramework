package com.qa.api.contacts.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.client.RestClient;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.ContactCredentials;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsAPITest extends BaseTest{
	
	private String accessToken;
	
	@BeforeMethod
	public void getToken() {
		
		ContactCredentials cred = ContactCredentials.builder().email("vijayalakshmig.mec@gmail.com").password("test@123").build();
		Response response = restClient.post(BASE_URL_CONTACTS, CONTACTS_LOGIN_ENDPOINT, ContentType.JSON, cred, AuthType.BEARER_TOKEN, null, null);
		accessToken = response.jsonPath().getString("token");
		System.out.println(accessToken);
		
	}
	
	@Test
	public void getAllContactsTest() {
		Response responseGet = restClient.get(BASE_URL_CONTACTS, CONTACTS_ENDPOINT, ContentType.JSON, AuthType.BEARER_TOKEN, null, null);
		Assert.assertEquals(responseGet.statusCode(), 200);
		Assert.assertEquals(responseGet.statusLine(), "OK");
	}

}
