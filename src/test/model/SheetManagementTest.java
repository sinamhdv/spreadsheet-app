package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SheetManagementTest {
    private Sheet[] sheets;

    @BeforeEach
    void initializeTests() {
        Sheet.getSheets().clear();
        sheets = new Sheet[] {
            new Sheet("ABCD"),
            new Sheet("test"),
            new Sheet("asdasd")
        };
    }

    @Test
    void testCreateSheet() {
        assertNull(Sheet.create("ABCD", new String[] {"A:STRING", "B:NUMBER"}));
        assertEquals(ErrorMessage.NAME_EXISTS,
                Sheet.create("ABCD", new String[] {"A:STRING", "B:NUMBER"}));
        assertEquals(1, Sheet.getSheets().size());
        Sheet sheet = Sheet.getSheets().get(0);
        assertEquals("ABCD", sheet.getName());
        assertEquals("A", sheet.getSchema().get(0).getName());
        assertEquals(DataType.NUMBER, sheet.getSchema().get(1).getType());
    }
    
    @Test
    void testOpenSheet() {
        for (Sheet sheet : sheets) {
            Sheet.getSheets().add(sheet);
        }
        assertEquals(ErrorMessage.NAME_NOT_FOUND, Sheet.openSheet("nonexistent"));
        assertNull(Sheet.openSheet("asdasd"));
        assertEquals(sheets[2], Sheet.getCurrentSheet());
    }
}
