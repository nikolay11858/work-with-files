import java.util.ArrayList;
import java.util.HashMap;

public class YearReportHandler {
    HashMap<Integer, ArrayList<YearReportRecord>> perYearReport = new HashMap<>();
    ReaderFile readerFile = new ReaderFile();

    HashMap<Integer, ArrayList<YearReportRecord>> checkSpendingPerYear(Integer year) {
        String fileContents = readerFile.readFileContents("resources/y." + year + ".csv");

        if (fileContents == null) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        } else {
            System.out.println("Загружен отчёт за " + year);
        }

        String[] strings = fileContents.split("\n");
        ArrayList<YearReportRecord> yearReportRecordArr = new ArrayList<>();

        for (int i = 1; i < strings.length; i++) {
            String tmpString[] = strings[i].split(",");
            YearReportRecord yearReportRecord = new YearReportRecord(Integer.parseInt(tmpString[0].trim()), Integer.parseInt(tmpString[1].trim()),
                    Boolean.parseBoolean(tmpString[2].trim()));
            yearReportRecordArr.add(yearReportRecord);
        }
        perYearReport.put(year, yearReportRecordArr);
        return perYearReport;
    }
}
