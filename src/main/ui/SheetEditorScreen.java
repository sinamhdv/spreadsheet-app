package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Cell;
import model.Column;
import model.Row;
import model.Sheet;

// the application screen where the sheets are displayed and edited
public class SheetEditorScreen extends JPanel {
    private static final int TABLE_ROW_HEIGHT = 40;

    private DefaultTableModel table = new DefaultTableModel();

    private List<JTextField> addingFields = new ArrayList<>();
    private JComboBox<String> selectedColumnCombo;

    // EFFECTS: construct a new SheetEditorScreen object and add necessary GUI parts to it
    public SheetEditorScreen() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addEditorControls();
        setTableSchema();
        refreshSheetDisplay();
    }

    // MODIFIES: this
    // EFFECTS: refresh the GUI sheet display by removing all rows from the table
    //          and adding all rows to the table again
    private void refreshSheetDisplay() {
        table.setRowCount(0);
        for (Row row : Sheet.getCurrentSheet().getRows()) {
            int colIndex = 0;
            String[] rowData = new String[row.getCells().size()];
            for (Cell cell : row.getCells()) {
                rowData[colIndex++] = cell.toString();
            }
            table.addRow(rowData);
        }
    }

    // MODIFIES: this
    // EFFECTS: set the column names for the graphical table
    private void setTableSchema() {
        for (Column column : Sheet.getCurrentSheet().getSchema()) {
            table.addColumn(column.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: add control buttons for the editor screen
    private void addEditorControls() {
        add(UIUtils.createText(Sheet.getCurrentSheet().getName()));
        JPanel editorControlsPanel = new JPanel();
        editorControlsPanel.setMaximumSize(UIUtils.TEXT_FIELD_DIMENSION);
        addControlsPanelParts(editorControlsPanel);
        add(editorControlsPanel);
        
        JPanel addingPanel = new JPanel();
        addingPanel.setLayout(new GridLayout(1, Sheet.getCurrentSheet().getSchema().size() + 1));
        addingPanel.setMaximumSize(UIUtils.TEXT_FIELD_DIMENSION);
        addAddingPanelParts(addingPanel);
        add(addingPanel);

        JTable jtable = new JTable(table) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jtable.setRowHeight(TABLE_ROW_HEIGHT);
        jtable.setFont(jtable.getFont().deriveFont(UIUtils.TEXT_SIZE));
        jtable.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(jtable));
    }

    // MODIFIES: this
    // EFFECTS: add parts to the control panel of the editor (sort button, combo box, etc.)
    private void addControlsPanelParts(JPanel controlsPanel) {
        List<Column> columns = Sheet.getCurrentSheet().getSchema();
        String[] colNames = new String[columns.size()];
        int index = 0;
        for (Column column : columns) {
            colNames[index++] = column.getName();
        }
        selectedColumnCombo = UIUtils.createComboBox(colNames);
        controlsPanel.add(selectedColumnCombo);
        JButton sortButton = UIUtils.createButton("Sort");
        sortButton.addActionListener(this::handleSortButton);
        controlsPanel.add(sortButton);
    }
    
    // MODIFIES: this
    // EFFECTS: add text fields and a button to the row insertion panel
    private void addAddingPanelParts(JPanel addingPanel) {
        int count = Sheet.getCurrentSheet().getSchema().size();
        for (int i = 0; i < count; i++) {
            addingFields.add(UIUtils.createTextField());
            addingPanel.add(addingFields.get(i));
        }
        JButton addButton = UIUtils.createButton("Add");
        addButton.addActionListener(this::handleAddButton);
        addingPanel.add(addButton);
    }

    // MODIFIES: this, Sheet.getCurrentSheet()
    // EFFECTS: handle the pressing of Sort button. Sort the sheet and refresh GUI display
    private void handleSortButton(ActionEvent e) {
        Sheet.getCurrentSheet().sortBy((String)selectedColumnCombo.getSelectedItem());
        refreshSheetDisplay();
    }

    // MODIFIES: this, Sheet.getCurrentSheet()
    // EFFECTS: handle the pressing of Add button. Add a new row to the table and refresh GUI
    private void handleAddButton(ActionEvent e) {
        String[] addingData = new String[addingFields.size()];
        for (int i = 0; i < addingFields.size(); i++) {
            addingData[i] = addingFields.get(i).getText();
        }
        Sheet.getCurrentSheet().insertRow(addingData);
        refreshSheetDisplay();
    }
}
