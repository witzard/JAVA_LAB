package ProjectGUI;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveFileSystem {
    SaveFileSystem(ArrayList<Transaction> l, String name) {
        try {
            File file = new File("/home/wittawin/IdeaProjects/JAVA_LAB/src/ProjectGUI/"+ name + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(l);
            oos.close();
            JOptionPane.showMessageDialog(null, "Success!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
