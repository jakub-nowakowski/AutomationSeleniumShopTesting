Feature: Buy an item

  Scenario Outline: User buys an item
    Given User's on mystore-testlab.coderslab.pl page
    When User logs-in with <e-mail>, <password>
    And User adds new <item> too cart
    Then User checks the <discount %>
    And User parametrize it with <size>, <quantity>, <colour>
    Then User proceeds to checkout
    And User chooses delivery, <shipment> and <payment> methods
    Then User takes screenshot of order confirmation
    And User closes browser


    Examples:
      | e-mail                          | password     | item                        | size | colour | quantity | discount % | shipment   | payment          |
      | AnthonySSullivan@jourrapide.com | Haisenberg08 | Hummingbird Printed T-Shirt | L    | Black  | 5        | SAVE 20%   | PrestaShop | Pay by Check     |
      | AnthonySSullivan@jourrapide.com | Haisenberg08 | Hummingbird Printed T-Shirt | M    | White  | 7        | SAVE 20%   | My carrier | Pay by bank wire |