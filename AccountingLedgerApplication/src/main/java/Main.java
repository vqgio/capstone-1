import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final String filePath = "C:/Users/rsant/pluralsight/capstone-1/AccountingLedgerApplication/src/main/resources/transactions.csv";
    private static final DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter hourMinuteFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static Scanner scanner = new Scanner(System.in);

    //home screen setup
    public static void main(String[] args) {
        boolean running = true;
        while(running) {
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
                    running = false;
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

    private static void addDeposit() {
        System.out.println("- Add Deposit -");
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

    private static void saveTransaction(String transaction) {
        try (BufferedWriter writer = new BufferedWriter( new FileWriter(filePath, true))) {
            writer.write(transaction + "\n");
            writer.close();
            } catch (IOException e) {
            System.out.println("ERROR, Transaction could not be saved!" + e.getMessage());
        }
    }
    private static void makePayment() {
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
        //allows to enter negative
        String transaction = date + "|" + time + "|" + description + "|" + vendor + "|" + (=amount);
        saveTransaction(transaction);
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
            System.out.print("Please choose an option");
            String choice scanner.nextLine().toUpperCase();

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
}
