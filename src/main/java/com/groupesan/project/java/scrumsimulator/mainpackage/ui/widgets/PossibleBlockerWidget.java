package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;

public class PossibleBlockerWidget extends JPanel implements BaseComponent {

    private JLabel id;
    private JLabel name;
    private JLabel desc;
    private JLabel probability;
    private JLabel deleteIcon;

    private transient PossibleBlocker _possibleBlocker;


    private transient MouseAdapter openEditDialog = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // EditUserStoryForm form = new EditUserStoryForm(userStory);
            // form.setVisible(true);

            // form.addWindowListener(
            //     new java.awt.event.WindowAdapter() {
            //         public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            //             init();
            //         }
            //     }
            // );
        }
    };

    private transient MouseAdapter deleteUserStory = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // UserStoryStore.getInstance().deleteUserStory(userStory);
            // System.out.println(_userStoryListPane.getName());
            // _userStoryListPane.reloadUserStoryPannel();
        }
    };

    public PossibleBlockerWidget(PossibleBlocker pb) {
        this._possibleBlocker = pb;
        this.init();
    }

    public String print(){
        System.out.println(this._possibleBlocker.getId() + " - " + this._possibleBlocker.getName() + " - " + this._possibleBlocker.getDescription());
        return  this._possibleBlocker.getId() + " - " + this._possibleBlocker.getName() + " - " + this._possibleBlocker.getDescription();
    }

    public String getBlockerId() {
        return this._possibleBlocker.getId();
    }

    public void init() {
        removeAll();        

        id = new JLabel(_possibleBlocker.getId());
        // id.addMouseListener(openEditDialog);

        name = new JLabel(_possibleBlocker.getName());
        // name.addMouseListener(openEditDialog);

        desc = new JLabel(_possibleBlocker.getDescription());
        // desc.addMouseListener(openEditDialog);

        probability = new JLabel(_possibleBlocker.getProbability());
        // probability.addMouseListener(openEditDialog);
        
        deleteIcon = new JLabel(
            new ImageIcon(
                new ImageIcon(
                    System.getProperty("user.dir") + "/assets/delete-icon.png"
                ).getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH)
            )
        );
        // deleteIcon.addMouseListener(deleteUserStory);

        GridBagLayout myGridBagLayout = new GridBagLayout();

        setLayout(myGridBagLayout);

        add(
            id,
            new CustomConstraints(
                0, 0, GridBagConstraints.WEST, 
                0.1, 0.0, GridBagConstraints.HORIZONTAL
            )
        );

        add(
            name,
            new CustomConstraints(
                2, 0, GridBagConstraints.WEST, 
                0.2, 0.0, GridBagConstraints.HORIZONTAL
            )
        );

        add(
            desc,
            new CustomConstraints(
                3, 0, GridBagConstraints.WEST, 
                0.7, 0.0, GridBagConstraints.HORIZONTAL
            )
        );

        add(
            probability,
            new CustomConstraints(
                4, 0, GridBagConstraints.WEST, 
                0.7, 0.0, GridBagConstraints.HORIZONTAL
            )
        );

        add(
            deleteIcon,
            new CustomConstraints(
                5, 0, GridBagConstraints.WEST, 
                0.7, 0.0, GridBagConstraints.HORIZONTAL
            )
        );
    }
}
