package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.SprintWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox; // Import for JComboBox
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.InvalidInputWindow;
import java.security.SecureRandom;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintStore;

public class SprintListPane extends JFrame implements BaseComponent {
    public SprintListPane() {
        this.init();
        random = new SecureRandom();
    }

    private static int sprintCountSet = 0;
    private SecureRandom random;

    private List<SprintWidget> widgets = new ArrayList<>();

    public void init() {
        SprintStore sprintStore = SprintStore.getInstance();
        int sprintCount = sprintStore.getSprints().size();
        if (sprintCountSet != 0) {
            if (sprintCount < sprintCountSet) {
                int numAdd = sprintCountSet - sprintCount;
                InvalidInputWindow invalidInputWindow = new InvalidInputWindow("You need to add " + numAdd + " more sprints.", "Warning");
                invalidInputWindow.setVisible(true);
            }
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Sprints list");
        setSize(800, 800);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        for (Sprint sprint : SprintStore.getInstance().getSprints()) {
            widgets.add(new SprintWidget(sprint));
        }

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        int i = 0;
        for (SprintWidget widget : widgets) {
            subPanel.add(
                    widget,
                    new CustomConstraints(
                            0,
                            i++,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }

        myJpanel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        JButton newSprintButton = new JButton("New Sprint");
        newSprintButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SprintStore sprintStore = SprintStore.getInstance();
                        int sprintCount = sprintStore.getSprints().size();
                        if (sprintCountSet != 0 && sprintCount >= sprintCountSet) {
                            InvalidInputWindow invalidInputWindow = new InvalidInputWindow("You have enough sprints already.", "Warning");
                            invalidInputWindow.setVisible(true);
                        } else {
                            NewSprintForm form = new NewSprintForm();
                            form.setVisible(true);
                        }
                        dispose();
                    }
                });
        myJpanel.add(
                newSprintButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        JLabel maxNumSprints = new JLabel("Enter the maximum number of sprints:");
        myJpanel.add(
                maxNumSprints,
                new CustomConstraints(
                    0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));

        JTextField maxNumSprintsField = new JTextField();
        myJpanel.add(
            maxNumSprintsField,
            new CustomConstraints(
                        1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel minNumSprints = new JLabel("Enter the minimum number of sprints: ");
        myJpanel.add(
                minNumSprints,
                new CustomConstraints(
                    0, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));

        JTextField minNumSprintsField = new JTextField();
        myJpanel.add(
            minNumSprintsField,
            new CustomConstraints(
                        1, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));
        
        JButton rangeNumSprintsButton = new JButton("Confirm the Range");
        rangeNumSprintsButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String maxDaysInput = maxNumSprintsField.getText();
                        String minDaysInput = minNumSprintsField.getText();
                        int maxDays = 0;
                        int minDays = 0;
                        try {
                            maxDays = Integer.parseInt(maxDaysInput);
                            minDays = Integer.parseInt(minDaysInput);
                        }

                        catch (NumberFormatException ex) {
                            System.out.println("Please enter a valid number");
                            InvalidInputWindow invalidInputWindow = new InvalidInputWindow("Must enter integers.", "Invalid Input");
                            invalidInputWindow.setVisible(true);
                        }

                        if (minDays >= maxDays || minDays <= 0) {
                            InvalidInputWindow invalidInputWindow = new InvalidInputWindow("Enter a valid range.", "Invalid Input");
                            invalidInputWindow.setVisible(true);
                        }
                        else {
                            int randomNum = random.nextInt((maxDays - minDays) + 1) + minDays;
                            sprintCountSet = randomNum;
                            InvalidInputWindow invalidInputWindow = new InvalidInputWindow("Sprint count is set to " + randomNum + ".", "Success");
                            invalidInputWindow.setVisible(true);
                        }
                    }
                }
        );

        myJpanel.add(
                rangeNumSprintsButton,
                new CustomConstraints(2, 3, GridBagConstraints.CENTER, GridBagConstraints.NONE));

        // Add another dropdown to select the number of sprints (options from 1 to 5)
        JLabel numSprintsLabel = new JLabel("Select the number of sprints:");
        myJpanel.add(
                numSprintsLabel,
                new CustomConstraints(0, 4, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));

        String[] sprintOptions = {"1", "2", "3", "4", "5"};
        JComboBox<String> sprintCountDropdown = new JComboBox<>(sprintOptions);
        sprintCountDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sprintCountDropdown.getSelectedItem();
                sprintCountSet = Integer.parseInt(selectedOption);
                InvalidInputWindow invalidInputWindow = new InvalidInputWindow("Sprint count set to " + sprintCountSet + ".", "Success");
                invalidInputWindow.setVisible(true);
            }
        });

        myJpanel.add(
                sprintCountDropdown,
                new CustomConstraints(1, 4, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }
}
