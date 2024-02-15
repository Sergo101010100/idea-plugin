package org.intellij.sdk.toolWindow.dto;

import java.util.List;

public class Parameters {
    public List<Object> variables;
    public List<Object> entries;

    @Override
    public String toString() {
        return "Parameters{" +
                "variables=" + variables +
                ", entries=" + entries +
                '}';
    }
}
