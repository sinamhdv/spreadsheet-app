package model;

// enum representing an error message returned from model to ui
public enum ErrorMessage {
    // Main Menu
    NAME_NOT_FOUND("Name not found"),
    NAME_EXISTS("Name already exists"),
    LOAD_ERROR("Cannot load sheet from the specified file"),
    
    // Sheet Editor
    BAD_ROW_LENGTH("Incorrect row length"),
    COLUMN_NOT_FOUND("Column not found"),
    INVALID_INDEX("Index out of range"),
    SAVE_ERROR("Cannot save sheet into the specified file");

    private final String text;

    // EFFECTS: construct an ErrorMessage enum object with given error text
    private ErrorMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
