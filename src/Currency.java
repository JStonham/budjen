public class Currency {
    public String formatPounds(long i) {
        if (i == 0) {
            return "0";
        }
        long remainder = i % 100;
        String pence = remainder < 10 ? "0" + remainder : "" + remainder;
        return "£" + (i / 100) + "." + pence;
    }
}
