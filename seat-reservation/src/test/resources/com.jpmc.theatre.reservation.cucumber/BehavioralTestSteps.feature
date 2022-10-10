@api
Feature: Testing a API
  Users should be able to submit GET and POST requests to a service

  Scenario: Show schedule retrieval from a service
    When make a GET call on /shows/schedule
    Then receive 200 response status code
    And receive a non-empty Get valid response

  Scenario: Seat Reservation
    When make a POST call on /seats/reserve
    Then receive 200 response status code
    And receive a non-empty Post valid response
