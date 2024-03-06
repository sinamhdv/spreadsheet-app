package persistence;

import model.Sheet;

public class DataSaver {
    private String filePath;

    // EFFECTS: construct a new DataSaver object with the given file path
    public DataSaver(String filePath) {
        this.filePath = filePath;
    }

    public Sheet loadSheet() {
        return null;    // TODO
    }
}
