package Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class While {

  /*         break       ����ѭ����
            continue    ����ѭ��������ִ�� ѭ������continue �������䣬ֱ�ӽ�����һѭ����
            return      �������������ص��ú��� ����*/
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
                   //continue;//���β�ִ�� �����´�
                   break;//��������ѭ��
                   // return;//����ȫ��ѭ��
                 }
                 System.out.println("������ֵ��"+aa);
             }
             System.out.println("===========");
         }




  /*      int i=0;
        while(i<10){
            i++;
            System.out.println(i);
            if(i==5) continue;//�����´�ѭ�� ���治ִ��
         *//*   if(i==6)
                break;
            System.out.println("i="+i);//����*//*
            System.out.println("i="+i);//����
            if(i==6){
                break;
            }

        }*/

    }
}
