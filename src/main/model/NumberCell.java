package model;

public class NumberCell extends Cell {
    private static final double EPSILON = 1e-8;

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
        return String.format("%.6f", data);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass().equals(getClass())) {
            Double otherData = ((NumberCell)obj).getData();
            return Math.abs(data - otherData) <= EPSILON;
        }
        return false;
    }
}
