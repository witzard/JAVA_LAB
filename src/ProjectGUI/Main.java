package ProjectGUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.Date;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.time.*;
import java.util.Locale;

public class Main extends JFrame implements ActionListener{
    static JMenuBar menuBar;
    static JPanel sidePanel ,bottomPanel;
    static JPanel datePanel,transactionPanel,categoryPanel,notePanel,actionPanel;
    static JTextArea noteArea;
    static JComboBox<String> categoryDropdown;
    static JTextField incomeField,expenseField;
    static JButton addButton;
    static JDateChooser dateChooser;
    static JTable recordTable;
    static DefaultTableModel tableModel;
    Sheet sheet = new Sheet();
    public Main(){
        //    TOP_PANEL     ////////////////////////////////////////////////////////
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newFiles = new JMenuItem("Open Sheet");
        JMenuItem newSheets = new JMenuItem("New Sheet");
        fileMenu.add(newFiles);
        fileMenu.add(newSheets);
        menuBar.add(fileMenu);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
        JMenu editMenu = new JMenu("Edit");
        //JMenuItem edit = new JMenuItem("Edit transaction");
        JMenuItem remove = new JMenuItem("Remove transaction");
        //editMenu.add(edit);
        editMenu.add(remove);
        menuBar.add(editMenu);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
        JMenu helpMenu = new JMenu("Help");
        JMenuItem help = new JMenuItem("Help your self.");
        helpMenu.add(help);
        menuBar.add(helpMenu);

//     SIDE_PANEL       ////////////////////////////////////////////////////////
        JPanel sidePanelBack = new JPanel(new BorderLayout());
        sidePanelBack.add(setCustomBorder(20,1024),BorderLayout.EAST);
        sidePanelBack.add(setCustomBorder(20,1024),BorderLayout.WEST);
        sidePanelBack.add(setCustomBorder(300,20),BorderLayout.NORTH);
        sidePanelBack.add(setCustomBorder(300,20),BorderLayout.SOUTH);
        sidePanel = new JPanel(new GridLayout(5, 1));
        sidePanel.setBackground(Color.BLACK);
        sidePanel.setPreferredSize(new Dimension(300, 1024));

        datePanel = new JPanel();
        datePanel.setBackground(Color.BLACK);;
        datePanel.add(setCustomTextFormat("date"));
        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(new Dimension(290,25));
        datePanel.add(dateChooser);

        JPanel transactionPanelBack =  new JPanel(new BorderLayout());
        transactionPanel = new JPanel(new GridLayout(2,2,10,10));
        transactionPanel.setBackground(Color.BLACK);
        transactionPanelBack.add(transactionPanel);
        transactionPanel.add(setCustomTextFormat("Income"));
        transactionPanel.add(setCustomTextFormat("Expense"));
        incomeField = new JTextField();
        transactionPanel.add(incomeField);
        expenseField = new JTextField();
        transactionPanel.add(expenseField);
        transactionPanelBack.add(setCustomBorder(5,150),BorderLayout.EAST);
        transactionPanelBack.add(setCustomBorder(5,150),BorderLayout.WEST);
        transactionPanelBack.add(setCustomBorder(300,35),BorderLayout.NORTH);
        transactionPanelBack.add(setCustomBorder(300,25),BorderLayout.SOUTH);

        categoryPanel = new JPanel();
        categoryPanel.setBackground(Color.BLACK);
        categoryPanel.add(setCustomTextFormat("Category"));
        categoryDropdown = new JComboBox<>(new String[]{"Select", "Food", "Parent", ""});
        categoryDropdown.setEditable(true);
        categoryDropdown.setPreferredSize(new Dimension(290, 25));
        categoryPanel.add(categoryDropdown);

        notePanel = new JPanel();
        notePanel.setBackground(Color.BLACK);
        notePanel.add(setCustomTextFormat("Note"));
        noteArea = new JTextArea(30,30);
        noteArea.setPreferredSize(new Dimension(260, 30));
        notePanel.add(noteArea);

        actionPanel = new JPanel();
        actionPanel.setBackground(Color.BLACK);
        addButton = new JButton("Add Record");
        actionPanel.add(addButton);


        sidePanel.add(datePanel);
        sidePanel.add(transactionPanelBack);
        sidePanel.add(categoryPanel);
        sidePanel.add(notePanel);
        sidePanel.add(actionPanel);

        sidePanelBack.add(sidePanel);


//    BOTTOM_PANEL      ////////////////////////////////////////////////////////
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.setPreferredSize(new Dimension(1440,60));
//    CENTER_PANEL      ////////////////////////////////////////////////////////

        JPanel sheetSelector = new JPanel();
        sheetSelector.setBackground(Color.DARK_GRAY);
        recordTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{" ","Date","Category","Income","Expense","Balance","Note"}, 0);
        recordTable.setModel(tableModel);
//      FRAME      ////////////////////////////////////////////////////////

        setTitle("Income & Expense - transaction1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1440,1024);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        setJMenuBar(menuBar);
        add(sidePanelBack, BorderLayout.WEST);
        add(new JScrollPane(recordTable), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this);
    }

    public static void main(String[] args) {
        Main program = new Main();
        program.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addRecord();
        }
    }

    public Component setCustomTextFormat(String text){
        JLabel t = new JLabel();
        t.setText(text);
        t.setForeground(Color.WHITE);//change back later to white na
        t.setHorizontalAlignment(SwingConstants.LEFT);
        t.setHorizontalTextPosition(JLabel.LEFT);
        t.setFont(new Font("JetBrains Mono", Font.PLAIN, 20));
        return t;
    }
    public Component setCustomBorder(int width,int height){
        JPanel border = new JPanel();
        border.setBackground(Color.BLACK);
        border.setPreferredSize(new Dimension(width,height));
        return border;
    }
    public void addRecord(){
        Date dateInput = dateChooser.getDate();
        String incomeInput = incomeField.getText();
        String  expenseInput = expenseField.getText();
        double income = 0, expense = 0, balance = 0;
        String category = (String) categoryDropdown.getSelectedItem();
        String note = noteArea.getText();

        if(!incomeInput.isEmpty()){
            income = Double.parseDouble(incomeInput);
            expense = 0;
            sheet.updateBalance(income,expense);
        }else if(!expenseInput.isEmpty()){
            expense = Double.parseDouble(expenseInput);
            income = 0;
            sheet.updateBalance(income,expense);
        }else{
            JOptionPane.showMessageDialog(null, "Please fill income or expense");
        }
        sheet.transactionList.add(new Transaction(dateInput,income,expense,sheet.balance,category,note));

        incomeField.setText("");
        expenseField.setText("");
        noteArea.setText("");
        int rowCount = tableModel.getRowCount();
        rowCount++;
        tableModel.addRow(new Object[] {rowCount,dateInput,category,income,expense,sheet.balance,note});
    }

}

