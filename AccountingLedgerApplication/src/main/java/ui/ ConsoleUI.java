package com.yourname.ledger.ui;

import com.yourname.ledger.model.Transaction;
import com.yourname.ledger.service.TransactionService;
import com.yourname.ledger.service.ReportService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionService transactionService;
    private final ReportService reportService;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ConsoleUI() {
        String filePath = "src/main/resources/transactions.csv"; // Update path if needed
        this.transactionService = new TransactionService(filePath);
        this.reportService = new ReportService();
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "D":
                    handleAddDeposit();
                    break;
                case "P":
                    handleMakePayment();
                    break;
                case "L":
                    handleLedgerMenu();
                    break;
                case "X":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n--- Financial Tracker ---");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) View Ledger");
        System.out.println("X) Exit");
        System.out.print("Choose an option: ");
    }

    private void handleAddDeposit() {
        System.out.println("\n--- Add Deposit ---");
        Transaction transaction = createTransaction(false);
        if (transaction != null) {
            transactionService.saveTransaction(transaction);
            System.out.println("Deposit added successfully.");
        }
    }

    private void handleMakePayment() {
        System.out.println("\n--- Make Payment ---");
        Transaction transaction = createTransaction(true);
        if (transaction != null) {
            transactionService.saveTransaction(transaction);
            System.out.println("Payment added successfully.");
        }
    }

    private Transaction createTransaction(boolean isPayment) {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DATE_FORMATTER);
        String time = now.format(TIME_FORMATTER);

        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
            return null;
        }

        if (amount < 0) {
            System.out.println("Amount must be positive.");
            return null;
        }

        if (isPayment) amount = -amount;

        return new Transaction(date, time, description, vendor, amount);
    }

    private void handleLedgerMenu() {
        boolean inLedger = true;
        while (inLedger) {
            System.out.println("\n--- Ledger ---");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "A":
                    displayTransactions(transactionService.loadTransactions());
                    break;
                case "D":
                    displayTransactions(transactionService.loadTransactions()
                            .stream().filter(t -> t.getAmount() > 0).toList());
                    break;
                case "P":
                    displayTransactions(transactionService.loadTransactions()
                            .stream().filter(t -> t.getAmount() < 0).toList());
                    break;
                case "R":
                    handleReportMenu();
                    break;
                case "H":
                    inLedger = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void handleReportMenu() {
        boolean inReports = true;
        while (inReports) {
            System.out.println("\n--- Reports ---");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine().trim().toUpperCase();
            List<Transaction> transactions = transactionService.loadTransactions();
            LocalDate today = LocalDate.now();

            switch (input) {
                case "1":
                    displayTransactions(reportService.filterByMonth(transactions, YearMonth.from(today)));
                    break;
                case "2":
                    displayTransactions(reportService.filterByMonth(transactions, YearMonth.from(today).minusMonths(1)));
                    break;
                case "3":
                    displayTransactions(transactions.stream()
                            .filter(t -> LocalDate.parse(t.getDate(), DATE_FORMATTER).getYear() == today.getYear())
                            .toList());
                    break;
                case "4":
                    displayTransactions(transactions.stream()
                            .filter(t -> LocalDate.parse(t.getDate(), DATE_FORMATTER).getYear() == today.getYear() - 1)
                            .toList());
                    break;
                case "5":
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    displayTransactions(reportService.filterByVendor(transactions, vendor));
                    break;
                case "H":
                    inReports = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.printf("\n%-12s %-10s %-25s %-15s %10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("----------------------------------------------------------------------------");
        for (Transaction t : transactions) {
            System.out.printf("%-12s %-10s %-25s %-15s %10.2f\n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
}
