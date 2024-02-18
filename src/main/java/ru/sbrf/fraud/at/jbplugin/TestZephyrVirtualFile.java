package ru.sbrf.fraud.at.jbplugin;

import com.intellij.testFramework.LightVirtualFile;
import ru.sbrf.fraud.at.jbplugin.dto.Test;

public class TestZephyrVirtualFile extends LightVirtualFile {
    private final Test test;

    public TestZephyrVirtualFile(Test test) {
        super(test.key);
        this.test = test;
    }

    public Test getTest() {
        return test;
    }
}
