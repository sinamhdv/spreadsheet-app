package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewSheetInputForm extends JPanel {
    private static final float TEXT_SIZE = 25;
    private static final Dimension TEXT_FIELD_DIMENSION = new Dimension(1000, 50);

    private JTextField nameField;
    private JTextField columnsCountField;
    private JButton continueButton;

    public NewSheetInputForm() {
        super();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(createText("Sheet name: "));
        nameField = createTextField();
        add(nameField);
        add(createText("Columns count: "));
        columnsCountField = createTextField();
        add(columnsCountField);
        continueButton = createButton("Continue");
        continueButton.addActionListener(this::handleContinueButton);
        add(continueButton);
    }

    private JLabel createText(String text) {
        JLabel result = new JLabel(text);
        result.setFont(result.getFont().deriveFont(TEXT_SIZE));
        return result;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setMaximumSize(TEXT_FIELD_DIMENSION);
        textField.setFont(textField.getFont().deriveFont(TEXT_SIZE));
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(button.getFont().deriveFont(TEXT_SIZE));
        return button;
    }

    private void handleContinueButton(ActionEvent e) {
        try {
            int columns = Integer.parseInt(columnsCountField.getText());
        } catch (NumberFormatException ex) {
            showError("Invalid column count");
        }
        removeAll();
    }

    private static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
