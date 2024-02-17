// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package ru.sbrf.fraud.at.jbplugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.impl.FocusManagerImpl;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.treeStructure.Tree;
import ru.sbrf.fraud.at.jbplugin.dto.Test;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


final class ZephyrToolWindowFactory implements ToolWindowFactory, DumbAware {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        final Test[] testList = getTests();

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Tests from Zephyr");
        createTreeNodeTests(top, testList);

        Tree tree = new Tree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);


        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if (node.getUserObject() instanceof Test test) {
                            String data = test.getFullData();

                            // Добавляем Editor в Editor tabs
                            VirtualFile wrapper = new LightVirtualFile(test.key, data);

                            FileEditorManager.getInstance(project).openFile(wrapper, true);

                        }
                    }
                }
            }
        };
        tree.addMouseListener(mouseListener);


        Content content = ContentFactory.getInstance().createContent(tree, "", false);
        toolWindow.getContentManager().addContent(content);
    }


    private void createTreeNodeTests(DefaultMutableTreeNode top, Test[] tests) {
        //https://docs.oracle.com/javase/tutorial/uiswing/components/tree.html
        DefaultMutableTreeNode folder;
        DefaultMutableTreeNode nodeTest;

        for (Test test : tests) {
            //TODO здесь нужно научится разделять и склеивать папки
            folder = new DefaultMutableTreeNode(test.folder);
            top.add(folder);
            nodeTest = new DefaultMutableTreeNode(test);
            folder.add(nodeTest);

        }


    }

    private Test[] getTests() {
        String text;
        try {
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Sergo\\IdeaProjects\\tool_window\\src\\main\\resources\\tests.json"), StandardCharsets.UTF_8);
            text = String.join(" ", lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper om = new ObjectMapper();
        Test[] testList;
        try {
            testList = om.readValue(text, Test[].class);
        } catch (IOException e) {
            throw new TestNotFoundException(e);
        }
        return testList;
    }

}
