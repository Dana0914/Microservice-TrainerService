Feature: Create training summary
  Scenario: I create the training summary
    Given create training summary with the following details:
      | id | month   | year | trainingSummaryDuration | trainingId |
      | 1  | 01      | 2025 | 2                       | 1          |
    When I find the training summary by id 1
    Then the training summary should return the following details:
      | id | month   | year | trainingSummaryDuration | trainingId |
      | 1  | 01      | 2025 | 2                       | 1          |
