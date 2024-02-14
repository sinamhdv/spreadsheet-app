package ui;

import java.util.Scanner;
import java.util.regex.Matcher;

import model.ErrorMessage;
import ui.commands.MenuCommand;

// shared methods between different menus
public abstract class Menu {
    private static final Scanner SCANNER = new Scanner(System.in);

    protected abstract void printPrompt();

    // EFFECTS: show an error message
    protected void showAlert(ErrorMessage message) {
        System.out.println("Error: " + message.getText());
    }

    // EFFECTS: input loop for all menus
    public void run() {
        Matcher matcher;
        while (true) {
            printPrompt();
            String line = SCANNER.nextLine();
            boolean valid = false;
            for (MenuCommand command : MenuCommand.values()) {
                if ((matcher = MenuCommand.getMatcher(line, command)) != null) {
                    command.getHandler().run(matcher);
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                System.out.println("Error: Invalid command");
            }
        }
    }
}