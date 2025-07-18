
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtils {

    public static void appendTransactionToFile(String path, Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            String line = String.join("|", transaction.getDate(), transaction.getTime(),
                                      transaction.getDescription(), transaction.getVendor(),
                                      String.valueOf(transaction.getAmount()));
            writer.write(line + "\n");
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public static List<Transaction> readTransactionsFromFile(String path) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    transactions.add(new Transaction(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
        Collections.reverse(transactions);
        return transactions;
    }
}
