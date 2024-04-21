package ProjectGUI;

import javax.swing.*;
import java.awt.*;

public class RenameFileFrame extends JFrame {
    private String fileName;
    RenameFileFrame(){
        setTitle("New transaction");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel center = new JPanel();
        center.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        JLabel label = new JLabel("Name the file : ");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel holder = new JPanel();
        holder.setPreferredSize(new Dimension(400,70));
        JTextField name = new JTextField(10);
        JButton set = new JButton("Enter");
        set.addActionListener(e -> {
            fileName = name.getText();
            if(!fileName.isEmpty()) {
                new IncomeExpenseGUI(getFileName()).setLocationRelativeTo(null);
                setVisible(false);
            }else{
                fileName = "Transaction - untitled";
                new IncomeExpenseGUI(getFileName()).setLocationRelativeTo(null);
                setVisible(false);
            }
        });
        holder.add(name);
        holder.add(set);

        add(label,BorderLayout.CENTER);
        add(holder,BorderLayout.SOUTH);


        setVisible(true);
    }
    public String getFileName(){
        return fileName;
    }


}
