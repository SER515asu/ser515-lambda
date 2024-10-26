package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.PossibleBlockerWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockersStore;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.Solutions;

public class SpikesListPane extends JFrame implements BaseComponent {
    private JPanel _subPanel;

    public SpikesListPane() {
        this.init();
    }

    @Override
    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Spikes");
        setSize(500, 400);  // Increased window size
    
        JPanel myJPanel = new JPanel(new GridBagLayout());
        myJPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
    
        JLabel enterRole = new JLabel("Enter Your Role: ");
        myJPanel.add(
            enterRole,
            new CustomConstraints(0, 1, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );
    
        // Dropdown for roles
        JComboBox<String> roleDropdown = new JComboBox<>(new String[]{"Developer", "Scrum Master", "Product Owner"});
        myJPanel.add(
            roleDropdown,
            new CustomConstraints(1, 1, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );
    
        // "Verify Role" Button
        JButton verifyButton = new JButton("Verify Role");
        verifyButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Role Verified!");
                }
            }
        );
        myJPanel.add(
            verifyButton,
            new CustomConstraints(1, 2, GridBagConstraints.CENTER, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );
    
        add(myJPanel);   
        setContentPane(myJPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}