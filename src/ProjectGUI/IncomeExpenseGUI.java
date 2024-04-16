package ProjectGUI;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;

public class IncomeExpenseGUI extends JFrame implements ActionListener {
        static JMenuBar menuBar;
        static JPanel sidePanel, bottomPanel;
        static JPanel datePanel, transactionPanel, categoryPanel, notePanel, actionPanel;
        static JTextArea noteArea;
        static JComboBox<String> categoryDropdown;
        static JTextField incomeField, expenseField;
        static JButton addButton;
        static JDateChooser dateChooser;
        static JTable recordTable;
        static DefaultTableModel tableModel;
        Sheet sheet = new Sheet();

        public IncomeExpenseGUI() {
            //    TOP_PANEL     ////////////////////////////////////////////////////////
            menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenuItem newFile = new JMenuItem("New File");
            newFile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ProjectGUI.Main newWindow = new ProjectGUI.Main();
                    newWindow.setTitle("Untitled Transaction");
                    newWindow.setVisible(true);

                }
            });
            JMenuItem openFile = new JMenuItem("Open File");
            JMenuItem saveFile = new JMenuItem("Save File");
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
            //JMenuItem edit = new JMenuItem("Edit transaction");
            JMenuItem remove = new JMenuItem("Remove transaction");
            remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tableModel.setRowCount(0);
                    sheet.emptyTransaction();
                }
            });
            //editMenu.add(edit);
            editMenu.add(remove);
            menuBar.add(editMenu);
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
            JMenu helpMenu = new JMenu("Help");
            JMenuItem help = new JMenuItem("Help");
            help.addActionListener(e -> JOptionPane.showMessageDialog(null, "Nah, I can not help ya."));
            helpMenu.add(help);
            menuBar.add(helpMenu);

//     SIDE_PANEL       ////////////////////////////////////////////////////////
            JPanel sidePanelBack = new JPanel(new BorderLayout());
            sidePanelBack.add(setCustomBorder(20, 1024), BorderLayout.EAST);
            sidePanelBack.add(setCustomBorder(20, 1024), BorderLayout.WEST);
            sidePanelBack.add(setCustomBorder(300, 20), BorderLayout.NORTH);
            sidePanelBack.add(setCustomBorder(300, 20), BorderLayout.SOUTH);
            sidePanel = new JPanel(new GridLayout(5, 1));
            sidePanel.setBackground(Color.BLACK);
            sidePanel.setPreferredSize(new Dimension(300, 1024));

            datePanel = new JPanel();
            datePanel.setBackground(Color.BLACK);
            datePanel.add(setCustomTextFormat("date"));
            dateChooser = new JDateChooser();
            dateChooser.setPreferredSize(new Dimension(290, 25));
            datePanel.add(dateChooser);

            JPanel transactionPanelBack = new JPanel(new BorderLayout());
            transactionPanel = new JPanel(new GridLayout(2, 2, 10, 10));
            transactionPanel.setBackground(Color.BLACK);
            transactionPanelBack.add(transactionPanel);
            transactionPanel.add(setCustomTextFormat("Income"));
            transactionPanel.add(setCustomTextFormat("Expense"));
            incomeField = new JTextField();
            transactionPanel.add(incomeField);
            expenseField = new JTextField();
            transactionPanel.add(expenseField);
            transactionPanelBack.add(setCustomBorder(5, 150), BorderLayout.EAST);
            transactionPanelBack.add(setCustomBorder(5, 150), BorderLayout.WEST);
            transactionPanelBack.add(setCustomBorder(300, 35), BorderLayout.NORTH);
            transactionPanelBack.add(setCustomBorder(300, 25), BorderLayout.SOUTH);

            categoryPanel = new JPanel();
            categoryPanel.setBackground(Color.BLACK);
            categoryPanel.add(setCustomTextFormat("Category"));
            categoryDropdown = new JComboBox<>(new String[]{" ", "Food", "Parent", "Drink", "Salary", "Electric Bills", "Water Bills", "Internet"});
            categoryDropdown.setEditable(true);
            categoryDropdown.setPreferredSize(new Dimension(290, 25));
            categoryPanel.add(categoryDropdown);

            notePanel = new JPanel();
            notePanel.setBackground(Color.BLACK);
            notePanel.add(setCustomTextFormat("Note"));
            noteArea = new JTextArea(30, 30);
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
            bottomPanel.setPreferredSize(new Dimension(1440, 60));
//    CENTER_PANEL      ////////////////////////////////////////////////////////
            JPanel sheetSelector = new JPanel();
            sheetSelector.setBackground(Color.DARK_GRAY);
            recordTable = new JTable();
            tableModel = new DefaultTableModel(new Object[]{" ", "Date", "Category", "Income", "Expense", "Balance", "Note"}, 0);
            recordTable.setModel(tableModel);
//      FRAME      ////////////////////////////////////////////////////////
            setTitle("Income & Expense - transaction1");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1440, 1024);
            getContentPane().setBackground(Color.WHITE);
            setLayout(new BorderLayout());

            setJMenuBar(menuBar);
            add(sidePanelBack, BorderLayout.WEST);
            add(new JScrollPane(recordTable), BorderLayout.CENTER);
            add(bottomPanel, BorderLayout.SOUTH);
            addButton.addActionListener(this);
        }



        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addButton) {
                sheet.addRecord(dateChooser.getDate(), incomeField.getText(), expenseField.getText(), (String) categoryDropdown.getSelectedItem(), noteArea.getText());
                incomeField.setText("");
                expenseField.setText("");
                noteArea.setText("");
                int rowCount = tableModel.getRowCount();
                rowCount++;
                Transaction data = sheet.transactionList.get(rowCount - 1);
                tableModel.addRow(new Object[]{rowCount, data.getDate(), data.getCategory(), data.getIncome(), data.getExpense(), data.getBalance(), data.getNote()});
            }
        }

        public Component setCustomTextFormat(String text) {
            JLabel t = new JLabel();
            t.setText(text);
            t.setForeground(Color.WHITE);//change back later to white na
            t.setHorizontalAlignment(SwingConstants.LEFT);
            t.setHorizontalTextPosition(JLabel.LEFT);
            t.setFont(new Font("JetBrains Mono", Font.PLAIN, 20));
            return t;
        }

        public Component setCustomBorder(int width, int height) {
            JPanel border = new JPanel();
            border.setBackground(Color.BLACK);
            border.setPreferredSize(new Dimension(width, height));
            return border;
        }
    }


}
