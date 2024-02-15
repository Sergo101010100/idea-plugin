package org.intellij.sdk.toolWindow.dto;

import java.util.List;

public class TestScript {
    public int id;
    public String type;
    public List<Step> steps;

    @Override
    public String toString() {
        return "TestScript{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", steps=" + steps +
                '}';
    }
}