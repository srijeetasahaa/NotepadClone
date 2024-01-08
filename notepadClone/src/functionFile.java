import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class functionFile {
    gui gui;
    String fileName;
    String fileAddress;

    public functionFile(gui gui) {
        this.gui = gui;
    }

    // functions to create a new file when new menu is clicked
    public void newFile() {
        gui.textArea.setText(" ");// erase all the text
        gui.window.setTitle("New");// set the default name of the new file

    }

    // function to open an existing file
    public void newOpen() {
        // to display dialog file
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        // logic to open any file
        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            System.out.println(fileName + fileAddress);// to prove this is working
            gui.window.setTitle(fileName);
        }

        // display the content of the file using buffer reader
        try {
            // address of the file needs to be read
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
            gui.textArea.setText(" ");

            String line = null;
            while ((line = br.readLine()) != null) {
                gui.textArea.append(line + "\n");
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // function to save a file
    public void save() {
        if (fileName == null) {
            saveAs();
        } else {
            try {
                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.textArea.getText());
                fw.close();
                gui.window.setTitle(fileName);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    // function to saveAs a file
    public void saveAs() {
        FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try {
            FileWriter fw = new FileWriter(fileAddress + fileName);
            fw.write(gui.textArea.getText());
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //function to create exit operation
    public void exit () {
        System.exit(0);
    }
}
