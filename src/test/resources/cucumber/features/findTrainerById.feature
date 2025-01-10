
Feature: Find trainer by id
  Scenario: Successfully find a trainer by ID
    Given a trainer exists with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
    When I find the trainer by id 1
    Then the response should include the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |

  Scenario: Searching with a non-existent ID
    Given a trainer exists with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
    When I search for trainer with id 100
    Then return no result
