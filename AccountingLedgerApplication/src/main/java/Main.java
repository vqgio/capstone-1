import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final String filePath = "C:/Users/rsant/pluralsight/capstone-1/AccountingLedgerApplication/src/main/resources/transactions.csv";
    private static final DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter hourMinuteFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean(running) = true;
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
                case "X":
                    running = false;
                    System.out.println("Logging out...Have a nice day!");
                    break;
                default:
                    System.out.println("Sorry that is not an option! Please try again. ")
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


}
