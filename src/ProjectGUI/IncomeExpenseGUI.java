package ProjectGUI;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class IncomeExpenseGUI extends JFrame {
    String fileName;
    private final JDateChooser dateChooser;
    private final JComboBox<String> typeComboBox;
    private final JTextField amountField;
    private final JComboBox<String> categoryComboBox;
    private final JTextArea noteArea;
    private final Sheet tableModel;
    private final JLabel balanceLabel;
    private double balance;

    public IncomeExpenseGUI(String fileName) {
        this.fileName = fileName;
        //    TOP_PANEL     ////////////////////////////////////////////////////////
        JMenuBar menuBar = getjMenuBar();

//     SIDE_PANEL       ////////////////////////////////////////////////////////
        JPanel sidePanel = new JPanel(new GridLayout(4,1));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        sidePanel.setPreferredSize(new Dimension(350, 1024));

        JPanel datePanel = new JPanel(new GridLayout(2,1));
        dateChooser = new JDateChooser();
        dateChooser.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        datePanel.add(setCustomTextFormat("Date & Time:"));
        datePanel.add(dateChooser);

        JPanel transactionPanel = new JPanel(new GridLayout(2,2,10,10));

        JPanel fieldMargin = new JPanel(new BorderLayout());
        amountField = new JTextField(20);
        amountField.setPreferredSize(new Dimension(30,37));
        fieldMargin.add(amountField,BorderLayout.SOUTH);

        typeComboBox = new JComboBox<>(new String[]{"   Income", "   Expense"});
        typeComboBox.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));

        JPanel buttonMargin = new JPanel(new BorderLayout());
        JButton addButton = new JButton("Add Record");
        addButton.addActionListener(e -> addRecord());
        addButton.setPreferredSize(new Dimension(30,37));
        buttonMargin.add(addButton,BorderLayout.SOUTH);

        transactionPanel.add(setCustomTextFormat("Amount:"));
        transactionPanel.add(fieldMargin);
        transactionPanel.add(typeComboBox);
        transactionPanel.add(buttonMargin);

        JPanel categoryPanel = new JPanel(new GridLayout(2,1));
        categoryPanel.add(setCustomTextFormat("Category:"));
        categoryComboBox = new JComboBox<>(new String[]{" ", "Food", "Parent", "Drink", "Salary", "Electric Bills", "Water Bills", "Internet"});
        categoryComboBox.setEditable(true);
        categoryComboBox.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        categoryPanel.add(categoryComboBox);

        JPanel notePanel = new JPanel(new GridLayout(2,1));
        noteArea = new JTextArea(30, 30);
        noteArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        notePanel.add(setCustomTextFormat("Note:"));
        notePanel.add(noteArea);

        sidePanel.add(datePanel, BorderLayout.CENTER);
        sidePanel.add(transactionPanel, BorderLayout.CENTER);
        sidePanel.add(categoryPanel, BorderLayout.CENTER);
        sidePanel.add(notePanel, BorderLayout.CENTER);
//    BOTTOM_PANEL      ////////////////////////////////////////////////////////
        balance = 0.0;
        balanceLabel = new JLabel("Balance: " + balance);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(balanceLabel);
        bottomPanel.setPreferredSize(new Dimension(1440, 60));

        //bottomPanel.setBackground(Color.DARK_GRAY);
//    CENTER_PANEL      ////////////////////////////////////////////////////////

        tableModel = new Sheet();
        JTable recordTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(recordTable);
        recordTable.setFillsViewportHeight(true);

//      FRAME      ////////////////////////////////////////////////////////
        setTitle(fileName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1440, 1024);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        setJMenuBar(menuBar);
        add(sidePanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private JMenuBar getjMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newFile = new JMenuItem("New File");
        newFile.addActionListener(e -> {new RenameFileFrame().setLocationRelativeTo(null);});
        JMenuItem openFile = new JMenuItem("Open File");
        JMenuItem saveFile = new JMenuItem("Save File");
        saveFile.addActionListener(e -> {new SaveFileSystem(tableModel.getList(),fileName);});
        JMenuItem renameFile = new JMenuItem("Rename File");
        JMenuItem dash = new JMenuItem("------------------------");
        JMenuItem newSheet = new JMenuItem("New Sheet");
        JMenuItem openSheet = new JMenuItem("Open Sheet");
        JMenuItem renameSheet = new JMenuItem("Rename Sheet");
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(renameFile);
        fileMenu.add(dash);
        fileMenu.add(newSheet);
        fileMenu.add(openSheet);
        fileMenu.add(renameSheet);
        menuBar.add(fileMenu);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
        JMenu editMenu = new JMenu("Edit");
        JMenuItem edit = new JMenuItem("Edit transaction");
        JMenuItem remove = new JMenuItem("Remove transaction");
        JMenuItem empty = new JMenuItem("Empty transaction");
        editMenu.add(edit);
        editMenu.add(remove);
        editMenu.add(empty);
        menuBar.add(editMenu);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
        JMenu helpMenu = new JMenu("Help");
        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(e -> JOptionPane.showMessageDialog(null, "Nah, I can not help ya."));
        helpMenu.add(help);
        menuBar.add(helpMenu);
        return menuBar;
    }

    private void addRecord() {
        Date date = dateChooser.getDate();
        String type = (String) typeComboBox.getSelectedItem();
        String amountString = amountField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        String note = noteArea.getText();
        double amount;
        if (amountString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter the Amount", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Amount Format", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (type.equals("Expense")) { amount *= -1; }

        balance += amount;
        balanceLabel.setText("Balance: " + balance);
        Transaction transaction = new Transaction(date, type, amount, balance, category, note);
        tableModel.addRecord(transaction);
        clearInputField();
    }
    private void emptyTable(){
       // tableModel.
    }

    private void clearInputField() {
        typeComboBox.setSelectedIndex(0);
        amountField.setText("");
        categoryComboBox.setSelectedIndex(0);
        noteArea.setText("");
    }

    public Component setCustomTextFormat(String text) {
        JLabel t = new JLabel(text);
        t.setForeground(Color.BLACK);//change back later to white na // mai woyyy
        t.setHorizontalAlignment(SwingConstants.LEFT);
        t.setVerticalAlignment(SwingConstants.BOTTOM);
        t.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        return t;
    }
}
