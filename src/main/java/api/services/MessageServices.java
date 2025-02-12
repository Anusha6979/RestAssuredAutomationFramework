package api.services;

import api.models.Messages;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class MessageServices {
    private final String MESSAGE_ENDPOINT = "/message/";

    public Response getAllMessages() {
        return given()
                .when().get(MESSAGE_ENDPOINT)
                .then().extract().response();
    }
    public Response getMessage(int messsageId) {
        return given()
                .when()
                .get(MESSAGE_ENDPOINT+messsageId)
                .then().extract().response();
    }

    public Response createMessage(Messages message) {
        return given()
                .header("Content-Type", "application/json")
                .body(message)
                .when().post(MESSAGE_ENDPOINT)
                .then().extract().response();
    }

    public Response updateMessage(int messageId, Messages message) {
        return given()
                .header("Content-Type", "application/json")
                .body(message)
                .when().put(MESSAGE_ENDPOINT  + messageId+ "/read")
                .then().extract().response();
    }

    public Response deleteMessage(int messageId) {
        return given()
                .when().delete(MESSAGE_ENDPOINT  + messageId)
                .then().extract().response();
}
}