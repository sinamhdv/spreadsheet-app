package persistence;

import java.io.FileOutputStream;
import org.json.JSONObject;
import org.json.JSONArray;

import model.Cell;
import model.Column;
import model.Row;
import model.Sheet;

public class DataSaver {
    private String filePath;

    // EFFECTS: construct a new DataSaver object with the given file path
    public DataSaver(String filePath) {
        this.filePath = filePath;
    }

    public void saveSheet(Sheet sheet) throws Exception {
        String data = serializeSheet(sheet);
        writeToFile(data);
    }

    private void writeToFile(String data) throws Exception {
        FileOutputStream file = new FileOutputStream(filePath);
        file.write(data.getBytes("UTF-8"));
        file.close();
    }

    private String serializeSheet(Sheet sheet) {
        JSONObject json = new JSONObject();
        json.put("name", sheet.getName());
        serializeSchema(sheet, json);
        serializeRows(sheet, json);
        return json.toString(4);
    }

    private void serializeSchema(Sheet sheet, JSONObject json) {
        JSONArray schema = new JSONArray();
        for (Column column : sheet.getSchema()) {
            JSONObject colJson = new JSONObject();
            colJson.put("name", column.getName());
            colJson.put("type", column.getType().name());
            schema.put(colJson);
        }
        json.put("schema", schema);
    }

    private void serializeRows(Sheet sheet, JSONObject json) {
        JSONArray rows = new JSONArray();
        for (Row row : sheet.getRows()) {
            JSONObject rowObj = new JSONObject();
            JSONArray cells = new JSONArray();
            rowObj.put("cells", cells);
            rows.put(rowObj);
            for (Cell cell : row.getCells()) {
                JSONObject cellObj = new JSONObject();
                cellObj.put("data", cell.getData());
                cells.put(cellObj);
            }
        }
        json.put("rows", rows);
    }
}
