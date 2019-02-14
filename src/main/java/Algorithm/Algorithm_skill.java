package Algorithm;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithm_skill {


    //1 巧用数组下标统计
   /*    ASCII:
         A-Z : 65  90
         a-z : 97  122
         0-9 : 48  57


      //评测题目:
     1. 对输入的所有字符串数组（字符串均为小写）进行分组，分组规则为同一分组内的字符串都由相同字符组成。
        如对字符串数组:["eat","tea","tan","ate","nat","bat"]分组结果为:[["ate","eat","tea"],["nat","tan"],["bat"]]，
        请写java代码实现此需求。
         String  srr26 ="";//长度为26位字符串


*/


       public static void main(String[] args) {

           Map m = new HashMap();
           //m.put();
           //字母 出现个数
           showNumOfChar();

           //字母显示的个数
           groupAnagrams();

    }

    //Group Anagrams
    private static void groupAnagrams() {

           String [] strArr = new String[]{"eat","tea","tan","ate","nat","bat"};
           //将乱序字母  找出相同的key

             for(int i=0;i<strArr.length;i++){
                  char[] ch =strArr[i].toCharArray();
                  System.out.println("乱序ch:"+new String(ch));
                  Arrays.sort(ch);
                  System.out.println("排序ch:"+new String(ch));

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
              System.out.println("字母："+j+"个数："+a[i]);
          }
        }
    }
}
