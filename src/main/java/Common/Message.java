package Common;

public class Message {
    protected int userId;
    protected String message;
    public Message(int userId, String message) {
        this.userId = userId;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public int getUserId() {
        return userId;
    }
    @Override
    public String toString () {
        return "id: "+userId+" "+message;
    }

}
