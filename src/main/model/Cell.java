package model;

public class Cell {
    private static final int MAX_REPR_WIDTH = 16;

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

    public String getStringRepr() {
        String str = (String)data;
        if (str.length() > MAX_REPR_WIDTH) {
            str = str.substring(0, MAX_REPR_WIDTH - 3) + "...";
        }
        return str;
    }

    public String getNumberRepr() {
        Double num = (Double)data;
        String str = String.format("%." + MAX_REPR_WIDTH + "f", num);
        if (str.length() > MAX_REPR_WIDTH) {
            str = str.substring(0, MAX_REPR_WIDTH - 3) + "...";
        }
        return str;
    }

    public int compareTo(Cell other) {
        if (data.getClass().equals(Double.class)) {
            return ((Double)data).compareTo((Double)other.data);
        } else {
            return ((String)data).compareTo((String)other.data);
        }
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
