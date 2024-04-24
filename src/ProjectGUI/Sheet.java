package ProjectGUI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import java.io.*;
import java.util.ArrayList;

public class Sheet extends AbstractTableModel implements Serializable {
    private final ArrayList<Transaction> transactionList = new ArrayList<>();
    private final String[] columnNames = {" ", "Date", "Type", "Amount", "Balance", "Category", "Note"};
    private final int[] columnWidths = {20, 100, 100, 100, 100, 100, 200};
    private double totalBalance;

    @Override
    public int getRowCount() {
        return transactionList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaction transaction = transactionList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> rowIndex + 1;
            case 1 -> transaction.getDate();
            case 2 -> transaction.getType();
            case 3 -> transaction.getAmount();
            case 4 -> transaction.getBalance();
            case 5 -> transaction.getCategory();
            case 6 -> transaction.getNote();
            default -> null;
        };
    }

    public void removeLast() {
        if(!transactionList.isEmpty()) {
            totalBalance -= transactionList.getLast().balance;
            int last = transactionList.size() - 1;
            transactionList.remove(last);
            fireTableRowsDeleted(last, last);
        }else{
            JOptionPane.showMessageDialog(null,"Transaction is empty");
        }
    }

    public void emptyTransaction() {
        if(!transactionList.isEmpty()) {
            totalBalance = 0;
            int rowCount = transactionList.size();
            transactionList.clear();
            fireTableRowsDeleted(0, rowCount - 1);
        }else{
            JOptionPane.showMessageDialog(null,"Transaction is empty");
        }
    }

    public void addRecord(Transaction transaction) {
            transactionList.add(transaction);
            fireTableRowsInserted(transactionList.size() - 1, transactionList.size() - 1);
    }

    public ArrayList<Transaction> getList() {
        return transactionList;
    }

    public void setColumnWidths(TableColumnModel columnModel) {
        for (int i = 0; i < columnWidths.length && i < getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(columnWidths[i]);
        }
    }

    public ArrayList<Transaction> updateBalance() {
        double previousBalance = 0;
        for (Transaction t : transactionList) {
                previousBalance += t.amount;
                t.balance = previousBalance;
                System.out.println(t.amount +"="+ t.balance);
        }
        System.out.println();
        totalBalance = previousBalance;
        fireTableDataChanged();
        return transactionList;
    }

    public double getTotalBalance() {
        return totalBalance;
    }
}
