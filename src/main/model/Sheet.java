package model;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
    private String name;
    private List<Row> rows = new ArrayList<>();

    private static List<Sheet> sheets = new ArrayList<>();

    public Sheet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Row> getRows() {
        return rows;
    }

    public static List<Sheet> getSheets() {
        return sheets;
    }

    public static ErrorMessage create(String name, String[] args) {
        return null;    // TODO
    }
}
