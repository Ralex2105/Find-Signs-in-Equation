package lostSigns.view;


import lostSigns.controller.StartController;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class StartView {
    public StartView() {
        StartController startController = new StartController();
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField example = new JTextField(12);

        JButton decision = new JButton("Solve");
        panel.add(decision);
        startController.toStart(decision, example);

        example.setToolTipText("Пример");
        panel.add(example);

        JButton help = new JButton("Help");
        panel.add(help);
        startController.toHelp(help);

        JFrame startFrame = new JFrame("Lost signs");
        startFrame.setContentPane(panel);
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startFrame.setSize(400, 72);
        startFrame.setVisible(true);
    }
}