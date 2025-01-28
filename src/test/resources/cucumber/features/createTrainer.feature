Feature: Create trainer
  Scenario: I create the trainer
    Given I create trainer with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
    When I search for trainer with id 1
    Then the trainer should include the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
