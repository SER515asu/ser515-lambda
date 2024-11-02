package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import java.io.FileInputStream;
import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * SimulationUI is the main user interface for the simulation. It displays different UI elements
 * based on the user's selected role.
 */
public class SimulationUI extends JFrame implements BaseComponent {
    private String userRole;
    private String selectedSimulationId;
    private JPanel panel;

    /** Constructor for SimulationUI. It initializes the role selection process. */
    public SimulationUI() {
        init(); // Sets up the initial instruction panel
    }

    /**
     * This method is called externally (from DemoPane) to start the simulation selection process.
     */
    public void startSimulationSelection() {
        selectSimulation(); // Initiates the simulation selection process
    }

    // Method to select a simulation
    private void selectSimulation() {
        JSONArray simulations = getSimulations();
        if (simulations != null) {
            // Create a dialog to choose a simulation
            String[] simulationNames = new String[simulations.length()];
            for (int i = 0; i < simulations.length(); i++) {
                JSONObject simulation = simulations.getJSONObject(i);
                simulationNames[i] = simulation.getString("Name") + " - " + simulation.getString("ID");
            }
            String selectedSimulation = (String) JOptionPane.showInputDialog(
                    this,
                    "Select a Simulation:",
                    "Simulation Selection",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    simulationNames,
                    simulationNames[0]);

            // Store the selected simulation ID (extract from selectedSimulation)
            if (selectedSimulation != null) {
                this.selectedSimulationId = selectedSimulation.split(" - ")[1];
                selectUserRole(); 
            }
        }
    }

    // Method to read simulations from JSON file
    private JSONArray getSimulations() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/simulation.JSON")) {
            JSONTokener tokener = new JSONTokener(fis);
            JSONObject obj = new JSONObject(tokener);
            return obj.getJSONArray("Simulations");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /** Opens the RoleSelectionPane for the user to select their role. */
    private void selectUserRole() {
        RoleSelectionPane roleSelectionPane = new RoleSelectionPane(this::setUserRole);
        roleSelectionPane.setLocationRelativeTo(this);
        roleSelectionPane.setAlwaysOnTop(true);
        roleSelectionPane.setVisible(true);
    }

    /**
     * Sets the user role for the simulation and initializes the UI accordingly.
*
     * @param role The role selected by the user.
     */
    private void setUserRole(String role) {
        this.userRole = role;
        showSimulationUI(); 
    }

    private void showSimulationUI() {
        panel.removeAll();
        panel.add(new JLabel("Welcome to the Simulation"));
        if (userRole != null) {
            panel.add(new JLabel("Your Role: " + userRole));
        }
        if (selectedSimulationId != null) {
            panel.add(new JLabel("Selected Simulation ID: " + selectedSimulationId));
        }
        revalidate();
        repaint();
    }
    public String getUserRole() {
        return userRole; 
    }
    /**
     * Initializes the user interface components. This method is called after the user role has been
     * set.
     */
    @Override
    public void init() {
        setTitle("Simulation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        panel.add(new JLabel("Please select an active Simulation, then a role to join the Simulation"));
        setContentPane(panel);
        setVisible(true); 
    }
}