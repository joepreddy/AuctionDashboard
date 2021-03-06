package Views.DialogPanels;

import Controllers.ProjectSettings;
import Views.CustomComponents.CatLabelFx;
import Views.CustomComponents.CatPanel;
import Views.ViewPresets.ColorSettings;
import Views.ViewPresets.FontSettings;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.Instant;
import java.time.ZoneOffset;

/**
 * DialogDateFilterPanel allows the user to select the start and end dates for the chunk of campaign they wish to view
 */
public class DialogDateFilterPanel extends CatPanel {

    private JFXPanel dateJFXPanel;
    private DatePicker startPicker;
    private DatePicker endPicker;

    public DialogDateFilterPanel() {
        this.init();
    }

    private void init() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorSettings.PANEL_BORDER_COLOR),
                "Date Settings", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, FontSettings.GLOB_FONT.getFont(),
                ColorSettings.TEXT_COLOR));
        this.dateJFXPanel = new JFXPanel();
        Platform.runLater(this::prepFXPanel);
        this.add(dateJFXPanel, BorderLayout.CENTER);
    }

    private void prepFXPanel() {
        Group root = new Group();
        Scene scene = new Scene(root, Color.rgb(ColorSettings.BG_COLOR.getRed(), ColorSettings.BG_COLOR.getGreen(), ColorSettings.BG_COLOR.getBlue()));

        CatLabelFx startLabel = new CatLabelFx("Start Date: ");
        CatLabelFx endLabel = new CatLabelFx("End Date: ");

        startPicker = new DatePicker();
        endPicker = new DatePicker();

        GridPane grid = new GridPane();
        grid.add(startLabel, 0, 0);
        grid.add(startPicker, 1, 0);
        grid.add(endLabel, 0, 1);
        grid.add(endPicker, 1, 1);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(25);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(75);
        grid.getColumnConstraints().addAll(column1, column2);

        root.getChildren().add(grid);

        dateJFXPanel.setScene(scene);
    }

    public Instant getStartDate() {
        if (startPicker.getValue() == null) {
            return ProjectSettings.MIN_DATE;
        }
        return startPicker.getValue().atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    public Instant getEndDate() {
        if (endPicker.getValue() == null) {
            return ProjectSettings.MAX_DATE;
        }
        return endPicker.getValue().atStartOfDay().toInstant(ZoneOffset.UTC);
    }
}
