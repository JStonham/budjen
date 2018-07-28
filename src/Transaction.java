import java.util.Date;

/**
 * Each transaction will include a transaction type,
 * a transation reason and a number of money in pounds sterling.
 */
public class Transaction {
    private String type;
    private String description;
    private long money;
    private Date date;

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public long getMoney() {
        return money;
    }

    public String getSummary() {
        if (money == 0) {
            return "No data.";
        }
        if (money <0) {
            return "You have spent £" + (money * -1);
        }
            return "You have £" + money;
    }
}
