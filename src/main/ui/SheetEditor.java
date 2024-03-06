package ui;

import java.util.List;
import java.util.regex.Matcher;

import model.Cell;
import model.Column;
import model.ErrorMessage;
import model.Row;
import model.Sheet;

// the initial menu of the application
public class SheetEditor extends Menu {
    private static final int CELL_REPR_WIDTH = 16;

    private static SheetEditor instance;

    // MODIFIES: instance
    // EFFECTS: returns a singleton instance of SheetEditor
    public static SheetEditor getInstance() {
        return (instance == null ? (instance = new SheetEditor()) : instance);
    }

    // EFFECTS: print terminal prompt
    @Override
    protected void printPrompt() {
        System.out.print("editor> ");
    }

    // EFFECTS: command handler for insert row command
    public void handleInsertRow(Matcher matcher) {
        ErrorMessage error = Sheet.getCurrentSheet().insertRow(matcher.group("data").split(";"));
        if (error != null) {
            showAlert(error);
        }
    }

    // EFFECTS: command handler for sort command
    public void handleSortBy(Matcher matcher) {
        ErrorMessage error = Sheet.getCurrentSheet().sortBy(matcher.group("name"));
        if (error != null) {
            showAlert(error);
        }
    }

    // EFFECTS: command handler for search command
    public void handleSearch(Matcher matcher) {
        List<Row> result = Sheet.getCurrentSheet().search(matcher.group("name"), matcher.group("data"));
        if (result == null) {
            showAlert(ErrorMessage.COLUMN_NOT_FOUND);
            return;
        }
        System.out.println("Matches: ");
        System.out.println("============");
        for (Row row : result) {
            displayRow(row);
        }
        System.out.println("");
    }

    // EFFECTS: command handler for sum row command
    public void handleSumRow(Matcher matcher) {
        int index = Integer.parseInt(matcher.group("index"));
        Double sum = Sheet.getCurrentSheet().sumRow(index);
        if (sum == null) {
            showAlert(ErrorMessage.INVALID_INDEX);
            return;
        }
        System.out.println("Sum of row #" + index + " is " + sum);
    }

    public void handleSaveSheet(Matcher matcher) {
        // TODO
    }

    // EFFECTS: display the whole sheet
    public void handleDisplay(Matcher matcher) {
        Sheet sheet = Sheet.getCurrentSheet();
        System.out.printf("%s (%dx%d):\n", sheet.getName(), sheet.getRows().size(), sheet.getSchema().size());
        displaySchema(sheet.getSchema());
        int index = 1;
        for (Row row : sheet.getRows()) {
            System.out.printf("%8d) ", index);
            displayRow(row);
            index++;
        }
    }

    // EFFECTS: display the schema of the current sheet
    private void displaySchema(List<Column> schema) {
        System.out.print("          ");
        for (Column column : schema) {
            printWithAdjustedLength(column.getName());
        }
        System.out.print("\n          ");
        for (Column column : schema) {
            printWithAdjustedLength(column.getType().toString());
        }
        System.out.println("");
    }

    // EFFECTS: display a single row of the sheet
    private void displayRow(Row row) {
        for (Cell cell : row.getCells()) {
            printWithAdjustedLength(cell.toString());
        }
        System.out.println("");
    }

    // EFFECTS: print str with a fixed width
    private void printWithAdjustedLength(String str) {
        if (str.length() > CELL_REPR_WIDTH) {
            System.out.print(str.substring(0, CELL_REPR_WIDTH - 3) + "...");
        } else {
            System.out.printf("%-" + CELL_REPR_WIDTH + "s", str);
        }
        System.out.print(" | ");
    }
}
