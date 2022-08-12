import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthReportHandler {
    HashMap<Integer, ArrayList<MonthReportRecord>> perMonthReport = new HashMap<>();
    ReaderFile readerFile = new ReaderFile();

    HashMap<Integer, ArrayList<MonthReportRecord>> checkSpendingPerMonth() {
        for (int i = 1; i <= 3; i++) {
            String fileContents = readerFile.readFileContents("resources/m.20220" + i + ".csv");

            if (fileContents == null) {
                System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
                return null;
            } else {
                System.out.println("Загружен отчёт за " + Month.of(i));
            }

            String[] strings = fileContents.split("\n");
            ArrayList<MonthReportRecord> monthReportRecordsArr = new ArrayList<>();
            for (int j = 1; j < strings.length; j++) {
                String tmpString[] = strings[j].split(",");
                MonthReportRecord monthReportRecord = new MonthReportRecord(tmpString[0].trim(), Boolean.parseBoolean(tmpString[1].trim()),
                        Integer.parseInt(tmpString[2].trim()), Integer.parseInt(tmpString[3].trim()));
                monthReportRecordsArr.add(monthReportRecord);
                perMonthReport.put(i, monthReportRecordsArr);
            }
        }
        return perMonthReport;
    }
}
