package Algorithm;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithm_skill {


    //1 ���������±�ͳ��
   /*    ASCII:
         A-Z : 65  90
         a-z : 97  122
         0-9 : 48  57


      //������Ŀ:
     1. ������������ַ������飨�ַ�����ΪСд�����з��飬�������Ϊͬһ�����ڵ��ַ���������ͬ�ַ���ɡ�
        ����ַ�������:["eat","tea","tan","ate","nat","bat"]������Ϊ:[["ate","eat","tea"],["nat","tan"],["bat"]]��
        ��дjava����ʵ�ִ�����
         String  srr26 ="";//����Ϊ26λ�ַ���


*/


       public static void main(String[] args) {

           Map m = new HashMap();
           //m.put();
           //��ĸ ���ָ���
           showNumOfChar();

           //��ĸ��ʾ�ĸ���
           groupAnagrams();

    }

    //Group Anagrams
    private static void groupAnagrams() {

           String [] strArr = new String[]{"eat","tea","tan","ate","nat","bat"};
           //��������ĸ  �ҳ���ͬ��key

             for(int i=0;i<strArr.length;i++){
                  char[] ch =strArr[i].toCharArray();
                  System.out.println("����ch:"+new String(ch));
                  Arrays.sort(ch);
                  System.out.println("����ch:"+new String(ch));

             }


    }



    private static void showNumOfChar() {
        int [] a = new int[128];
        String str = "adasdaaaafff";
        //a-z
        System.out.println("str_a:"+(int)'a');
        System.out.println("str_z:"+(int)'z');
        //A-Z
        System.out.println("str_A:"+(int)'A');
        System.out.println("str_Z:"+(int)'Z');
        //0-9
        System.out.println("str_0:"+(int)'0');
        System.out.println("str_9:"+(int)'9');
        System.out.println("===============================");
        for(int i=0;i<str.length();i++){
            a[str.charAt(i)]++;
        }
        for(int i=0;i<a.length;i++){
          if(0!=a[i]){
             // System.out.println("i:"+a[i]);
              char j = (char)i;
              System.out.println("��ĸ��"+j+"������"+a[i]);
          }
        }
    }
}
