package ru.sbrf.fraud.at.jbplugin.dto;

import java.util.List;

public class Step{
    public List<Attachment> attachments;
    public String expectedResult;
    public String description;
    public int index;
    public int id;
    public String testData;

    @Override
    public String toString() {
        return "Step{" +
                "attachments=" + attachments +
                ", expectedResult='" + expectedResult + '\'' +
                ", description='" + description + '\'' +
                ", index=" + index +
                ", id=" + id +
                ", testData='" + testData + '\'' +
                '}';
    }
}