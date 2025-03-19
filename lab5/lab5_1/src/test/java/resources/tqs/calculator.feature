Feature: Basic Arithmetic

  Background: A Calculator
    Given a calculator I just turned on

  Scenario: Addition
    When I add 4 and 5
    Then the result is 9

  Scenario: Substraction
    When I substract 7 to 2 
    Then the result is 5

  Scenario: Multiplication
    When I multiply 5 and 3
    Then the result is 15

  Scenario: Division
    When I divide 20 by 4
    Then the result is 5

  Scenario: Division by 0
    When I divide 8 by 0
    The the result is an error

  Scenario Outline: Several additions
    When I add <a> and <b>
    Then the result is <c>

  Examples: Single digits
    | a | b | c  |
    | 1 | 2 | 3  |
    | 3 | 7 | 10 |

  Scenario Outline: Several substractions
    When I substract <a> and <b>
    Then the result is <c>

  Examples: Single digits  
    | a | b | c  |
    | 9 | 5 | 4  |
    | 2 | 1 | 1  |

  Scenario Outline: Several multiplications
    When I multiply <a> and <b>
    Then the result is <c>

  Examples: Single digits  
    | a | b | c  |
    | 2 | 3 | 6  |
    | 4 | 2 | 8  |


  Scenario Outline: Several divisions
    When I divide <a> by <b>
    Then the result is <c>

  Examples: Single digits  
    | a | b | c  |
    | 6 | 2 | 3  |
    | 2 | 1 | 2  |
