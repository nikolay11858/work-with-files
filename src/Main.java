import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int year = 0;
        Scanner scanner = new Scanner(System.in);
        MonthReportHandler monthReportHandler = new MonthReportHandler();
        YearReportHandler yearReportHandler = new YearReportHandler();
        CheckerReport checker = new CheckerReport();

        while (true) {
            printMenu();
            System.out.println("Какое действие вы хотите сделать?");
            int command = scanner.nextInt();
            if (command == 1) {
                monthReportHandler.checkSpendingPerMonth();
            } else if (command == 2) {
                System.out.println("За какой год вы хотите загруить отчёт?");
                year = scanner.nextInt();
                yearReportHandler.checkSpendingPerYear(year);
            } else if (command == 3) {
                System.out.println("Сверка отчётов");
                checker.checkReports(monthReportHandler, yearReportHandler);
            } else if (command == 4) {
                System.out.println("Информация о месячных отчётах");
                checker.showReportPerMonths(monthReportHandler);
            } else if (command == 5) {
                System.out.println("Информация о годовом отчёте");
                checker.showReportPerYears(yearReportHandler);
            } else if (command == 0) {
                System.out.println("Выход из программы");
                return;
            } else {
                System.out.println("Вы ввели неправильную комманду");
            }
        }
    }

    static void printMenu() {
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("0. Выход из программы");

    }
}