package model;

// an abstract class for a cell in the table
public abstract class Cell {
    // EFFECTS: get the data of the current Cell
    public abstract Object getData();

    // MODIFIES: this
    // EFFECTS: set the data of the current cell
    public abstract void setData(Object data);

    // EFFECTS: compare the current cell to another cell
    public abstract int compareTo(Cell other);

    // REQUIRES: type != null
    // EFFECTS: create a cell of the given data type with the given data.
    //          returns a cell with internal data null in case of error in
    //          converting the data string to the requested data type.
    public static Cell of(DataType type, String data) {
        switch (type) {
            case STRING:
                return new StringCell(data);
            default:    // NUMBER
                try {
                    Double number = Double.parseDouble(data);
                    return new NumberCell(number);
                } catch (NumberFormatException e) {
                    return new NumberCell(null);
                }
        }
    }
}
