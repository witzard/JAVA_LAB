package ProjectGUI;

import javax.swing.*;
import java.awt.*;

public class NameFileSystem extends JFrame {
    private String fileName;

    NameFileSystem(boolean open) {
        if (open) {
            setTitle("New transaction");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 150);
            setResizable(false);
            getContentPane().setBackground(Color.WHITE);
            setLayout(new BorderLayout());

            JPanel center = new JPanel();
            center.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            JLabel label = new JLabel("Name the file : ");
            label.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel holder = new JPanel();
            holder.setPreferredSize(new Dimension(400, 70));
            JTextField name = new JTextField(10);
            JButton set = new JButton("Enter");
            set.addActionListener(e -> {
                fileName = name.getText();
                if (!fileName.isEmpty()) {
                    new IncomeExpenseGUI(getFileName()).setLocationRelativeTo(null);
                    setVisible(false);

                } else {
                    fileName = "Transaction - untitled";
                    new IncomeExpenseGUI(getFileName()).setLocationRelativeTo(null);
                    setVisible(false);
                }
            });
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(e -> System.exit(0));

            holder.add(name);
            holder.add(set);
            holder.add(cancel);

            add(label, BorderLayout.CENTER);
            add(holder, BorderLayout.SOUTH);

            setVisible(true);
        }
    }

    public String getFileName() {
        return fileName;
    }
}
