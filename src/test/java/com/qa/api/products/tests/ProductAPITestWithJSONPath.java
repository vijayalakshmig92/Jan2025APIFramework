package com.qa.api.products.tests;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestWithJSONPath extends BaseTest{
	
	@Test
	public void getProductTest() {
		Response response = restClient.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINT, ContentType.ANY, AuthType.NO_AUTH, null, null);
		double sum = JsonPathUtil.read(response, "sum($[*].id)");
		System.out.println(sum);
		List<String> listTitles = JsonPathUtil.readList(response, "$[*].[?(@.description =~ /.*comfort.*/i)].title");
		System.out.println(listTitles);
		List<Map<String, Object>> listIdTitle = JsonPathUtil.readList(response, "$[*].['id', 'title']");
		System.out.println(listIdTitle);
	}

}
