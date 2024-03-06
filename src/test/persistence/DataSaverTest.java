package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DataType;
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
        sheet.insertRow(new String[] {"str2", "-137"});
    }

    @Test
    void testIOErrors() {
        assertThrows(IOException.class,
                () -> new DataSaver("./nonexistent/output.json").saveSheet(sheet));
    }

    @Test
    void testSaveSheet() {
        try {
            new DataSaver(SAVE_PATH).saveSheet(sheet);
            sheet = new DataLoader(SAVE_PATH).loadSheet();
            assertEquals("sheetName", sheet.getName());
            assertEquals("A", sheet.getSchema().get(0).getName());
            assertEquals(DataType.STRING, sheet.getSchema().get(0).getType());
            assertEquals("B", sheet.getSchema().get(1).getName());
            assertEquals(DataType.NUMBER, sheet.getSchema().get(1).getType());
            assertEquals("str1", sheet.getRows().get(0).getCells().get(0).getData());
            assertEquals(987.6543, sheet.getRows().get(0).getCells().get(1).getData());
            assertEquals("str2", sheet.getRows().get(1).getCells().get(0).getData());
            assertEquals(-137.0, sheet.getRows().get(1).getCells().get(1).getData());
        } catch (Exception ex) {
            fail("Shouldn't throw exceptions");
        }
    }
}
