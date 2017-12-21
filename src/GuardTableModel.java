import javax.swing.table.AbstractTableModel;
import java.util.*;

public class GuardTableModel extends AbstractTableModel {
    private List<Employee> guards;
    private Map<String, String> salaries;
    private Set<String> jobs;

    private static final String[] HEADERS = {"Name", "Job", "Salary", "Secured Object", "Rooms cleaned"};

    public GuardTableModel(List<Employee> guards) {
        this.guards = new ArrayList<>(guards);
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
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = "";
        if (rowIndex >= guards.size())
            return ret;

        Employee guard = guards.get(rowIndex);

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
                if (guard instanceof Guard)
                    ret = ((Guard) guard).getSecuredObject();
                break;
            case 4:
                if (guard instanceof Cleaner)
                    ret = String.valueOf(((Cleaner) guard).getRoomsCleaned());
                break;
        }
        return ret;
    }

    @Override
    public String getColumnName(int index) {
        return HEADERS[index];
    }


    public Map<String, String> getMinSalaryInfo() {
        salaries = new HashMap<>();
        guards.forEach(x -> {
                    if (!salaries.containsKey(x.getJob())) {
                        salaries.put(x.getJob(), x.getSalary());
                    } else if (new Integer(x.getSalary()).compareTo(new Integer(salaries.get(x.getJob()))) < 0)
                        salaries.put(x.getJob(), x.getSalary());
                }
        );

        return salaries;
    }

    public Set<String> getJobs() {
        jobs = new TreeSet<>();
        if (salaries != null) {
            for (Map.Entry<String, String> entry : salaries.entrySet())
                jobs.add(entry.getKey());
            return jobs;
        }


        guards.forEach(x -> {
            jobs.add(x.getJob());
        });
        return jobs;
    }
}
