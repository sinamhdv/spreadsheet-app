package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static Sheet getCurrentSheet() {
        return currentSheet;
    }

    // MODIFIES: sheets
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
}
