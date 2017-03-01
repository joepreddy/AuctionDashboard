package Views;

import javax.swing.*;
import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * A MetricBoxPanel contains a single metric inside a bordered panel for easy display
 */
public class MetricBoxPanel extends JPanel {

    private MetricType metricType;
    private Number data;
    private JLabel metricDataLabel;

    public MetricBoxPanel(MetricType metricType) {
        this.metricType = metricType;
        this.init();
    }

    private void init() {
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createLineBorder(Color.BLACK)));
        this.setLayout(new BorderLayout());
        this.setBackground(DashboardMainFrame.BG_COLOR);

        JPanel metricNamePanel = new JPanel();
        metricNamePanel.setBackground(new Color(138, 180, 204));
        metricNamePanel.setLayout(new BoxLayout(metricNamePanel, BoxLayout.X_AXIS));
        JLabel metricNameLabel = new JLabel(metricType.toString());
        metricNameLabel.setFont(DashboardMainFrame.GLOB_FONT);

        metricNamePanel.add(Box.createRigidArea(new Dimension(10, 10)));
        metricNamePanel.add(metricNameLabel);

        metricDataLabel = new JLabel();
        metricDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
        metricDataLabel.setFont(DashboardMainFrame.GLOB_FONT.deriveFont(40F));

        this.add(metricNamePanel, BorderLayout.NORTH);
        this.add(metricDataLabel, BorderLayout.CENTER);
    }

    public void setData(Number data) {
        this.data = data;

        if (data.equals(0)) {
            metricDataLabel.setText("Cannot display this metric with current information.");
        }

        else {
            DecimalFormat df = new DecimalFormat("#.#####");
            df.setRoundingMode(RoundingMode.CEILING);
            metricDataLabel.setText(df.format(this.data));
        }

        repaint();
    }
}