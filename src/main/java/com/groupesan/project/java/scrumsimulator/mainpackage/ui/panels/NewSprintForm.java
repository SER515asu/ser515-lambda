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
import javax.swing.*;
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
    JComboBox<Integer> predefinedLengthsCombo;

    JSpinner startDaySpinner, startMonthSpinner, startYearSpinner;
    JSpinner endDaySpinner, endMonthSpinner, endYearSpinner;

    DefaultListModel<String> listModel;
    JList<String> usList;

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

        // Name field
        JLabel nameLabel = new JLabel("Name:");
        myJpanel.add(
                nameLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                nameField,
                new CustomConstraints(
                        1, 0, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        // Description field
        JLabel descLabel = new JLabel("Description:");
        myJpanel.add(
                descLabel,
                new CustomConstraints(
                        0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                new JScrollPane(descArea),
                new CustomConstraints(
                        1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

        // Length (Days) spinner
        JLabel pointsLabel = new JLabel("Length (Days):");
        myJpanel.add(
                pointsLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                sprintDays,
                new CustomConstraints(
                        1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.WEST));

        // Predefined lengths dropdown
        JLabel predefinedLabel = new JLabel("Predefined Sprint Lengths:");
        myJpanel.add(
                predefinedLabel,
                new CustomConstraints(
                        0, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));

        Integer[] predefinedLengths = {7, 14, 21};
        predefinedLengthsCombo = new JComboBox<>(predefinedLengths);
        predefinedLengthsCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer selectedLength = (Integer) predefinedLengthsCombo.getSelectedItem();
                sprintDays.setValue(selectedLength);
            }
        });

        myJpanel.add(
                predefinedLengthsCombo,
                new CustomConstraints(
                        1, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.WEST));

        // Start Date
        JLabel startDateLabel = new JLabel("Start Date:");
        myJpanel.add(
                startDateLabel,
                new CustomConstraints(
                        0, 4, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));

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
                        1, 4, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        // End Date
        JLabel endDateLabel = new JLabel("End Date:");
        myJpanel.add(
                endDateLabel,
                new CustomConstraints(
                        0, 5, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));

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
                        1, 5, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        // User Stories List
        listModel = new DefaultListModel<>();
        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            listModel.addElement(userStory.toString());
        }

        usList = new JList<>(listModel);
        usList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(usList);
        scrollPane.setPreferredSize(new Dimension(300, 100));

        JLabel userStoriesLabel = new JLabel("User Stories:");
        myJpanel.add(
                userStoriesLabel,
                new CustomConstraints(
                        0, 6, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                usList,
                new CustomConstraints(
                        1, 6, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.NONE));

        // Buttons
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
                        }
                        else if (User.getCurrentUser(null, null).getRole() == null) {
                            System.out.println("User role is null");
                            SimulationSwitchRolePane feedbackPanelUI = new SimulationSwitchRolePane();
                            feedbackPanelUI.setVisible(true);
                        }
                        else {
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

        myJpanel.add(
                cancelButton,
                new CustomConstraints(2, 7, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(3, 7, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    public Sprint getSprintObject() {
        String name = nameField.getText();
        String description = descArea.getText();
        Integer length = (Integer) sprintDays.getValue();

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

        SprintStore.getInstance().addSprint(mySprint);

        int startDay = (Integer) startDaySpinner.getValue();
        int startMonth = (Integer) startMonthSpinner.getValue();
        int startYear = (Integer) startYearSpinner.getValue();
        int endDay = (Integer) endDaySpinner.getValue();
        int endMonth = (Integer) endMonthSpinner.getValue();
        int endYear = (Integer) endYearSpinner.getValue();

        Calendar calendar = Calendar.getInstance();
        calendar.set(startYear, startMonth - 1, startDay);
        Date startDate = calendar.getTime();
        mySprint.setStartDate(startDate);

        calendar.set(endYear, endMonth - 1, endDay);
        Date endDate = calendar.getTime();
        mySprint.setEndDate(endDate);

        System.out.println(mySprint);

        return mySprint;
    }
}