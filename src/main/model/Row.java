package model;

import java.util.ArrayList;
import java.util.List;

// a row in the table
public class Row {
    private List<Cell> cells = new ArrayList<>();

    public List<Cell> getCells() {
        return cells;
    }

    // REQUIRES: schema.size() == cells.size()
    // EFFECTS: calculate the sum of the numeric cells in this row
    public double getSum(List<Column> schema) {
        double sum = 0;
        int i = 0;
        for (Cell cell : cells) {
            if (schema.get(i).getType().equals(DataType.NUMBER) && cell.getData() != null) {
                sum += ((NumberCell)cell).getData();
            }
            i++;
        }
        return sum;
    }
}
