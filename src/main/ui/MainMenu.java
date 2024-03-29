package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

// the main menu of the application
public class MainMenu extends JFrame {
    private static final Dimension WINDOW_DIMENSION = new Dimension(3000, 1700);
    private static final float MENU_FONT_SIZE = 25;
    
    private Container window = getContentPane();

    public MainMenu() {
        setupWindow();
        addMenuBar();
        setVisible(true);
    }

    private void setupWindow() {
        setTitle("Sheets");
        setSize(WINDOW_DIMENSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(fileMenu.getFont().deriveFont(MENU_FONT_SIZE));
        menuBar.add(fileMenu);
        addMenuBarItem(fileMenu, new NewSheetAction(), KeyStroke.getKeyStroke("control N"));
        addMenuBarItem(fileMenu, new OpenSheetAction(), KeyStroke.getKeyStroke("control O"));
        addMenuBarItem(fileMenu, new SaveSheetAction(), KeyStroke.getKeyStroke("control S"));
    }

    private void addMenuBarItem(JMenu menu, AbstractAction action, KeyStroke keyBinding) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setFont(menuItem.getFont().deriveFont(MENU_FONT_SIZE));
        menuItem.setAccelerator(keyBinding);
        menu.add(menuItem);
    }

    private class NewSheetAction extends AbstractAction {
        NewSheetAction() {
            super("New");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            window.removeAll();
            window.add(new NewSheetInputForm(window));
            window.revalidate();
        }
    }

    private class OpenSheetAction extends AbstractAction {
        OpenSheetAction() {
            super("Open");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("OPEN");
        }
    }

    private class SaveSheetAction extends AbstractAction {
        SaveSheetAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("SAVE");
        }
    }

    // EFFECTS: program entrypoint
    public static void main(String[] args) {
        new MainMenu();
    }
}
