

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ReportService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Transaction> filterByMonth(List<Transaction> transactions, YearMonth month) {
        return transactions.stream()
                .filter(t -> YearMonth.from(LocalDate.parse(t.getDate(), formatter)).equals(month))
                .collect(Collectors.toList());
    }

    public List<Transaction> filterByVendor(List<Transaction> transactions, String vendor) {
        return transactions.stream()
                .filter(t -> t.getVendor().toLowerCase().contains(vendor.toLowerCase()))
                .collect(Collectors.toList());
    }
}
