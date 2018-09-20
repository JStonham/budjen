import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Orderer {

    public Transaction[] order(Transaction[] unorderedTransactions) {
        List<Transaction> transactionList = Arrays.asList(unorderedTransactions);
        transactionList.sort(Comparator.comparing(Transaction::getDate));
        return transactionList.toArray(new Transaction[unorderedTransactions.length]);
    }
}
