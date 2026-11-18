package com.mycompany.prog5121_poe_part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class MessageTest {

    @Test
    public void testMessageHashGeneration() {
        Message m = new Message("M1", 1, "Did you get the cake?", "+27834557896", "Sent");
        String expectedHash = "+2:1:Did you get the cake?";
        assertEquals(expectedHash, m.getMessageHash());
    }

    @Test
    public void testCheckMessageID() {
        Message m = new Message("M12345", 1, "Test content", "+27834557896", "Sent");
        assertTrue(m.checkMessageID());
    }

    @Test
    public void testCheckMessageContent() {
        Message m = new Message("M1", 1, "Short message", "+27834557896", "Sent");
        assertTrue(m.checkMessageContent());
    }

    @Test
    public void testLongestMessageSearch() {
        ArrayList<Message> testStored = new ArrayList<>();
        testStored.add(new Message("M1", 1, "Short one", "+27838884567", "Stored"));
        testStored.add(new Message("M2", 2, "Where are you? You are late! I have asked you to be on time.", "+27838884567", "Stored"));
        
        Message longest = testStored.get(0);
        for (Message msg : testStored) {
            if (msg.getMessageContent().length() > longest.getMessageContent().length()) {
                longest = msg;
            }
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest.getMessageContent());
    }

    @Test
    public void testDeleteByHash() {
        ArrayList<Message> testList = new ArrayList<>();
        Message m2 = new Message("M2", 2, "Delete me", "+27838884567", "Stored");
        testList.add(m2);

        String targetHash = m2.getMessageHash();
        testList.removeIf(msg -> msg.getMessageHash().equals(targetHash));
        
        assertTrue(testList.isEmpty());
    }
}