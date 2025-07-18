package service;


import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static model.Transaction.*;

public class ReportService {
    public static void displayMonthToDate() {
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

    public static void displayPreviousMonth() {
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

    public static void displayYearToDate() {
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

    public static void displayPreviousYear() {
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

    public static void searchVendor() {
        //Explanation to code is in readme file
        System.out.print("\nPlease enter vendor name to search: ");
        String vendorSearch = scanner.nextLine().toLowerCase();

        System.out.println("- Results For Vendor: " + vendorSearch + "-");
        System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("|----------------------------------------------------------------------|");

        List<String> transactions = loadTransactions();
        for (String transaction : transactions) {
            String[] parts = transaction.split("\\|");
            if (parts.length == 5) {
                if (parts[3].toLowerCase().contains(vendorSearch)) {
                    System.out.printf("%-12s %-10s %-25s %-15s %-10s\n", parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
        }
        System.out.println("Please press Enter to continue...");
        scanner.nextLine();
    }
}