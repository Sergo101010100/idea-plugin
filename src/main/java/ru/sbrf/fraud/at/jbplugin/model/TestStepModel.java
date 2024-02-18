package ru.sbrf.fraud.at.jbplugin.model;

import ru.sbrf.fraud.at.jbplugin.dto.Step;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TestStepModel extends AbstractTableModel {
private final List<Step> steps;

    public TestStepModel(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public int getRowCount() {
        return 3;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> steps.get(rowIndex).description;
            case 1 -> steps.get(rowIndex).expectedResult;
            case 2 -> steps.get(rowIndex).testData;
            default -> {
                System.out.println(columnIndex);
                throw new RuntimeException();
            }
        };
    }
}
