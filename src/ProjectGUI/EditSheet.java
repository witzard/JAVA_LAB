package ProjectGUI;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class EditSheet extends JFrame {
    public int check =0 ;
    private ArrayList<Transaction> temp;
    EditSheet(boolean open, ArrayList<Transaction> transactionList,Sheet tableModel) {
        if (open) {
            setTitle("Edit transaction");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 200);
            setResizable(false);
            getContentPane().setBackground(Color.WHITE);
            setLayout(new BorderLayout());

            JPanel center = new JPanel();
            center.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 30));
            JLabel label = new JLabel("Select row number and column name to continue editing");
            label.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel holder = new JPanel();
            holder.setPreferredSize(new Dimension(400, 70));
            JTextField row = new JTextField(10);
            row.setPreferredSize(new Dimension(40, 20));

            JComboBox<String> selectEdit = new JComboBox<>(new String[]{"Date", "Type & Amount", "Category", "Note"});
            JButton set = new JButton("Enter");
            set.addActionListener(e -> {
                if (!row.getText().isEmpty()) {
                    int targetRowNumber = Integer.parseInt(row.getText());
                    String selected = (String) Objects.requireNonNull(selectEdit.getSelectedItem());
                    int index = 1;
                    if (targetRowNumber > 0 && targetRowNumber <= transactionList.size()) {
                        for (Transaction t : transactionList) {
                            if (targetRowNumber == index) {
                                setNewData(targetRowNumber,selected, t);
                            }
                            index++;
                        }
                        if(check == 1){
                            temp.addAll(tableModel.updateBalance());
                            tableModel.emptyTransaction();
                            for(Transaction ts : temp) {
                                transactionList.add(ts);
                                System.out.println(ts.date+ts.type+ts.amount+ts.balance+ts.category+ts.note);
                                tableModel.fireTableRowsInserted(transactionList.size()-1,transactionList.size()-1);
                            }
                            check = 0;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill number match with transaction amount");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select row number to edit.");
                }

                dispose();
            });

            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(e -> dispose());
            holder.add(row);
            holder.add(selectEdit);
            holder.add(set);
            holder.add(cancel);

            add(label, BorderLayout.CENTER);
            add(holder, BorderLayout.SOUTH);

            setVisible(true);
        }
    }

    public void setNewData(int selectedRow,String selectEdit, Transaction t) {
        JFrame frame = new JFrame("Set new " + selectEdit + " data in row " + selectedRow );
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setVisible(true);
        dispose();
        //enter.setPreferredSize(new Dimension());
        switch (selectEdit) {
            case "Date" -> {
                JPanel datePanel = new JPanel(new BorderLayout());
                JDateChooser dateChooser = new JDateChooser();
                JButton enter = new JButton("Enter");
                JButton cancel = new JButton("Cancel");
                cancel.addActionListener(e -> frame.dispose());
                JPanel buttonHolder = new JPanel();
                buttonHolder.add(enter);
                buttonHolder.add(cancel);

                datePanel.add(setCustomTextFormat("Date & Time:"), BorderLayout.NORTH);
                datePanel.add(dateChooser, BorderLayout.CENTER);
                datePanel.add(buttonHolder, BorderLayout.SOUTH);

                enter.addActionListener(e -> {
                    t.date = dateChooser.getDate();
                    check = 1;
                    JOptionPane.showMessageDialog(null, "Edit success!");
                    dispose();
                    frame.dispose();
                });
                frame.add(datePanel, BorderLayout.CENTER);
            }
            case "Type & Amount" -> {
                JPanel fieldMargin = new JPanel(new BorderLayout());
                JTextField amountField = new JTextField(20);
                amountField.setPreferredSize(new Dimension(30, 37));
                JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"   Income", "   Expense"});
                JButton enter = new JButton("Enter");
                JButton cancel = new JButton("Cancel");
                cancel.addActionListener(e -> frame.dispose());
                JPanel buttonHolder = new JPanel();
                buttonHolder.add(typeComboBox);
                buttonHolder.add(enter);
                buttonHolder.add(cancel);

                fieldMargin.add(setCustomTextFormat("Amount:"), BorderLayout.NORTH);
                fieldMargin.add(amountField, BorderLayout.CENTER);
                fieldMargin.add(buttonHolder, BorderLayout.SOUTH);

                enter.addActionListener(e -> {
                    String newType = (String) typeComboBox.getSelectedItem();
                    double newAmount = Double.parseDouble(amountField.getText());
                    if (newType.equals("   Expense")) {
                        newAmount *= -1;
                    }
                    t.type = newType;
                    t.amount = newAmount;
                    check = 1;
                    JOptionPane.showMessageDialog(null, "Edit success!");
                    dispose();
                    frame.dispose();
                });
                frame.add(fieldMargin, BorderLayout.CENTER);
            }
            case "Category" -> {
                JPanel categoryPanel = new JPanel(new BorderLayout());
                JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{" ", "Food", "Parent", "Drink", "Salary", "Electric Bills", "Water Bills", "Internet"});
                categoryComboBox.setEditable(true);
                categoryComboBox.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
                JButton enter = new JButton("Enter");
                JButton cancel = new JButton("Cancel");
                cancel.addActionListener(e -> frame.dispose());
                JPanel buttonHolder = new JPanel();
                buttonHolder.add(enter);
                buttonHolder.add(cancel);

                categoryPanel.add(setCustomTextFormat("Category:"), BorderLayout.NORTH);
                categoryPanel.add(categoryComboBox, BorderLayout.CENTER);
                categoryPanel.add(buttonHolder, BorderLayout.SOUTH);

                enter.addActionListener(e -> {
                    t.category = (String) categoryComboBox.getSelectedItem();
                    check = 1;
                    JOptionPane.showMessageDialog(null, "Edit success!");
                    dispose();
                    frame.dispose();
                });
                frame.add(categoryPanel, BorderLayout.CENTER);
            }
            case "Note" -> {
                JPanel notePanel = new JPanel(new BorderLayout());
                JTextArea noteArea = new JTextArea(30, 30);
                noteArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JButton enter = new JButton("Enter");
                JButton cancel = new JButton("Cancel");
                cancel.addActionListener(e -> frame.dispose());
                JPanel buttonHolder = new JPanel();
                buttonHolder.add(enter);
                buttonHolder.add(cancel);

                notePanel.add(setCustomTextFormat("Note:"), BorderLayout.NORTH);
                notePanel.add(noteArea, BorderLayout.CENTER);
                notePanel.add(buttonHolder, BorderLayout.SOUTH);

                enter.addActionListener(e -> {
                    t.note = noteArea.getText();
                    check = 1;
                    JOptionPane.showMessageDialog(null, "Edit success!");
                    dispose();
                    frame.dispose();
                });
                frame.add(notePanel, BorderLayout.CENTER);
            }

        }
    }

    public Component setCustomTextFormat(String text) {
        JLabel t = new JLabel(text);
        t.setForeground(Color.BLACK);
        t.setHorizontalAlignment(SwingConstants.LEFT);
        t.setVerticalAlignment(SwingConstants.BOTTOM);
        t.setFont(new Font("JetBrains Mono", Font.BOLD, 15));
        return t;
    }

}
