package Test;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.exec.util.StringUtils;

public class MongodbSessionId {

    public String name;

    public String name1;

    public String name2;

    public String name3;
    public String name4;
    public String name5;
    public String name6;
    public String name7;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getName5() {
        return name5;
    }

    public void setName5(String name5) {
        this.name5 = name5;
    }

    public String getName6() {
        return name6;
    }

    public void setName6(String name6) {
        this.name6 = name6;
    }

    public String getName7() {
        return name7;
    }

    public void setName7(String name7) {
        this.name7 = name7;
    }

    @Override
    public String toString() {

        return   (( name == null||name.trim().equals("")) ? 0 : 1) + ""
                + (( name1 == null||name1.trim().equals("")) ? 0 : 1) + ""
                + (( name2 == null||name2.trim().equals("")) ? 0 : 1) + ""
                + (( name3 == null||name3.trim().equals("")) ? 0 : 1) + ""
                + (( name4 == null||name4.trim().equals("")) ? 0 : 1) + ""
                + (( name5 == null||name5.trim().equals("")) ? 0 : 1) + ""
                + (( name6 == null||name6.trim().equals("")) ? 0 : 1) + ""
                + (( name7 == null||name7.trim().equals("")) ? 0 : 1) + "";

    }

    public static void main(String[] args) {

        MongodbSessionId sessionId1 = new MongodbSessionId();
        sessionId1.setName(" SDAS");
        sessionId1.setName7("     ");
        System.out.println(sessionId1);

        MongodbSessionId sessionId2 = new MongodbSessionId();
        sessionId2.setName("");
        sessionId2.setName7(" ");
        System.out.println(sessionId2);

        String sessionId3="{\"name\":\"JSON\",\"name2\":\"24\",\"name7\":\"北京市西城区\"}";

        String sessionId4="{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}";

        MongodbSessionId id3 = (MongodbSessionId) JSONObject.parseObject(sessionId3,MongodbSessionId.class);
        MongodbSessionId id4 = (MongodbSessionId) JSONObject.parseObject(sessionId4,MongodbSessionId.class);

        System.out.println(id3.toString());
        System.out.println(id4.toString());


    }
}
