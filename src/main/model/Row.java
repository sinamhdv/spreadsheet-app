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

    public double getSum(List<Column> schema) {
        double sum = 0;
        int i = 0;
        for (Cell cell : cells) {
            if (schema.get(i).getType().equals(DataType.NUMBER)) {
                sum += ((NumberCell)cell).getData();
            }
        }
        return sum;
    }
}
