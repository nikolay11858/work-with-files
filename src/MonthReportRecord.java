public class MonthReportRecord {
    String itemName;
    Boolean isExpense;
    int quantity;
    int sumOfOne;

    MonthReportRecord(String itemName, Boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}