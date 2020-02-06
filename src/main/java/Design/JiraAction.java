package Design;



public class JiraAction implements Action {
    private  boolean isPutMessage = false;
    @Override
    public boolean handleReply() {
        System.out.println("this a JiraAction");
        return false;
    }

    public boolean isPutMessage() {
        return isPutMessage;
    }

    public void setPutMessage(boolean putMessage) {
        isPutMessage = putMessage;
    }

    @Override
    public boolean PutMessage() {
        return true;
    }
}
