package ui.commands;

import java.util.regex.Matcher;

// interface representing a command handler function
public interface CommandHandler {
    void run(Matcher matcher);
}
