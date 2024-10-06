package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.User;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;

public class SimulationSwitchRolePane extends JFrame {

    private JRadioButton developerRadioButton;
    private JRadioButton scrumMasterRadioButton;
    private JRadioButton productOwnerRadioButton;
    private ButtonGroup roleButtonGroup;
    private JButton switchButton;

    //no user object was created when the app is opened
    //so it is created here to store switched role


    public SimulationSwitchRolePane() {
        setTitle("Simulation Status");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Roles:");
        panel.add(label);

        developerRadioButton = new JRadioButton("Developer");
        scrumMasterRadioButton = new JRadioButton("Scrum Master");
        productOwnerRadioButton = new JRadioButton("Product Owner");
        roleButtonGroup = new ButtonGroup();
        roleButtonGroup.add(developerRadioButton);
        roleButtonGroup.add(scrumMasterRadioButton);
        roleButtonGroup.add(productOwnerRadioButton);
        panel.add(developerRadioButton);
        panel.add(scrumMasterRadioButton);
        panel.add(productOwnerRadioButton);

        switchButton = new JButton("Switch Role");
        switchButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Logic for join button
                        onSwitchButtonClicked();
                    }
                });

        setLayout(new BorderLayout());
        add(switchButton, BorderLayout.SOUTH);
        add(panel);
    }

    private void onSwitchButtonClicked() {
        if (developerRadioButton.isSelected()) {    
            ScrumRole currentRole = new ScrumRole("Developer");
            //create the user instance or update the role
            User.getCurrentUser("defaultName", currentRole);
            JOptionPane.showMessageDialog(
                    null, "Switched to Developer", "Role Switching", JOptionPane.PLAIN_MESSAGE);
        } else if (scrumMasterRadioButton.isSelected()) {
            ScrumRole currentRole = new ScrumRole("Scrum Master");
            //create the user instance or update the role
            User.getCurrentUser("defaultName", currentRole);
            JOptionPane.showMessageDialog(
                    null, "Switched to Scrum Master", "Role Switching", JOptionPane.PLAIN_MESSAGE);
        } else if (productOwnerRadioButton.isSelected()) {
            ScrumRole currentRole = new ScrumRole("Product Owner");
            //create the user instance or update the role
            User.getCurrentUser("defaultName", currentRole);
            JOptionPane.showMessageDialog(
                    null, "Switched to Product Owner", "Role Switching", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Failed to Switch Role",
                    "Role Switching Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        roleButtonGroup.clearSelection();
        dispose();
        return;
    }
}
