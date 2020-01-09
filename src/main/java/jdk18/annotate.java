package jdk18;

import com.google.common.collect.Maps;

import java.util.*;

@Deprecated
@anno
public class annotate {

    public String A(String s){
        returnB(s);
        System.out.println("==");
        if(s.equals("3")){
            return "4";
        }
        return s;
    }
    void returnB(String s){
        if(s.equals("3")){
            return;
        }
    }
    public static void main(String[] args) {


    //return
        System.out.println("==="+new annotate().A("3"));


        //Annotate
        System.out.println(annotate.class.getAnnotations().length);
        System.out.println(Arrays.asList(annotate.class.getAnnotations()).toString());

    //lambda
        Map<Integer,String> map = Maps.newHashMap();
        List<Integer> definitionList = new ArrayList<Integer>();
        definitionList.add(1);
        definitionList.add(2);
        definitionList.add(3);
        definitionList.add(4);
        definitionList.add(null);

        String context="7";
        definitionList.forEach(d -> Optional.ofNullable(invoke(d, context)).ifPresent(c -> map.put(d,c)));

        System.out.println("个数："+map.size());
        System.out.println("栈信息："+map.toString());
    }

    public static String invoke(Integer d,String context){

        String str = null;

        if (d!=null&&d.intValue()%2==0){//偶数:偶数
            str=String.valueOf(d.intValue());


        }else{
            str=context;

        }
        if(d==null){
            str=null;
        }
        System.out.println("str:"+str);
        return str;
        //request4
        //222222222222222222222222222222
        //add new request
    }
}
