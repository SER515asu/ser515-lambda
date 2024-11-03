package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.CustomSpikeStorage;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.User;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.CustomSpike;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.InvalidInputWindow;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.SimulationSwitchRolePane;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.SimulationUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class SpikesListPane extends JFrame implements BaseComponent {
    private JPanel _subPanel;
    private SimulationUI simulationUI; // Reference to SimulationUI for role verification

    /**
     * Constructor for SpikesListPane. Takes an instance of SimulationUI for role verification.
     *
     * @param simulationUI The main simulation UI instance to access the selected role.
     */
    public SpikesListPane(SimulationUI simulationUI) {
        this.simulationUI = simulationUI; 
        this.init();
    }

    @Override
    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Spikes");
        setSize(500, 400);  // Increased window size
    
        JPanel myJPanel = new JPanel(new GridBagLayout());
        myJPanel.setBorder(new EmptyBorder(20, 20, 20, 20));


        // "Choose the Spike to Resolve" Label
        JLabel spikeResolve = new JLabel("Choose the Spike to Resolve: ");
        myJPanel.add(
            spikeResolve,
            new CustomConstraints(0, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        // Spike Dropdown
        CustomSpikeStorage customSpikes = CustomSpikeStorage.getInstance();
        HashMap <String, CustomSpike> blockerSpike = customSpikes.getCustomSpikeMap();
        ArrayList<String> spikeList = new ArrayList<>();
        if (blockerSpike != null && !blockerSpike.isEmpty()) {
            for (Map.Entry<String, CustomSpike> entry : blockerSpike.entrySet()) {
                CustomSpike spike = entry.getValue();
                String blockerId = entry.getKey();
                spikeList.add(blockerId);
            }
        }

        JComboBox<String> spikeDropdown = new JComboBox<>(spikeList.toArray(new String[0]));
        myJPanel.add(
            spikeDropdown,
            new CustomConstraints(1, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        // "Resolve Spike" Button
        JButton resolveButton = new JButton("Resolve Spike");
        resolveButton.addActionListener(e -> {
            //verify the role
             User user = User.getCurrentUser(null, null);
            if (user != null && user.getRole()!= null &&
                (user.getRole().getName().equals("Scrum Master") || user.getRole().getName().equals("Developer"))) {
                SpikesListPane.this.dispose(); 
                JOptionPane.showMessageDialog(null, "Spike Resolved!");
                CustomSpikeStorage spikeStorage = CustomSpikeStorage.getInstance();
                String blockerId = (String) spikeDropdown.getSelectedItem();
                spikeStorage.deleteCustomSpike(blockerId);
                SpikesListPane window = new SpikesListPane(simulationUI);
                window.setVisible(true);
                dispose();                  
            }
            else {
                InvalidInputWindow invalidInputWindow = new InvalidInputWindow("Switch to Scrum Master or Developer.", "Warning");
                invalidInputWindow.setAlwaysOnTop(true);
                invalidInputWindow.setLocationRelativeTo(SpikesListPane.this);
                invalidInputWindow.setVisible(true);
                SimulationSwitchRolePane switchRolePane = new SimulationSwitchRolePane();
                switchRolePane.setVisible(true);
                    
            } 
            });
        myJPanel.add(
            resolveButton,
            new CustomConstraints(1, 3, GridBagConstraints.CENTER, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );
        
        add(myJPanel);   
        setContentPane(myJPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}