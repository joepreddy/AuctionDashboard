package Views;

import Views.CustomComponents.CatButton;
import Views.CustomComponents.CatPanel;
import Views.DialogPanels.DialogDateFilterPanel;
import Views.DialogPanels.DialogFilterPanel;
import Views.ViewPresets.AttributeType;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.*;
import java.util.List;

/**
 * The frame which pops up when the "add filters" button is clicked, allows the user to select the filters they wish to
 * apply to their data.
 */
public class DashboardFilterDialog extends JDialog {

    public final static int APPROVE_OPTION = 1;
    public final static int CANCEL_OPTION = 2;
    private List<DialogFilterPanel> filterPanels;
    private Instant startDate;
    private Instant endDate;
    private int returnVal;
    private DialogDateFilterPanel datePanel;

    public DashboardFilterDialog(Window parent, Map<AttributeType, List<String>> possibleVals) {
        super(parent, "Choose Filters", ModalityType.APPLICATION_MODAL);
        this.filterPanels = new ArrayList<>();
        this.init(possibleVals);
    }

    private void init(Map<AttributeType, List<String>> possibleVals) {
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        CatPanel contentPane = new CatPanel();
        this.setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        CatPanel filterListPanel = new CatPanel();
        filterListPanel.setBorder(BorderFactory.createEmptyBorder());
        filterListPanel.setLayout(new BoxLayout(filterListPanel, BoxLayout.Y_AXIS));
        filterListPanel.add(Box.createGlue());

        datePanel = new DialogDateFilterPanel();
        filterListPanel.add(datePanel);
        filterListPanel.add(Box.createGlue());

        possibleVals.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(AttributeType::toString))).forEach((entry) -> {
            DialogFilterPanel filterPanel = new DialogFilterPanel(entry.getKey(), entry.getValue());
            filterPanels.add(filterPanel);
            filterListPanel.add(filterPanel);
            filterListPanel.add(Box.createHorizontalGlue());
        });

        CatButton confirmButton = new CatButton("Apply Filters");
        confirmButton.addActionListener((e) -> {
            if (this.getStartDate().isAfter(this.getEndDate())) {
                JOptionPane.showMessageDialog(this, "The start date must be before the end date!",
                        "Date Selection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.returnVal = DashboardFilterDialog.APPROVE_OPTION;
            this.setVisible(false);
        });

        this.add(filterListPanel, BorderLayout.CENTER);
        this.add(confirmButton, BorderLayout.SOUTH);
    }

    public int showFilterDialog() {
        this.returnVal = DashboardFilterDialog.CANCEL_OPTION; //Need to reset this or it might always approve
        this.setVisible(true);
        return this.returnVal;
    }

    public Map<AttributeType, List<String>> getFilters() {
        Map<AttributeType, List<String>> filters = new HashMap<>();

        for (DialogFilterPanel filterPanel: filterPanels) {
            if (!filterPanel.getSelected().isEmpty()) {
                filters.put(filterPanel.getAttribute(), filterPanel.getSelected());
            }
        }

        return filters;
    }

    public List<DialogFilterPanel> getFilterPanels() {
        return filterPanels;
    }

    public Instant getStartDate() {
        return datePanel.getStartDate();
    }

    public void setReturnVal(int returnVal) {
        this.returnVal = returnVal;
    }

    public Instant getEndDate() {
        return datePanel.getEndDate();
    }
}
