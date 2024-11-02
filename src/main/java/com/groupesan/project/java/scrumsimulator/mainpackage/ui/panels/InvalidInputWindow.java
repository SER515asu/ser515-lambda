package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InvalidInputWindow  extends JDialog{
    public InvalidInputWindow(String message, String title) {
        setTitle(title);
        setSize(300, 100);
        setLayout(new BorderLayout());
        //center the window
        setLocationRelativeTo(null);
        setModal(true);

        JLabel label = new JLabel(message);
        JButton ok = new JButton("OK");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(label, BorderLayout.CENTER);
        add(ok, BorderLayout.SOUTH);

    }

    
}
