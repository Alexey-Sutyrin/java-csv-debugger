import java.util.ArrayList;
public class MonthlyReport extends Report {
    private Integer month;
    public Integer getMonth() {

        return month;
    }
    private ArrayList<MonthlyRow> data = new ArrayList<MonthlyRow>();

    public void load(String filename) {
        month = Integer.parseInt(filename.split("\\.")[1].substring(4));
        super.load(filename);
        data.clear();
        for (String row: rawData) {
            data.add(new MonthlyRow(row));
        }
    }

    public void info() {
        if (!isLoaded()) {
            System.out.println("Файл не загружен, невозможно вывести информацию");
            return;
        }
        System.out.println("Месяц: " + month);
        MonthlyRow maxProfitRow = null;
        Integer maxProfit = 0;
        MonthlyRow maxExpenseRow = null;
        Integer maxExpense = 0;
        for (MonthlyRow row: data) {
            if (row.isExpense()) {
                Integer expense = row.getQuantity() * row.getSumOfOne();
                if (expense > maxExpense) {
                    maxExpense = expense;
                    maxExpenseRow = row;
                }
            }
            else {
                Integer profit = row.getQuantity() * row.getSumOfOne();
                if (profit > maxProfit) {
                    maxProfit = profit;
                    maxProfitRow = row;
                }
            }
        }

        if (maxProfitRow != null) {
            System.out.println("Самый прибыльный товар: " + maxProfitRow.getName() + ", " + maxProfit);
        }
        else {
            System.out.println("За месяц прибыли не было");
        }
        if (maxExpenseRow != null) {
            System.out.println("Самая большая трата: " + maxExpenseRow.getName() + ", " + maxExpense);
        }
        else {
            System.out.println("За месяц трат не было");
        }

    }

    public Integer getProfitSum() {
        Integer result = 0;
        for (MonthlyRow row: data) {
            if (!row.isExpense()) {
                result = result + row.getQuantity() * row.getSumOfOne();
            }
        }
        return result;
    }

    public Integer getExpenseSum() {
        Integer result = 0;
        for (MonthlyRow row: data) {
            if (row.isExpense()) {
                result = result + row.getQuantity() * row.getSumOfOne();
            }
        }
        return result;
    }
}
