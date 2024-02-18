package ru.sbrf.fraud.at.jbplugin.model;

import ru.sbrf.fraud.at.jbplugin.dto.Step;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TestStepModel extends AbstractTableModel {
    private final List<Step> steps;
    private final String[] columnNames = {"Описание", "Ожидаемый результат", "Тестовые данные"};


    public TestStepModel(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public int getRowCount() {
        return steps.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return steps.get(rowIndex).description;
            case 1:
                return steps.get(rowIndex).expectedResult;
            case 2:
                return steps.get(rowIndex).testData;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    // Если вы хотите, чтобы столбцы были редактируемыми, переопределите следующие методы:
    // isCellEditable, setValueAt
}
