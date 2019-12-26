package Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class While {

  /*         break       跳出循环；
            continue    继续循环，（不执行 循环体内continue 后面的语句，直接进行下一循环）
            return      跳出函数，返回调用函数 处。*/
    public static void main(String[] args) {

        String tel = "tal:9999";
        System.out.println(tel.startsWith(""));
        System.out.println(tel.startsWith("tal:99"));
        System.out.println(tel.startsWith("TEL:99"));

        List<List<String>> list =new ArrayList<List<String>>();

        List<String> list1 = new ArrayList<String>();
        list1.add("a1");
        list1.add("b1");
        list1.add("c1");
        list1.add("d1");
        list1.add("e1");
        List<String> list2 = new ArrayList<String>();
        list2.add("a2");
        list2.add("b2");
        list2.add("c2");
        list2.add("d2");
        list2.add("e2");
        list.add(list1);
        list.add(list2);
        System.out.println("list===============:"+list);

         for(int i=0;i<list.size();i++){
             List<String>  ll=   list.get(i);
             Iterator<String> it=  ll.iterator();
             while(it.hasNext()){
                 String aa=(String)it.next();
                 if(aa.contains("c")){
                   //continue;//本次不执行 继续下次
                   break;//跳出本次循环
                   // return;//跳出全部循环
                 }
                 System.out.println("本次数值："+aa);
             }
             System.out.println("===========");
         }




  /*      int i=0;
        while(i<10){
            i++;
            System.out.println(i);
            if(i==5) continue;//进入下次循环 后面不执行
         *//*   if(i==6)
                break;
            System.out.println("i="+i);//跳出*//*
            System.out.println("i="+i);//跳出
            if(i==6){
                break;
            }

        }*/

    }
}
