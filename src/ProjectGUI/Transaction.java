package ProjectGUI;

import java.util.Date;

public class Transaction {
    Date date;
    String type;
    double amount ;
    String category;
    String note;

    public Transaction(Date date, String type, double amount, String category, String note) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.note = note;
    }
    public Date getDate(){return date;}
    public String getType(){return type;}
    public double getAmount(){return amount;}
    public String getCategory(){return category;}
    public String getNote(){return note;}
}


