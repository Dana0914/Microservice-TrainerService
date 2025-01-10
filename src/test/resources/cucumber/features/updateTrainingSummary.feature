Feature: Update training summary
  Scenario: Update the training summary
    Given the training summary exists with the following details:
      | id | month   | year | trainingSummaryDuration | trainingId |
      | 1  | 01      | 2025 | 2                       | 1          |
    When I update the training summary by id 1
    Then the updated training summary should include the following details:
      | id | month   | year | trainingSummaryDuration | trainingId |
      | 1  | 01      | 2025 | 4                       | 1          |

