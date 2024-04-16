package ProjectGUI;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Sheet extends AbstractTableModel {
    private final ArrayList<Transaction> transactionList = new ArrayList<>();

    private final String[] columnNames = {"Date","Type","Amount","Category","Note"};

    public void deleteTransaction(){transactionList.removeLast();}

    public void emptyTransaction(){transactionList.clear();}

    public void addRecord(Transaction transaction) {
       transactionList.add(transaction);
       fireTableRowsInserted(transactionList.size()-1,transactionList.size()-1);
    }

    @Override
    public int getRowCount() {return transactionList.size();}

    @Override
    public int getColumnCount() {return columnNames.length;}

    @Override
    public String getColumnName(int column) {return columnNames[column];}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaction transaction = transactionList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> transaction.getDate();
            case 1 -> transaction.getType();
            case 2 -> transaction.getAmount();
            case 3 -> transaction.getCategory();
            case 4 -> transaction.getNote();
            default -> null;
        };
    }
}
