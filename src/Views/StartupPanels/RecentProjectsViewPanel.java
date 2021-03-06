package Views.StartupPanels;

import Views.CustomComponents.CatPanel;
import Views.CustomComponents.CatTitlePanel;
import Views.DashboardStartupFrame;
import Views.ViewPresets.ColorSettings;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * RecentProjectsViewPanel is a panel which shows the recent projects the user has worked on
 */
public class RecentProjectsViewPanel extends CatPanel {

    private List<File> recentFiles;
    private DashboardStartupFrame parent;

    public RecentProjectsViewPanel(List<File> recentFiles, DashboardStartupFrame parent) {
        this.recentFiles = recentFiles;
        this.parent = parent;
        this.initPanel();
    }

    private void initPanel() {

        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(300, 0));
        this.setBorder(BorderFactory.createLineBorder(ColorSettings.PANEL_BORDER_COLOR));

        CatTitlePanel titlePanel = new CatTitlePanel("Recent Projects");

        CatPanel recentProjectBoxesPanel = new CatPanel();
        recentProjectBoxesPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        recentProjectBoxesPanel.setLayout(new BoxLayout(recentProjectBoxesPanel, BoxLayout.Y_AXIS));

        recentFiles.stream()
                .sorted(Collections.reverseOrder((file1, file2) -> Long.valueOf(file1.lastModified()).compareTo(file2.lastModified())))
                .forEach((file) -> {
            RecentProjectInfoPanel infoPanel = new RecentProjectInfoPanel(file, this);
            recentProjectBoxesPanel.add(infoPanel);
        });
        recentProjectBoxesPanel.add(Box.createVerticalGlue());

        this.add(titlePanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(recentProjectBoxesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void chooseRecentProject(File project) {
        parent.chooseRecentProject(project);
    }
}
