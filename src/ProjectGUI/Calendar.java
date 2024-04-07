package ProjectGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import com.toedter.calendar.JCalendar;
import java.time.*;

public class Calendar extends JFrame {

        private JPanel panel;
        private JButton button;
        private JCalendar calendar;

        public Calendar() {

            panel = new JPanel();
            panel.setLayout(new FlowLayout());


            button = new JButton("Select");
//            button.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    Date date = calendar.getDate();
//                    JOptionPane.showMessageDialog(null, "Selected date: " + date);
//
//
//                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                    int day = localDate.getDayOfMonth();
//                    int month = localDate.getMonthValue();
//                    int year = localDate.getYear();
//                    System.out.println(day + "/"+month+"/"+year);
//
//                }
//            });

            calendar = new JCalendar();
            calendar.setTodayButtonVisible(true);
            calendar.setMaxSelectableDate(new Date());

            panel.add(calendar);
            panel.add(button);
//
//
//            setSize(300, 300);
//            setLocationRelativeTo(null);
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setVisible(true);
        }

//        public static void main(String[] args) {
//            new Calendar();
//        }
    }
