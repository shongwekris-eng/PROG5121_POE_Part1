package com.mycompany.prog5121_poe_part1;
public class Message {
    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private int messageNumber;

    public Message(String messageID, String recipientCell, String messageText, int messageNumber) {
        this.messageID = messageID;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageHash = createMessageHash();
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipientCell != null && recipientCell.length() <= 10 && 
            (recipientCell.startsWith("+") || recipientCell.startsWith("0") || recipientCell.matches("^[0-9]+$"))) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String createMessageHash() {
        try {
            String idPrefix = (messageID != null && messageID.length() >= 2) ? messageID.substring(0, 2) : "00";
            String firstWord = "";
            String lastWord = "";
            
            if (messageText != null && !messageText.trim().isEmpty()) {
                String cleaned = messageText.replaceAll("[^a-zA-Z ]", "").trim();
                String[] words = cleaned.split("\\s+");
                if (words.length > 0) {
                    firstWord = words[0];
                    lastWord = words[words.length - 1];
                }
            }
            return (idPrefix + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
        } catch (Exception e) {
            return "00:0:ERROR";
        }
    }

    public String checkMessageLength() {
        if (messageText != null && messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    public String sentMessage(int choice) {
        switch (choice) {
            case 1: return "Message successfully sent.";
            case 2: return "Press 0 to delete the message.";
            case 3: return "Message successfully stored.";
            default: return "Invalid choice.";
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + messageText;
    }
}