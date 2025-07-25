package ui;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static model.Transaction.*;
import static model.Transaction.displayDeposits;
import static model.Transaction.displayPayments;
import static service.ReportService.*;
import static service.ReportService.customSearch;
import static service.TransactionService.addDeposit;
import static service.TransactionService.makePayment;

public class ConsoleUI {
    //can be resigned for various tasks through the code
    private static Scanner scanner = new Scanner(System.in);

    //home screen setup
    public void display(){
        boolean appOpen = true;
        while (appOpen) {
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
            System.out.println("6) Custom Search");
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
                case "6":
                    customSearch();
                    break;
                case "H":
                    reports = false;
                    break;
                default:
                    System.out.println("Sorry, please pick from the following actions.");
            }
        }
    }
}