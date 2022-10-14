package com.slack.stepDefs;

import com.slack.utils.CommonUtils;
import com.slack.utils.PayloadUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

public class SendMessageStepDefs {

    Response response;

    @Given("user has valid slack url")
    public void user_has_valid_slack_url() {
        Assert.assertNotNull(RestAssured.baseURI);
        Assert.assertNotNull(RestAssured.basePath);
    }

    @When("user sends a message to slack channel")
    public void user_sends_a_message_to_slack_channel() {
        response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getSlackMessagePayload(CommonUtils.readProp("message"), CommonUtils.readProp("channel")))
                .header("Authorization", CommonUtils.readProp("token"))
                .when()
                .post()
                .then().log().all().extract().response();
    }

    @Then("status code is {int}")
    public void status_code_is(Integer expectedStatusCode) {
        Integer actualStatusCode = response.statusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("message is successfully delivered")
    public void message_is_successfully_delivered() {
        JsonPath parsedResponse = response.jsonPath();
        boolean isOk = parsedResponse.getBoolean("ok");
        Assert.assertTrue(isOk);
    }
}
