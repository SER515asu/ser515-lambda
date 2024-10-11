package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.User;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class NewSprintForm extends JFrame implements BaseComponent {
    JTextField nameField = new JTextField();
    JTextArea descArea = new JTextArea();
    SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(5, 1, 999999, 1);
    JSpinner sprintDays = new JSpinner(spinnerNumberModel);

    JSpinner startDaySpinner, startMonthSpinner, startYearSpinner;
    JSpinner endDaySpinner, endMonthSpinner, endYearSpinner;

    DefaultListModel<String> listModel;
    JList<String> usList;

    // Dropdown for selecting the number of sprints
    JComboBox<String> sprintCountDropdown;

    public NewSprintForm() {
        this.init();
    }

    public void init() {
        setTitle("New Sprint");
        setSize(500, 400);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        BorderLayout myBorderLayout = new BorderLayout();
        setLayout(myBorderLayout);

        JLabel nameLabel = new JLabel("Name:");
        myJpanel.add(
                nameLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                nameField,
                new CustomConstraints(
                        1, 0, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel descLabel = new JLabel("Description:");
        myJpanel.add(
                descLabel,
                new CustomConstraints(
                        0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                new JScrollPane(descArea),
                new CustomConstraints(
                        1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

        JLabel pointsLabel = new JLabel("Length (Days):");
        myJpanel.add(
                pointsLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                sprintDays,
                new CustomConstraints(
                        1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.WEST));

        // Start Date
        JLabel startDateLabel = new JLabel("Start Date:");
        myJpanel.add(
                startDateLabel,
                new CustomConstraints(
                        0, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));

        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        startDaySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        startMonthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        startYearSpinner = new JSpinner(new SpinnerNumberModel(2023, 2000, 2100, 1));
        startDatePanel.add(new JLabel("Day:"));
        startDatePanel.add(startDaySpinner);
        startDatePanel.add(new JLabel("Month:"));
        startDatePanel.add(startMonthSpinner);
        startDatePanel.add(new JLabel("Year:"));
        startDatePanel.add(startYearSpinner);

        myJpanel.add(
                startDatePanel,
                new CustomConstraints(
                        1, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        // End Date
        JLabel endDateLabel = new JLabel("End Date:");
        myJpanel.add(
                endDateLabel,
                new CustomConstraints(
                        0, 4, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));

        JPanel endDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        endDaySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        endMonthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        endYearSpinner = new JSpinner(new SpinnerNumberModel(2023, 2000, 2100, 1));
        endDatePanel.add(new JLabel("Day:"));
        endDatePanel.add(endDaySpinner);
        endDatePanel.add(new JLabel("Month:"));
        endDatePanel.add(endMonthSpinner);
        endDatePanel.add(new JLabel("Year:"));
        endDatePanel.add(endYearSpinner);

        myJpanel.add(
                endDatePanel,
                new CustomConstraints(
                        1, 4, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        // Add dropdown for selecting number of sprints
        JLabel sprintCountLabel = new JLabel("Number of Sprints:");
        sprintCountDropdown = new JComboBox<>(new String[] {"1", "2", "3"});
        
        myJpanel.add(
                sprintCountLabel,
                new CustomConstraints(
                        0, 5, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                sprintCountDropdown,
                new CustomConstraints(
                        1, 5, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (User.getCurrentUser(null, null) == null) {
                            System.out.println("User instance is null");
                            SimulationSwitchRolePane feedbackPanelUI = new SimulationSwitchRolePane();
                            feedbackPanelUI.setVisible(true);
                        } else if (User.getCurrentUser(null, null).getRole() == null) {
                            System.out.println("User role is null");
                            SimulationSwitchRolePane feedbackPanelUI = new SimulationSwitchRolePane();
                            feedbackPanelUI.setVisible(true);
                        } else {
                            String roleName = User.getCurrentUser(null, null).getRole().getName();
                            System.out.println("current role: " + roleName);
                            if (roleName.equals("Scrum Master")) {
                                getSprintObject();
                                System.out.println("Sprint created");
                                SprintListPane sprintListUI = new SprintListPane();
                                sprintListUI.setVisible(true);
                                dispose();
                            } else {
                                SimulationSwitchRolePane feedbackPanelUI = new SimulationSwitchRolePane();
                                feedbackPanelUI.setVisible(true);
                                System.out.println("Wrong role");
                            }
                        }
                    }
                });

        listModel = new DefaultListModel<>();
        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            listModel.addElement(userStory.toString());
        }

        usList = new JList<>(listModel);
        usList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(usList);
        scrollPane.setPreferredSize(new Dimension(300, 100));

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 6, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 6, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    public Sprint getSprintObject() {
        String name = nameField.getText();
        String description = descArea.getText();
        Integer length = (Integer) sprintDays.getValue();
        String sprintCount = (String) sprintCountDropdown.getSelectedItem(); // Get selected number of sprints

        SprintFactory sprintFactory = SprintFactory.getSprintFactory();
        Sprint mySprint = sprintFactory.createNewSprint(name, description, length);

        int[] selectedIdx = usList.getSelectedIndices();

        for (int idx : selectedIdx) {
            String stringIdentifier = listModel.getElementAt(idx);
            for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
                if (stringIdentifier.equals(userStory.toString())) {
                    mySprint.addUserStory(userStory);
                    break;
                }
            }
        }

        // Process sprintCount if needed in your logic
        
        SprintStore.getInstance().addSprint(mySprint);

        return mySprint;
    }
}