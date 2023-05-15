Feature: Login to Spotify

  @SpotifyTests
  Scenario: Retrieve access token
    Given User is retrieving access token
    When User is checking the API functionalities
    Then User can do API calls

  @SpotifyTests
  Scenario: Check the search
    Given User has access to Spotify API
    When User is searching for a track
    Then The track is validated

  @SpotifyTests
  Scenario: Check Authorization Flow
    Given Authorization calls are done
    When User is checking the currently playing track
    Then The track is retrieved

  @SpotifyTests
  Scenario: Check the API of Spotify
    Given User has access to Spotify API
    When User skips to the next track
    Then The next track is playing

  @SpotifyTests
  Scenario: Test test test
    Given Basic login authentication
    When Code is retrieved
    Then Authorization is done