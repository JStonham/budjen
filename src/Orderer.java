import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Orderer {

    public List<Transaction> order(List<Transaction> transactions) {
        transactions.sort(Comparator.comparing(Transaction::getDate));
        return transactions;
    }
}
