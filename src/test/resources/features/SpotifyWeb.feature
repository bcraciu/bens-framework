Feature: Login to Spotify

  @SeleniumTests
  Scenario: Login to Spotify with Spotify Account
    Given User has access to Spotify
    When User logs in with spotify credentials
    Then Spotify Login is working as expected

  @SeleniumTests
  Scenario: Login to Spotify with Facebook Account
    Given User has access to Spotify
    When User logs in with Facebook credentials
    Then Facebook Login is working as expected

  @SeleniumTests
  Scenario: Login to Spotify with Apple Account
    Given User has access to Spotify
    When User logs in with Apple credentials
    Then Apple Login is failing as expected

  @SeleniumTests
  Scenario: Login to Spotify with Google Account
    Given User has access to Spotify
    When User logs in with Google credentials
    Then Google Login is working as expected

#    Examples:
#      | spotify  |
#      | facebook |
#      | apple    |
#      | google   |



