

import java.util.List;

public class TransactionService {
    private final String filePath;

    public TransactionService(String filePath) {
        this.filePath = filePath;
    }

    public void saveTransaction(Transaction transaction) {
        FileUtils.appendTransactionToFile(filePath, transaction);
    }

    public List<Transaction> loadTransactions() {
        return FileUtils.readTransactionsFromFile(filePath);
    }
}
