import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JPanel;

public class gui implements ActionListener {

    // global objects
    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuView, menuHelp;
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    JMenuItem wordWrap, fontArial, fontCSMS, sans, fontTMR, font8, font12, font16, font20, font24, font28;
    JMenu menuFont, menuFontSize;
    JMenu color;
    JMenuItem color1, color2, color3;
    JMenuItem undo, redo;
    JMenu zoom;
    JMenuItem zoomIn, zoomOut;
    JMenuItem statusBar;
    JLabel status;
    JLabel lineStatus, columnStatus;
    JMenuItem about;

    int ZOOM_IN_FACTOR = 4;
    int ZOOM_OUT_FACTOR = -4;

    boolean wordWrapOn = false;
    boolean statusBarOn = true;

    // creating objects for the other classes
    functionFile file = new functionFile(this);
    functionFormat format = new functionFormat(this);
    functionEdit edit = new functionEdit(this);
    functionView view = new functionView(this);
    functionHelp help = new functionHelp(this);

    // undo redo
    UndoManager um = new UndoManager();

    public gui() {
        // calling all the methods
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createFormatMenu();
        createEditMenu();
        createViewMenu();
        createStatusBar();
        createHelpBar();

        // set default font and size
        format.selectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();
        format.color("White");
        view.status();

        window.setVisible(true);
    }

