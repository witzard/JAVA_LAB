package ProjectGUI;

import java.util.ArrayList;

public class SheetFolder {
    private String sheetName;
    private int sheetCount;
    private double totalBalance;
    private final ArrayList<Sheet> sheetArrayList = new ArrayList<>();

    public String getSheetName() {
        return sheetName;
    }

    public ArrayList<Sheet> getSheetArrayList() {
        return sheetArrayList;
    }

    public int getSheetCount(){
        return sheetCount;
    }
}
