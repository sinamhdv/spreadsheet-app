package model;

public class NumberCell extends Cell {
    private Double data;

    public NumberCell(Double data) {
        this.data = data;
    }

    @Override
    public Double getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = (Double)data;
    }

    @Override
    public int compareTo(Cell other) {
        return data.compareTo((Double)other.getData());
    }

    @Override
    public String toString() {
        String str = String.format("%." + Cell.MAX_REPR_WIDTH + "f", data);
        if (str.length() > Cell.MAX_REPR_WIDTH) {
            str = str.substring(0, Cell.MAX_REPR_WIDTH - 3) + "...";
        }
        return str;
    }
}
