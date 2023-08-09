import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class YearlyReport extends Report {
    private Integer year;
    private ArrayList<YearlyRow> data = new ArrayList<YearlyRow>();
    private Map<Integer,Integer> profits=new HashMap<Integer,Integer>();
    private Map<Integer,Integer> expenses=new HashMap<Integer,Integer>();

    public void load(String filename) {
        year = Integer.parseInt(filename.split("\\.")[1]);
        super.load(filename);
        data.clear();
        profits.clear();
        expenses.clear();
        for (String row: rawData) {
            data.add(new YearlyRow(row));
        }
        for (YearlyRow row: data) {
            if (row.isExpense()) {
                expenses.put(row.getMonth(), row.getAmount());
            }
            else {
                profits.put(row.getMonth(), row.getAmount());
            }
        }
    }


    public void info() {
        if (!isLoaded()) {
            System.out.println("Файл не загружен, невозможно вывести информацию");
            return;
        }
        System.out.println("Год: " + year);

        Integer profitSum = 0;
        Integer expenseSum = 0;  
        for (YearlyRow row: data) {
            if (row.isExpense()) {
                expenseSum = expenseSum + row.getAmount();
            }
            else {
                profitSum = profitSum + row.getAmount();
            }
        }

        List<Integer> months=new ArrayList<Integer>(profits.keySet());
        Collections.sort(months);
        System.out.println("Прибыль по месяцам: ");
        for (Integer month: months) {
            System.out.println(month+": " + (profits.getOrDefault(month, 0) - expenses.getOrDefault(month, 0)));
        }

        if (profits.size() > 0) {
            System.out.println("Средний доход: " + (profitSum / profits.size()));
        }
        else {
            System.out.println("В отчете нет доходов");
        }

        if (expenses.size() > 0) {
            System.out.println("Средний расход: " + (expenseSum / expenses.size()));
        }
        else {
            System.out.println("В отчете нет расходов");
        }
    }

    public void check(ArrayList<MonthlyReport> reports) {  // проверка сходимости расходов в отчётах
        boolean isProblem = false;
        for (MonthlyReport report: reports) {
            if (!report.getExpenseSum().equals(expenses.getOrDefault(report.getMonth(), 0))) {
                isProblem = true;
                System.out.println("Не сходятся расходы в месяце "+report.getMonth() + ": В месячном "+report.getExpenseSum() + ", в годовом "+ expenses.getOrDefault(report.getMonth(), 0));
            }
            if (!report.getProfitSum().equals(profits.getOrDefault(report.getMonth(), 0))) {
                isProblem = true;
                System.out.println("Не сходятся доходы в месяце "+report.getMonth() + ": В месячном "+report.getProfitSum() + ", в годовом "+ profits.getOrDefault(report.getMonth(), 0));
            }
        }
        if (!isProblem) {
            System.out.println("Проблем не обнаружено");
        }
    }
}