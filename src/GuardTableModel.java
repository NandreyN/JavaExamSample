import javax.swing.table.AbstractTableModel;
import java.util.*;

public class GuardTableModel extends AbstractTableModel {
    private List<Employee> guards;
    private Map<String, String> salaries;

    private static final String[] HEADERS = {"Name", "Job", "Salary", "Secured Object"};

    public GuardTableModel(List<Employee> guards) {
        this.guards = new ArrayList<Employee>(guards);
    }

    public void update(List<Employee> guards) {
        this.guards = guards;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return guards.size();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= guards.size())
            return "??";

        Employee guard = guards.get(rowIndex);
        if (!(guard instanceof Guard))
            return "??";

        Object ret = "??";
        switch (columnIndex) {
            case 0:
                ret = guard.getName();
                break;
            case 1:
                ret = guard.getJob();
                break;
            case 2:
                ret = guard.getSalary();
                break;
            case 3:
                ret = ((Guard) guard).getSecuredObject();
                break;
        }
        return ret;
    }

    @Override
    public String getColumnName(int index) {
        return HEADERS[index];
    }


    public Map<String, String> getMinSalaryInfo() {
        salaries = new HashMap<String, String>();
        guards.stream().forEach(x -> {
                    if (!salaries.containsKey(x.getJob())) {
                        salaries.put(x.getJob(), x.getSalary());
                    }
                    else
                        if (new Integer(x.getSalary()).compareTo(new Integer(salaries.get(x.getJob()))) < 0)
                            salaries.put(x.getJob(),x.getSalary());
                }
        );

        return salaries;
    }
}
