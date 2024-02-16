package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTest {
    private StringCell strCell;
    private NumberCell numCell;
    
    @BeforeEach
    void initializeTests() {
        strCell = (StringCell)Cell.of(DataType.STRING, "hello");
        numCell = (NumberCell)Cell.of(DataType.NUMBER, "123.45");
    }

    @Test
    void testCellOf() {
        assertNull(Cell.of(null, "123"));
        assertNotNull(strCell);
        assertNotNull(numCell);
    }

    @Test
    void testToString() {
        assertEquals("hello", strCell.toString());
        assertEquals("123.450000", numCell.toString());
    }

    @Test
    void testEquals() {
        assertFalse(numCell.equals(strCell));
        assertFalse(strCell.equals(numCell));
        assertFalse(numCell.equals(null));
        assertFalse(strCell.equals(null));
        assertTrue(numCell.equals(numCell));
        assertTrue(strCell.equals(strCell));
    }

    @Test
    void testSetData() {
        numCell.setData(1.2);
        assertEquals(1.2, numCell.getData());
        strCell.setData("test");
        assertEquals("test", strCell.getData());
    }
}
