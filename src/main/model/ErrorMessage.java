package model;

// enum representing an error message returned from model to ui
public enum ErrorMessage {
    // Main Menu
    NAME_NOT_FOUND("Name not found"),
    NAME_EXISTS("Name already exists");

    private final String text;

    private ErrorMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
