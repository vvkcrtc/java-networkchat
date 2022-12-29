package Common;

public class User {
    protected String name;
    protected int userId;
    protected boolean isActive;

    public User(String name, int userId) {
        this.name = name;
        this.userId = userId;
        isActive = false;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
    public String getNameById(int id) {
        if(id == userId) {
            return name;
        }
        return null;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    @Override
    public String toString() {
        return "Name: "+name+" id: "+userId;
    }
}
