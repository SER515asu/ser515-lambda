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

public class PossibleBlockersListPane extends JFrame implements BaseComponent {
    private JPanel _subPanel;

    public PossibleBlockersListPane() {
        this.init();
    }

    @Override
    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Possible Blockers");
        setSize(400, 300);

        JPanel myJPanel = new JPanel(new GridBagLayout());
        myJPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Dummy data
        ArrayList<PossibleBlockerWidget> dummyPBList = new ArrayList<>(
            Arrays.asList(
                new PossibleBlockerWidget(
                    new PossibleBlocker("Test 1", "It's a dummy desc for the possible blocker")
                ),
                new PossibleBlockerWidget(
                    new PossibleBlocker("Test 2", "It's a dummy desc for the possible blocker")
                ),
                new PossibleBlockerWidget(
                    new PossibleBlocker("Test 3", "It's a dummy desc for the possible blocker")
                ),
                new PossibleBlockerWidget(
                    new PossibleBlocker("Test 4", "It's a dummy desc for the possible blocker")
                )
            ) 
        );

        _subPanel = new JPanel(new GridBagLayout());

        for (int i = 0; i < dummyPBList.size(); i++) {
            PossibleBlockerWidget pbw = dummyPBList.get(i);
            pbw.print();

            _subPanel.add(
                pbw,
                new CustomConstraints(
                    0, i, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL
                )
            );
        }

        myJPanel.add(
            new JScrollPane(_subPanel),
            new CustomConstraints(
                0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.BOTH
            )
        );

        JButton newBlockerButton = new JButton("New Blocker Story");
        myJPanel.add(
            newBlockerButton,
            new CustomConstraints(
                0, 1, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL
            )
        );

        setContentPane(myJPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}