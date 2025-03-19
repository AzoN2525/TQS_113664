Feature: Online Library Search

  Scenario: Search for a book by title
    Given the user is on the library homepage
    When the user searches for "Clean Code"
    Then the search results should contain "Clean Code"
  
  Scenario: Search for books by author
    Given the user is on the library homepage
    When the user searches for books written by "Robert C. Martin"
    Then the search results should contain "Clean Code"
  
  Scenario: Search for a non-existent book
    Given the user is on the library homepage
    When the user searches for "Non-Existent Book"
    Then the search results should be empty
