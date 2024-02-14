package ui;

import java.util.regex.Matcher;

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

    public void handleCreateSheet(Matcher matcher) {
        System.out.println("CREATE");   // TODO
    }

    public void handleListSheets(Matcher matcher) {
        System.out.println("LIST"); // TODO
    }

    public void handleOpenSheet(Matcher matcher) {
        System.out.println("OPEN"); // TODO
    }
}
