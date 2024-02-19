package model;

// a cell with string data type
public class StringCell extends Cell {
    private String data;

    public StringCell(String data) {
        this.data = data;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = (String)data;
    }

    // REQUIRES: other has an actual type of StringCell
    // EFFECTS: compare the other cell with the current lexicographically. Returns negative
    //          if 'this' is smaller, positive if 'this' is larger, and zero if they are equal.
    @Override
    public int compareTo(Cell other) {
        return data.compareTo((String)other.getData());
    }

    // EFFECTS: return a string representation of this cell
    @Override
    public String toString() {
        return data;
    }

    // EFFECTS: check if the content of this cell is equal to another cell
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass().equals(getClass())) {
            return ((StringCell)obj).getData().equals(data);
        }
        return false;
    }
}
