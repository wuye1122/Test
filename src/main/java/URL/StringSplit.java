package URL;

public class StringSplit {

    /*1������á�.����Ϊ�ָ��Ļ�,����������д��,String.split("\\."),����������ȷ�ķָ���,������String.split(".");
    2������á�|����Ϊ�ָ��Ļ�,����������д��,String.split("\\|"),����������ȷ�ķָ���,������String.split("|");
    3 limit -1 �����ܶ�ָ�


    */
    public static void main(String[] args) {
        String temp = "CON:034102a5f6affa84|ANI:18017379005|PH:18017379005|SC:95511|SK:11605|SK1:11606|ID:02";
        String temp1 = "CON:034102a5f6affa84||PH:18017379005|SC:95511|SK:11605|SK1:11606|ID:02|";
        String []arr1= temp.split("|");
        //for(String ss:arr1){
        //����õ�Ԥ�ڽ��  ת���ַ���Ҫ��\\
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
