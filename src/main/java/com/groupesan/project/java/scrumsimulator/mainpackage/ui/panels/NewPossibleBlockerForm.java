package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewPossibleBlockerForm extends JFrame implements BaseComponent {
    private JTextField nameField = new JTextField();
    private JTextArea descArea = new JTextArea();
    private JComboBox<String> userStoryComboBox;

    public NewPossibleBlockerForm() {
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Create New Possible Blockers");
        setSize(400, 300);

        nameField = new JTextField();
        descArea = new JTextArea();
        userStoryComboBox = new JComboBox<>(new String[]{ "US1", "US2", "US3" });
        // userStoryComboBox.setSelectedItem(userStoriesMap.values().toArray(new String[0])[0]);

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

        JLabel selectUserStoryLabel = new JLabel("Select User Stories:");
        myJpanel.add(
                selectUserStoryLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                userStoryComboBox,
                new CustomConstraints(
                        1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

        JButton submitButton = new JButton("Create");

        submitButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // String name = nameField.getText();
                    // String description = descArea.getText();
                    // Double points = (Double) pointsCombo.getSelectedItem();
                    UserStory us = (UserStory) userStoryComboBox.getSelectedItem();
                    System.out.println(us.getName());
                    System.out.println(us.getId());

                    // userStory.setName(name);
                    // userStory.setDescription(description);
                    // userStory.setPointValue(points);
                    dispose();
                }
            }
        );

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 3, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 3, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }
}