package model;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
    private String name;
    private List<Row> rows = new ArrayList<>();
    private List<Column> schema;

    private static List<Sheet> sheets = new ArrayList<>();

    public Sheet(String name, int columnCount) {
        this.name = name;
        this.schema = new ArrayList<>(columnCount);
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

    public static Sheet getSheetByName(String name) {
        for (Sheet sheet : sheets) {
            if (sheet.getName().equals(name)) {
                return sheet;
            }
        }
        return null;
    }

    public static ErrorMessage create(String name, String[] args) {
        if (getSheetByName(name) != null) {
            return ErrorMessage.NAME_EXISTS;
        }
        Sheet sheet = new Sheet(name, args.length);
        for (String col : args) {
            String[] splitted = col.split(":");
            sheet.schema.add(new Column(splitted[0], DataType.valueOf(splitted[1])));
        }
        sheets.add(sheet);
        return null;
    }

    public static ErrorMessage openSheet(String name) {
        return null;    // TODO
    }
}
