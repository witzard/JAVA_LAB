package ProjectGUI;

import java.io.Serializable;
import java.util.Date;


public class Transaction implements Serializable {
    Date date;
    String type;
    double amount ;
    double balance ;
    String category;
    String note;
    public Transaction(Date date, String type, double amount,double balance, String category, String note) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.category = category;
        this.note = note;
    }
    public Date getDate(){return date;}
    public String getType(){return type;}
    public double getAmount(){return amount;}
    public double getBalance(){return balance;}
    public String getCategory(){return category;}
    public String getNote(){return note;}
}


