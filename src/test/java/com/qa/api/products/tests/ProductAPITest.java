package com.qa.api.products.tests;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.ObjectMapperUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITest extends BaseTest{
	
	@Test
	public void getProductsTest() {
		Response response = restClient.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINT, ContentType.ANY, AuthType.NO_AUTH, null, null);
		Product[] products = ObjectMapperUtil.deserialize(response, Product[].class);
		System.out.println(products);
	}
}
