# language: en

@multiplefilters
Feature: Event filters

  Background:
    Given Open main page

  Scenario: Select events by multiple filters
    Given Open economic calendar page
    And Select language "en"
    When Select current month
    And Deselect importance "HOLIDAYS"
    And Deselect importance "LOW"
    And Select importance "MEDIUM"
    And Deselect importance "HIGH"
    And Select currency "CHF" and deselect others
    And Open event # 1
    Then Event importance is "MEDIUM"
    And Event country is "SWITZERLAND"
    And Print event history for last 12 months
    And Print event page short url