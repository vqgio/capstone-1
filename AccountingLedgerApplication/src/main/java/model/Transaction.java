package model;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Transaction {

    public static final Scanner scanner = new Scanner(System.in);
    public static final DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter hourMinuteFormatter = DateTimeFormatter.ofPattern("HH:mm");
    public static final String filePath = "transactions.txt";


    public static void addDeposit() {
        System.out.println("Add Deposit");
        LocalDateTime now = LocalDateTime.now();
        String date  = now.format(yearMonthFormatter);
        String time = now.format(hourMinuteFormatter);

        System.out.print("Enter Description Of Deposit: ");
        String description = scanner.nextLine();
        System.out.print("Enter Vendor Name: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter Amount: ");

        double amount = Double.parseDouble(scanner.nextLine());

        if (amount < 0) {
            System.out.println("Deposit amount must be over $0");
            return;
        }

        String transaction = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
        saveTransaction(transaction);
        System.out.println("Deposit Added Successfully!");
    }

    public static void makePayment() {
        System.out.println("- Make Payment -");
        System.out.println("Please remember this is a debit account only action");
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(yearMonthFormatter);
        String time = now.format(hourMinuteFormatter);

        System.out.print("Enter Description Of Payment: ");
        String description = scanner.nextLine();
        System.out.print("Enter Vendor Name: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount < 0) {
            System.out.println("Please enter an amount over $0");
            return;
        }

        String transaction = date + "|" + time + "|" + description + "|" + vendor + "|" + (-amount);
        saveTransaction(transaction);
        System.out.println("Payment Added Successfully!");
    }

    public static void saveTransaction(String transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(transaction + "\n");
        } catch (IOException e) {
            System.out.println("ERROR, Transaction could not be saved! " + e.getMessage());
        }
    }

    public static List<String> loadTransactions() {
        List<String> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            System.out.println("No transactions found. Make a transaction first.");
        }
        Collections.reverse(transactions); // Show newest first
        return transactions;
    }

    public static void displayAllTransactions() {
        System.out.println("- All Transactions -");
        System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("|----------------------------------------------------------------------|");

        List<String> transactions = loadTransactions();
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            if (parts.length == 5) {
                System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", parts[0], parts[1], parts[2], parts[3], parts[4]);
            }
        }
        System.out.println("Please press Enter to continue...");
        scanner.nextLine();
    }

    public static void displayDeposits() {
        System.out.println("- Deposits -");
        System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("|----------------------------------------------------------------------|");

        List<String> transactions = loadTransactions();
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            if (parts.length == 5) {
                double amount = Double.parseDouble(parts[4]);
                if (amount > 0) {
                    System.out.printf("%-12s %-10s %-25s %-15s %-10.2f\n", parts[0], parts[1], parts[2], parts[3], amount);
                }
            }
        }
        System.out.println("Please press Enter to continue...");
        scanner.nextLine();
    }

    public static void displayPayments() {
        System.out.println("- Payments -");
        System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("|----------------------------------------------------------------------|");

        List<String> transactions = loadTransactions();
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            if (parts.length == 5) {
                double amount = Double.parseDouble(parts[4]);
                if (amount < 0) {
                    System.out.printf("%-12s %-10s %-25s %-15s %-10.2f\n", parts[0], parts[1], parts[2], parts[3], amount);
                }
            }
        }
        System.out.println("Please press Enter to continue...");
        scanner.nextLine();
    }
}