package IO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.lang.StringUtils;

public class ExeclUtils {


    public static void main(String[] args) {
        //��ȡ�ļ�������ͺ���ҵд��map
        try {
            readFile.test1(new File("E:\\wuhl\\����\\���������ļ�\\wuhl\\800��ҵ\\201808\\IVRʱ���ж�\\GLS_RESOURCE_NUM.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        ExeclUtils obj = new ExeclUtils();
        // �˴�Ϊ�Ҵ���Excel·����E:/zhanhj/studysrc/jxl��
        File file = new File("E:\\wuhl\\����\\���������ļ�\\wuhl\\800��ҵ\\201808\\IVRʱ���ж�\\IVR.xls");
        List excelList = obj.readExcel(file);
        System.out.println("list�е����ݴ�ӡ����");
        System.out.println("excelList ��������"+excelList.size());
        System.out.println("excelList �����ݣ�"+excelList.toString());
        List<List<String>> newList = new ArrayList<List<String>>();
         for(int i=1;i<excelList.size();i++){
             List list = (List) excelList.get(i);
             System.out.print("��"+i+"�У�"+list.toString()+" list.size() "+list.size());
               List<String> ll =new ArrayList<String>();
               ll.add(list.get(0).toString());//�����
               ll.add(readFile.map.get(list.get(0).toString()));//��ҵ��
               ll.add(list.get(1).toString().split("\"")[1]);//��ʼʱ��
               ll.add(list.get(1).toString().split("\"")[3]);//����ʱ��
               newList.add(ll);
/*
              for(int j=0;j<list.size();j++){
                 if(j==1){
                     System.out.print(" ��ʼʱ�䣺"+list.get(j).toString().split("\"")[1]);
                     System.out.print(" ����ʱ�䣺"+list.get(j).toString().split("\"")[3]);
                    ((List) excelList.get(i)).add(list.get(j).toString().split("\"")[1]);
                     ((List) excelList.get(i)).add(list.get(j).toString().split("\"")[3]);
                 }else{
                     System.out.print(" ����ţ�"+list.get(j));
                     //System.out.println("��ѯ��Ӧ����ҵ��"+readFile.map.get(list.get(j)));
                  //   ((List) excelList.get(i)).add(readFile.map.get(list.get(j)));


                 }
              }*/
             System.out.println();
         }
        System.out.println("excelList ��������"+excelList.size());
        System.out.println("excelList �����ݣ�"+excelList.toString());
        System.out.println("newList ��������"+newList.size());
        System.out.println("newList �����ݣ�"+newList.toString());
          //��ȡ�ļ�������д�������ļ�

       // test("E:\\wuhl\\����\\���������ļ�\\wuhl\\800��ҵ\\201808\\ivr_enterprise_info.sql",newList);

       // ivr_enterprise_info("E:\\wuhl\\����\\���������ļ�\\wuhl\\800��ҵ\\201808\\IVRʱ���ж�\\��ʼ���ű�\\4.5���հ汾\\ivr_enterprise_info.sql",newList);
       // judge_worktimesite_temp("E:\\wuhl\\����\\���������ļ�\\wuhl\\800��ҵ\\201808\\IVRʱ���ж�\\��ʼ���ű�\\4.5���հ汾\\judge_worktimesite_temp.sql",newList);
       // un_worktime("E:\\wuhl\\����\\���������ļ�\\wuhl\\800��ҵ\\201808\\IVRʱ���ж�\\��ʼ���ű�\\4.5���հ汾\\un_worktime.sql",newList);
          worktime("E:\\wuhl\\����\\���������ļ�\\wuhl\\800��ҵ\\201808\\IVRʱ���ж�\\��ʼ���ű�\\4.5���հ汾\\worktime.sql",newList);

    }
    private static void un_worktime(String a,List<List<String>> list) {
        //2��
        FileReader m= null;
        try {
            m = new FileReader(a);

            BufferedReader reader=new BufferedReader(m);
            String one=reader.readLine();
            String two=reader.readLine();
            System.out.println("��ǰ��Ϣ��one"+one);
            System.out.println("��ǰ��Ϣ��two"+two);

            reader.close();
            for(int i=0;i<list.size();i++){
                if(StringUtils.isNotBlank(list.get(i).get(1))&&StringUtils.isNotBlank(list.get(i).get(0))) {

                    String a1 = one.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0));
                    String a2 = two.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0));
                    System.out.println("��"+i+a1);
                    System.out.println("��"+i+a2);
                    writeFile.method2(a, a1);
                    writeFile.method2(a, a2);
                }
            }
            System.out.println("��un_worktime���Ѿ�������ϣ�");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void worktime(String a,List<List<String>> list) {
      //7��
        FileReader m= null;
        try {
            m = new FileReader(a);

            BufferedReader reader=new BufferedReader(m);
            String one=reader.readLine();
            String two=reader.readLine();
            String f3=reader.readLine();
            String f4=reader.readLine();
            String f5=reader.readLine();
            String f6=reader.readLine();
            String f7=reader.readLine();
            System.out.println("��ǰ��Ϣ��one"+one);
            System.out.println("��ǰ��Ϣ��two"+two);
            System.out.println("��ǰ��Ϣ��f3"+f3);
            System.out.println("��ǰ��Ϣ��f4"+f4);
            System.out.println("��ǰ��Ϣ��f5"+f5);
            System.out.println("��ǰ��Ϣ��f6"+f6);
            System.out.println("��ǰ��Ϣ��f7"+f7);

            reader.close();
            for(int i=0;i<list.size();i++){

                if(StringUtils.isNotBlank(list.get(i).get(1))&&StringUtils.isNotBlank(list.get(i).get(0))) {

                    String a1 = one.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0)).replace("09:00:00", list.get(i).get(2)).replace("18:00:00", list.get(i).get(3));
                    String a2 = two.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0)).replace("09:00:00", list.get(i).get(2)).replace("18:00:00", list.get(i).get(3));
                    String a3 = f3.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0)).replace("09:00:00", list.get(i).get(2)).replace("18:00:00", list.get(i).get(3));
                    String a4 = f4.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0)).replace("09:00:00", list.get(i).get(2)).replace("18:00:00", list.get(i).get(3));
                    String a5 = f5.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0)).replace("09:00:00", list.get(i).get(2)).replace("18:00:00", list.get(i).get(3));
                    String a6 = f6.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0)).replace("09:00:00", list.get(i).get(2)).replace("18:00:00", list.get(i).get(3));
                    String a7 = f7.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0)).replace("09:00:00", list.get(i).get(2)).replace("18:00:00", list.get(i).get(3));

                    System.out.println(i+a1);
                    System.out.println(i+a2);
                    System.out.println(i+a3);
                    System.out.println(i+a4);
                    System.out.println(i+a5);
                    System.out.println(i+a6);
                    System.out.println(i+a7);

                    writeFile.method2(a, a1);
                    writeFile.method2(a, a2);
                    writeFile.method2(a, a3);
                    writeFile.method2(a, a4);
                    writeFile.method2(a, a5);
                    writeFile.method2(a, a6);
                    writeFile.method2(a, a7);
                }
            }

            System.out.println("��worktime���Ѿ�������ϣ�");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void judge_worktimesite_temp(String a,List<List<String>> list) {
     //1��
        FileReader m= null;
        try {
            m = new FileReader(a);

            BufferedReader reader=new BufferedReader(m);
            String one=reader.readLine();
            String two=reader.readLine();
            System.out.println("��ǰ��Ϣ��one"+one);

            reader.close();
            for(int i=0;i<list.size();i++){
                if(StringUtils.isNotBlank(list.get(i).get(1))&&StringUtils.isNotBlank(list.get(i).get(0))) {
                    System.out.println("��"+i+one.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0)));
                    writeFile.method2(a, one.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0)));
                }
            }
            System.out.println("��judge_worktimesite_temp���Ѿ�������ϣ�");

        } catch (Exception e) {
            e.printStackTrace();
        } //1��
    }

    private static void test(String a,List<List<String>> list) {
        FileReader m= null;
        try {
            m = new FileReader(a);

            BufferedReader reader=new BufferedReader(m);
            String nextline=reader.readLine();
            System.out.println("��ǰ��Ϣ��"+nextline);
            reader.close();
            String ivrEnterprise =nextline;
            for(int i=0;i<list.size();i++){
                System.out.println("��"+i+":"+list.get(i).toString());
              //  System.out.println(i+"����֮����Ϣ��"+ivrEnterprise.replace("8989",list.get(i).get(1)).replace("0000",list.get(i).get(0)));
              //  writeFile.method2(a,ivrEnterprise.replace("8989",list.get(i).get(1)).replace("0000",list.get(i).get(0)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } //1��
    }
    private static void ivr_enterprise_info(String a,List<List<String>> list) {
        FileReader m= null;
        try {
            m = new FileReader(a);

        BufferedReader reader=new BufferedReader(m);
            String nextline=reader.readLine();
            System.out.println("��ǰ��Ϣ��"+nextline);
        reader.close();
        String ivrEnterprise =nextline;
        for(int i=0;i<list.size();i++){
            if(StringUtils.isNotBlank(list.get(i).get(1))&&StringUtils.isNotBlank(list.get(i).get(0))){
                System.out.println(i+"����֮����Ϣ��"+ivrEnterprise.replace("8989",list.get(i).get(1)).replace("0000",list.get(i).get(0)));
                writeFile.method2(a,ivrEnterprise.replace("8989",list.get(i).get(1)).replace("0000",list.get(i).get(0)));
            }
        }

        } catch (Exception e) {
            e.printStackTrace();
        } //1��
    }

    // ȥ��Excel�ķ���readExcel���÷�������ڲ���Ϊһ��File����
    public List readExcel(File file) {
        try {
            // ��������������ȡExcel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl�ṩ��Workbook��
            Workbook wb = Workbook.getWorkbook(is);
            // Excel��ҳǩ����
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<List>();
                // ÿ��ҳǩ����һ��Sheet����
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()���ظ�ҳ��������
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()���ظ�ҳ��������
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if(cellinfo.isEmpty()){
                            continue;
                        }
                        innerList.add(cellinfo);
                      //  System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);
                }
                return outerList;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
