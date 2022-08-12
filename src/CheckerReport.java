import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

public class CheckerReport {
    void checkReports(MonthReportHandler monthReportHandler, YearReportHandler yearReportHandler) {
        HashMap<Integer, ArrayList<MonthReportRecord>> perMonthReport = monthReportHandler.perMonthReport;
        HashMap<Integer, ArrayList<YearReportRecord>> perYearReport = yearReportHandler.perYearReport;
        HashMap<Integer, ArrayList<Integer>> newReportDict = new HashMap<>();

        if ((perMonthReport.isEmpty()) || (perYearReport.isEmpty())) {
            System.out.println("Вы не считали информацию, либо месячного отчёта, либо годового.");
            return;
        }

        for (int months : perMonthReport.keySet()) {
            int income = 0;
            int expense = 0;
            ArrayList<MonthReportRecord> monthReportRecord = perMonthReport.get(months);
            for (int i = 0; i < monthReportRecord.size(); i++) {
                if (monthReportRecord.get(i).isExpense) {
                    expense += monthReportRecord.get(i).sumOfOne * monthReportRecord.get(i).quantity;
                } else {
                    income += monthReportRecord.get(i).sumOfOne * monthReportRecord.get(i).quantity;
                }
            }
            ArrayList<Integer> newOperationArr = new ArrayList<>();
            newOperationArr.add(income);
            newOperationArr.add(expense);

            System.out.println("");
            System.out.println("За " + months + " месяц. Вы получили: " + income);
            System.out.println("За " + months + " месяц. Вы потратили: " + expense);
            newReportDict.put(months, newOperationArr);
        }

        for (ArrayList<YearReportRecord> yearReportRecord : perYearReport.values()) {
            int month = 0;
            Boolean isError = false;
            int operationValue = 0;

            for (int i = 0; i < yearReportRecord.size(); i++) {
                if ((yearReportRecord.get(i).month != month) && (yearReportRecord.get(i).isExpense == false)) {
                    month = yearReportRecord.get(i).month;
                    operationValue = newReportDict.get(month).get(0);
                    if (operationValue != yearReportRecord.get(i).amount) {
                        System.out.println("Проверка значения не пройдена. Расхождение с " + Month.of(month));
                        isError = true;
                    }
                } else if ((yearReportRecord.get(i).month != month) && (yearReportRecord.get(i).isExpense == true)) {
                    month = yearReportRecord.get(i).month;
                    operationValue = newReportDict.get(month).get(1);
                    if (operationValue != yearReportRecord.get(i).amount) {
                        System.out.println("Проверка значения не пройдена. Расхождение с " + Month.of(month));
                        isError = true;
                    }
                } else if ((yearReportRecord.get(i).month == month) && (yearReportRecord.get(i).isExpense == false)) {
                    month = yearReportRecord.get(i).month;
                    operationValue = newReportDict.get(month).get(0);
                    if (operationValue != yearReportRecord.get(i).amount) {
                        System.out.println("Проверка значения не пройдена. Расхождение с " + Month.of(month));
                        isError = true;
                    }
                } else if ((yearReportRecord.get(i).month == month) && (yearReportRecord.get(i).isExpense == true)) {
                    month = yearReportRecord.get(i).month;
                    operationValue = newReportDict.get(month).get(1);
                    if (operationValue != yearReportRecord.get(i).amount) {
                        System.out.println("Проверка значения не пройдена. Расхождение с " + Month.of(month));
                        isError = true;
                    }
                }
            }
            if (isError == false) {
                System.out.println("Проверка отчётов выполнена успешно.");
            }
        }
    }

    void showReportPerMonths(MonthReportHandler monthReportHandler) {
        HashMap<Integer, ArrayList<MonthReportRecord>> perMonthReport = monthReportHandler.perMonthReport;

        if (perMonthReport.isEmpty()) {
            System.out.println("Считайте, пожалуйста, отчёт за месяц");
            return;
        }

        for (int months : perMonthReport.keySet()) {
            int maxIncome = 0;
            int maxExpense = 0;
            String nameItemIncome = "";
            String nameItemExpense = "";
            System.out.println("");
            ArrayList<MonthReportRecord> monthReportRecord = perMonthReport.get(months);
            for (int i = 0; i < monthReportRecord.size(); i++) {
                int quantity = monthReportRecord.get(i).sumOfOne * monthReportRecord.get(i).quantity;
                if (maxIncome < quantity && (monthReportRecord.get(i).isExpense == false)) {
                    maxIncome = quantity;
                    nameItemIncome = monthReportRecord.get(i).itemName;
                }
                if (maxExpense < quantity && (monthReportRecord.get(i).isExpense == true)) {
                    maxExpense = quantity;
                    nameItemExpense = monthReportRecord.get(i).itemName;
                }
            }

            System.out.println("Месяц " + Month.of(months));
            System.out.println("Максимальный доход - " + nameItemIncome + ". Это принесло - " + maxIncome);
            System.out.println("Максимальная трата - " + nameItemExpense + ". Было потрачен " + maxExpense);
        }
    }

    void showReportPerYears(YearReportHandler yearReportHandler) {
        HashMap<Integer, ArrayList<YearReportRecord>> perYearReport = yearReportHandler.perYearReport;

        if (perYearReport.isEmpty()) {
            System.out.println("Считайте, пожалуйста, отчёт за год");
            return;
        }
        for (Integer yearTemp : perYearReport.keySet()) {
            System.out.println("");
            System.out.println("Траты за " + yearTemp + " год.");
            for (ArrayList<YearReportRecord> yearReportRecord : perYearReport.values()) {
                int sumIncome = 0;
                int sumExpense = 0;
                int income = 0;
                int expense = 0;
                int month = 0;

                for (int i = 0; i < yearReportRecord.size(); i++) {
                    if (yearReportRecord.get(i).month == month) {
                        if (yearReportRecord.get(i).isExpense) {
                            expense += yearReportRecord.get(i).amount;
                            sumExpense += yearReportRecord.get(i).amount;
                        } else {
                            income += yearReportRecord.get(i).amount;
                            sumIncome += yearReportRecord.get(i).amount;
                        }
                        System.out.println("Прибыль за " + Month.of(month) + " составлют - " + (income - expense));
                    } else {
                        month = yearReportRecord.get(i).month;
                        income = 0;
                        expense = 0;
                        if (yearReportRecord.get(i).isExpense) {
                            expense += yearReportRecord.get(i).amount;
                            sumExpense += yearReportRecord.get(i).amount;
                        } else {
                            income += yearReportRecord.get(i).amount;
                            sumIncome += yearReportRecord.get(i).amount;
                        }
                    }
                }

                System.out.println("");
                System.out.println("Средний доход за 12 месяцев - " + (sumIncome / 12));
                System.out.println("Средний расход за 12 месяцев - " + (sumExpense / 12));
            }
        }
    }
}
