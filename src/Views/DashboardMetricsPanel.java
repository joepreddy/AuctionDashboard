package Views;

import javax.swing.*;
import java.awt.*;

/**
 * DashboardMetricsPanel is the GUI element responsible for displaying a list of metrics computed by the application.
 * This will only be a necessary component for Increment 1.
 */
public class DashboardMetricsPanel extends JPanel {

    public DashboardMetricsPanel() {

    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(800, 900));

        JLabel title = new JLabel("Some Key Metrics");
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(Box.createRigidArea(new Dimension(0, 70)));
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalGlue());

        JList<String> metrics = new JList<>();

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(metrics, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 100)), BorderLayout.SOUTH);
    }
}