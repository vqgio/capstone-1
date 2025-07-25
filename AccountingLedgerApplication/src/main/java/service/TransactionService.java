package service;

import java.time.LocalDateTime;

import static model.Transaction.*;

public class TransactionService {

    public static void addDeposit() {
        System.out.println("Add Deposit");
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(yearMonthFormatter);
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
}