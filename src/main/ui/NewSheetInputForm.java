package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Sheet;

// the input form used to create a new sheet
public class NewSheetInputForm extends JPanel {
    private Container window;

    private JTextField nameField;
    private JTextField columnsCountField;

    private List<JTextField> columnNameFields;
    private List<JComboBox<String>> columnDataTypeBox;

    // EFFECTS: construct a new object with the given parent window.
    //          adds the initial text fields and buttons of the form.
    public NewSheetInputForm(Container window) {
        super();
        this.window = window;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(UIUtils.createText("Sheet name: "));
        nameField = UIUtils.createTextField();
        add(nameField);
        add(UIUtils.createText("Columns count: "));
        columnsCountField = UIUtils.createTextField();
        add(columnsCountField);
        JButton continueButton = UIUtils.createButton("Continue");
        continueButton.addActionListener(this::handleContinueButton);
        add(continueButton);
    }

    // MODIFIES: this
    // EFFECTS: handle pressing of Continue button by removing old GUI elements
    //          and loading multiple text fields to get input for table schema.
    private void handleContinueButton(ActionEvent e) {
        try {
            int columns = Integer.parseInt(columnsCountField.getText());
            if (columns <= 0) {
                throw new NumberFormatException();
            }
            showSchemaInputFields(columns);
        } catch (NumberFormatException ex) {
            UIUtils.showError("Invalid column count");
        }
    }

    // MODIFIES: this
    // EFFECTS: show input fields required to get info about table schema
    private void showSchemaInputFields(int colCount) {
        removeAll();
        add(UIUtils.createText("Select column name and types:"));
        columnNameFields = new ArrayList<>();
        columnDataTypeBox = new ArrayList<>();
        for (int i = 0; i < colCount; i++) {
            columnNameFields.add(UIUtils.createTextField());
            columnDataTypeBox.add(UIUtils.createComboBox(new String[] {"NUMBER", "STRING"}));
            JPanel inputContainer = new JPanel();
            inputContainer.setMaximumSize(UIUtils.TEXT_FIELD_DIMENSION);
            inputContainer.add(columnNameFields.get(i));
            inputContainer.add(columnDataTypeBox.get(i));
            add(inputContainer);
        }
        JButton createButton = UIUtils.createButton("Create");
        createButton.addActionListener(this::handleCreateButton);
        add(createButton);
        revalidate();
        repaint();
    }

    // MODIFIES: this, Sheet.getCurrentSheet()
    // EFFECTS: creates a new sheet with the given information after the Create button is pressed
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
        window.add(new SheetEditorScreen());
        window.revalidate();
    }
}
