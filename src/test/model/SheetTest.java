package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SheetTest {
    private Sheet sheet;

    @BeforeEach
    void initializeTests() {
        Sheet.getSheets().clear();
        Sheet.create("Sheet-B", new String[] {"A:STRING", "B:NUMBER", "x:NUMBER"});
        Sheet.openSheet("Sheet-B");
        sheet = Sheet.getCurrentSheet();
        sheet.insertRow(new String[] {"123", "2.4", "3.7"});
        sheet.insertRow(new String[] {"aa", "bb", "1.5798"});
        sheet.insertRow(new String[] {"aa", "-11283.34", "-3l"});
    }

    @Test
    void testInsertRow() {
        assertNull(Sheet.create("Sheet-A", new String[] {"A:STRING", "B:NUMBER", "x:NUMBER"}));
        assertNull(Sheet.openSheet("Sheet-A"));
        Sheet sheet = Sheet.getCurrentSheet();
        assertEquals(ErrorMessage.BAD_ROW_LENGTH, sheet.insertRow(new String[] {"asd"}));
        assertEquals(ErrorMessage.BAD_ROW_LENGTH, sheet.insertRow(new String[] {"a", "a", "a", "a"}));
        assertNull(sheet.insertRow(new String[] {"123", "2.4", "3.7"}));
        assertNull(sheet.insertRow(new String[] {"aa", "bb", "1.5798"}));
        assertEquals("123", sheet.getRows().get(0).getCells().get(0).getData());
        assertEquals(2.4, sheet.getRows().get(0).getCells().get(1));
        assertEquals(3.7, sheet.getRows().get(0).getCells().get(2));
        assertEquals(1.5798, sheet.getRows().get(1).getCells().get(2));
        assertNull(sheet.getRows().get(1).getCells().get(1));
    }

    @Test
    void testSortByNumber() {
        assertEquals(ErrorMessage.COLUMN_NOT_FOUND, sheet.sortBy("nonexistent"));
        assertNull(sheet.sortBy("B"));
        assertEquals(-11283.34, sheet.getRows().get(0).getCells().get(1).getData());
        assertEquals(2.4, sheet.getRows().get(1).getCells().get(1).getData());
        assertNull(sheet.getRows().get(2).getCells().get(1).getData());
    }

    @Test
    void testSortByString() {
        assertNull(sheet.sortBy("A"));
        assertEquals("123", sheet.getRows().get(0).getCells().get(0).getData());
        assertEquals("aa", sheet.getRows().get(1).getCells().get(0).getData());
        assertEquals("aa", sheet.getRows().get(2).getCells().get(0).getData());
    }

    @Test
    void testSearchNumber() {
        assertNull(sheet.search("nonexistent", "asd"));
        assertTrue(sheet.search("x", "-3l").isEmpty());
        List<Row> result = sheet.search("x", "1.5798");
        assertEquals(1, result.size());
        assertEquals(1.5798, result.get(0).getCells().get(2));
    }

    @Test
    void testSearchString() {
        List<Row> result = sheet.search("A", "aa");
        assertEquals(2, result.size());
        assertNull(result.get(0).getCells().get(1));
        assertEquals(-11283.34, result.get(1).getCells().get(1));
    }

    @Test
    void testSumRow() {
        assertNull(sheet.sumRow(0));
        assertNull(sheet.sumRow(4));
        assertEquals(6.1, sheet.sumRow(0));
        assertEquals(1.5798, sheet.sumRow(1));
        assertEquals(-11283.34, sheet.sumRow(2));
    }
}
