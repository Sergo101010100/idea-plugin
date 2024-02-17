package ru.sbrf.fraud.at.jbplugin;

import javax.swing.*;

public class ZephyrTest {
    private JPanel test;
    private JTextField key;
    private JTextField name;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ZephyrTest");
        frame.setContentPane(new ZephyrTest().test);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


}
