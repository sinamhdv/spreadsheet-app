package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import model.ErrorMessage;
import model.Sheet;

// the main menu of the application
public class MainMenu extends JFrame {
    private static final String LOADING_POPUP_GIF = "data/loading.gif";
    private static final String STARTUP_IMAGE = "data/cmatrix.png";
    private static final Dimension WINDOW_DIMENSION = new Dimension(3000, 1700);
    
    private Container window = getContentPane();

    public MainMenu() {
        setupWindow();
        addMenuBar();
        addStartupImage();
        setVisible(true);
    }

    private void setupWindow() {
        setTitle("Sheets");
        setSize(WINDOW_DIMENSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addStartupImage() {
        ImageIcon image = new ImageIcon(STARTUP_IMAGE);
        JLabel label = new JLabel(image);
        window.add(label);
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(fileMenu.getFont().deriveFont(UIUtils.TEXT_SIZE));
        menuBar.add(fileMenu);
        addMenuBarItem(fileMenu, new NewSheetAction(), KeyStroke.getKeyStroke("control N"));
        addMenuBarItem(fileMenu, new OpenSheetAction(), KeyStroke.getKeyStroke("control O"));
        addMenuBarItem(fileMenu, new SaveSheetAction(), KeyStroke.getKeyStroke("control S"));
    }

    private void addMenuBarItem(JMenu menu, AbstractAction action, KeyStroke keyBinding) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setFont(menuItem.getFont().deriveFont(UIUtils.TEXT_SIZE));
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
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                ErrorMessage error = Sheet.load(fileChooser.getSelectedFile().getPath());
                if (error != null) {
                    UIUtils.showError(error.getText());
                    return;
                }
                window.removeAll();
                window.add(new SheetEditorScreen());
                window.revalidate();
            }
        }
    }

    private class SaveSheetAction extends AbstractAction {
        SaveSheetAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Sheet.getCurrentSheet() == null) {
                UIUtils.showError("No sheet open");
                return;
            }
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                ErrorMessage error = Sheet.getCurrentSheet().save(fileChooser.getSelectedFile().getPath());
                if (error != null) {
                    UIUtils.showError(error.getText());
                    return;
                }
                displayLoadingPopup();
            }
        }
    }

    private void displayLoadingPopup() {
        JDialog dialog = new JDialog(this, "Loading", true);
        ImageIcon image = new ImageIcon(LOADING_POPUP_GIF);
        JLabel label = new JLabel(image);
        dialog.add(label);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(image.getIconWidth(), image.getIconHeight());
        dialog.setLocationRelativeTo(this);
        Timer timer = new Timer(1000, (e) -> {
            dialog.setVisible(false);
            dialog.dispose();
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    // EFFECTS: program entrypoint
    public static void main(String[] args) {
        new MainMenu();
    }
}
