package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.PossibleBlockerWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class Solutions extends JFrame implements BaseComponent  {
    private JPanel _subPanel;
    private String blocker_id;
    private ArrayList<String> solutionList = new ArrayList<String>();
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
        myJPanel.setBorder(new EmptyBorder(100, 100, 100, 100));

        // Dummy data
        ArrayList<String> solutionList = new ArrayList<>(
            Arrays.asList(
                "Dummy solution 1",
                "Dummy solution 2",
                "Dummy solution 3",
                "Dummy solution 4"
            ) 
        );

        _subPanel = new JPanel(new GridBagLayout());
        for (int i = 0; i < solutionList.size(); i++) {
            String solution = solutionList.get(i);
            JLabel solutionLabel = new JLabel(solution);
            _subPanel.add(
                solutionLabel,
                new CustomConstraints(
                    0, i, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL
                )
            );
        }

        //make a scroll bar
        myJPanel.add(new JScrollPane(_subPanel));

        setContentPane(myJPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        




    }

    
}
