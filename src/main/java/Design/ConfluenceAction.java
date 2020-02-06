package Design;

public class ConfluenceAction implements Action {
    private  boolean isPutMessage = false;

    @Override
    public boolean handleReply() {
        System.out.println("this the ConfluenceAction");
        return false;
    }
}
