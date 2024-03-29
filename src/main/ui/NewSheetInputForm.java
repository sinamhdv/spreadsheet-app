package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Sheet;

public class NewSheetInputForm extends JPanel {
    private static final float TEXT_SIZE = 25;
    private static final Dimension TEXT_FIELD_DIMENSION = new Dimension(1000, 50);
    private static final int TEXT_FIELD_COLUMNS = 30;

    private Container window;

    private JTextField nameField;
    private JTextField columnsCountField;

    private List<JTextField> columnNameFields;
    private List<JComboBox<String>> columnDataTypeBox;

    public NewSheetInputForm(Container window) {
        super();
        this.window = window;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(createText("Sheet name: "));
        nameField = createTextField();
        add(nameField);
        add(createText("Columns count: "));
        columnsCountField = createTextField();
        add(columnsCountField);
        JButton continueButton = createButton("Continue");
        continueButton.addActionListener(this::handleContinueButton);
        add(continueButton);
    }

    private JLabel createText(String text) {
        JLabel result = new JLabel(text);
        result.setFont(result.getFont().deriveFont(TEXT_SIZE));
        return result;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(TEXT_FIELD_COLUMNS);
        textField.setMaximumSize(TEXT_FIELD_DIMENSION);
        textField.setFont(textField.getFont().deriveFont(TEXT_SIZE));
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(button.getFont().deriveFont(TEXT_SIZE));
        return button;
    }

    private JComboBox<String> createComboBox(String[] options) {
        JComboBox<String> combo = new JComboBox<>(options);
        combo.setFont(combo.getFont().deriveFont(TEXT_SIZE));
        return combo;
    }

    private void handleContinueButton(ActionEvent e) {
        try {
            int columns = Integer.parseInt(columnsCountField.getText());
            if (columns <= 0) {
                throw new NumberFormatException();
            }
            showSchemaInputFields(columns);
        } catch (NumberFormatException ex) {
            showError("Invalid column count");
        }
    }

    private static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSchemaInputFields(int colCount) {
        removeAll();
        add(createText("Select column name and types:"));
        columnNameFields = new ArrayList<>();
        columnDataTypeBox = new ArrayList<>();
        for (int i = 0; i < colCount; i++) {
            columnNameFields.add(createTextField());
            columnDataTypeBox.add(createComboBox(new String[] {"NUMBER", "STRING"}));
            JPanel inputContainer = new JPanel();
            inputContainer.setMaximumSize(TEXT_FIELD_DIMENSION);
            inputContainer.add(columnNameFields.get(i));
            inputContainer.add(columnDataTypeBox.get(i));
            add(inputContainer);
        }
        JButton createButton = createButton("Create");
        createButton.addActionListener(this::handleCreateButton);
        add(createButton);
        revalidate();
        repaint();
    }

    private void handleCreateButton(ActionEvent e) {
        String name = nameField.getText();
        int count = Integer.parseInt(columnsCountField.getText());
        String[] schema = new String[count];
        System.out.println(name);
        for (int i = 0; i < count; i++) {
            schema[i] = columnNameFields.get(i).getText() + ":" + columnDataTypeBox.get(i).getSelectedItem();
            System.out.println(schema[i]);
        }
        Sheet.create(name, schema);
        window.removeAll();
        window.add(new SheetEditorScreen(window));
        window.revalidate();
    }
}
