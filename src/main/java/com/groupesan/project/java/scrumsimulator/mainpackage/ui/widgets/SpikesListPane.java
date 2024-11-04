package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.CustomSpikeStorage;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.User;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.CustomSpike;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.InvalidInputWindow;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.SimulationSwitchRolePane;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.SimulationUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class SpikesListPane extends JFrame implements BaseComponent {
    private SimulationUI simulationUI;
    private boolean isRoleVerified = false;

    public SpikesListPane(SimulationUI simulationUI) {
        this.simulationUI = simulationUI;
        this.init();
    }

    private GridBagConstraints createConstraints(int gridx, int gridy, int anchor, double weightx, double weighty, int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = fill;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }

    @Override
    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Spikes");
        setSize(500, 400);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Status Label (Row 0)
        JLabel statusLabel = new JLabel("⚠️ Please verify your role first");
        statusLabel.setForeground(Color.RED);
        GridBagConstraints statusConstraints = createConstraints(0, 0, GridBagConstraints.CENTER, 1.0, 0.0, GridBagConstraints.HORIZONTAL);
        statusConstraints.gridwidth = 2;
        mainPanel.add(statusLabel, statusConstraints);

        // Role Selection Area (Row 1)
        JLabel roleLabel = new JLabel("Select Role: ");
        mainPanel.add(roleLabel, createConstraints(0, 1, GridBagConstraints.WEST, 0.0, 0.0, GridBagConstraints.NONE));

        JComboBox<String> roleDropdown = new JComboBox<>(new String[]{"Developer", "Scrum Master", "Product Owner"});
        mainPanel.add(roleDropdown, createConstraints(1, 1, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        // Verify Role Button (Row 2)
        JButton verifyButton = new JButton("Verify Role");
        GridBagConstraints verifyButtonConstraints = createConstraints(0, 2, GridBagConstraints.CENTER, 1.0, 0.0, GridBagConstraints.HORIZONTAL);
        verifyButtonConstraints.gridwidth = 2;
        mainPanel.add(verifyButton, verifyButtonConstraints);

        // Spike Selection Area (Row 3)
        JLabel spikeLabel = new JLabel("Choose the Spike to Resolve: ");
        mainPanel.add(spikeLabel, createConstraints(0, 3, GridBagConstraints.WEST, 0.0, 0.0, GridBagConstraints.NONE));

        // Populate spike list
        CustomSpikeStorage customSpikes = CustomSpikeStorage.getInstance();
        HashMap<String, CustomSpike> blockerSpike = customSpikes.getCustomSpikeMap();
        ArrayList<String> spikeList = new ArrayList<>();
        if (blockerSpike != null && !blockerSpike.isEmpty()) {
            for (Map.Entry<String, CustomSpike> entry : blockerSpike.entrySet()) {
                spikeList.add(entry.getKey());
            }
        }

        JComboBox<String> spikeDropdown = new JComboBox<>(spikeList.toArray(new String[0]));
        mainPanel.add(spikeDropdown, createConstraints(1, 3, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        // Resolve Button (Row 4)
        JButton resolveButton = new JButton("Resolve Spike");
        resolveButton.setEnabled(false); // Initially disabled
        GridBagConstraints resolveButtonConstraints = createConstraints(0, 4, GridBagConstraints.CENTER, 1.0, 0.0, GridBagConstraints.HORIZONTAL);
        resolveButtonConstraints.gridwidth = 2;
        mainPanel.add(resolveButton, resolveButtonConstraints);

        // Add vertical spacing
        GridBagConstraints spacerConstraints = createConstraints(0, 5, GridBagConstraints.CENTER, 1.0, 1.0, GridBagConstraints.VERTICAL);
        spacerConstraints.gridwidth = 2;
        mainPanel.add(Box.createVerticalStrut(10), spacerConstraints);

        // Action Listeners
        verifyButton.addActionListener(e -> {
            String enteredRole = (String) roleDropdown.getSelectedItem();
            String selectedRole = simulationUI.getUserRole();

            if (enteredRole.equals(selectedRole)) {
                isRoleVerified = true;
                statusLabel.setText("✅ Role verified successfully");
                statusLabel.setForeground(new Color(0, 128, 0)); // Dark green
                resolveButton.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Role Verified!");
            } else {
                isRoleVerified = false;
                statusLabel.setText("⚠️ Role verification failed - please try again");
                statusLabel.setForeground(Color.RED);
                resolveButton.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Role Verification Failed! Please select the correct role.");
            }
        });

        resolveButton.addActionListener(e -> {
            if (!isRoleVerified) {
                JOptionPane.showMessageDialog(null, "Please verify your role first!");
                return;
            }

            User user = User.getCurrentUser(null, null);
            if (user != null && user.getRole() != null &&
                (user.getRole().getName().equals("Scrum Master") || user.getRole().getName().equals("Developer"))) {
                
                CustomSpikeStorage spikeStorage = CustomSpikeStorage.getInstance();
                String blockerId = (String) spikeDropdown.getSelectedItem();
                spikeStorage.deleteCustomSpike(blockerId);
                JOptionPane.showMessageDialog(null, "Spike Resolved!");
                
                // Refresh the window
                SpikesListPane newWindow = new SpikesListPane(simulationUI);
                newWindow.setVisible(true);
                dispose();
            } else {
                InvalidInputWindow invalidInputWindow = new InvalidInputWindow("Switch to Scrum Master or Developer.", "Warning");
                invalidInputWindow.setAlwaysOnTop(true);
                invalidInputWindow.setLocationRelativeTo(this);
                invalidInputWindow.setVisible(true);
                
                SimulationSwitchRolePane switchRolePane = new SimulationSwitchRolePane();
                switchRolePane.setVisible(true);
            }
        });

        // Add role change listener to reset verification
        roleDropdown.addActionListener(e -> {
            isRoleVerified = false;
            statusLabel.setText("⚠️ Please verify your role first");
            statusLabel.setForeground(Color.RED);
            resolveButton.setEnabled(false);
        });

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}