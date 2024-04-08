# Spreadsheet

This is a **spreadsheet editor** that can create, open, edit, and manage spreadsheets

## Applications

This program can be used to edit and save data, and make calculations based on data

## User Stories

- As a user, I want to be able to add rows to the current sheet
- As a user, I want to be able to display all rows in the current sheet
- As a user, I want to be able to search for rows of the sheet that have a specific value in a specific column
- As a user, I want to be able to sort rows of the sheet by one of the columns
- As a user, I want to be able to have the option of saving the entire current spreadsheet into a file
- As a user, I want to be able to have the option of loading a spreadsheet from a file and editing it

## Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by creating/opening a sheet and then entering data in the text fields above the sheet table and pressing the "add" button to add a new row.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by selecting a column name in the combo box above the sheet table and pressing the "sort" button beside it to sort the table according to that column, or enter a search query in the text field next to it and select a column, and search that column for that query by pressing the search button.
- You can locate my visual component in the initial page of the application (the background image) and the progress GIF displayed after saving a sheet.
- You can save the state of my application by selecting the "save" option in the menu bar (or Ctrl+S)
- You can reload the state of my application by selecting the "open" option in the menu bar (or Ctrl+O)

## Phase 4: Task 2 (Sample Logs)

```
Logs:
Mon Apr 01 19:57:41 PDT 2024
added a new row to sheet 'class-A-grades' with data: student-1;98;88;70
Mon Apr 01 19:57:50 PDT 2024
added a new row to sheet 'class-A-grades' with data: student-4;98;88;70
Mon Apr 01 19:58:04 PDT 2024
added a new row to sheet 'class-A-grades' with data: student-3;90;80;73
Mon Apr 01 19:58:20 PDT 2024
added a new row to sheet 'class-A-grades' with data: student-2;93;75;99
Mon Apr 01 19:58:25 PDT 2024
sorted sheet 'class-A-grades' by column 'name'
Mon Apr 01 19:58:33 PDT 2024
sorted sheet 'class-A-grades' by column 'math'
Mon Apr 01 19:58:44 PDT 2024
sorted sheet 'class-A-grades' by column 'phys'
Mon Apr 01 19:58:53 PDT 2024
sorted sheet 'class-A-grades' by column 'chem'
Mon Apr 01 19:59:15 PDT 2024
searched column 'chem' of sheet 'class-A-grades' for query '70' and found 2 matches
Mon Apr 01 19:59:20 PDT 2024
searched column 'chem' of sheet 'class-A-grades' for query '79' and found 0 matches
```

## Phase 4: Task 3

If I had more time to work on this project, one refactoring I would do could be not using static values
for the dimensions of GUI elements and the window, and taking the screen size into account to dynamically determine those.
So, I would write a function to dynamically determine the window size and return a `Dimension` object, and then I would use that function instead of the constant defined to create the window.

Another possible refactoring is extracting smaller functions from `SheetEditorScreen::addEditorControls`, as this function is pretty long and is doing a number of different tasks. So I could instead divide its functionality into smaller helper functions.
