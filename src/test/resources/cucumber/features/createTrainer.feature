Feature: Create trainer
  Scenario: Successfully create the trainer
    Given create trainer with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
    When I find the trainer by id 1
    Then the response should include the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
