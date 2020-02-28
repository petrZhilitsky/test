Feature: Yandex disk operations
  As a Yandex user
  I want to enter Yandex Disk after log in and perform some basic operations

  @All
  Scenario Outline: Log in Yandex Disk account page
    Given user is on the log in page
    When user clicks log in button
    And user enters login <login>
    And user clicks confirm button
    And user enters password <password>
    And user clicks confirm button
    Then user reaches Yandex Disk account page
    Examples:
      | login          | password  |
      | petr.zhilitsky | Gomel2020 |

  @All
  Scenario: Main Menu items navigation
    When user navigates the Recent item
    Then user gets the /client/recent Main Menu page
    When user navigates the Files item
    Then user gets the /client/disk Main Menu page
    When user navigates the Photo item
    Then user gets the /client/photo Main Menu page
    When user navigates the Shared item
    Then user gets the /client/shared Main Menu page
    When user navigates the Journal item
    Then user gets the /client/journal Main Menu page
    When user navigates the Archive item
    Then user gets the /client/attach Main Menu page
    When user navigates the Trash item
    Then user gets the /client/trash Main Menu page

  @All
  Scenario: Creation of new folder
    When user navigates the Files item
    And user clicks Create button
    And user clicks Folder button
    And user gives name to folder
    And user clicks Save button
    Then user sees new folder in Files item

  @All
  Scenario: Creation and edition of new Word file
    When user clicks Create button
    And user clicks Folder button
    And user gives name to folder
    And user clicks Save button
    And user opens new folder
    And user clicks Create button
    And user clicks Word File button
    And user navigates Word editor tab
    And user types text in new Word file
    And user renames new Word file
    And user closes Word editor
    Then user sees new Word file in Files item
    And user opens new Word file
    And user navigates Word editor tab
    And user sees text of new Word file
    And user closes Word editor

  @All
  Scenario: Moving folder to trash
    When user navigates the Files item
    And user clicks Create button
    And user clicks Folder button
    And user gives name to folder
    And user clicks Save button
    And user chooses new folder
    And user clicks Delete button
    And user navigates the Trash item
    Then user sees new folder in Trash item

  @All
  Scenario: Removing folder from trash
    When user navigates the Files item
    And user clicks Create button
    And user clicks Folder button
    And user gives name to folder
    And user clicks Save button
    And user chooses new folder
    And user clicks Delete button
    And user navigates the Trash item
    And user chooses new folder
    And user clicks Remove button
    Then user do not see new folder in Trash item