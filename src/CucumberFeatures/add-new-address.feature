Feature: Add new address

  Scenario Outline: User adds new address
    Given I'm on mystore-testlab.coderslab.pl page
    When I sing in as a registered user with <e-mail>, <password>
    And I add new address and confirm it with <alias>, <address>, <city>, <zipPostalCode>, <country>, <phone>
    Then I compare the address details with <alias>, <full name>, <address>, <city>, <zipPostalCode>, <country>, <phone>
    And I delete the address and see success deleted alert "Address successfully deleted!"
    Then I take screenshot
    And I close the browser

    Examples:
      | full name        | e-mail                          | password     | alias | address  | city   | zipPostalCode | country        | phone       |
      | Anthony Sullivan | AnthonySSullivan@jourrapide.com | Haisenberg08 | Tony  | Polna 36 | Zgierz | 66-666        | United Kingdom | 666-666-666 |