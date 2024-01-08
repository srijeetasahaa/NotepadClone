import javax.swing.JOptionPane;

public class functionHelp {
    gui gui;

    public functionHelp(gui gui) {
        this.gui = gui;
    }
    
    public void aboutDialog () {
        String aboutMessage="Notepad Clone\nVersion 1.0\nCreated by Srijeeta Saha";
        JOptionPane.showMessageDialog(gui.window, aboutMessage, "About Notepad", JOptionPane.INFORMATION_MESSAGE);
    }
}
