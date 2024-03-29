package ui;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Cell;
import model.Column;
import model.Row;
import model.Sheet;

public class SheetEditorScreen extends JPanel {
    private Container window;

    private DefaultTableModel table = new DefaultTableModel();

    public SheetEditorScreen(Container window) {
        super();
        this.window = window;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addEditorControls();
        setTableSchema();
        refreshSheetDisplay();
    }

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
        // window.revalidate();
        // window.repaint();
    }

    private void setTableSchema() {
        for (Column column : Sheet.getCurrentSheet().getSchema()) {
            table.addColumn(column.getName());
        }
    }

    private void addEditorControls() {
        add(UIUtils.createText(Sheet.getCurrentSheet().getName()));
        JPanel editorControlsPanel = new JPanel();
        editorControlsPanel.setMaximumSize(UIUtils.TEXT_FIELD_DIMENSION);
        addControlsPanelParts(editorControlsPanel);
        add(editorControlsPanel);
        JTable jtable = new JTable(table);
        jtable.setFont(jtable.getFont().deriveFont(UIUtils.TEXT_SIZE));
        add(jtable);
    }

    private void addControlsPanelParts(JPanel controlsPanel) {

    }
}
