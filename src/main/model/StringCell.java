package model;

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

    @Override
    public int compareTo(Cell other) {
        return data.compareTo((String)other.getData());
    }

    @Override
    public String toString() {
        String str = data;
        if (str.length() > MAX_REPR_WIDTH) {
            str = str.substring(0, MAX_REPR_WIDTH - 3) + "...";
        }
        return str;
    }
}
