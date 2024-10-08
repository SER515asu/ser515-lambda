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

public class SprintListPane extends JFrame implements BaseComponent {
    public SprintListPane() {
        this.init();
    }

    private List<SprintWidget> widgets = new ArrayList<>();

    public void init() {
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
                        NewSprintForm form = new NewSprintForm();
                        form.setVisible(true);
                        //close the sprint list with outdated information
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

                    }
                }
        );

        myJpanel.add(
                rangeNumSprintsButton,
                new CustomConstraints(2, 3, GridBagConstraints.CENTER, GridBagConstraints.NONE));



        add(myJpanel);
    }
}
