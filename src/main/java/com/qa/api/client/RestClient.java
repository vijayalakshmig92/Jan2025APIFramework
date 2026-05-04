package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.Map;
import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIException;
import com.qa.api.manager.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.expect;

public class RestClient {
	
	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	private ResponseSpecification responseSpec201 = expect().statusCode(201);
	private ResponseSpecification responseSpec204 = expect().statusCode(204);
	private ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200), equalTo(404)));
	private ResponseSpecification responseSpec401 = expect().statusCode(401);
	private ResponseSpecification responseSpec200or201 = expect().statusCode(anyOf(equalTo(200), equalTo(201)));
	private ResponseSpecification responseSpec200or204 = expect().statusCode(anyOf(equalTo(200), equalTo(204)));
	
	
	
	private RequestSpecification setupRequest(String baseURL, AuthType authType, ContentType contentType) {
		
		RequestSpecification request = RestAssured.given().log().all()
					.baseUri(baseURL)
					.contentType(contentType)
					.accept(contentType);
		
		
		switch(authType) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer "+ ConfigManager.getProperty("bearertoken"));
			break;
		case API_KEY:
			request.header("x-api-key", "api key");
			break;
		case BASIC_AUTH:
			request.header("Authorization", "Basic "+ generateBasicAuthToken(ConfigManager.getProperty("username"), ConfigManager.getProperty("password")));
			break;
		case NO_AUTH:
			System.out.println("Auth is not required");
			break;
		case OAUTH2:
			request.header("Authorization", "Bearer "+ ConfigManager.getProperty("bearertoken"));
			break;
		default:
			System.out.println("Please pass the correct Auth type");
			throw new APIException("Invalid Auth Type");
		}
		
		return request;
		
	}
	
	private String generateBasicAuthToken(String username, String password) {
		
		String basicAuth = username+":"+password;
		return Base64.getEncoder().encodeToString(basicAuth.getBytes());
		
	}
	
	
	private void applyParams(RequestSpecification request, Map<String, String> queryParams, Map<String, String> pathParams ) {

		if(pathParams != null) {
			request.pathParams(pathParams);
		}
		if(queryParams != null) {
			request.queryParams(queryParams);
		}

	}
	
	public Response get(String baseURL, String endpoint, ContentType contentType, AuthType authType, Map<String, String> queryParams, Map<String, String> pathParams) {
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.get(endpoint)
			   .then().log().all()
			   .assertThat()
			   .spec(responseSpec200or404)
			   .extract()
			   .response();
		
		response.prettyPrint();
		return response;
		
	}
	
	public <T> Response post(String baseURL, String endpoint, ContentType contentType, T body, AuthType authType, Map<String, String> queryParams, Map<String, String> pathParams) {
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.body(body)
		.post(endpoint)
		.then().log().all()
		.spec(responseSpec200or201)
		.extract()
		.response();
		
		response.prettyPrint();
		
		return response;
		
					
	}
	
	public Response post(String baseURL, String endpoint, ContentType contentType, File body, AuthType authType, Map<String, String> queryParams, Map<String, String> pathParams) {
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.body(body)
		.post(endpoint)
		.then().log().all()
		.spec(responseSpec200or201)
		.extract()
		.response();
		
		response.prettyPrint();
		return response;
		
					
	}
	
	public <T>Response put(String baseURL, String endpoint, ContentType contentType, T body, AuthType authType, Map<String, String> queryParams, Map<String, String> pathParams) {
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.body(body)
		.put(endpoint)
		.then().log().all()
		.spec(responseSpec200)
		.extract()
		.response();
		
		response.prettyPrint();
		return response;
		
					
	}
	
	public <T>Response patch(String baseURL, String endpoint, ContentType contentType, T body, AuthType authType, Map<String, String> queryParams, Map<String, String> pathParams) {
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.body(body)
		.patch(endpoint)
		.then().log().all()
		.spec(responseSpec200)
		.extract()
		.response();
		
		response.prettyPrint();
		return response;
					
	}
	
	public Response delete(String baseURL, String endpoint, ContentType contentType, AuthType authType, Map<String, String> queryParams, Map<String, String> pathParams) {
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.delete(endpoint)
			   .then().log().all()
			   .assertThat()
			   .spec(responseSpec200or204)
			   .extract()
			   .response();
		
		response.prettyPrint();
		return response;
		
	}
	
	

}
