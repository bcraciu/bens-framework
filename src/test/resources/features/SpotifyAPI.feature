Feature: Login to Spotify

  @SpotifyTests
  Scenario: Check the API of Spotify
    Given User has access to Spotify API
    When User is searching for a track that is playing
    Then The track is validated