package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;

public class Solutions extends JFrame implements BaseComponent {
    private JPanel _subPanel;
    private String blocker_id;
    private ArrayList<String> solutionList = new ArrayList<>();

    public Solutions(String blocker_id) {
        this.blocker_id = blocker_id;
        this.init();
    }

    public void addSolution(String solution) {
        solutionList.add(solution);
    }

    @Override
    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Solutions for " + blocker_id);
        setSize(800, 300);

        JPanel myJPanel = new JPanel(new GridBagLayout());
        myJPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        // Dummy data
        solutionList = new ArrayList<>(
                Arrays.asList(
                        "Dummy solution 1",
                        "Dummy solution 2",
                        "Dummy solution 3",
                        "Dummy solution 4"));

        _subPanel = new JPanel(new GridBagLayout());
        refreshSolutions();

        // Scroll pane for the solutions
        JScrollPane scrollPane = new JScrollPane(_subPanel);

        GridBagConstraints scrollPaneConstraints = new GridBagConstraints();
        scrollPaneConstraints.gridx = 0;
        scrollPaneConstraints.gridy = 0;
        scrollPaneConstraints.gridwidth = GridBagConstraints.REMAINDER;
        scrollPaneConstraints.gridheight = GridBagConstraints.RELATIVE;
        scrollPaneConstraints.weightx = 1.0;
        scrollPaneConstraints.weighty = 1.0; // Make the scroll panel take up most of the space
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        scrollPaneConstraints.anchor = GridBagConstraints.NORTH;

        myJPanel.add(scrollPane, scrollPaneConstraints);

        // Add button to create new solutions
        JButton createSolutionButton = new JButton("Create New Solution");
        createSolutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCreateSolutionDialog();
            }
        });

        // Place the button at the bottom with some space in between
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = GridBagConstraints.RELATIVE;
        buttonConstraints.weightx = 1.0;
        buttonConstraints.weighty = 0.0; // Don't expand vertically
        buttonConstraints.insets = new java.awt.Insets(20, 0, 0, 0); // Add some space above the button
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonConstraints.anchor = GridBagConstraints.SOUTH; // Align it to the bottom

        myJPanel.add(createSolutionButton, buttonConstraints);

        setContentPane(myJPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void refreshSolutions() {
        _subPanel.removeAll();
        for (int i = 0; i < solutionList.size(); i++) {
            String solution = solutionList.get(i);
            JLabel solutionLabel = new JLabel(solution);
            _subPanel.add(
                    solutionLabel,
                    new CustomConstraints(
                            0, i, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));
        }
        _subPanel.revalidate();
        _subPanel.repaint();
    }

    private void openCreateSolutionDialog() {
        JDialog dialog = new JDialog(this, "Add New Solution", true);
        dialog.setSize(400, 200); // Increase the size of the dialog
        dialog.setLayout(new GridBagLayout());

        JTextField solutionTextField = new JTextField(30); // Increased size of the entry box
        solutionTextField.setPreferredSize(new java.awt.Dimension(300, 30)); // Set preferred size for the text field

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newSolution = solutionTextField.getText();
                if (!newSolution.isEmpty()) {
                    addSolution(newSolution);
                    refreshSolutions();
                    dialog.dispose();
                }
            }
        });

        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        textFieldConstraints.gridx = 0;
        textFieldConstraints.gridy = 0;
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        textFieldConstraints.insets = new java.awt.Insets(10, 10, 10, 10); // Add padding around the text field

        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 1;
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonConstraints.insets = new java.awt.Insets(10, 10, 10, 10); // Add padding around the button

        dialog.add(solutionTextField, textFieldConstraints);
        dialog.add(submitButton, buttonConstraints);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}