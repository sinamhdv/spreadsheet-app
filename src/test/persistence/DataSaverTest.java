package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DataType;
import model.ErrorMessage;
import model.Sheet;

// tests for data saver class
public class DataSaverTest {
    private static final String SAVE_PATH = "./data/testSaveSheet.json";
    private Sheet sheet;

    // MODIFIES: sheet
    // EFFECTS: initialize the objects used by the tests
    @BeforeEach
    void initializeTests() {
        new File(SAVE_PATH).delete();
        Sheet.create("sheetName", new String[] {"A:STRING", "B:NUMBER"});
        sheet = Sheet.getCurrentSheet();
        sheet.insertRow(new String[] {"str1", "987.6543"});
        sheet.insertRow(new String[] {"str2", "abcd"});
    }

    @Test
    void testIOErrors() {
        assertEquals(ErrorMessage.SAVE_ERROR, sheet.save("./nonexistent/output.json"));
    }

    @Test
    void testSaveSheet() {
        assertNull(sheet.save(SAVE_PATH));
        assertNull(Sheet.load(SAVE_PATH));
        sheet = Sheet.getCurrentSheet();
        assertEquals("sheetName", sheet.getName());
        assertEquals("A", sheet.getSchema().get(0).getName());
        assertEquals(DataType.STRING, sheet.getSchema().get(0).getType());
        assertEquals("B", sheet.getSchema().get(1).getName());
        assertEquals(DataType.NUMBER, sheet.getSchema().get(1).getType());
        assertEquals("str1", sheet.getRows().get(0).getCells().get(0).getData());
        assertEquals(987.6543, sheet.getRows().get(0).getCells().get(1).getData());
        assertEquals("str2", sheet.getRows().get(1).getCells().get(0).getData());
        assertNull(sheet.getRows().get(1).getCells().get(1).getData());
    }
}
