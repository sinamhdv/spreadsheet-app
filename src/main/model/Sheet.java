package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import persistence.DataLoader;
import persistence.DataSaver;

// a spreadsheet/table of information
public class Sheet {
    private String name;
    private List<Row> rows = new ArrayList<>();
    private List<Column> schema = new ArrayList<>();

    private static Sheet currentSheet;

    // EFFECTS: construct a Sheet object with the given name
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

    // MODIFIES: this
    // EFFECTS: add a row to the end of the sheet. In case of error returns
    //          an ErrorMessage, otherwise returns null
    public ErrorMessage insertRow(String[] data) {
        if (data.length != schema.size()) {
            return ErrorMessage.BAD_ROW_LENGTH;
        }
        Row row = new Row();
        for (int i = 0; i < data.length; i++) {
            Column col = schema.get(i);
            row.getCells().add(Cell.of(col.getType(), data[i]));
        }
        rows.add(row);
        return null;
    }

    // EFFECTS: returns the index of the column with the given name.
    //          returns -1 if not found
    private int getColumnIndexByName(String name) {
        for (int i = 0; i < schema.size(); i++) {
            if (schema.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    // MODIFIES: this
    // EFFECTS: sorts the rows by the values in one column
    public ErrorMessage sortBy(String colName) {
        int index = getColumnIndexByName(colName);
        if (index == -1) {
            return ErrorMessage.COLUMN_NOT_FOUND;
        }
        Collections.sort(rows, (r1, r2) -> {
            Cell c1 = r1.getCells().get(index);
            Cell c2 = r2.getCells().get(index);
            return c1.compareTo(c2);
        });
        return null;
    }

    // EFFECTS: search and return a list of rows containing given data in the specified column.
    //          returns null if the column name is invalid.
    public List<Row> search(String name, String data) {
        int index = getColumnIndexByName(name);
        if (index == -1) {
            return null;
        }
        List<Row> result = new ArrayList<>();
        Cell referenceCell = Cell.of(schema.get(index).getType(), data);
        for (Row row : rows) {
            if (referenceCell.equals(row.getCells().get(index))) {
                result.add(row);
            }
        }
        return result;
    }

    // EFFECTS: sum the non-empty numeric cells in a row
    public Double sumRow(int index) {
        if (index <= 0 || index > rows.size()) {
            return null;
        }
        return rows.get(index - 1).getSum(schema);
    }

    // EFFECTS: save the sheet object in the given file. returns an ErrorMessage or null if succeeded
    public ErrorMessage save(String path) {
        try {
            new DataSaver(path).saveSheet(this);
            return null;
        } catch (Exception ex) {
            return ErrorMessage.SAVE_ERROR;
        }
    }

    public static Sheet getCurrentSheet() {
        return currentSheet;
    }

    // MODIFIES: currentSheet
    // EFFECTS: create a new sheet. returns an ErrorMessage or null if succeeded
    public static ErrorMessage create(String name, String[] args) {
        Sheet sheet = new Sheet(name);
        for (String col : args) {
            String[] splitted = col.split(":");
            sheet.schema.add(new Column(splitted[0], DataType.valueOf(splitted[1])));
        }
        currentSheet = sheet;
        return null;
    }

    // MODIFIES: currentSheet
    // EFFECTS: load the currentSheet from a file. returns an ErrorMessage or null if succeeded
    public static ErrorMessage load(String path) {
        try {
            currentSheet = new DataLoader(path).loadSheet();
            return null;
        } catch (Exception ex) {
            return ErrorMessage.LOAD_ERROR;
        }
    }
}
