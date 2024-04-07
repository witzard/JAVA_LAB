package ProjectGUI;
import java.util.ArrayList;
public class Sheet {
    String sheetName;
    double balance = 0 ;
    public ArrayList<Transaction> transactionList = new ArrayList<>();

    public Object[] getTransaction(){
        Sheet s = new Sheet();
        return new Object[]{s.transactionList.getLast()};
    }

    public void updateBalance(double income,double expense){
        balance = balance + income - expense;
    }
    public void deleteTransaction(){

    }

}
