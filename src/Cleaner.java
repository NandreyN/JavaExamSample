public class Cleaner extends Employee {
    private int roomsCleaned;
    public Cleaner()
    {
        super();
        roomsCleaned = 0;
    }

    public Cleaner(String name, String job, String salary, int cleaned) {
        super(name, job, salary);
        setRoomsCleaned(cleaned);
    }

    public int getRoomsCleaned()
    {
        return roomsCleaned;
    }

    public void setRoomsCleaned(int roomsCleaned)
    {
        this.roomsCleaned = roomsCleaned;
    }
}
