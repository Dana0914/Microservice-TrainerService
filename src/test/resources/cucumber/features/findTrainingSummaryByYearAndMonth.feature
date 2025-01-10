Feature: Find training summary by year and month
  Scenario: Successfully find a training summary by year and month
    Given the training summary exists with the following details:
      | id | month   | year | trainingSummaryDuration | trainingId |
      | 1  | 01      | 2025 | 2                       | 1          |
    When I find the training by year 2025 and month 01
    Then the training summary should return the following details:
      | id | month   | year | trainingSummaryDuration | trainingId |
      | 1  | 01      | 2025 | 2                       | 1          |

  Scenario: Searching training summary with a non-existent year and month
    Given the training summary exists with the following details:
      | id | month   | year | trainingSummaryDuration | trainingId |
      | 1  | 01      | 2025 | 2                       | 1          |
    When I search for training summary with year 2028 and month 05
    Then training summary should return no result

