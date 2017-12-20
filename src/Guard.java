
public class Guard extends Employee{
    private String securedObject;

    public Guard()
    {
        super();
    }

    public Guard(String name, String job, String salary, String securedObject) {
        super(name, job, salary);
        setSecuredObject(securedObject);
    }

    public String getSecuredObject()
    {
        return securedObject;
    }

    public void setSecuredObject(String securedObject)
    {
        this.securedObject = securedObject;
    }

}
