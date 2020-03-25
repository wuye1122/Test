package jdk18.regular;

import org.apache.commons.lang.StringUtils;
import org.bson.io.BsonOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class regularTest {
    //java 正则表达式
    //regular: https://www.runoob.com/java/java-regular-expressions.html

    //react :https://www.runoob.com/react/react-tutorial.html

    public static void main(String[] args) {
        String reguklar ="^subscription_[0-9]+";//^[1-9]\d*$
        String reguklar2 ="^subscription_[1-9]\\d*$";//^[1-9]\d*$
        String reguklar3 = "^[a-z][\\d](1-5)|(6-9)$";

        System.out.println("a00".matches(reguklar3));
        System.out.println("a018".matches(reguklar3));
        System.out.println("a19".matches(reguklar3));


        //\d
        //数字字符匹配。等效于 [0-9]。

        //\D
        //非数字字符匹配。等效于 [^0-9]。

    /*    System.out.println("0subscription_".matches(reguklar2));
        System.out.println("0subscription__".matches(reguklar2));
        System.out.println("0subscrip12tion__".matches(reguklar2));

        System.out.println("subscription_10".matches(reguklar2));
        System.out.println("subscription_wqeqeaa1".matches(reguklar2));
        System.out.println("subscription_01".matches(reguklar2));
        System.out.println("subscription_0231".matches(reguklar2));*/

/*
        l.add("2313");
*/
        List<String> l=new ArrayList();
        l.add(" ");
        l.add("222");
        System.out.println(l.size());
        System.out.println(l.size()!=0?l.get(0):l.size());
        List<String> projectKey=l.stream().filter(x-> StringUtils.isNotBlank(x)).collect(Collectors.toList());
        System.out.println(projectKey==null);
        System.out.println(projectKey.size()==0);
        System.out.println(projectKey);
        String email ="^[a-zA-Z0-9_-]+((\\.)*)+([a-zA-Z0-9_-]*)+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        System.out.println("lemonw-_u@zoom.us".matches(email));
        System.out.println("lemon.wu@zoom.us".matches(email));
        System.out.println("12393721869@zoom.us".matches(email));

        System.out.println("1239372/?@zoom.us".matches(email));

        String patter="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        System.out.println("lemon.wu@zoom.us".matches(email));
        System.out.println("1293721.869@qq.com".matches(email));

    }



}
