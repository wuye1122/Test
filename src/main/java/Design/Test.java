package Design;

public class Test {

    public static void main(String[] args) {
    Action jira = new JiraAction();
    Action confluence = new ConfluenceAction();

        System.out.println(jira.PutMessage());
        System.out.println(confluence.PutMessage());

        System.out.println("---"+((JiraAction) jira).isPutMessage());
        ((JiraAction) jira).setPutMessage(true);
        System.out.println("---");
        System.out.println("---"+((JiraAction) jira).isPutMessage());

        System.out.println(confluence.PutMessage());

    }
}
