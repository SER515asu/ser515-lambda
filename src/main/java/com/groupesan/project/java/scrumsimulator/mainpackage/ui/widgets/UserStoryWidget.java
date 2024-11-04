package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerState;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockersStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.EditUserStoryForm;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.UserStoryListPane;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;

public class UserStoryWidget extends JPanel implements BaseComponent {

    JLabel id;
    JLabel points;
    JLabel name;
    JLabel desc;
    JLabel businessValue; 
    JLabel deleteOrProhibitedIcon;

    private UserStory userStory;
    private UserStoryListPane _userStoryListPane;
    private boolean _userStoryHasBlocker = false;

    MouseAdapter openEditDialog = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(_userStoryHasBlocker){
                return;
            }

            EditUserStoryForm form = new EditUserStoryForm(userStory);
            form.setVisible(true);

            // This was not allowing the user to edit user story multiple times
            // form.addWindowListener(
            //     new java.awt.event.WindowAdapter() {
            //         public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            //             init();
            //         }
            //     }
            // );
        }
    };

    MouseAdapter deleteUserStory = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(_userStoryHasBlocker){
                return;
            }
            
            UserStoryStore.getInstance().deleteUserStory(userStory);
            _userStoryListPane.reloadUserStoryPannel();
        }
    };

    public UserStoryWidget(UserStory userStory) {
        this.userStory = userStory;
        this.init();
    }

    public UserStoryWidget(UserStory userStory, UserStoryListPane userStoryListPane) {
        this.userStory = userStory;
        this._userStoryListPane = userStoryListPane;
        this.init();
    }

    public void init() {
        removeAll();      
        
        _userStoryHasBlocker = PossibleBlockersStore.getInstance().checkUserStoryWithBlockerState(userStory, PossibleBlockerState.IN_PROGRESS);

        if(_userStoryHasBlocker){
            setBackground(Color.RED);
        }

        id = new JLabel(userStory.getId().toString());
        id.addMouseListener(openEditDialog);

        points = new JLabel(Double.toString(userStory.getPointValue()));
        points.addMouseListener(openEditDialog);

        name = new JLabel(userStory.getName());
        name.addMouseListener(openEditDialog);

        desc = new JLabel(userStory.getDescription());
        desc.addMouseListener(openEditDialog);

        businessValue = new JLabel(Double.toString(userStory.getBusinessValue()));
        businessValue.addMouseListener(openEditDialog);
        
        deleteOrProhibitedIcon = new JLabel(
            new ImageIcon(
                new ImageIcon(
                    (System.getProperty("user.dir") + "/assets/") + ((!_userStoryHasBlocker)? "delete-icon.png" : "prohibited.png")
                ).getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH)
            )
        );

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
            points,
            new CustomConstraints(
                1, 0, GridBagConstraints.WEST, 
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
                0.5, 0.0, GridBagConstraints.HORIZONTAL
            )
        );

        add(
            businessValue,
            new CustomConstraints(
                4, 0, GridBagConstraints.WEST, 
                0.2, 0.0, GridBagConstraints.HORIZONTAL
            )
        );

        add(
            deleteOrProhibitedIcon,
            new CustomConstraints(
                5, 0, GridBagConstraints.WEST, 
                0.1, 0.0, GridBagConstraints.HORIZONTAL
            )
        );
    }
}
