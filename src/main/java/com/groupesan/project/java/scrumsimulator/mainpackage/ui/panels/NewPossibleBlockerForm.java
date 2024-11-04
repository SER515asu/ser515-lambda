package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockersStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.security.SecureRandom;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.User;

public class NewPossibleBlockerForm extends JFrame implements BaseComponent {
    private SecureRandom random = new SecureRandom();
    private JTextField nameField = new JTextField();
    private JTextArea descArea = new JTextArea();
    private JComboBox<UserStory> userStoryComboBox;

    public NewPossibleBlockerForm() {
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Create New Possible Blockers");
        setSize(400, 300);

        nameField = new JTextField();
        descArea = new JTextArea();
        userStoryComboBox = new JComboBox<>(UserStoryStore.getInstance().getUserStories().toArray(new UserStory[0]));
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
                1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL
            )
        );

        JLabel probabilityLabel = new JLabel("Probability of Occurrence, max: ");
        myJpanel.add(
            probabilityLabel,
            new CustomConstraints(
                0, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL
            )
        );

        String[] probabilities = new String[101];
        for (int i = 0; i <= 100; i++) {
            probabilities[i] = i + "%";
        }
        JComboBox<String> probabilityDropdown = new JComboBox<>(probabilities);
        myJpanel.add(
            probabilityDropdown,
            new CustomConstraints(
                1, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL
            )
        );

        JLabel probabilityLabel2 = new JLabel("Probability of Occurrence, min: ");
        myJpanel.add(
            probabilityLabel2,
            new CustomConstraints(
                0, 4, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL
            )
        );

        JComboBox<String> probabilityDropdown2 = new JComboBox<>(probabilities);
        myJpanel.add(
            probabilityDropdown2,
            new CustomConstraints(
                1, 4, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL
            )
        );


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
                    String name = nameField.getText();
                    String description = descArea.getText();
                    UserStory us = (UserStory) userStoryComboBox.getSelectedItem();
                    String probabilityStrMax = (String) probabilityDropdown.getSelectedItem().toString();
                    int probabilityMax = Integer.parseInt(probabilityStrMax.replace("%", "").trim());
                    String probabilityStrMin = (String) probabilityDropdown2.getSelectedItem().toString();
                    int probabilityMin = Integer.parseInt(probabilityStrMin.replace("%", "").trim());
                
                    if (name.isEmpty() || description.isEmpty() || probabilityStrMax == null || probabilityStrMin == null) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                        return;
                    }
                    if (probabilityMax < probabilityMin) {
                        JOptionPane.showMessageDialog(null, "Enter the correct probability range.");
                    }

                    //randonly select a probability between the min and max
                    int probability = (random.nextInt(probabilityMax - probabilityMin + 1) + probabilityMin);
                    String roleName = "None";
                    if (User.getCurrentUser(null, null).getRole() != null) {
                        roleName = User.getCurrentUser(null, null).getRole().getName();
                    }
                    System.out.println("current role: " + roleName);
                    if (roleName.equals("Scrum Master")) {
                        PossibleBlocker newBlocker = new PossibleBlocker(name, description, us, probability);
                        PossibleBlockersStore.getInstance().addNewBlocker(newBlocker);
                        JOptionPane.showMessageDialog(null, "Blocker Submitted!");
                    }
                    else {
                        PossibleBlocker newBlocker = new PossibleBlocker(name, description, us);
                        PossibleBlockersStore.getInstance().addNewBlocker(newBlocker);
                        JOptionPane.showMessageDialog(null, "Blocker Submitted! But only Scrum Master can set the probability.");
                    }
                    dispose(); 
                }
            }
        );

        myJpanel.add(
                cancelButton,
                new CustomConstraints(6, 5, GridBagConstraints.EAST, GridBagConstraints.NONE));

        myJpanel.add(
                submitButton,
                new CustomConstraints(5, 5, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }
}