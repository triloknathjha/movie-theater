package com.jpmc.theatre.reservation.cucumber;

import com.jpmc.theater.reservation.Application;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com.jpmc.theatre.reservation.cucumber/BehavioralTestSteps.feature",
        glue = {"com/jpmc/theatre/reservation/cucumber/steps"},
        monochrome = true
)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes = {Application.class})
public class TestRunner {
}
