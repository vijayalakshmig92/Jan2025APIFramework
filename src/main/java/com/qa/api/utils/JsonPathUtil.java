package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathUtil {
	
	
	public static <T> T read(Response response, String jsonPathExpression) {
		ReadContext ctx = JsonPath.parse(response.getBody().asString());
		return ctx.read(jsonPathExpression);
	}
	
	public static <T> List<T> readList(Response response, String jsonPathExpression) {
		ReadContext ctx = JsonPath.parse(response.getBody().asString());
		return ctx.read(jsonPathExpression);
	}

	public static <T> List<Map<String, T>> readListOfMap(Response response, String jsonPathExpression){
		ReadContext ctx = JsonPath.parse(response.getBody().asString());
		return ctx.read(jsonPathExpression);
	}
}
