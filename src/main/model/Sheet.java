package model;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
    private String name;
    private List<Row> rows = new ArrayList<>();
    private List<Column> schema = new ArrayList<>();

    private static List<Sheet> sheets = new ArrayList<>();
    private static Sheet currentSheet;

    public Sheet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Row> getRows() {
        return rows;
    }

    public List<Column> getSchema() {
        return schema;
    }

    public ErrorMessage insertRow(String[] data) {
        if (data.length != schema.size()) {
            return ErrorMessage.BAD_ROW_LENGTH;
        }
        Row row = new Row(data.length);
        for (int i = 0; i < data.length; i++) {
            Column col = schema.get(i);
            row.getCells().set(i, Cell.of(col.getType(), data[i]));
        }
        rows.add(row);
        return null;
    }

    public ErrorMessage sortBy(String colName) {
        return null;    // TODO
    }

    public static List<Sheet> getSheets() {
        return sheets;
    }

    public static Sheet getCurrentSheet() {
        return currentSheet;
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
        Sheet sheet = new Sheet(name);
        for (String col : args) {
            String[] splitted = col.split(":");
            sheet.schema.add(new Column(splitted[0], DataType.valueOf(splitted[1])));
        }
        sheets.add(sheet);
        return null;
    }

    public static ErrorMessage openSheet(String name) {
        Sheet sheet = getSheetByName(name);
        if (sheet == null) {
            return ErrorMessage.NAME_NOT_FOUND;
        }
        currentSheet = sheet;
        return null;
    }
}
