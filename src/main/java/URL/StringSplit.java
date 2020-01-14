package URL;

public class StringSplit {

    /*1、如果用“.”作为分隔的话,必须是如下写法,String.split("\\."),这样才能正确的分隔开,不能用String.split(".");
    2、如果用“|”作为分隔的话,必须是如下写法,String.split("\\|"),这样才能正确的分隔开,不能用String.split("|");
    3 limit -1 尽可能多分割


    */
    public static void main(String[] args) {
        String temp = "CON:034102a5f6affa84|ANI:18017379005|PH:18017379005|SC:95511|SK:11605|SK1:11606|ID:02";
        String temp1 = "CON:034102a5f6affa84||PH:18017379005|SC:95511|SK:11605|SK1:11606|ID:02|";
        String []arr1= temp.split("|");
        //for(String ss:arr1){
        //不会得到预期结果  转义字符需要加\\
        //  System.out.println(ss);
        // }
        System.out.println("=====arr1:"+arr1.length+ "========arr1"+arr1.toString());
        String []arr2= temp1.split("\\|",-1);
        System.out.println("=====arr2======["+arr2.length+ "]");
        for(String ss:arr2){
            System.out.println("******====="+ss);
        }


    }
}
