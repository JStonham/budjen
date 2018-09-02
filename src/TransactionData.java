public class TransactionData {

    private final Object[] transactionData = new Object[] {
            new Object[] { TransactionType.CREDIT, "Dress Refund", 2999 },
            new Object[] { TransactionType.CREDIT, "Pocket Money", 250 },
            new Object[] { TransactionType.CREDIT, "Lottery winnings", 5600000 },
            new Object[] { TransactionType.CREDIT, "TV Appearance", 100000 },
            new Object[] { TransactionType.CREDIT, "Book Deal", 500000 },
            new Object[] { TransactionType.CREDIT, "Poker winnings", 80000 },
            new Object[] { TransactionType.CREDIT, "Paper Round", 2300 },
            new Object[] { TransactionType.CREDIT, "Let flat", 420000 },
            new Object[] { TransactionType.DEBIT, "Lottery Ticket", -300 },
            new Object[] { TransactionType.DEBIT, "Train Ticket", -12330 },
            new Object[] { TransactionType.DEBIT, "Dog", -700000 },
            new Object[] { TransactionType.DEBIT, "Dog toys", -1549 },
            new Object[] { TransactionType.DEBIT, "Blue Audi", -4000000 },
            new Object[] { TransactionType.DEBIT, "Small Yacht", -5900099 },
            new Object[] { TransactionType.DEBIT, "Plastic Surgery", -2500000 },
            new Object[] { TransactionType.DEBIT, "Skiing holiday", -510000 }

    };

    private Transaction[] seedTransactions() {
        Transaction[] transactions = new Transaction[transactionData.length];
        for (int i = 0; i < transactionData.length; i++) {
            Object[] seedData = (Object[]) transactionData[i];
            Transaction transaction = new Transaction();
            transaction.setType((TransactionType) seedData[0]);
            transaction.setDescription((String) seedData[1]);
            transaction.setMoney((Long) seedData[2]);
            transactions[i] = transaction;
        }
        return transactions;
    }

    private Transaction returnedDressRefund() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.CREDIT);
        transaction.setDescription("Dress Refund");
        transaction.setMoney(3000);
        return transaction;
    }

    private Transaction pocketMoney() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.CREDIT);
        transaction.setDescription("Pocket Money");
        transaction.setMoney(250);
        return transaction;
    }

    private Transaction winTheLottery() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.CREDIT);
        transaction.setDescription("Lottery winnings");
        transaction.setMoney(5600000);
        return transaction;
    }

    private Transaction buyATrainTicket() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEBIT);
        transaction.setDescription("Train Ticket");
        transaction.setMoney(-12300);
        return transaction;
    }

    private Transaction buyADog() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEBIT);
        transaction.setDescription("Dog");
        transaction.setMoney(-70000);
        return transaction;
    }

    private Transaction buyASmallYacht() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEBIT);
        transaction.setDescription("Small Yacht");
        transaction.setMoney(-4500000);
        return transaction;
    }
}
