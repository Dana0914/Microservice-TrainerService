Feature: Find trainer by username
  Scenario: I search for a trainer by username
    Given a trainer exists with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
    When I find the trainer by username "Aron.Cuper"
    Then the trainer should include the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |

  Scenario: I search a non-existent username
    Given a trainer exists with the following details:
      | id | username   | firstname | lastname | duration | isActive |
      | 1  | Aron.Cuper | Aron      | Cuper    | 2        | true     |
    When I search for trainer with username "John.Doe"
    Then return no result