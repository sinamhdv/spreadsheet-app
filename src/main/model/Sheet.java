package model;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
    private String name;
    private List<Row> rows = new ArrayList<>();

    public Sheet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Row> getRows() {
        return rows;
    }
}
