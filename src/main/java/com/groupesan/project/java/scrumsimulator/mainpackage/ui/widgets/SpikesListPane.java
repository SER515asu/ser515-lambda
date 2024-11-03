package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.SimulationUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    
        // "Enter Your Role" Label
        JLabel enterRole = new JLabel("Enter Your Role: ");
        myJPanel.add(
            enterRole,
            new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        // Role Dropdown
        JComboBox<String> roleDropdown = new JComboBox<>(new String[]{"Developer", "Scrum Master", "Product Owner"});
        myJPanel.add(
            roleDropdown,
            new CustomConstraints(1, 0, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        // "Verify Role" Button
        JButton verifyButton = new JButton("Verify Role");

        verifyButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String enteredRole = (String) roleDropdown.getSelectedItem(); 
                    String selectedRole = simulationUI.getUserRole(); 
                    
                    if (enteredRole.equals(selectedRole)) {
                        JOptionPane.showMessageDialog(null, "Role Verified!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Role Verification Failed! Please select the correct role.");
                    }
                }
            }
        );

        myJPanel.add(
            verifyButton,
            new CustomConstraints(1, 1, GridBagConstraints.CENTER, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        // "Choose the Spike to Resolve" Label
        JLabel spikeResolve = new JLabel("Choose the Spike to Resolve: ");
        myJPanel.add(
            spikeResolve,
            new CustomConstraints(0, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        // Spike Dropdown
        JComboBox<String> spikeDropdown = new JComboBox<>(new String[]{"Dummy Spike 1", "Dummy Spike 2", "Dummy Spike 3", "Dummy Spike 4"});
        myJPanel.add(
            spikeDropdown,
            new CustomConstraints(1, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        // "Resolve Spike" Button
        JButton resolveButton = new JButton("Resolve Spike");
        resolveButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Spike Resolved!"));
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