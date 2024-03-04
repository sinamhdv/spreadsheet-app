package model;

// a cell with number data type
public class NumberCell extends Cell {
    private static final double EPSILON = 1e-8;

    private Double data;

    // EFFECTS: construct a NumberCell object with the given data
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

    // REQUIRES: other has an actual type of NumberCell
    // EFFECTS: compare the other cell with the current. Returns negative
    //          if 'this' is smaller, positive if 'this' is larger, and zero if they are equal.
    //          empty cells are larger than other cells
    @Override
    public int compareTo(Cell other) {
        if (data == null) {
            return (other.getData() == null ? 0 : 1);
        } else if (other.getData() == null) {
            return -1;
        }
        return data.compareTo((Double)other.getData());
    }

    // EFFECTS: return a string representation of this cell
    @Override
    public String toString() {
        if (data == null) {
            return "";
        }
        return String.format("%.6f", data);
    }

    // EFFECTS: check if the content of this cell is equal to another cell
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass().equals(getClass())) {
            Double otherData = ((NumberCell)obj).getData();
            if (data == null || otherData == null) {
                return false;
            }
            return Math.abs(data - otherData) <= EPSILON;
        }
        return false;
    }
}