    // define the create window method
    public void createWindow() {
        window = new JFrame("notepad clone");
        window.setSize(600, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // to provide the text area
    public void createTextArea() {
        textArea = new JTextArea();

        // apply undo redo manager
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {

            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        window.add(scrollPane);// textArea not needed to be added as it is already added in the JScrollPane ()
    }

    // to create the menu bar
    public void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        // file menu
        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        // edit menu
        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        // format menu
        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        // view menu
        menuView = new JMenu("View");
        menuBar.add(menuView);

        // help menu
        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);
    }

    // menu to create menu items for file menu
    public void createFileMenu() {
        // new menu item
        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        // open menu item
        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);

        // save menu item
        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        // saveAs menu item
        iSaveAs = new JMenuItem("SaveAs");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        menuFile.add(iSaveAs);

        // exit menu item
        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);
    }

    // method to create menu items for format menu
    public void createFormatMenu() {
        // word wrap menu item
        wordWrap = new JMenuItem("Word Wrap: Off");
        wordWrap.addActionListener(this);
        wordWrap.setActionCommand("Word Wrap");
        menuFormat.add(wordWrap);

        // font menu
        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        // adding menu items to font menu
        fontArial = new JMenuItem("Arial");
        fontArial.addActionListener(this);
        fontArial.setActionCommand("Arial");
        menuFont.add(fontArial);

        fontCSMS = new JMenuItem("Monospaced");
        fontCSMS.addActionListener(this);
        fontCSMS.setActionCommand("Monospaced");
        menuFont.add(fontCSMS);

        sans = new JMenuItem("SansSerif");
        sans.addActionListener(this);
        sans.setActionCommand("SansSerif");
        menuFont.add(sans);

        fontTMR = new JMenuItem("Times New Roman");
        fontTMR.addActionListener(this);
        fontTMR.setActionCommand("Times New Roman");
        menuFont.add(fontTMR);

        // font size menu
        menuFontSize = new JMenu("Font size");
        menuFormat.add(menuFontSize);

        // adding menu items to font size menu
        font8 = new JMenuItem("8");
        font8.addActionListener(this);
        font8.setActionCommand("8");
        menuFontSize.add(font8);

        font12 = new JMenuItem("12");
        font12.addActionListener(this);
        font12.setActionCommand("12");
        menuFontSize.add(font12);

        font16 = new JMenuItem("16");
        font16.addActionListener(this);
        font16.setActionCommand("16");
        menuFontSize.add(font16);

        font20 = new JMenuItem("20");
        font20.addActionListener(this);
        font20.setActionCommand("20");
        menuFontSize.add(font20);

        font24 = new JMenuItem("24");
        font24.addActionListener(this);
        font24.setActionCommand("24");
        menuFontSize.add(font24);

        font28 = new JMenuItem("28");
        font28.addActionListener(this);
        font28.setActionCommand("28");
        menuFontSize.add(font28);

        // add the color sub menu
        color = new JMenu("Color");
        color.addActionListener(this);
        color.setActionCommand("Color");
        menuFormat.add(color);

        // add the menu items to color sub menu
        color1 = new JMenuItem("White");
        color1.addActionListener(this);
        color1.setActionCommand("White");
        color.add(color1);

        color2 = new JMenuItem("Black");
        color2.addActionListener(this);
        color2.setActionCommand("Black");
        color.add(color2);

        color3 = new JMenuItem("Yellow");
        color3.addActionListener(this);
        color3.setActionCommand("Yellow");
        color.add(color3);

    }

    // method to add items to edit menu
    public void createEditMenu() {
        // add menu items in edit menu
        undo = new JMenuItem("Undo");
        undo.addActionListener(this);
        undo.setActionCommand("Undo");
        menuEdit.add(undo);

        redo = new JMenuItem("Redo");
        redo.addActionListener(this);
        redo.setActionCommand("Redo");
        menuEdit.add(redo);
    }

    // method to add items to view menu
    public void createViewMenu() {
        // add zoom menu to view
        zoom = new JMenu("Zoom");
        zoom.addActionListener(this);
        zoom.setActionCommand("Zoom");
        menuView.add(zoom);

        // add menu items to zoom menu
        zoomIn = new JMenuItem("Zoom In");
        zoomIn.addActionListener(this);
        zoomIn.setActionCommand("Zoom In");
        zoom.add(zoomIn);

        zoomOut = new JMenuItem("Zoom Out");
        zoomOut.addActionListener(this);
        zoomOut.setActionCommand("Zoom Out");
        zoom.add(zoomOut);

        // add status bar to view menu
        statusBar = new JMenuItem("Status Bar: OFF");
        statusBar.addActionListener(this);
        statusBar.setActionCommand("Status Bar");
        menuView.add(statusBar);

    }

    public void createStatusBar() {

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BorderLayout());

        window.add(statusPanel, BorderLayout.SOUTH);

        status = new JLabel("Status bar: OFF");
        status.setBorder(BorderFactory.createEtchedBorder());
        window.add(status, BorderLayout.NORTH);

        lineStatus = new JLabel(" ");
        statusPanel.add(lineStatus, BorderLayout.WEST);

        columnStatus = new JLabel("Line: 1 Column: 1");
        statusPanel.add(columnStatus, BorderLayout.EAST);

        JPanel fillerPanel = new JPanel();
        fillerPanel.setOpaque(false);
        statusPanel.add(fillerPanel, BorderLayout.CENTER);
    }

    public void createHelpBar() {
        about = new JMenuItem("About-Notepad Clone");
        about.addActionListener(this);
        about.setActionCommand("about");
        menuHelp.add(about);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New":
                file.newFile();
                break;
            case "Open":
                file.newOpen();
                break;
            case "SaveAs":
                file.saveAs();
                break;
            case "Save":
                file.save();
                break;
            case "Exit":
                file.exit();
                break;
            case "Word Wrap":
                format.wordWrap();
                break;
            case "Arial":
                format.setFont(command);
                break;
            case "Monospaced":
                format.setFont(command);
                break;
            case "SansSerif":
                format.setFont(command);
                break;
            case "Times New Roman":
                format.setFont(command);
                break;
            case "8":
                format.createFont(8);
                break;
            case "12":
                format.createFont(12);
                break;
            case "16":
                format.createFont(16);
                break;
            case "20":
                format.createFont(20);
                break;
            case "24":
                format.createFont(24);
                break;
            case "28":
                format.createFont(28);
                break;
            case "White":
                format.color(command);
                break;
            case "Black":
                format.color(command);
                break;
            case "Yellow":
                format.color(command);
                break;
            case "Undo":
                edit.undo();
                break;
            case "Redo":
                edit.redo();
                break;
            case "Zoom In":
                // view.actionPerformed(e);
                view.zoomIn(ZOOM_IN_FACTOR);
                break;
            case "Zoom Out":
                // view.actionPerformed(e);
                view.zoomIn(ZOOM_OUT_FACTOR);
                break;
            case "Status Bar":
                view.status();
                break;
            case "about":
                help.aboutDialog();
                break;
        }
    }
}
