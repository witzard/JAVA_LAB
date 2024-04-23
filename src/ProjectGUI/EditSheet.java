package ProjectGUI;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class EditSheet extends JFrame{
    private Date newDate;

    private double newBalance;
    private String newType;
    private double newAmount;
    private String newCategory;
    private String newNote;

    EditSheet(boolean open, ArrayList<Transaction> TransactionList){
        if (open) {
            setTitle("Edit transaction");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 200);
            //setResizable(false);
            getContentPane().setBackground(Color.WHITE);
            setLayout(new BorderLayout());

            JPanel center = new JPanel();
            center.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 30));
            JLabel label = new JLabel("Select row number and column to continue edit");
            label.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel holder = new JPanel();
            holder.setPreferredSize(new Dimension(400, 70));
            JTextField row = new JTextField(10);
            row.setPreferredSize(new Dimension(40, 20));

            JComboBox<String> selectEdit = new JComboBox<>(new String[]{"Date","Type","Amount","Category","Note"});
            JButton set = new JButton("Enter");
            set.addActionListener(e -> {
                int rowNumber = Integer.parseInt(row.getText());
                String selected = (String) Objects.requireNonNull(selectEdit.getSelectedItem());
                int i = 1;
                if (rowNumber != 0) {
                    for(Transaction t: TransactionList) {
                        if (t != null && i == rowNumber) {
                            setNewData(selected,t);
                        }
                        i++;
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Please select row number to edit.");
                }
            });
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(e -> System.exit(0));
            holder.add(row);
            holder.add(selectEdit);
            holder.add(set);
            holder.add(cancel);

            add(label, BorderLayout.CENTER);
            add(holder, BorderLayout.SOUTH);

            setVisible(true);
        }
    }
    public void setNewData(String selectEdit,Transaction t){
        JFrame frame = new JFrame("Set new "+selectEdit+" data");
        JButton enter = new JButton("Enter");
        enter.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        switch (selectEdit) {
            case "Date" -> {
                frame.add(setNewDate(), BorderLayout.CENTER);
                enter.addActionListener(e -> {
                    t.date = newDate;
                });
                frame.add(enter);
                }
            case "Type","Amount" -> {
                frame.add(setNewTypeAndAmount(), BorderLayout.CENTER);
                enter.addActionListener(e -> {
                    t.type = newType;
                    t.amount = newAmount;
                });
                frame.add(enter);
            }
            case "Category" -> {
                frame.add(setNewCategory(), BorderLayout.CENTER);
                enter.addActionListener(e -> {
                    t.category = newCategory;
                });
                frame.add(enter);
            }
            case "Note" -> {
                frame.add(setNewNote(), BorderLayout.CENTER);
                enter.addActionListener(e -> {
                    t.note = newNote;
                });
                frame.add(enter);
            }
        };


        frame.setVisible(true);
    }

    public Component setCustomTextFormat(String text) {
        JLabel t = new JLabel(text);
        t.setForeground(Color.BLACK);//change back later to white na // mai woyyy
        t.setHorizontalAlignment(SwingConstants.LEFT);
        t.setVerticalAlignment(SwingConstants.BOTTOM);
        t.setFont(new Font("JetBrains Mono", Font.BOLD, 10));

        return t;
    }
    public Component setNewDate(){
        JPanel datePanel = new JPanel(new GridLayout(2,1));
        JDateChooser dateChooser = new JDateChooser();
        datePanel.add(setCustomTextFormat("Date & Time:"));
        datePanel.add(dateChooser);
        newDate = dateChooser.getDate();
        return dateChooser;
    }


    public Component setNewTypeAndAmount(){
        JPanel fieldMargin = new JPanel(new BorderLayout());
        JTextField amountField = new JTextField(20);
        amountField.setPreferredSize(new Dimension(30,37));
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"   Income", "   Expense"});
        typeComboBox.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        fieldMargin.add(typeComboBox,BorderLayout.NORTH);
        fieldMargin.add(setCustomTextFormat("Amount:"),BorderLayout.CENTER);
        fieldMargin.add(amountField,BorderLayout.SOUTH);

        newType = (String)typeComboBox.getSelectedItem();
        newAmount = Double.parseDouble(amountField.getText());
        if(newType.equals("   Expense")){
            newAmount*=-1;
        }
        return fieldMargin;
    }

    public Component setNewCategory(){
        JPanel categoryPanel = new JPanel(new GridLayout(2,1));
        categoryPanel.add(setCustomTextFormat("Category:"));
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{" ", "Food", "Parent", "Drink", "Salary", "Electric Bills", "Water Bills", "Internet"});
        categoryComboBox.setEditable(true);
        categoryComboBox.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        categoryPanel.add(categoryComboBox);
        newCategory = (String) categoryComboBox.getSelectedItem();
        return categoryPanel;
    }
    public Component setNewNote(){
        JPanel notePanel = new JPanel(new GridLayout(2,1));
        JTextArea noteArea = new JTextArea(30, 30);
        noteArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        notePanel.add(setCustomTextFormat("Note:"));
        notePanel.add(noteArea);
        newNote = noteArea.getText();
        return notePanel;}
}
