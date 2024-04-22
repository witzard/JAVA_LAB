package ProjectGUI;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveFileSystem {
    SaveFileSystem(ArrayList<Transaction> l, String name) {
        JFileChooser fileChooser = new JFileChooser("/home/wittawin/IdeaProjects/JAVA_LAB/src/ProjectGUI/");
        fileChooser.setDialogTitle("Save File");
        int response = fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null && !selectedFile.getName().isEmpty()) {
                String fileName = selectedFile.getAbsolutePath();
                if (!fileName.endsWith(".dat")) {
                    fileName += ".dat";
                }
                saveFile(l, fileName);
            } else {
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "File name cannot be empty!");
                } else {
                    saveFile(l, name + ".dat");
                }
            }
        }
    }

    private void saveFile(ArrayList<Transaction> l, String fileName) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(l);
            oos.close();
            JOptionPane.showMessageDialog(null, "File saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage());
        }
    }
}
