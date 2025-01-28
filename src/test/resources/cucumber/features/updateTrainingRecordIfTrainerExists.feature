Feature: Update Training record if trainer exists by username and invoke
  the method to calculate training sessio by year and month
  and if it does not exist, then create trainer by username
  Scenario: I update training record with existing trainer
    Given I update the training with the following details:
      | id | trainerId   | date            | actionType | duration |
      | 1  | 2           | 2025-01-01      | ADD        | 2        |
    When I update the training
    Then the training session should include the following details:
      | id | trainerId   | date            | actionType | duration |
      | 1  | 2           | 2025-01-01      | ADD        | 2        |


  Scenario: I create trainer record if it does not exist by username
    Given I create the trainer by username with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 2  | John.Doe   | John      | Doe      | 2        | true     |
    When I create the trainer
    Then the trainer record should include the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 2  | John.Doe   | John      | Doe    | 2          | true     |


  Scenario: I create trainer if it does not exist by given username
    Given I create the trainer by username with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 2  | John.Doe   | John      | Doe      | 2        | true     |
    When I create the trainer
    Then the trainer record should include the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 2  | John.Doe   | John      | Doe    | 2          | true     |
