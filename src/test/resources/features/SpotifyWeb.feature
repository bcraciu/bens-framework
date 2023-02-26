Feature: Login to Spotify

  @SpotifyTests
  Scenario: Login to Spotify with different accounts
    Given User has access to Spotify
    When User logs in with spotify credentials
    And User logs in with facebook credentials
    And User logs in with apple credentials
    And User logs in with google credentials
    Then Login feature is working as expected

#    Examples:
#      | loginType|
#      | spotify  |
#      | facebook |
#      | apple    |
#      | google   |



