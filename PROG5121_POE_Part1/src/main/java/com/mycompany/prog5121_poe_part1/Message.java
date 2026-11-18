package com.mycompany.prog5121_poe_part1;

public class Message {
    private String messageID;
    private int messageNumber;
    private String messageContent;
    private String recipient;
    private String messageHash;
    private String flag; // Sent, Stored, Disregarded

    public Message(String messageID, int messageNumber, String messageContent, String recipient, String flag) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.messageContent = messageContent;
        this.recipient = recipient;
        this.flag = flag;
        this.messageHash = createMessageHash();
    }

    public String createMessageHash() {
        String initials = "";
        if (recipient != null && recipient.length() >= 2) {
            initials = recipient.substring(0, 2).toUpperCase();
        }
        return initials + ":" + messageNumber + ":" + messageContent;
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public boolean checkMessageContent() {
        return messageContent != null && messageContent.length() <= 250;
    }

    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getMessageContent() { return messageContent; }
    public String getRecipient() { return recipient; }
    public String getMessageHash() { return messageHash; }
    public String getFlag() { return flag; }
}