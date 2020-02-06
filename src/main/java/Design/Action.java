package Design;

public interface Action {

    default boolean PutMessage(){
        return false;
    }

     boolean handleReply();

    public boolean putMessage = false;



}
