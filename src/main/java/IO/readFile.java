package IO;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class readFile {
    //��File��Ϊ����ԴFile->FileReader
    public static Map<String,String> map=new HashMap<String,String>();
    public static void test1(File source) throws Exception {
        FileReader m=new FileReader(source);
        BufferedReader reader=new BufferedReader(m);

        while(true) {
            String nextline=reader.readLine();
            if(nextline==null) break;

         //  System.out.println(nextline);

            if(StringUtils.isNotBlank(nextline)&&nextline.contains("\"")&&nextline.split("\"").length>=5){
              map.put(nextline.split("\"",-1)[5],nextline.split("\"",-1)[3]);
           }
        }
        System.out.println(readFile.map.size());
        System.out.println(readFile.map.toString());
        reader.close();

    }

    //��InputStream��Ϊ����Դ InputStream->InputStreamReader
    public static void test2(InputStream source)throws Exception{
        InputStreamReader m=new InputStreamReader(source,"GBK");
        BufferedReader reader=new BufferedReader(m);

        while(true) {
            String nextline=reader.readLine();
            if(nextline==null) break;
            System.out.println("got:"+nextline);
        }
        reader.close();
    }

    public static void test3()throws Exception
    {
        InputStreamReader m=new InputStreamReader(System.in);
        BufferedReader reader=new BufferedReader(m);
        while(true) {
            System.out.print(">");
            String nextline=reader.readLine();
            if(nextline==null) break;
            if("exit".equals(nextline)) {
                System.out.println("Good Bye");
                break;
            }
            //�����û�����
            System.out.println("handle command:"+nextline);
        }
        reader.close();
    }


    public static void testLTDSJ(File source) throws Exception {
        FileReader m=new FileReader(source);
        BufferedReader reader=new BufferedReader(m);
  /*      insert  into `CFG_DATA_PUSH`(`ENT_ID`,`SERVICE_NAME`,`EVENT_TYPE`,`CONDITIONS`,`CUST_URL`,`REPUSH_TIMES`,`PUSH_STATUS`,`DDS_VERSION`,`ADAPTER_ID`)
        value ('LTBD2018100807','��ϸ','R_DETAIL','253','http://10.130.29.226:8081/dps/target/test_post.do','1','1','1',13)
        insert  into `CFG_DATA_PUSH`(`ENT_ID`,`SERVICE_NAME`,`EVENT_TYPE`,`CONDITIONS`,`CUST_URL`,`REPUSH_TIMES`,`PUSH_STATUS`,`DDS_VERSION`,`ADAPTER_ID`)
        value ('LTBD2018100807','¼��','6','253','http://10.130.29.226:8081/dps/target/test_post.do','1','1','1',13)
*/

    /*    insert  into `CFG_DATA_PUSH`(`ID`,`ENT_ID`,`SERVICE_NAME`,`EVENT_TYPE`,`CONDITIONS`,`CUST_URL`,`REPUSH_TIMES`,`PUSH_STATUS`,`DDS_VERSION`,`ADAPTER_ID`)
        value ('10','LTBD2018070612','��ϸ','R_DETAIL','253','http://10.130.29.226:8081/dps/target/test_post.do','1','1','1',13)
*/

     /*    insert  into `CFG_DATA_PUSH`(`ID`,`ENT_ID`,`SERVICE_NAME`,`EVENT_TYPE`,`CONDITIONS`,`CUST_URL`,`REPUSH_TIMES`,`PUSH_STATUS`,`DDS_VERSION`,`ADAPTER_ID`)
        value ('      10','LTBD2018070612        ','¼��','R_DETAIL','253','http://10.130.29.226:8081/dps/target/test_post.do','1','1','1',13)
*/
        String A = "insert  into `CFG_DATA_PUSH`(`ID`,`ENT_ID`,`SERVICE_NAME`,`EVENT_TYPE`,`CONDITIONS`,`CUST_URL`,`REPUSH_TIMES`,`PUSH_STATUS`,`DDS_VERSION`,`ADAPTER_ID`)\n" +
             " values ";
        String B = "','��ϸ','R_DETAIL','253','http://10.130.29.226:8081/dps/target/test_post.do','1','1','1',13) ";

        String C = "','¼��','6','253','http://10.130.29.226:8081/dps/target/test_post.do','1','1','1',13)";



 /*       insert  into `CFG_DATA_PUSH`(`ID`,`ENT_ID`,`SERVICE_NAME`,`EVENT_TYPE`,`CONDITIONS`,`CUST_URL`,`REPUSH_TIMES`,`PUSH_STATUS`,`DDS_VERSION`,`ADAPTER_ID`)
        values ('LTBD20180823031','LTBD2018082303 ','��ϸ','R_DETAIL','253','http://10.130.29.226:8081/dps/target/test_post.do','1','1','1',13) ,
                ('LTBD20180823032','LTBD2018082303 ','¼��','6','253','http://10.130.29.226:8081/dps/target/test_post.do','1','1','1',13)
       */
        System.out.println(A);
        while(true) {
            String nextline=reader.readLine();
            if(nextline==null) break;


            //  System.out.println(nextline);
            System.out.println("('"+nextline.trim()+"1','"+nextline.trim()+B+",");
            System.out.println("('"+nextline.trim()+"2','"+nextline.trim()+C+",");

        }
        System.out.println(readFile.map.size());
        System.out.println(readFile.map.toString());
        reader.close();

    }

    public static void testLT(File source) throws Exception {
        FileReader m=new FileReader(source);
        BufferedReader reader=new BufferedReader(m);
         String s="";
        while(true) {
            String nextline=reader.readLine();
            if(nextline==null) break;
             s=s+nextline+",";
        }
        System.out.println(s);
        reader.close();

    }
    public static void main(String[] args){
        try {
  /*          String a="\"OID\",\"ENTERPRISEID\",\"RESOURCE_NUM\",\"RESOURCE_DESC\"";

            System.out.println(a.split("\"")[5]);
            System.out.println(a.split("\"")[3]);
*/
           // test1(new File("E:\\JUC\\����\\���������ļ�\\JUC\\800��ҵ\\201808\\IVRʱ�����\\IVRʱ���ж�\\GLS_RESOURCE_NUM.txt"));
          /*  System.out.println(readFile.map.size());
            System.out.println(readFile.map.toString());*/
            testLTDSJ(new File("E:\\JUC\\����\\���������ļ�\\JUC\\800��ҵ\\201810\\dps���Ѳ�����kafka\\txt.txt"));

            //test2(new FileInputStream("c:/example/aaa"));
            //test3();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
