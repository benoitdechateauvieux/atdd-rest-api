Feature: Create new activities

  Scenario Outline: Activity with title are created
    When I publish an activity with title "<title>"
    Then the HTTP status code of the response is <status>

  Examples:
  | title         | status |
  |  Hello World! |  201   |
  |  @#$@%$  ^&*& |  201   |
  |  ""''<script> |  201   |
  |               |  400   |