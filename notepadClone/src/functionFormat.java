import java.awt.Color;
import java.awt.Font;

public class functionFormat {
    gui gui;
    Font arial, comicSansMs, timesNewRoman;
    public String selectedFont;
    Font sans;

    public functionFormat(gui gui) {
        this.gui = gui;

    }

    // word wrap method
    public void wordWrap() {
        if (gui.wordWrapOn == false) {
            gui.wordWrapOn = true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
            gui.wordWrap.setText("Word wrap: On");

        } else {
            gui.wordWrapOn = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.wordWrap.setText("Word wrap: Off");
        }
    }

    int sizeOfFont;

    // method for font type
    public void createFont(int fontSize) {
        sizeOfFont=fontSize;
        arial = new Font("Arial", Font.PLAIN, fontSize);
        comicSansMs = new Font("Monospaced", Font.PLAIN, fontSize);
        timesNewRoman = new Font("Times New Roman", Font.PLAIN, fontSize);
        sans=new Font("SansSerif",Font.PLAIN,fontSize);


        setFont(selectedFont);
    }

    // set font method
    public void setFont(String font) {
        selectedFont = font;
        switch (selectedFont) {
            case "Arial":
                gui.textArea.setFont(arial);
                break;
            case "Monospaced":
                gui.textArea.setFont(comicSansMs);
                break;
                case "SansSerif":
                gui.textArea.setFont(sans);
                break;
            case "Times New Roman":
                gui.textArea.setFont(timesNewRoman);
                break;
        }
        gui.textArea.repaint();
    }

    public void color(String command) {
        switch (command) {
            case "White":
                gui.window.getContentPane().setBackground(Color.white);
                gui.textArea.setBackground(Color.white);
                gui.textArea.setForeground(Color.black);
                break;
            case "Black":
                gui.window.getContentPane().setBackground(Color.black);
                gui.textArea.setBackground(Color.black);
                gui.textArea.setForeground(Color.white);
                break;
            case "Yellow":
                gui.window.getContentPane().setBackground(Color.yellow);
                gui.textArea.setBackground(Color.yellow);
                gui.textArea.setForeground(Color.white);
                break;
        }
    }

    public void zoomOut(int zoomOutFactor) {
        createFont(sizeOfFont-zoomOutFactor);
    }

    public void zoomIn(int zoomInFactor) {
        createFont(sizeOfFont+zoomInFactor);
    }

}
