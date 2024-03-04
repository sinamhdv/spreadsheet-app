package ui.commands;

import java.util.regex.Matcher;

// interface representing a command handler function
public interface CommandHandler {
    // EFFECTS: run the command handler with the given regex matcher object
    void run(Matcher matcher);
}
