Feature: Buy an item

  Scenario Outline: User buys an item
    Given User's on mystore-testlab.coderslab.pl page
    When User logs-in with <e-mail>, <password>
    And User adds new <item> too cart
    Then User parametrize it with <size>, <quantity>, <colour>
    And User checks the discount and proceeds to checkout
    Then User chooses delivery, <shipment> and <payment> methods
    And User takes screenshot of order confirmation
    Then User closes browser

    Examples:
      | e-mail                          | password     | item                        | size | colour | quantity | shipment   | payment          |
      | AnthonySSullivan@jourrapide.com | Haisenberg08 | Hummingbird Printed T-Shirt | L    | Black  | 5        | PrestaShop | Pay by Check     |
      | AnthonySSullivan@jourrapide.com | Haisenberg08 | Hummingbird Printed Sweater | M    | -      | 7        | My carrier | Pay by bank wire |
      | AnthonySSullivan@jourrapide.com | Haisenberg08 | Mug The Adventure Begins    | -    | -      | 12       | My carrier | Pay by bank wire |