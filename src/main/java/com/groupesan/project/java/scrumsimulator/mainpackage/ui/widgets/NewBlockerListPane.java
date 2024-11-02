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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewBlockerListPane extends JFrame implements BaseComponent {
    private JPanel _subPanel;

    public NewBlockerListPane() {
        this.init();
    }

    @Override
    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Create New Blocker");
        setSize(500, 400);  // Increased window size
    
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
        submitButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Blocker Submitted!"));
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