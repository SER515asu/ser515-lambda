package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

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
    JLabel deleteIcon;

    // TODO: This is a non transient field and this class is supposed to be serializable. this needs
    // to be dealt with before this object can be serialized
    private UserStory userStory;
    private UserStoryListPane _userStoryListPane;

    // ActionListener actionListener = e -> {};

    MouseAdapter openEditDialog = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
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
            UserStoryStore.getInstance().deleteUserStory(userStory);
            System.out.println(_userStoryListPane.getName());
            _userStoryListPane.reloadUserStoryPannel();
            // UserStoryListPane.getInstance().reloadUserStoryPannel();
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

        id = new JLabel(userStory.getId().toString());
        id.addMouseListener(openEditDialog);

        points = new JLabel(Double.toString(userStory.getPointValue()));
        points.addMouseListener(openEditDialog);

        name = new JLabel(userStory.getName());
        name.addMouseListener(openEditDialog);

        desc = new JLabel(userStory.getDescription());
        desc.addMouseListener(openEditDialog);
        
        deleteIcon = new JLabel(
            new ImageIcon(
                new ImageIcon(
                    System.getProperty("user.dir") + "/assets/delete-icon.png"
                ).getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH)
            )
        );
        deleteIcon.addMouseListener(deleteUserStory);

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
