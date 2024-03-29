package ui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UIUtils {
    public static final float TEXT_SIZE = 25;
    public static final Dimension TEXT_FIELD_DIMENSION = new Dimension(1000, 50);
    public static final int TEXT_FIELD_COLUMNS = 30;

    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static JLabel createText(String text) {
        JLabel result = new JLabel(text);
        result.setFont(result.getFont().deriveFont(TEXT_SIZE));
        return result;
    }

    public static JTextField createTextField() {
        JTextField textField = new JTextField(TEXT_FIELD_COLUMNS);
        textField.setMaximumSize(TEXT_FIELD_DIMENSION);
        textField.setFont(textField.getFont().deriveFont(TEXT_SIZE));
        return textField;
    }

    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(button.getFont().deriveFont(TEXT_SIZE));
        return button;
    }

    public static JComboBox<String> createComboBox(String[] options) {
        JComboBox<String> combo = new JComboBox<>(options);
        combo.setFont(combo.getFont().deriveFont(TEXT_SIZE));
        return combo;
    }
}
