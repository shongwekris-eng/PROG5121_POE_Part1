package com.mycompany.prog5121_poe_part1;

import java.util.ArrayList;
import java.util.Scanner;

public class PROG5121_POE_Part1 {

    // The 5 mandatory arrays/ArrayLists for Part 3
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> disregardedMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    private static ArrayList<String> messageHashes = new ArrayList<>();
    private static ArrayList<String> messageIDs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Populate arrays using official Part 3 Test Data
        populateTestData();

        int choice = 0;
        do {
            System.out.println("\n--- QUICKCHAT MAIN MENU ---");
            System.out.println("1. Send a new message");
            System.out.println("2. Show sent messages report");
            System.out.println("3. (Reserved)");
            System.out.println("4. Stored Messages Operations (Part 3 Features)");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Message entry feature active.");
                        break;
                    case 2:
                        displaySentReport();
                        break;
                    case 4:
                        runStoredMessagesMenu(scanner);
                        break;
                    case 5:
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose between 1 and 5.");
                }
            } else {
                scanner.next(); // clear invalid input
                System.out.println("Please enter a valid number.");
            }
        } while (choice != 5);
        
        scanner.close();
    }

    // Populate arrays with mandatory Test Data from Part 3 Brief
    private static void populateTestData() {
        // Message 1 (Sent)
        Message m1 = new Message("M1", 1, "Did you get the cake?", "+27834557896", "Sent");
        sentMessages.add(m1);
        messageHashes.add(m1.getMessageHash());
        messageIDs.add(m1.getMessageID());

        // Message 2 (Stored)
        Message m2 = new Message("M2", 2, "Where are you? You are late! I have asked you to be on time.", "+27838884567", "Stored");
        storedMessages.add(m2);
        messageHashes.add(m2.getMessageHash());
        messageIDs.add(m2.getMessageID());

        // Message 3 (Disregarded)
        Message m3 = new Message("M3", 3, "Yohoooo, I am at your gate.", "+27834484567", "Disregard");
        disregardedMessages.add(m3);
        messageHashes.add(m3.getMessageHash());
        messageIDs.add(m3.getMessageID());

        // Message 4 (Sent / Developer Entry)
        Message m4 = new Message("M4", 4, "It is dinner time !", "0838884567", "Sent");
        sentMessages.add(m4);
        messageHashes.add(m4.getMessageHash());
        messageIDs.add(m4.getMessageID());

        // Message 5 (Stored)
        Message m5 = new Message("M5", 5, "Ok, I am leaving without you.", "+27838884567", "Stored");
        storedMessages.add(m5);
        messageHashes.add(m5.getMessageHash());
        messageIDs.add(m5.getMessageID());
    }

    // Sub-menu for Option 4: Stored Messages Features
    private static void runStoredMessagesMenu(Scanner scanner) {
        int subChoice = 0;
        do {
            System.out.println("\n--- STORED MESSAGES MENU ---");
            System.out.println("a. Display sender and recipient of all stored messages");
            System.out.println("b. Display the longest stored message");
            System.out.println("c. Search for a message ID");
            System.out.println("d. Search messages for a particular recipient");
            System.out.println("e. Delete a message using the message hash");
            System.out.println("f. Display full stored message report");
            System.out.println("g. Return to Main Menu");
            System.out.print("Choose an option (a-g): ");
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "a":
                    displayStoredSendersRecipients();
                    break;
                case "b":
                    displayLongestStoredMessage();
                    break;
                case "c":
                    System.out.print("Enter Message ID to search: ");
                    String searchId = scanner.nextLine();
                    searchMessageByID(searchId);
                    break;
                case "d":
                    System.out.print("Enter Recipient number to search: ");
                    String searchRecip = scanner.nextLine();
                    searchByRecipient(searchRecip);
                    break;
                case "e":
                    System.out.print("Enter Message Hash to delete: ");
                    String hashToDelete = scanner.nextLine();
                    deleteByHash(hashToDelete);
                    break;
                case "f":
                    displayStoredReport();
                    break;
                case "g":
                    subChoice = 1;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        } while (subChoice == 0);
    }

    private static void displayStoredSendersRecipients() {
        System.out.println("\n--- STORED MESSAGES: SENDER & RECIPIENT ---");
        for (Message m : storedMessages) {
            System.out.println("Recipient: " + m.getRecipient() + " | Content: " + m.getMessageContent());
        }
    }

    private static void displayLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }
        Message longest = storedMessages.get(0);
        for (Message m : storedMessages) {
            if (m.getMessageContent().length() > longest.getMessageContent().length()) {
                longest = m;
            }
        }
        System.out.println("\n--- LONGEST STORED MESSAGE ---");
        System.out.println("Message: " + longest.getMessageContent() + " (Length: " + longest.getMessageContent().length() + " chars)");
    }

    private static void searchMessageByID(String id) {
        boolean found = false;
        for (Message m : storedMessages) {
            if (m.getMessageID().equalsIgnoreCase(id)) {
                System.out.println("\nFound -> Recipient: " + m.getRecipient() + " | Message: " + m.getMessageContent());
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Message ID not found in stored messages.");
    }

    private static void searchByRecipient(String recipient) {
        System.out.println("\n--- MESSAGES FOR RECIPIENT: " + recipient + " ---");
        boolean found = false;
        for (Message m : storedMessages) {
            if (m.getRecipient().equals(recipient)) {
                System.out.println("- " + m.getMessageContent());
                found = true;
            }
        }
        if (!found) System.out.println("No stored messages found for this recipient.");
    }

    private static void deleteByHash(String hash) {
        boolean removed = storedMessages.removeIf(m -> m.getMessageHash().equals(hash));
        if (removed) {
            System.out.println("Message successfully deleted using hash.");
        } else {
            System.out.println("Message hash not found.");
        }
    }

    private static void displayStoredReport() {
        System.out.println("\n================ STORED MESSAGE REPORT ================");
        for (Message m : storedMessages) {
            System.out.println("Hash      : " + m.getMessageHash());
            System.out.println("Recipient : " + m.getRecipient());
            System.out.println("Message   : " + m.getMessageContent());
            System.out.println("-------------------------------------------------------");
        }
    }

    private static void displaySentReport() {
        System.out.println("\n================ SENT MESSAGE REPORT ================");
        for (Message m : sentMessages) {
            System.out.println("Hash      : " + m.getMessageHash());
            System.out.println("Recipient : " + m.getRecipient());
            System.out.println("Message   : " + m.getMessageContent());
            System.out.println("-----------------------------------------------------");
        }
    }
}