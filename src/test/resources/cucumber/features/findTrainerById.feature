
Feature: Find trainer by id
  Scenario: I search for a trainer by ID
    Given a trainer exists with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
    When I find the trainer by id 1
    Then the trainer should include the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |

  Scenario: I search by a non-existent ID
    Given a trainer exists with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
    When I search for trainer with id 100
    Then return no result
