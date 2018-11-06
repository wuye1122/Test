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
        //读取文件将介入和和企业写成map
        try {
            readFile.test1(new File("E:\\wuhl\\桌面\\其他桌面文件\\wuhl\\800企业\\201808\\IVR时间判断\\GLS_RESOURCE_NUM.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        ExeclUtils obj = new ExeclUtils();
        // 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
        File file = new File("E:\\wuhl\\桌面\\其他桌面文件\\wuhl\\800企业\\201808\\IVR时间判断\\IVR.xls");
        List excelList = obj.readExcel(file);
        System.out.println("list中的数据打印出来");
        System.out.println("excelList 的数量："+excelList.size());
        System.out.println("excelList 的内容："+excelList.toString());
        List<List<String>> newList = new ArrayList<List<String>>();
         for(int i=1;i<excelList.size();i++){
             List list = (List) excelList.get(i);
             System.out.print("第"+i+"行："+list.toString()+" list.size() "+list.size());
               List<String> ll =new ArrayList<String>();
               ll.add(list.get(0).toString());//接入号
               ll.add(readFile.map.get(list.get(0).toString()));//企业号
               ll.add(list.get(1).toString().split("\"")[1]);//开始时间
               ll.add(list.get(1).toString().split("\"")[3]);//结束时间
               newList.add(ll);
/*
              for(int j=0;j<list.size();j++){
                 if(j==1){
                     System.out.print(" 开始时间："+list.get(j).toString().split("\"")[1]);
                     System.out.print(" 结束时间："+list.get(j).toString().split("\"")[3]);
                    ((List) excelList.get(i)).add(list.get(j).toString().split("\"")[1]);
                     ((List) excelList.get(i)).add(list.get(j).toString().split("\"")[3]);
                 }else{
                     System.out.print(" 接入号："+list.get(j));
                     //System.out.println("查询对应的企业："+readFile.map.get(list.get(j)));
                  //   ((List) excelList.get(i)).add(readFile.map.get(list.get(j)));


                 }
              }*/
             System.out.println();
         }
        System.out.println("excelList 的数量："+excelList.size());
        System.out.println("excelList 的内容："+excelList.toString());
        System.out.println("newList 的数量："+newList.size());
        System.out.println("newList 的内容："+newList.toString());
          //读取文件将内容写入其他文件

       // test("E:\\wuhl\\桌面\\其他桌面文件\\wuhl\\800企业\\201808\\ivr_enterprise_info.sql",newList);

       // ivr_enterprise_info("E:\\wuhl\\桌面\\其他桌面文件\\wuhl\\800企业\\201808\\IVR时间判断\\初始化脚本\\4.5最终版本\\ivr_enterprise_info.sql",newList);
       // judge_worktimesite_temp("E:\\wuhl\\桌面\\其他桌面文件\\wuhl\\800企业\\201808\\IVR时间判断\\初始化脚本\\4.5最终版本\\judge_worktimesite_temp.sql",newList);
       // un_worktime("E:\\wuhl\\桌面\\其他桌面文件\\wuhl\\800企业\\201808\\IVR时间判断\\初始化脚本\\4.5最终版本\\un_worktime.sql",newList);
          worktime("E:\\wuhl\\桌面\\其他桌面文件\\wuhl\\800企业\\201808\\IVR时间判断\\初始化脚本\\4.5最终版本\\worktime.sql",newList);

    }
    private static void un_worktime(String a,List<List<String>> list) {
        //2行
        FileReader m= null;
        try {
            m = new FileReader(a);

            BufferedReader reader=new BufferedReader(m);
            String one=reader.readLine();
            String two=reader.readLine();
            System.out.println("当前信息：one"+one);
            System.out.println("当前信息：two"+two);

            reader.close();
            for(int i=0;i<list.size();i++){
                if(StringUtils.isNotBlank(list.get(i).get(1))&&StringUtils.isNotBlank(list.get(i).get(0))) {

                    String a1 = one.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0));
                    String a2 = two.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0));
                    System.out.println("第"+i+a1);
                    System.out.println("第"+i+a2);
                    writeFile.method2(a, a1);
                    writeFile.method2(a, a2);
                }
            }
            System.out.println("表【un_worktime】已经插入完毕！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void worktime(String a,List<List<String>> list) {
      //7行
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
            System.out.println("当前信息：one"+one);
            System.out.println("当前信息：two"+two);
            System.out.println("当前信息：f3"+f3);
            System.out.println("当前信息：f4"+f4);
            System.out.println("当前信息：f5"+f5);
            System.out.println("当前信息：f6"+f6);
            System.out.println("当前信息：f7"+f7);

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

            System.out.println("表【worktime】已经插入完毕！");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void judge_worktimesite_temp(String a,List<List<String>> list) {
     //1行
        FileReader m= null;
        try {
            m = new FileReader(a);

            BufferedReader reader=new BufferedReader(m);
            String one=reader.readLine();
            String two=reader.readLine();
            System.out.println("当前信息：one"+one);

            reader.close();
            for(int i=0;i<list.size();i++){
                if(StringUtils.isNotBlank(list.get(i).get(1))&&StringUtils.isNotBlank(list.get(i).get(0))) {
                    System.out.println("第"+i+one.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0)));
                    writeFile.method2(a, one.replace("8989", list.get(i).get(1)).replace("0000", list.get(i).get(0)));
                }
            }
            System.out.println("表【judge_worktimesite_temp】已经插入完毕！");

        } catch (Exception e) {
            e.printStackTrace();
        } //1行
    }

    private static void test(String a,List<List<String>> list) {
        FileReader m= null;
        try {
            m = new FileReader(a);

            BufferedReader reader=new BufferedReader(m);
            String nextline=reader.readLine();
            System.out.println("当前信息："+nextline);
            reader.close();
            String ivrEnterprise =nextline;
            for(int i=0;i<list.size();i++){
                System.out.println("第"+i+":"+list.get(i).toString());
              //  System.out.println(i+"更改之后信息："+ivrEnterprise.replace("8989",list.get(i).get(1)).replace("0000",list.get(i).get(0)));
              //  writeFile.method2(a,ivrEnterprise.replace("8989",list.get(i).get(1)).replace("0000",list.get(i).get(0)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } //1行
    }
    private static void ivr_enterprise_info(String a,List<List<String>> list) {
        FileReader m= null;
        try {
            m = new FileReader(a);

        BufferedReader reader=new BufferedReader(m);
            String nextline=reader.readLine();
            System.out.println("当前信息："+nextline);
        reader.close();
        String ivrEnterprise =nextline;
        for(int i=0;i<list.size();i++){
            if(StringUtils.isNotBlank(list.get(i).get(1))&&StringUtils.isNotBlank(list.get(i).get(0))){
                System.out.println(i+"更改之后信息："+ivrEnterprise.replace("8989",list.get(i).get(1)).replace("0000",list.get(i).get(0)));
                writeFile.method2(a,ivrEnterprise.replace("8989",list.get(i).get(1)).replace("0000",list.get(i).get(0)));
            }
        }

        } catch (Exception e) {
            e.printStackTrace();
        } //1行
    }

    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public List readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()返回该页的总列数
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
