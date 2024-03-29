package ui.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ui.menus.CommandLineMainMenu;
import ui.menus.CommandLineSheetEditor;

// enum representing regex pattern and handler method for terminal commands
public enum MenuCommand {
    // Main Menu
    CREATE_SHEET("^\\s*create\\s+sheet\\s+(?<name>\\w+)\\s+(?<args>(\\w+:\\w+\\s+)*\\w+:\\w+)\\s*$",
            CommandLineMainMenu.getInstance()::handleCreateSheet),
    OPEN_SHEET("^\\s*open\\s+sheet\\s+(?<path>\\S.*)$", CommandLineMainMenu.getInstance()::handleOpenSheet),
    
    // Sheet Editor
    INSERT_ROW("^\\s*insert\\s+row\\s+(?<data>\\S.*)$", CommandLineSheetEditor.getInstance()::handleInsertRow),
    SORT_BY("^\\s*sort\\s+by\\s+(?<name>\\w+)\\s*$", CommandLineSheetEditor.getInstance()::handleSortBy),
    SEARCH("^\\s*search\\s+(?<name>\\w+)\\s+(?<data>\\S.*)$", CommandLineSheetEditor.getInstance()::handleSearch),
    DISPLAY_SHEET("^\\s*display\\s+sheet\\s*$", CommandLineSheetEditor.getInstance()::handleDisplay),
    SUM_ROW("^\\s*sum\\s+row\\s+(?<index>\\d+)\\s*$", CommandLineSheetEditor.getInstance()::handleSumRow),
    SAVE_SHEET("^\\s*save\\s+(?<path>\\S.*)$", CommandLineSheetEditor.getInstance()::handleSaveSheet);

    private final String regex;
    private final CommandHandler handler;

    // EFFECTS: construct a MenuCommand enum instance with the
    //          given command regex and command handler function
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
