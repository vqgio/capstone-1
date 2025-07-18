import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static model.Transaction.*;

public class Main {
    //final bc they are not going to change
    private static final String filePath = "/pluralsight/capstone-1/AccountingLedgerApplication/src/main/resources/transactions.csv";
    private static final DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter hourMinuteFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    //can be resigned for various tasks through the code
    private static Scanner scanner = new Scanner(System.in);

    //home screen setup
    public static void main(String[] args) {
        boolean appOpen = true;
        while(appOpen) {
            displayHomeScreen();
            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    displayLedger();
                    break;
                case "X":
                    appOpen = false;
                    System.out.println("Logging out...Have a nice day!");
                    break;
                default:
                    System.out.println("Sorry that is not an option! Please try again. ");
            }
        }
    }
    private static void displayHomeScreen() {
        System.out.println("- Financial Transaction Tracker -");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make A Payment(Debit Only)");
        System.out.println("L) Display Ledger");
        System.out.println("X) Exit The Application");
        System.out.print("Choose from the following options: ");
    }


    private static void displayLedger() {
        boolean inLedger = true;
        while (inLedger) {
            System.out.println("- Ledger -");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Please choose an option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    displayAllTransactions();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                case "R":
                    displayReportsScreen();
                case "H":
                    inLedger = false;
                    break;
                default:
                    System.out.println("Sorry that is not an option, please choose from the following. ");
            }
        }
    }

    private static void displayReportsScreen() {
        boolean reports = true;
        while (reports) {
            System.out.println("- Reports -");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search By Vendor");
            System.out.println("H) Home");
            System.out.print("Please choose from the following options: ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "1":
                    displayMonthToDate();
                    break;
                case "2":
                    displayPreviousMonth();
                    break;
                case "3":
                    displayYearToDate();
                    break;
                case "4":
                    displayPreviousYear();
                    break;
                case "5":
                    searchVendor();
                    break;
                case "H":
                    reports = false;
                    break;
                default:
                    System.out.println("Sorry, please pick from the following actions.");
            }
        }
    }
    private static void displayMonthToDate() {
        System.out.println("- Month To Date Report -");
        //formatting to make it look nice, newline characters and format specifiers
        System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("|----------------------------------------------------------------------|");

        List<String> transactions = loadTransactions();
        LocalDate now = LocalDate.now();
        //uses time to create currentMonth to use in if statement
        YearMonth currentMonth = YearMonth.from(now);
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            if (parts.length == 5) {
                LocalDate transactionDate = LocalDate.parse(parts[0], yearMonthFormatter);
                //as long as transaction are from currentMonth it will print only the following
                if (YearMonth.from(transactionDate).equals(currentMonth)) {
                    System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
        }
        System.out.println("Please press Enter to continue...");
        scanner.nextLine();
    }
    private static void displayPreviousMonth() {
        System.out.println("- Previous Month Report -");
        //formatting to make it look nice, newline characters and format specifiers
        System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("|----------------------------------------------------------------------|");

        List<String> transactions = loadTransactions();
        LocalDate now = LocalDate.now();
        //same code as before but changing to previousMonth and using .minusMonth to retrieve only transactions from the month prior
        YearMonth previousMonth = YearMonth.from(now).minusMonths(1);
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            if (parts.length == 5) {
                LocalDate transactionDate = LocalDate.parse(parts[0], yearMonthFormatter);
                if (YearMonth.from(transactionDate).equals(previousMonth)) {
                    System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
        }
        System.out.println("Please press Enter to continue...");
        scanner.nextLine();
    }
    private static void displayYearToDate() {
        System.out.println("- Year To Date Report -");
        //formatting to make it look nice, newline characters and format specifiers
        System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("|----------------------------------------------------------------------|");

        List<String> transactions = loadTransactions();
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            if (parts.length == 5) {
                LocalDate transactionDate = LocalDate.parse(parts[0], yearMonthFormatter);
                if (transactionDate.getYear() == currentYear) {
                    System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
        }
        System.out.println("Please press Enter to continue...");
        scanner.nextLine();
    }
    private static void displayPreviousYear() {
        System.out.println("- Previous Year Report -");
        //formatting to make it look nice, newline characters and format specifiers
        System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("|----------------------------------------------------------------------|");

        List<String> transactions = loadTransactions();
        LocalDate now = LocalDate.now();
        int previousYear = now.getYear() - 1;

        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            if (parts.length == 5) {
                LocalDate transactionDate = LocalDate.parse(parts[0], yearMonthFormatter);
                if (transactionDate.getYear() == previousYear) {
                    System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
        }
        System.out.println("Please press Enter to continue...");
        scanner.nextLine();
    }
    private static void searchVendor() {
        //Explanation to code is in readme file
        System.out.print("\nPlease enter vendor name to search: ");
        String vendorSearch = scanner.nextLine().toLowerCase();

        System.out.println("- Results For Vendor: "+ vendorSearch + "-");
        System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("|----------------------------------------------------------------------|");

        List<String> transactions = loadTransactions();
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            if(parts.length == 5) {
                if (parts[3].toLowerCase().contains(vendorSearch)) {
                    System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
        }
        System.out.println("Please press Enter to continue...");
        scanner.nextLine();
    }
}


