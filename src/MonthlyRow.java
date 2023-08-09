
public class MonthlyRow {
    private String name;
    public String getName() {

        return name;
    }

    private boolean expense;
    public boolean isExpense() {
        return expense;
    }
    private int quantity;
    public int getQuantity() {

        return quantity;
    }
    private int sumOfOne;
    public int getSumOfOne() {

        return sumOfOne;
    }

    public MonthlyRow(String row) {
        String[] fields = row.split(",");
        name = fields[0];
        expense = Boolean.parseBoolean(fields[1]);
        quantity = Integer.parseInt(fields[2]);
        sumOfOne = Integer.parseInt(fields[3]);
    }
    
}
