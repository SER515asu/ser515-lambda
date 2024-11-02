package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockersStore;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewBlockerListPane extends JFrame implements BaseComponent {
    private JPanel _subPanel;
    private PossibleBlockersListPane possibleBlockersListPane;
    
    public NewBlockerListPane(PossibleBlockersListPane possibleBlockersListPane) {
        this.possibleBlockersListPane = possibleBlockersListPane;
        this.init();
    }

    @Override
    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Create New Blocker");
        setSize(500, 400);

        JPanel myJPanel = new JPanel(new GridBagLayout());
        myJPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel blockerLabel = new JLabel("Enter Name of Blocker: ");
        myJPanel.add(
            blockerLabel,
            new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        JTextField blockerName = new JTextField();
        myJPanel.add(
            blockerName,
            new CustomConstraints(1, 0, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        JLabel blockerDesc = new JLabel("Enter Blocker Description: ");
        myJPanel.add(
            blockerDesc,
            new CustomConstraints(0, 1, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        JTextField blockerDescText = new JTextField();
        blockerDescText.setColumns(20);
        myJPanel.add(
            blockerDescText,
            new CustomConstraints(1, 1, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        JLabel probabilityLabel = new JLabel("Probability of Occurrence: ");
        myJPanel.add(
            probabilityLabel,
            new CustomConstraints(0, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        String[] probabilities = new String[101];
        for (int i = 0; i <= 100; i++) {
            probabilities[i] = i + "%";
        }
        JComboBox<String> probabilityDropdown = new JComboBox<>(probabilities);
        myJPanel.add(
            probabilityDropdown,
            new CustomConstraints(1, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        JButton submitButton = new JButton("Submit Blocker");
        submitButton.addActionListener(e -> {
            String name = blockerName.getText();
            String description = blockerDescText.getText();
            int probability = probabilityDropdown.getSelectedIndex();
        
            if (!name.isEmpty() && !description.isEmpty()) {
                PossibleBlocker newBlocker = new PossibleBlocker(name, description);
                newBlocker.setProbability(probability);
                PossibleBlockersStore.getInstance().addNewBlocker(newBlocker);
                System.out.println("New blocker added. Total blockers: " + PossibleBlockersStore.getInstance().size());
                possibleBlockersListPane.refreshBlockersList();
        
                JOptionPane.showMessageDialog(this, "Blocker Submitted!");
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both name and description.");
            }
        });

        myJPanel.add(
            submitButton,
            new CustomConstraints(0, 3, GridBagConstraints.CENTER, 2.0, 0.1, GridBagConstraints.HORIZONTAL)
        );

        add(myJPanel);
        setContentPane(myJPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}