package petstore.utils;

import petstore.config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class RequestSpecFactory {

	public static RequestSpecification build() {
		return new RequestSpecBuilder()
				.setBaseUri(ConfigReader.get("base.url"))
				.setContentType("application/json")
				.addFilter(new io.restassured.filter.log.RequestLoggingFilter())
				.addFilter(new io.restassured.filter.log.ResponseLoggingFilter())
				.build();
	}
}
