package com.jpmc.theatre.reservation.cucumber.steps;

import com.jpmc.theater.reservation.dto.Reservation;
import com.jpmc.theater.reservation.dto.Showing;
import com.jpmc.theatre.reservation.utils.TestUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@CucumberContextConfiguration
public class ReservationIntegrationTest {

    private static final String baseUrl ="http://localhost:9091";
    private Response response;
    private Reservation reservation;

    @When("^make a GET call on ([^\"]*)$")
    public void makeGetCallToFetchShows(String path) {
        RestAssured.baseURI =  baseUrl;
        RequestSpecification request = RestAssured.given();
        response = request.get(path);
    }

    @Then("^receive (\\d+) response status code$")
    public void receiveGetStatusCodeResponse(int code) {
        Assert.assertEquals(code, response.getStatusCode());
    }

    @And("^receive a non-empty Get valid response$")
    public void receiveGetNonEmptyValidBody() {
        response.then().body(Matchers.notNullValue());
        List<Showing> shows = JsonPath.from(response.getBody().asString()).get("shows");
        Assert.assertTrue(shows.size() > 0);
    }

    @When("make a POST call on ([^\"]*)$")
    public void makePostCallToMakeReservation(String path) throws IOException {
        RestAssured.baseURI =  baseUrl;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        reservation = TestUtil.createReservation("TestCustomer","123",1,2);
        response = request.body(TestUtil.convertObjectToJsonBytes(reservation)).post(path);
    }

    @And("^receive a non-empty Post valid response$")
    public void receivePostGetNonEmptyBody() {
        response.then().body(Matchers.notNullValue());
        Map<String,String> customer = JsonPath.from(response.getBody().asString()).get("customer");
        Assert.assertEquals(customer.get("name"), this.reservation.getCustomer().getName());
    }
}
