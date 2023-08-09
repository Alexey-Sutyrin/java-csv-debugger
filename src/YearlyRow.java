
public class YearlyRow {
    private int month;
    public int getMonth() {

        return month;
    }

    private int amount;
    public int getAmount() {

        return amount;
    }

    private boolean expense;
    public boolean isExpense() {

        return expense;
    }

    public YearlyRow(String row) {
        String[] fields = row.split(",");
        month = Integer.parseInt(fields[0]);
        amount = Integer.parseInt(fields[1]);
        expense = Boolean.parseBoolean(fields[2]);


    }
    
}
