package model;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Cell> cells;

    public Row(int size) {
        cells = new ArrayList<>(size);
    }

    public List<Cell> getCells() {
        return cells;
    }
}
