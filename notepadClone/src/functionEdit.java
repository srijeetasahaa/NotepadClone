import java.awt.Color;

public class functionEdit {
    gui gui;

    public functionEdit(gui gui) {
        this.gui = gui;
    }

    public void undo () {
        gui.um.undo();

    }

    public void redo () {
        gui.um.redo();

    }

}
