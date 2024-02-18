package ru.sbrf.fraud.at.jbplugin;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ru.sbrf.fraud.at.jbplugin.dto.Test;

public class TestZephyrEditorProvider implements FileEditorProvider, DumbAware {

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return virtualFile instanceof TestZephyrVirtualFile;
    }

    @Override
    public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        Test test = ((TestZephyrVirtualFile) virtualFile).getTest();
        return new TestZephyrFileEditor(project,test,virtualFile);
    }

    @Override
    public @NotNull @NonNls String getEditorTypeId() {
        return "Zephyr-Test-Editor";
    }

    @Override
    public @NotNull FileEditorPolicy getPolicy() {
        // Определите политику для вашего FileEditor (например, заменить стандартный редактор)
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }


}
