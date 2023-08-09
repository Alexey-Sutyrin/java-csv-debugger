import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        ArrayList<MonthlyReport> reports = new ArrayList<MonthlyReport>();
        YearlyReport yearlyReport = new YearlyReport();

        System.out.println("Здравствуйте,уважаемая Бухгалтерия!"); // вступительный текст
        System.out.println("Что пожелаете сделать? ");

        while (true) {
            // Вывод доступных пунктов меню в консоль
            System.out.println("1 - Считать месячные отчеты");
            System.out.println("2 - Считать годовой отчет");
            System.out.println("3 - Сверить отчеты на отсутсвие ошибок");
            System.out.println("4 - Вывести информацию о месячных отчетах");
            System.out.println("5 - Вывести информацию о годовом отчете");
            System.out.println("0 - Выход");
            int userInput; 
            try {
                userInput = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Некорректная команда");
                scanner.nextLine();
                continue;
            }
            // Вызов соответствующего метода в зависимости от userInput
            switch (userInput) {
                case 1:
                    // Считываем месячные отчеты из представленных на старте
                    reports.clear();
                    for (Integer i = 1; i < 4; i++) {
                        MonthlyReport report = new MonthlyReport();
                        report.load("m.20210"+i+".csv");
                        reports.add(report);
                    }
                    break;
                case 2:
                    // Считывание годового отчета, представленного в условии на старте
                    yearlyReport.load("y.2021.csv");
                    break;
                case 3:
                    // Проверка отчетов - загружены или нет
                    if (reports.size() == 0) {
                        System.out.println("Месячные отчеты не загружены");
                    }
                    else if (yearlyReport.isLoaded() == false) {
                        System.out.println("Годовой отчет не загружен");
                    }
                    else {
                        yearlyReport.check(reports);
                    }
                    break;
                case 4:
                    // Вывод информации о месячных отчетах
                    if (reports.size() == 0) {
                        System.out.println("Отчеты не загружены, загрузите отчеты");
                    }
                    for (MonthlyReport report: reports) {
                        report.info();
                    }
                    break;
                case 5:
                    // Информация о годовом отчете
                    yearlyReport.info();
                    break;
                case 0:
                    // Выход
                    scanner.close();
                    System.exit(0);
                    break;
            }
          } 
    }
}