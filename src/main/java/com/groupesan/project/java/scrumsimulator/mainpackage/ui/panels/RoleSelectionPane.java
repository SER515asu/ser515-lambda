package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import java.awt.*;
import java.util.function.Consumer;
import javax.swing.*;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.User;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;

/**
 * RoleSelectionPane provides a user interface for users to select their role in the simulation. It
 * supports the selection of roles like 'Pig', 'Product Owner', and 'Scrum Master'.
 */
public class RoleSelectionPane extends JFrame implements BaseComponent {
    private Consumer<String> onRoleSelected;

    /**
     * Constructor for RoleSelectionPane.
     *
     * @param onRoleSelected A Consumer functional interface to handle the selected role.
     */
    public RoleSelectionPane(Consumer<String> onRoleSelected) {
        this.onRoleSelected = onRoleSelected;
        setTitle("Select Role");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        init();
    }

    /**
     * Initializes the UI components for role selection. Adds buttons for each role and sets up
     * action listeners.
     */
    @Override
    public void init() {
        setLayout(new FlowLayout());

        JButton pigButton = new JButton("Developer");
        JButton productOwnerButton = new JButton("Product Owner");
        JButton scrumMasterButton = new JButton("Scrum Master");

        pigButton.addActionListener(e -> {
        selectRole("Developer");
        ScrumRole currentRole = new ScrumRole("Developer");
        //create the user instance or update the role
        User.getCurrentUser("defaultName", currentRole);
        JOptionPane.showMessageDialog(
                null, "Switched to Developer", "Role Switching", JOptionPane.PLAIN_MESSAGE);}
        );
        productOwnerButton.addActionListener(e -> {selectRole("Product Owner");
            ScrumRole currentRole = new ScrumRole("Product Owner");
            //create the user instance or update the role
            User.getCurrentUser("defaultName", currentRole);
            JOptionPane.showMessageDialog(
                null, "Switched to Product Owner", "Role Switching", JOptionPane.PLAIN_MESSAGE);
        });
        scrumMasterButton.addActionListener(e -> {selectRole("Scrum Master");
            ScrumRole currentRole = new ScrumRole("Scrum Master");
            //create the user instance or update the role
            User.getCurrentUser("defaultName", currentRole);
            JOptionPane.showMessageDialog(
                null, "Switched to Scrum Master", "Role Switching", JOptionPane.PLAIN_MESSAGE);
        });

        add(pigButton);
        add(productOwnerButton);
        add(scrumMasterButton);
    }

    /**
     * Handles the role selection and notifies the SimulationUI.
     *
     * @param role The role selected by the user.
     */
    private void selectRole(String role) {
        if (onRoleSelected != null) {
            onRoleSelected.accept(role);
        }
        this.dispose(); // Close the role selection window
    }
}
