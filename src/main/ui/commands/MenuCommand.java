package ui.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ui.CodeBrowser;
import ui.MainMenu;
import ui.ProjectMenu;

// enum representing regex pattern and handler method for terminal commands
public enum MenuCommand {
    // Main Menu
    CREATE_SHEET("^\\s*create\\s+sheet\\s+(?<name>\\w+)\\s+(?<args>\\S.+)$",
            MainMenu.getInstance()::handleCreateSheet),
    LIST_SHEETS("^\\s*list\\s+sheets\\s*$",
            MainMenu.getInstance()::handleListSheets),
    OPEN_SHEET("^\\s*open\\s+sheet\\s+(?<name>\\w+)\\s*$",
            MainMenu.getInstance()::handleOpenSheet),
    
    // Sheet Editor
    INSERT_ROW("^\\s*insert\\s+row\\s+(?<data>\\S.*)$",
            MainMenu.getInstance()::handleInsertRow),
    SORT_BY("^\\s*sort\\s+by\\s+(?<name>\\w+)\\s*$",
            MainMenu.getInstance()::handleSortBy),
    SEARCH("^\\s*search\\s+(?<name>\\w+)\\s+(?<data>\\S.*)$",
            MainMenu.getInstance()::handleSearch);

    private final String regex;
    private final CommandHandler handler;

    private MenuCommand(String regex, CommandHandler handler) {
        this.regex = regex;
        this.handler = handler;
    }

    public CommandHandler getHandler() {
        return handler;
    }

    // EFFECTS: return the regex matcher if input matches the command pattern
    //          or null if there is no match.
    public static Matcher getMatcher(String input, MenuCommand command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return (matcher.matches() ? matcher : null);
    }
}
