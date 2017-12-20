/**
 * Created by andre on 20.12.2017.
 */
public class Employee {
    private String name;
    private String job;
    private String salary;

    public Employee()
    {}

    public Employee(String name , String job, String salary)
    {
        setJob(job);
        setName(name);
        setSalary(salary);
    }

    public String getName()
    {
        return name;
    }
    public String getJob()
    {
        return job;
    }

    public String getSalary()
    {
        return salary;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setJob(String job)
    {
        this.job = job;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }
}
