package persistence;

import java.io.FileInputStream;

import org.json.JSONObject;
import org.json.JSONArray;

import model.Column;
import model.DataType;
import model.NumberCell;
import model.Row;
import model.Sheet;
import model.StringCell;

public class DataLoader {
    private String filePath;

    // EFFECTS: construct a new DataLoader object with the given file path
    public DataLoader(String filePath) {
        this.filePath = filePath;
    }

    public Sheet loadSheet() throws Exception {
        String data = readFile();
        return deserializeSheet(data);
    }

    private String readFile() throws Exception {
        FileInputStream file = new FileInputStream(filePath);
        byte[] dataBytes = file.readAllBytes();
        file.close();
        String dataStr = new String(dataBytes, "UTF-8");
        return dataStr;
    }

    private Sheet deserializeSheet(String data) {
        JSONObject json = new JSONObject(data);
        String name = json.getString("name");
        Sheet sheet = new Sheet(name);
        JSONArray schema = json.getJSONArray("schema");
        parseSchema(sheet, schema);
        JSONArray rows = json.getJSONArray("rows");
        parseRows(sheet, rows);
        return sheet;
    }

    private void parseRows(Sheet sheet, JSONArray rows) {
        for (Object rowObj : rows) {
            Row row = new Row();
            sheet.getRows().add(row);
            JSONArray cells = ((JSONObject)rowObj).getJSONArray("cells");
            int index = 0;
            for (Object cellObj : cells) {
                switch (sheet.getSchema().get(index).getType()) {
                    case STRING:
                        row.getCells().add(new StringCell(((JSONObject)cellObj).getString("data")));
                        break;
                    default:    // NUMBER
                        row.getCells().add(new NumberCell(((JSONObject)cellObj).getDouble("data")));
                        break;
                }
                index++;
            }
        }
    }

    private void parseSchema(Sheet sheet, JSONArray schema) {
        for (Object colObj : schema) {
            String name = ((JSONObject)colObj).getString("name");
            String type = ((JSONObject)colObj).getString("type");
            Column column = new Column(name, DataType.valueOf(type));
            sheet.getSchema().add(column);
        }
    }
}
