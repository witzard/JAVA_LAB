package ProjectGUI;

import java.util.Date;

public class Transaction {
    Date date;
    double income ;
    double expense;
    double balance;
    String note;
    String category;

    public Transaction(Date d, double i, double e, double b, String n, String c) {
        date = d;
        income = i;
        expense = e;
        balance = b;
        note = n;
        category = c;
    }


}


