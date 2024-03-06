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

    public void handleOpenSheet(Matcher matcher) {
        // TODO
    }
}
