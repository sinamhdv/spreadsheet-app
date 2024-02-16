package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ErrorMessageTest {
    @Test
    void testGetText() {
        assertEquals("Index out of range", ErrorMessage.INVALID_INDEX.getText());
    }
}
