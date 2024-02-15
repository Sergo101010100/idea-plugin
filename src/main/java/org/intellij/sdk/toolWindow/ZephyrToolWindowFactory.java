// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.sdk.toolWindow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.intellij.sdk.toolWindow.dto.Test;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


final class ZephyrToolWindowFactory implements ToolWindowFactory, DumbAware {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
//        CalendarToolWindowContent toolWindowContent = null;
//        try {
//            toolWindowContent = new CalendarToolWindowContent(toolWindow);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String text;
        try {
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Sergo\\IdeaProjects\\tool_window\\src\\main\\resources\\tests.json"), StandardCharsets.UTF_8);
            text= String.join(" ", lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper om = new ObjectMapper();
        Test[] testList = new Test[0];
        try {
            testList = om.readValue(text, Test[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JBList<Test> jListTests = new JBList<>(testList);

        Test[] finalTestList = testList;
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = jListTests.locationToIndex(e.getPoint());
                    System.out.println("Double clicked on Item " + index);
                }
            }
        };
        jListTests.addMouseListener(mouseListener);


        Content content = ContentFactory.getInstance().createContent(jListTests, "", false);
        toolWindow.getContentManager().addContent(content);
    }

//    private static class CalendarToolWindowContent {
//
//        private final JPanel contentPanel = new JPanel();
//
//
//        public CalendarToolWindowContent(ToolWindow toolWindow) throws IOException {
//            ObjectMapper om = new ObjectMapper();
//            Test[] testList = om.readValue(String.join("", Files.readAllLines(Path.of("src/main/resources/tests.json"))), Test[].class);
//
//            JBList<Test> jListTests = new JBList<>(testList);
//            contentPanel.add(jListTests);
//
//        }
//
//        public JPanel getContentPanel() {
//            return contentPanel;
//        }
//    }

}
