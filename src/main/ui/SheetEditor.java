package ui;

import java.util.List;
import java.util.regex.Matcher;

import model.ErrorMessage;
import model.Row;
import model.Sheet;

// the initial menu of the application
public class SheetEditor extends Menu {
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

    public void handleInsertRow(Matcher matcher) {
        ErrorMessage error = Sheet.getCurrentSheet().insertRow(matcher.group("data").split(";"));
        if (error != null) {
            showAlert(error);
        }
    }

    public void handleSortBy(Matcher matcher) {
        ErrorMessage error = Sheet.getCurrentSheet().sortBy(matcher.group("name"));
        if (error != null) {
            showAlert(error);
        }
    }

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

    public void handleInfo(Matcher matcher) {

    }

    public void handleDisplay(Matcher matcher) {

    }

    private void displayRow(Row row) {
        
    }
}
