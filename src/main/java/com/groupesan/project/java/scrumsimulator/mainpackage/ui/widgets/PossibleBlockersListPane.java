package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockersStore;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
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
        setPreferredSize(new Dimension(500, 400)); 
        setResizable(false); 

        JPanel myJPanel = new JPanel(new GridBagLayout());
        myJPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        _subPanel = new JPanel(new GridBagLayout());
        _subPanel.setPreferredSize(new Dimension(300, 5));
        refreshBlockersList(); 

        myJPanel.add(
            new JScrollPane(_subPanel),
            new CustomConstraints(
                0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.BOTH
            )
        );

        JButton newBlockerButton = new JButton("New Blocker Story");
        newBlockerButton.addActionListener(e -> {
            NewBlockerListPane formb = new NewBlockerListPane(this); 
            formb.setVisible(true);
        });

        myJPanel.add(
            newBlockerButton,
            new CustomConstraints(
                0, 1, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL
            )
        );

        JButton fineTuneButton = new JButton("Fine Tune Probabilities");
        fineTuneButton.addActionListener(e -> {
            FineTuneListPane form = new FineTuneListPane();
            form.setVisible(true);
        });

        myJPanel.add(
            fineTuneButton,
            new CustomConstraints(
                0, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL
            )
        );

        setContentPane(myJPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void refreshBlockersList() {
        _subPanel.removeAll(); 
        List<PossibleBlocker> blockers = PossibleBlockersStore.getInstance().getListOfPossibleBlockers();
    
        for (int i = 0; i < blockers.size(); i++) {
            PossibleBlocker pbw = blockers.get(i);
            JButton solutionListButton = new JButton(pbw.print());
            solutionListButton.addActionListener(e -> {
                Solutions solutions = new Solutions(pbw.getId());
                solutions.setVisible(true);
            });
            _subPanel.add(
                solutionListButton,
                new CustomConstraints(
                    0, i, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL
                )
            );
        }
    
        _subPanel.revalidate();
        _subPanel.repaint();
    }
}
