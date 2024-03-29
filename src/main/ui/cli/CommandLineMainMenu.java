package ui.cli;

import java.util.regex.Matcher;

import model.ErrorMessage;
import model.Sheet;

// the initial menu of the application
public class CommandLineMainMenu extends CommandLineMenu {
    private static CommandLineMainMenu instance;

    // MODIFIES: instance
    // EFFECTS: returns a singleton instance of MainMenu
    public static CommandLineMainMenu getInstance() {
        return (instance == null ? (instance = new CommandLineMainMenu()) : instance);
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
            return;
        }
        CommandLineSheetEditor.getInstance().run();
    }

    // EFFECTS: command handler for 'open' command
    public void handleOpenSheet(Matcher matcher) {
        ErrorMessage error = Sheet.load(matcher.group("path"));
        if (error != null) {
            showAlert(error);
            return;
        }
        CommandLineSheetEditor.getInstance().run();
    }
}
