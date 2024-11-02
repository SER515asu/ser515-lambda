package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SolutionStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.CustomSpikeStorage;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.CustomSpike;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.InvalidInputWindow;

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
        setSize(800, 400); 

        JPanel myJPanel = new JPanel(new GridBagLayout());
        myJPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        SolutionStore solutionStore = SolutionStore.getInstance();
        solutionList = solutionStore.getSolutions(blocker_id);

        _subPanel = new JPanel(new GridBagLayout());
        if (solutionList != null) {
            refreshSolutions();
        }

        JScrollPane scrollPane = new JScrollPane(_subPanel);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 150));

        GridBagConstraints scrollPaneConstraints = new GridBagConstraints();
        scrollPaneConstraints.gridx = 0;
        scrollPaneConstraints.gridy = 0;
        scrollPaneConstraints.gridwidth = GridBagConstraints.REMAINDER;
        scrollPaneConstraints.weightx = 1.0;
        scrollPaneConstraints.weighty = 1.0;
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        scrollPaneConstraints.anchor = GridBagConstraints.NORTH;
        myJPanel.add(scrollPane, scrollPaneConstraints);

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        JButton createSolutionButton = new JButton("Create New Solution");
        createSolutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCreateSolutionDialog();
            }
        });

        JButton createSpikeButton = new JButton("Create New Spike");
        createSpikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCreateSpikeDialog();
            }
        });

        GridBagConstraints solutionButtonConstraints = new GridBagConstraints();
        solutionButtonConstraints.gridx = 0;
        solutionButtonConstraints.gridy = 0;
        solutionButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
        solutionButtonConstraints.insets = new java.awt.Insets(10, 0, 5, 0);
        buttonPanel.add(createSolutionButton, solutionButtonConstraints);

        GridBagConstraints spikeButtonConstraints = new GridBagConstraints();
        spikeButtonConstraints.gridx = 0;
        spikeButtonConstraints.gridy = 1;
        spikeButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
        spikeButtonConstraints.insets = new java.awt.Insets(5, 0, 10, 0);
        buttonPanel.add(createSpikeButton, spikeButtonConstraints);

        GridBagConstraints buttonPanelConstraints = new GridBagConstraints();
        buttonPanelConstraints.gridx = 0;
        buttonPanelConstraints.gridy = 1;
        buttonPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        myJPanel.add(buttonPanel, buttonPanelConstraints);

        setContentPane(myJPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void refreshSolutions() {
        _subPanel.removeAll();
        
        for (int i = 0; i < solutionList.size(); i++) {
            String solution = solutionList.get(i);
            JLabel solutionLabel = new JLabel(solution);
    
            solutionLabel.setFont(solutionLabel.getFont().deriveFont(15f)); 
    
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = i;
            constraints.anchor = GridBagConstraints.WEST;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1.0;
            constraints.insets = new java.awt.Insets(2, 5, 2, 5); 
    
            _subPanel.add(solutionLabel, constraints);
        }
        
        _subPanel.revalidate();
        _subPanel.repaint();
    }

    private void openCreateSolutionDialog() {
        JDialog dialog = new JDialog(this, "Add New Solution", true);
        dialog.setSize(600, 300);
        dialog.setLayout(new GridBagLayout());

        JTextField solutionTextField = new JTextField(30);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newSolution = solutionTextField.getText();
                if (!newSolution.isEmpty()) {
                    SolutionStore solutionStore = SolutionStore.getInstance();
                    solutionStore.addSolution(newSolution, blocker_id);
                    dialog.dispose();
                    new Solutions(blocker_id).setVisible(true);
                }
            }
        });

        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        textFieldConstraints.gridx = 0;
        textFieldConstraints.gridy = 0;
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        textFieldConstraints.insets = new java.awt.Insets(10, 10, 10, 10);

        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 1;
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonConstraints.insets = new java.awt.Insets(10, 10, 10, 10);

        dialog.add(solutionTextField, textFieldConstraints);
        dialog.add(submitButton, buttonConstraints);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void openCreateSpikeDialog() {
        JDialog dialog = new JDialog(this, "Add New Spike", true);
        dialog.setSize(600, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Spike Title:");
        JTextField titleTextField = new JTextField(30);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionTextField = new JTextField(30);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleTextField.getText();
                String description = descriptionTextField.getText();
                if (!title.isEmpty() && !description.isEmpty()) {
                    System.out.println("Spike Title: " + title);
                    System.out.println("Spike Description: " + description);
                    CustomSpike spike = new CustomSpike(title, description);
                    CustomSpikeStorage spikeStorage = CustomSpikeStorage.getInstance();
                    //check if a spike for the blocker already exists
                    if (spikeStorage.getCustomSpike(blocker_id) == null) {
                        spikeStorage.addCustomSpike(blocker_id, spike);
                        dialog.dispose();
                    }
                    else {
                        InvalidInputWindow invalidInputWindow = new InvalidInputWindow("Resolve the previous spike first.", "Warning");
                        invalidInputWindow.setAlwaysOnTop(true);
                        invalidInputWindow.setLocationRelativeTo(dialog);
                        invalidInputWindow.setVisible(true);
                        System.out.println(spikeStorage.getCustomSpike(blocker_id).getSpikeTitle());
                    }
                    
                }
                else {
                    InvalidInputWindow invalidInputWindow = new InvalidInputWindow("Please fill in all fields.", "Warning");
                    invalidInputWindow.setAlwaysOnTop(true);
                    invalidInputWindow.setLocationRelativeTo(dialog);
                    invalidInputWindow.setVisible(true);
                }
            }
        });

        GridBagConstraints titleLabelConstraints = new GridBagConstraints();
        titleLabelConstraints.gridx = 0;
        titleLabelConstraints.gridy = 0;
        titleLabelConstraints.anchor = GridBagConstraints.WEST;
        titleLabelConstraints.insets = new java.awt.Insets(5, 5, 5, 5);

        GridBagConstraints titleTextFieldConstraints = new GridBagConstraints();
        titleTextFieldConstraints.gridx = 1;
        titleTextFieldConstraints.gridy = 0;
        titleTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        titleTextFieldConstraints.insets = new java.awt.Insets(5, 5, 5, 5);

        GridBagConstraints descriptionLabelConstraints = new GridBagConstraints();
        descriptionLabelConstraints.gridx = 0;
        descriptionLabelConstraints.gridy = 1;
        descriptionLabelConstraints.anchor = GridBagConstraints.WEST;
        descriptionLabelConstraints.insets = new java.awt.Insets(5, 5, 5, 5);

        GridBagConstraints descriptionTextFieldConstraints = new GridBagConstraints();
        descriptionTextFieldConstraints.gridx = 1;
        descriptionTextFieldConstraints.gridy = 1;
        descriptionTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        descriptionTextFieldConstraints.insets = new java.awt.Insets(5, 5, 5, 5);

        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 2;
        buttonConstraints.gridwidth = 2;
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonConstraints.insets = new java.awt.Insets(20, 5, 5, 5);

        panel.add(titleLabel, titleLabelConstraints);
        panel.add(titleTextField, titleTextFieldConstraints);
        panel.add(descriptionLabel, descriptionLabelConstraints);
        panel.add(descriptionTextField, descriptionTextFieldConstraints);
        panel.add(submitButton, buttonConstraints);

        dialog.add(panel);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}