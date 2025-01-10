Feature: Find training by id
  Scenario: Successfully find a training by ID
    Given the training exists with the following details:
      | id | trainerId   | date            | actionType | duration |
      | 15  | 1          | 2025-01-01      | ADD        | 2        |
    When I find the training by id 15
    Then the training should include the following details:
      | id | trainerId   | date            | actionType | duration |
      | 15  | 1          | 2025-01-01      | ADD        | 2        |

  Scenario: Searching for training with a non-existent ID
    Given the training exists with the following details:
      | id | trainerId   | date            | actionType | duration |
      | 15  | 1          | 2025-01-01      | ADD        | 2        |
    When I search for training with id 100
    Then training return no result
