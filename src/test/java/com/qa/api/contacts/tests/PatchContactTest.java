package com.qa.api.contacts.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.Contact;
import com.qa.api.pojo.ContactCredentials;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchContactTest extends BaseTest{
	
	
	private String accessToken;
	
	@BeforeMethod
	public void getToken() {
		
		ContactCredentials cred = ContactCredentials.builder().email("vijayalakshmig.mec@gmail.com").password("test@123").build();
		Response response = restClient.post(BASE_URL_CONTACTS, CONTACTS_LOGIN_ENDPOINT, ContentType.JSON, cred, AuthType.NO_AUTH, null, null);
		accessToken = response.jsonPath().getString("token");
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println("Access Token is" + accessToken);
		ConfigManager.set("bearertoken", accessToken);
		
	}
	
	@Test
	public void updateContactTest() {
		
		// 1. Create a contact
		
		Contact contact = Contact.builder().firstName("Amy").lastName("Miller").birthdate("1992-02-02").email("amiller@fake.com").phone("8899889988")
				.street1("Madha koil street").street2("okkiyampettai").city("Chennai").stateProvince("Tamil Nadu").postalCode("600097").country("India").build();
		
		Response responsePost = restClient.post(BASE_URL_CONTACTS, CONTACTS_ENDPOINT, ContentType.JSON, contact, AuthType.BEARER_TOKEN, null, null);
		Assert.assertEquals(responsePost.statusLine(), "HTTP/1.1 201 Created");
		Assert.assertEquals(responsePost.statusCode(), 201);
		String contactId = responsePost.jsonPath().getString("_id");
		System.out.println(contactId);
		
		// 2 patch contact
		
		contact.setFirstName("Anna");
		Response responsePut = restClient.patch(BASE_URL_CONTACTS, CONTACTS_ENDPOINT+"/"+contactId, ContentType.JSON, contact, AuthType.BEARER_TOKEN, null, null);
		Assert.assertEquals(responsePut.statusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(responsePut.statusCode(), 200);
		Assert.assertEquals(responsePut.jsonPath().getString("firstName"), contact.getFirstName());
		
		// 3 get contact
		
		Response responseGet = restClient.get(BASE_URL_CONTACTS, CONTACTS_ENDPOINT+"/"+contactId, ContentType.JSON, AuthType.BEARER_TOKEN, null, null);
		Assert.assertEquals(responseGet.statusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(responseGet.statusCode(), 200);
		Assert.assertEquals(responsePut.jsonPath().getString("firstName"), contact.getFirstName());

}
	
}
