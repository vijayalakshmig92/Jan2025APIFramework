package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.client.RestClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	
	protected RestClient restClient;
	
	// API Base URL
	protected static final String BASE_URL_GOREST = "https://gorest.co.in";
	protected static final String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";
	protected static final String BASE_URL_BASICAUTH = "https://the-internet.herokuapp.com";
	protected static final String BASE_URL_PRODUCTS = "https://fakestoreapi.com";
	
	
	// API endpoints
	protected static final String GOREST_USERS_ENDPOINT = "/public/v2/users";
	protected static final String CONTACTS_LOGIN_ENDPOINT = "/users/login";
	protected static final String CONTACTS_ENDPOINT = "/contacts";
	protected static final String BASICAUTH_ENDPOINT = "/basic_auth";
	protected static final String PRODUCTS_ENDPOINT = "/products";
	
	@BeforeSuite
	public void setUpAllureReport() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	@BeforeTest
	public void setup() {
		restClient = new RestClient();
	}
}
