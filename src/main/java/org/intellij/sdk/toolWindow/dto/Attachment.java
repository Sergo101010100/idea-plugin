package org.intellij.sdk.toolWindow.dto;

import java.util.Date;



public class Attachment {
    public String fileName;
    public int fileSize;
    public String name;
    public int id;
    public Date createdOn;
    public int projectId;
    public String userKey;

    @Override
    public String toString() {
        return "Attachment{" +
                "fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", createdOn=" + createdOn +
                ", projectId=" + projectId +
                ", userKey='" + userKey + '\'' +
                '}';
    }
}

