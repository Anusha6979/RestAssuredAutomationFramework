package stepdefinitions;

import api.models.Messages;
import api.services.MessageServices;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ApiSteps {
    private Response response;
    private final MessageServices messageService = new MessageServices();
    private int messageId;

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = messageService.getAllMessages();
    }

    @When("I send a GET request to {string} retrieve")
    public void i_send_a_get_request_to_retrieve(String string) {
        // Write code here that turns the phrase above into concrete actions
        response = messageService.getMessage(messageId);
    }
    @Then("the response body should not be empty")
    public void the_response_body_should_not_be_empty() {
        assertFalse(response.getBody().asString().isEmpty());
    }

    @When("I send a POST request with the following invalid data")
    public void i_send_a_post_request_with_the_following_invalid_data(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            Messages message = new Messages(row.get("name"), row.get("email"), row.get("phone"), row.get("subject"), row.get("description"));
            response = messageService.createMessage(message);
        }
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the error message should be {string}")
    public void the_error_message_should_be(String errorMessage) {
        assertTrue(response.getBody().asString().contains(errorMessage));
    }

    @Given("I have created a message")
    public void i_have_created_a_message() {
        Messages message = new Messages("John Doe", "john@example.com", "12345678901", "Test message", "Message should be proper");
        response = messageService.createMessage(message);
        System.out.println(response.getBody().asString());
        messageId = response.jsonPath().getInt("messageid");
    }

    @When("I update the message with a non-existent ID")
    public void i_update_the_message_with_a_non_existent_id() {
        Messages updatedMessage = new Messages("John Doe", "john@example.com", "12345678901", "Updated Subject", "Updated message");
        response = messageService.updateMessage(messageId, updatedMessage);  // Non-existent ID
    }

//    @Then("the response status code should be 404")
//    public void the_response_status_code_should_be_404() {
//        assertEquals(404, response.getStatusCode());
//    }

    @When("I send a DELETE request for a non-existent message")
    public void i_send_a_delete_request_for_a_non_existent_message() {
        response = messageService.deleteMessage(messageId);  // Non-existent message
    }

    @Then("the message name should be {string}")
    public void the_message_name_should_be(String name) {
        assertEquals(name, response.jsonPath().getString("name"));
    }

    @Then("the message email should be {string}")
    public void the_message_email_should_be(String email) {
        assertEquals(email, response.jsonPath().getString("email"));
    }

    @Then("the message phone should be {string}")
    public void the_message_phone_should_be(String phone) {
        assertEquals(phone, response.jsonPath().getString("phone"));
    }

    @Then("the message subject should be {string}")
    public void the_message_subject_should_be(String subject) {
        assertEquals(subject, response.jsonPath().getString("subject"));
    }

    @Then("the message description should be {string}")
    public void the_message_description_should_be(String description) {
        assertEquals(description, response.jsonPath().getString("description"));
    }
}
