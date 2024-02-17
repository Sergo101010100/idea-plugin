package ru.sbrf.fraud.at.jbplugin.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Test {

    public String owner;
    public String updatedBy;
    public Map<String, String> customFields;
    public Date updatedOn;
    public String precondition;
    public int majorVersion;
    public String priority;
    public Date createdOn;
    public List<String> labels;
    public String objective;
    public String component;
    public String projectKey;
    public String folder;
    public String createdBy;
    public boolean latestVersion;
    public TestScript testScript;
    public String lastTestResultStatus;
    public String name;
    public Parameters parameters;
    public String key;
    public String status;


    @Override
    public String toString() {
        return key + " " + name;
    }


    public String getFullData() {
        return "Test{" +
                "owner='" + owner + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", customFields=" + customFields +
                ", updatedOn=" + updatedOn +
                ", precondition='" + precondition + '\'' +
                ", majorVersion=" + majorVersion +
                ", priority='" + priority + '\'' +
                ", createdOn=" + createdOn +
                ", labels=" + labels +
                ", objective='" + objective + '\'' +
                ", component='" + component + '\'' +
                ", projectKey='" + projectKey + '\'' +
                ", folder='" + folder + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", latestVersion=" + latestVersion +
                ", testScript=" + testScript +
                ", lastTestResultStatus='" + lastTestResultStatus + '\'' +
                ", name='" + name + '\'' +
                ", parameters=" + parameters +
                ", key='" + key + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
