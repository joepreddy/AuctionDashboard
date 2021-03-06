package Views.CustomComponents;

import Views.ViewPresets.ColorSettings;
import Views.ViewPresets.FontSettings;

import javax.swing.*;

/**
 * CatNumberSpinner is a customised JSpinner to fit with the CatAnalysis application
 */
public class CatNumberSpinner extends JSpinner {

    public CatNumberSpinner(int startingValue) {
        int min = 1;
        int max = 557000;
        int step = 1;
        SpinnerNumberModel model = new SpinnerNumberModel(startingValue, min, max, step);
        this.setModel(model);
        this.init();
    }

    private void init() {
        this.setBackground(ColorSettings.TEXT_AREA_BG_COLOR);
        this.setForeground(ColorSettings.TEXT_AREA_TEXT_COLOR);
        this.setFont(FontSettings.GLOB_FONT.getFont());
        this.setMaximumSize(this.getPreferredSize());
    }

    public int getNumber() {
        return (int) this.getValue();
    }
}
