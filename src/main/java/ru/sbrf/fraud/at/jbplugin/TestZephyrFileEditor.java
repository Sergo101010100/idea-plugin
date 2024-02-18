package ru.sbrf.fraud.at.jbplugin;

import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.fileEditor.impl.BaseRemoteFileEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.*;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sbrf.fraud.at.jbplugin.dto.Test;
import ru.sbrf.fraud.at.jbplugin.model.TestStepModel;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TestZephyrFileEditor extends BaseRemoteFileEditor {
    private final Test test;
    private final JComponent myPanel;
    private final VirtualFile virtualFile;

    public TestZephyrFileEditor(@NotNull Project project, @NotNull Test test, @NotNull VirtualFile virtualFile) {
        super(project);
        this.test = test;
        this.virtualFile = virtualFile;
        this.myPanel = createEditorPanel();
    }

    private JComponent createEditorPanel() {

        JPanel panel = new JPanel();
        panel.add(JBLabelDecorator.createJBLabelDecorator(test.key), BorderLayout.EAST);
        panel.add(new JBTextField(test.name), BorderLayout.WEST);

        JBTable tableSteps = getStep();
        panel.add(tableSteps, BorderLayout.CENTER);

        JBTabbedPane tabbedPane = new JBTabbedPane();
        tabbedPane.addTab("Общая информация", panel);
        tabbedPane.addTab("Шаги", tableSteps);

        return tabbedPane;
    }

    private JBTable getStep() {
        DefaultTableColumnModel columnModel = new DefaultTableColumnModel();

        TableColumn desc = new TableColumn(0);
        desc.setHeaderValue("Описание");

        TableColumn expected = new TableColumn(1);
        expected.setHeaderValue("Ожидаемый результат");

        TableColumn testData = new TableColumn(2);
        expected.setHeaderValue("Тестовые данные");

        columnModel.addColumn(desc);
        columnModel.addColumn(expected);
        columnModel.addColumn(testData);
        JBTable table = new JBTable(new TestStepModel(test.testScript.steps), columnModel);
        table.setAutoResizeMode(JBTable.AUTO_RESIZE_ALL_COLUMNS);
        return table;
    }

    @Override
    public @NotNull JComponent getComponent() {
        // Возвращает основной компонент редактора
        return myPanel;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        // Возвращает компонент, который должен быть сфокусирован при открытии файла
        return myPanel;
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) @NotNull String getName() {
        return "Zephyr-File-Editor";
    }


    @Override
    protected @Nullable TextEditor getTextEditor() {
        return null;
    }

    @Override
    public VirtualFile getFile() {
        return this.virtualFile;
    }
}
