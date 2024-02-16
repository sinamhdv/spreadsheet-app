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
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(getClass())) {
            return ((StringCell)obj).getData().equals(data);
        }
        return false;
    }
}
