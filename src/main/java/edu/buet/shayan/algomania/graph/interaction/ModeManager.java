package edu.buet.shayan.algomania.graph.interaction;

import javafx.scene.control.ToggleButton;

public class ModeManager {
    private ToggleButton activeToggleButton;

    public void activateMode(ToggleButton button) {
        if (activeToggleButton != null && activeToggleButton != button) {
            activeToggleButton.setSelected(false);
            activeToggleButton.setStyle("");
        }

        activeToggleButton = button.isSelected() ? button : null;
        button.setStyle(button.isSelected() ? "-fx-background-color: lightgreen" : "");
    }

    public ToggleButton getActiveToggleButton() {
        return activeToggleButton;
    }

    public void clearActiveButton() {
        if (activeToggleButton != null) {
            activeToggleButton.setSelected(false);
            activeToggleButton.setStyle("");
        }
        activeToggleButton = null;
    }
}
