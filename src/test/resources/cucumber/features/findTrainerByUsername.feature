Feature: Find trainer by username
  Scenario: Successfully find a trainer by username
    Given a trainer exists with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
    When I find the trainer by username "Aron.Cuper"
    Then the response should include the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |

  Scenario: Searching with a non-existent username
    Given a trainer exists with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
    When I search for trainer with username "John.Doe"
    Then return no result