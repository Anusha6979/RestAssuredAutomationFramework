package api.base;

import api.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BaseAPI {
    static {
        String environment = System.getProperty("env", "dev");
        RestAssured.baseURI = ConfigManager.getProperty("baseUri." + environment);

        // Enable logging for debugging
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
}
}