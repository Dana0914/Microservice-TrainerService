Feature: Create training
  Scenario: I create the training
    Given create training with the following details:
      | id | trainerId   | date            | actionType | duration |
      | 15  | 1          | 2025-01-01      | ADD        | 2        |
    When I find the training by id 15
    Then the training should include the following details:
      | id | trainerId   | date            | actionType | duration |
      | 15  | 1          | 2025-01-01      | ADD        | 2        |
