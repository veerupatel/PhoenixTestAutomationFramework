package com.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SensitiveDataFilter implements Filter {
	
	private static final Logger logger = LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		System.out.println("------------Hello from the filter!!------------");
		redactPayload(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);
		System.out.println("------------I got the response in filter!!------------");
		redactResponseBody(response);
		return response;
	}

	private void redactResponseBody(Response response) {
		String responseBody = response.asPrettyString();
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\":\"[redacted]\"");
		logger.info("RESPONSE BODY : {}",responseBody);
	}

	public void redactPayload(FilterableRequestSpecification requestSpec) {
		String requestpayload = requestSpec.getBody().toString();
		requestpayload = requestpayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\":\"[redacted]\"");
		logger.info("REQUEST BODY : {}",requestpayload);
	}

}