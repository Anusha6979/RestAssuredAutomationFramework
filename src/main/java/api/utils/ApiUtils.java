package api.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

import java.util.Map;

public class ApiUtils {

    // Method to create a base request specification
    public static RequestSpecification createRequestSpecification() {
        return RestAssured.given()
                .contentType("application/json");
               // .header("Authorization", "Bearer " + ConfigManager.getProperty("api.token"));
    }

    // Method to handle common GET requests
    public static Response sendGetRequest(String endpoint) {
        return createRequestSpecification()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // Method to handle common POST requests
    public static Response sendPostRequest(String endpoint, Object requestBody) {
        return createRequestSpecification()
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    // Method to handle common PUT requests
    public static Response sendPutRequest(String endpoint, Object requestBody) {
        return createRequestSpecification()
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    // Method to handle common DELETE requests
    public static Response sendDeleteRequest(String endpoint) {
        return createRequestSpecification()
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    // Extract a value from a response by JSONPath
    public static String extractValueFromResponse(Response response, String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }

    // Extract status code from the response
    public static int extractStatusCode(Response response) {
        return response.getStatusCode();
    }

    // Method to verify status code
    public static boolean isStatusCodeValid(Response response, int expectedStatusCode) {
        return response.getStatusCode() == expectedStatusCode;
    }

    // Method to verify if a response body contains a specific value
    public static boolean isResponseBodyContains(Response response, String expectedContent) {
        return response.getBody().asString().contains(expectedContent);
    }

    // Method to validate response headers
    public static boolean isHeaderValid(Response response, String headerName, String expectedValue) {
        return response.getHeader(headerName).equals(expectedValue);
    }

    // Utility method to convert a Map to a JSON string for request bodies
    public static String convertMapToJson(Map<String, String> data) {
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\": \"").append(entry.getValue()).append("\", ");
        }
        if (json.length() > 1) {
            json.setLength(json.length() - 2); // Remove last comma
        }
        json.append("}");
        return json.toString();
}
}
