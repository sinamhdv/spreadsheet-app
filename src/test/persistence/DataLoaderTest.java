package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.DataType;
import model.Sheet;

// tests for data loader class
public class DataLoaderTest {
    @Test
    void testIOErrors() {
        assertThrows(IOException.class, () -> new DataLoader("./data/nonexistent.json").loadSheet());
    }

    @Test
    void testLoadSheet() {
        try {
            Sheet sheet = new DataLoader("./data/testLoadSheet.json").loadSheet();
            assertEquals("testname", sheet.getName());
            assertEquals("colA", sheet.getSchema().get(0).getName());
            assertEquals(DataType.NUMBER, sheet.getSchema().get(0).getType());
            assertEquals("colB", sheet.getSchema().get(1).getName());
            assertEquals(DataType.STRING, sheet.getSchema().get(1).getType());
            assertEquals(1.23456789, sheet.getRows().get(0).getCells().get(0).getData());
            assertEquals("string1", sheet.getRows().get(0).getCells().get(1).getData());
            assertEquals(-123456789.0, sheet.getRows().get(1).getCells().get(0).getData());
            assertEquals("a very long string 2", sheet.getRows().get(1).getCells().get(1).getData());
        } catch (Exception ex) {
            fail("Shouldn't throw exceptions");
        }
    }
}
