Feature: Connect to Postgresql DB and perform CRUD operations via rest calls

  @DBTests
  Scenario: Perform crud operations on the postgresql database
    Given Connection with the DB is established
    When User is saving new persons
    And Updates a person
    And Waits for the DB to be available 2
    And Deletes a person with id '2'
    Then Data inserted in the Table is asserted
