/**
 * Each transaction will include a transaction type,
 * a transation reason and a number of money in pounds sterling.
 */
public class Transaction {
    private String type;
    private String description;
    private long money;

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public long getMoney() {
        return money;
    }

    public String getSummary() {
        if (money == 0) {
            return "No data.";
        }
        return "You have £10";
    }
}
