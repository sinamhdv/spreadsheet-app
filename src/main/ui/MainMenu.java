package ui;

import java.util.regex.Matcher;

import model.ErrorMessage;
import model.Sheet;

// the initial menu of the application
public class MainMenu extends Menu {
    private static MainMenu instance;

    // MODIFIES: instance
    // EFFECTS: returns a singleton instance of MainMenu
    public static MainMenu getInstance() {
        return (instance == null ? (instance = new MainMenu()) : instance);
    }

    // EFFECTS: print terminal prompt
    @Override
    protected void printPrompt() {
        System.out.print("> ");
    }

    // EFFECTS: command handler for create sheet command
    public void handleCreateSheet(Matcher matcher) {
        ErrorMessage error = Sheet.create(matcher.group("name"), matcher.group("args").strip().split("\\s+"));
        if (error != null) {
            showAlert(error);
        }
    }

    // EFFECTS: command handler for listing sheets
    public void handleListSheets(Matcher matcher) {
        System.out.println("List of sheets:");
        System.out.println("================");
        for (Sheet sheet : Sheet.getSheets()) {
            System.out.println(sheet.getName());
        }
        System.out.println("");
    }

    // EFFECTS: command handler for opening a sheet
    public void handleOpenSheet(Matcher matcher) {
        ErrorMessage error = Sheet.openSheet(matcher.group("name"));
        if (error != null) {
            showAlert(error);
        }
        SheetEditor.getInstance().run();
    }
}
