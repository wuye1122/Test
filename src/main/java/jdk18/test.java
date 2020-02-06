package jdk18;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;

public class test {

    public static boolean isSuccess(int a){
        try{
            Integer status = a;
            if(null!=status&&(status >= 200 && status < 300)){
                return true;
            }else{
                throw  new Exception("this is a success access!");
            }
        }catch (Exception e){
            System.out.println("e:"+e.getMessage());
            return false;
        }

    }

    public static final String JIRA_ASSIGNAEDITCTION = "jiraAssignEditAction";

    public static void main(String[] args) {

        //request 4.5.6.7.8


        System.out.println("be ready to release");


        //用户可能查询的是

        String str = "jiraAssignEditAction";
        System.out.println(test.JIRA_ASSIGNAEDITCTION.equals(str));
        System.out.println(str.equals(test.JIRA_ASSIGNAEDITCTION));
        // request 2


        //request3
        System.out.println("2222");

        System.out.println(StringUtils.isBlank(StringUtils.EMPTY));;
        for(int i=0;i<10;i++){
            if(i==5){
                continue;
            }
            if(i==6){
                continue;
            }
            System.out.println(i);

        }

        String context = "unassign";
        System.out.println(("Unassigned".toLowerCase().startsWith(context.toLowerCase())));
        System.out.println("Unassigned".startsWith(context));

        String path = "/node_modules\\@ant-design/";
        String path1 = "/node_modules\\/@ant-design/";
        System.out.println(path);
        System.out.println(path1);

        String str2="  son  g1";
      /*  1) + URL 中+号表示空格 %2B
        2) 空格 URL中的空格可以用+号或者编码 %20
        3) / 分隔目录和子目录 %2F
        4) ? 分隔实际的 URL 和参数 %3F
        5) % 指定特殊字符 %25
        6) # 表示书签 %23
        7) &amp; URL 中指定的参数间的分隔符 %26
        8) = URL 中指定参数的值 %3D*/
      String [] character = new String[]{"/"," ","?","%","#","&amp","=","+","11  22"};

            Arrays.stream(character).forEach(x->
            {
                try {
                    System.out.println(URLEncoder.encode(x.trim(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });



    }
}
