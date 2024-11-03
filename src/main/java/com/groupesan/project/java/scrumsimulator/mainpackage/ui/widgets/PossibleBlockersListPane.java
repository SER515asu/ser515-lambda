package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerState;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockersStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.NewPossibleBlockerForm;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.StringFormater;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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

        _subPanel = new JPanel(new GridBagLayout());

        loadOrReloadSubPanel();

        myJPanel.add(
                new JScrollPane(_subPanel),
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.BOTH));

        JButton newBlockerButton = new JButton("New Blocker Story");
        newBlockerButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NewPossibleBlockerForm form = new NewPossibleBlockerForm();
                        form.setVisible(true);
                    }
                });

        myJPanel.add(
                newBlockerButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));

        JButton fineTuneButton = new JButton("Fine Tune Probabilities");
        fineTuneButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        FineTuneListPane form = new FineTuneListPane();
                        form.setVisible(true);
                    }
                });

        myJPanel.add(
                fineTuneButton,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));

        setContentPane(myJPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void loadOrReloadSubPanel() {
        if (_subPanel == null) {
            return;
        }

        _subPanel.removeAll();

        List<PossibleBlocker> blockers = PossibleBlockersStore.getInstance().getListOfPossibleBlockers();
        for (int i = 0; i < blockers.size(); i++) {
            PossibleBlocker pb = blockers.get(i);
            String buttonText = pb.getName() + " - " + pb.getDescription() + " - " + pb.getProbability() + "%";
            JButton solutionListButton = new JButton(buttonText);

            JPopupMenu popupMenu = new JPopupMenu();

            JMenu changeState = new JMenu("Change State");
            for(PossibleBlockerState pbs: PossibleBlockerState.values()){
                JMenuItem state = new JMenuItem(StringFormater.snakeCaseConverter(pbs.toString()));
                state.setEnabled(pb.getCurrentState() != pbs);

                state.addActionListener(e -> {
                    pb.changeBlockerStatus(pbs);
                    loadOrReloadSubPanel();
                });

                changeState.add(state);
            }

            popupMenu.add(changeState);

            JMenuItem viewSolutionsOption = new JMenuItem("View Solutions");
            JMenuItem deleteOption = new JMenuItem("Delete");

            viewSolutionsOption.addActionListener(e -> {
                Solutions solutions = new Solutions(pb.getId());
                solutions.setVisible(true);
            });

            deleteOption.addActionListener(e -> {
                PossibleBlockersStore.getInstance().deleteBlocker(pb);
                loadOrReloadSubPanel();
            });

            popupMenu.add(viewSolutionsOption);
            popupMenu.add(deleteOption);

            solutionListButton
                    .addActionListener(e -> popupMenu.show(solutionListButton, 0, solutionListButton.getHeight()));

            _subPanel.add(
                solutionListButton,
                new CustomConstraints(0, i, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL)
            );
        }
    }
}