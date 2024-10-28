package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockersStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.NewPossibleBlockerForm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
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

        // Dummy data
        // PossibleBlockersStore.getInstance().addNewBlocker(
        //     new PossibleBlocker("Test 1", "It's a dummy desc for the possible blocker")
        // );
        // PossibleBlockersStore.getInstance().addNewBlocker(
        //     new PossibleBlocker("Test 2", "It's a dummy desc for the possible blocker")
        // );
        // PossibleBlockersStore.getInstance().addNewBlocker(
        //     new PossibleBlocker("Test 3", "It's a dummy desc for the possible blocker")
        // );
        // PossibleBlockersStore.getInstance().addNewBlocker(
        //     new PossibleBlocker("Test 4", "It's a dummy desc for the possible blocker")
        // );
        

        _subPanel = new JPanel(new GridBagLayout());

        loadOrReloadSubPannel();

        myJPanel.add(
            new JScrollPane(_subPanel),
            new CustomConstraints(
                0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.BOTH
            )
        );

        JButton newBlockerButton = new JButton("New Possible Blocker");
        newBlockerButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NewPossibleBlockerForm form = new NewPossibleBlockerForm();
                    form.setVisible(true);

                    form.addWindowListener(new WindowListener() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            loadOrReloadSubPannel();
                        }

                        public void windowClosing(WindowEvent e)        {}
                        public void windowOpened(WindowEvent e)         {}
                        public void windowIconified(WindowEvent e)      {}
                        public void windowDeiconified(WindowEvent e)    {}
                        public void windowActivated(WindowEvent e)      {}
                        public void windowDeactivated(WindowEvent e)    {}
                    });
                }
            }
        );

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

    private void loadOrReloadSubPannel(){
        if(_subPanel == null){
            return;
        }

        _subPanel.removeAll();

        System.out.println("Size of PB: " + PossibleBlockersStore.getInstance().size());

        List<PossibleBlocker> blockers = PossibleBlockersStore.getInstance().getListOfPossibleBlockers();
        for (int i = 0; i < PossibleBlockersStore.getInstance().size(); i++) {
            PossibleBlocker pb = blockers.get(i);

            JButton solutionListButton = new JButton(pb.print(false));

            JPopupMenu popupMenu = new JPopupMenu();

            JMenuItem viewSolitionsOption = new JMenuItem("View Solutions");
            JMenuItem deleteOption = new JMenuItem("Delete");

            viewSolitionsOption.addActionListener(e -> {
                Solutions solutions = new Solutions(pb.getId());
                solutions.setVisible(true);
            });

            deleteOption.addActionListener(e -> {
                PossibleBlockersStore.getInstance().deleteBlocker(pb);
                loadOrReloadSubPannel();
            });

            popupMenu.add(viewSolitionsOption);
            popupMenu.add(deleteOption);

            solutionListButton.addActionListener(e -> popupMenu.show(solutionListButton, 0, solutionListButton.getHeight()));

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