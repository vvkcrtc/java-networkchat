package Common;

import java.util.ArrayList;
import java.util.List;

public class Users {
    protected List<User> users = new ArrayList<>();

    public void addUser(String name,int userId) {
        if(!isPresent(name)) {
            users.add(new User(name, userId));
        }
    }

    public User getUser(String name) {
        for(User user : users) {
            if(name.equals(user.getName())) {
                return user;
            }
        }
        return null;
    }
    public boolean isPresent(String name) {
        for(User user : users) {
            if(name.equals(user.getName())) {
                return true;
            }
        }
        return false;
    }
}
