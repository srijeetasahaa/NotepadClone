import java.awt.event.ActionEvent;

public class functionView {
    gui gui;

    

    public functionView(gui gui) {
        this.gui = gui;
    }
    
    public void zoomIn (int ZOOM_IN_FACTOR) {
        gui.format.zoomIn (ZOOM_IN_FACTOR);
        updateLineColumnStatus();
        //functionFormat.createFont(sizeOfFont+zoomOutFactor);
    }

    public void zoomOut (int ZOOM_OUT_FACTOR) {
        gui.format.zoomOut (ZOOM_OUT_FACTOR);
        updateLineColumnStatus();
    }

    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        switch (command) {
            case "Zoom In":
                zoomIn(gui.ZOOM_IN_FACTOR);
                break;
        
            case "Zoom Out":
            zoomOut(gui.ZOOM_OUT_FACTOR);
                break;
        }
    }

    public void status() {
        if (gui.statusBarOn==false) {
            gui.statusBarOn=true;
            gui.statusBar.setText("Status bar: ON");
            gui.status.setVisible(true);
            updateLineColumnStatus ();
            
        } else {
            gui.statusBarOn=false;
            gui.statusBar.setText("Status bar: OFF");
            gui.status.setVisible(false);
            gui.lineStatus.setText(" ");
            gui.columnStatus.setText(" ");
        }
    }

    public void updateLineColumnStatus () {
        int position=gui.textArea.getCaretPosition();
        int lineNo;
        try {
            lineNo=gui.textArea.getLineOfOffset(position)+1;
            int columnNo=position-gui.textArea.getLineEndOffset(lineNo-1)+1;
            gui.lineStatus.setText("Line: "+lineNo);
            gui.columnStatus.setText("Column: "+columnNo);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
