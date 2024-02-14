package ui;

import java.util.regex.Matcher;

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
        System.out.println("CREATE");   // TODO
    }

    public void handleSortBy(Matcher matcher) {
        System.out.println("LIST"); // TODO
    }

    public void handleSearch(Matcher matcher) {
        System.out.println("OPEN"); // TODO
    }
}
