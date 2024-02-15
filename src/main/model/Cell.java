package model;

public class Cell {
    private Object data;

    public Cell(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Cell of(DataType type, String data) {
        switch (type) {
            case STRING:
                return new Cell(data);
            case NUMBER:
                try {
                    Double number = Double.parseDouble(data);
                    return new Cell(number);
                } catch (NumberFormatException e) {
                    return null;
                }
            default:
                return null;
        }
    }
}
