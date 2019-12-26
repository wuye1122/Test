package Algorithm.LeetCode;

public class IsHuiWen_9 {

   /*
   * 题解 | 9. 回文数
   *  一个整数是否是回文数
   *
   *  123321
   *  121
   *
   *  解决方案一： 反转一半字符
   *
   *  解决方案二： 不需要反转字符串
   *    使用toCharArray() = char[]
   *
   * */

    public boolean isHuiWen_1( int str){
        return false;
    }

    //如何不反转字符串判断是否是回文数
    //循环字符串长度的一半  分别判断前后数字是否相同
    public boolean isHuiWen_2(int str){
         String s = String.valueOf(str);
         char[] charArr = s.toCharArray();
         for(int i=0;i<charArr.length/2;i++){
               if(charArr[i]!=charArr[charArr.length-i-1]){
                  return false;
               }
         }
           return true;
    }
    public static void main(String[] args) {

        System.out.println(new IsHuiWen_9().isHuiWen_2(11122));
        System.out.println(new IsHuiWen_9().isHuiWen_2(22122));

        //字符串 转换成字符数组 toCharArray()
        //  5/2 = 2
        String str = "34443";
        System.out.println("================================");
        System.out.println(str.toCharArray().length);
        System.out.println(str.toCharArray()[0]);
        System.out.println(str.toCharArray()[str.toCharArray().length-1]);
        System.out.println(str.toCharArray().length/2);
        for(int i=0;i<2;i++){
            System.out.println("i:"+str.toCharArray()[i]);
        }

    }
}
