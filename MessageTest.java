package com.mycompany.prog5121_poe_part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testCheckMessageLength() {
        Message validMsg = new Message("ID1", "0831234567", "Hello world", 0);
        assertEquals("Message ready to send.", validMsg.checkMessageLength());
    }

    @Test
    public void testCheckRecipientCell() {
        Message validCell = new Message("ID1", "0831234567", "Hi", 0);
        assertEquals("Cell phone number successfully captured.", validCell.checkRecipientCell());

        Message invalidCell = new Message("ID1", "12345", "Hi", 0);
        assertTrue(invalidCell.checkRecipientCell().contains("incorrectly formatted"));
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("QU01", "0831234567", "Helloween night", 0);
        assertEquals("QU:0:HELLOWEENNIGHT", msg.createMessageHash());
    }
}