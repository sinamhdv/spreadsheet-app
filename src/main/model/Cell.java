package model;

public abstract class Cell {
    public abstract Object getData();

    public abstract void setData(Object data);

    public abstract int compareTo(Cell other);

    public static Cell of(DataType type, String data) {
        switch (type) {
            case STRING:
                return new StringCell(data);
            case NUMBER:
                try {
                    Double number = Double.parseDouble(data);
                    return new NumberCell(number);
                } catch (NumberFormatException e) {
                    return null;
                }
            default:
                return null;
        }
    }
}
