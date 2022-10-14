package com.slack.stepDefs;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {
    @Before
    public void setup(){
        RestAssured.baseURI = "https://slack.com";
        RestAssured.basePath = "api/chat.postMessage";
    }
}
