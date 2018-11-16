package Tree.Ivr;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.*;

import static Tree.NodeClass.*;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;


public class ivrSaxXml {
    public static void main(String[] args) throws IOException {
        javaParent();
    }


    public static void javaParent() throws IOException {
        /*
         * 2.java修改xml
         */
        // 创建SAXReader对象
        SAXReader sr = new SAXReader(); // 需要导入jar包:dom4j
        // 关联xml
        Document document = null;
        try {
            //  Document document =saxReader.read("D:/workspace/dom4/src/main/resources/mods.xml");
            document = sr.read("E:\\wuhl\\Index_Start_1.usml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        System.out.println(root.getName());
        //TODO 完成PlayFile
        List  list = document.selectNodes("/Service/WorkflowSet/Workflow");
        System.out.println("size:========"+list.size());
        Iterator iter = list.iterator();
        if (iter.hasNext()) {
            Element ownerElement = (Element) iter.next();
            System.out.println("ownerElement:"+ownerElement);
            System.out.println("ownerElement:name "+ownerElement.getName());

            Element ServiceElement = ownerElement.getParent();
            while(!ServiceElement.getName().equals("Service")){
                ServiceElement = ServiceElement.getParent();
            }
            System.out.println("根目录："+ServiceElement);
            Element ServiceElement1 = ServiceElement.getParent();
            while(null!=ServiceElement1&&!ServiceElement1.getName().equals("Service")){
                ServiceElement = ServiceElement.getParent();
            }
           // List  list1 = owner.selectNodes("/ImportServiceSet");
       /*     List  list1 = ServiceElement.selectNodes("/Service/ImportServiceSet");

            System.out.println("list1:"+list1.get(0));
            Element ImportServiceSetElement = (Element) list1.get(0);
            System.out.println("ImportServiceSetElement:"+ImportServiceSetElement);
            System.out.println("ImportServiceSetElement:name:"+ImportServiceSetElement.getName());
*/

        }
        // 调用下面的静态方法完成xml的写出
    }


    public static void java_XiuGai__XML(Tree.Node node) throws IOException {
        /*
         * 2.java修改xml
         */
        // 创建SAXReader对象
        SAXReader sr = new SAXReader(); // 需要导入jar包:dom4j
        // 关联xml
        Document document = null;
        try {
          //  Document document =saxReader.read("D:/workspace/dom4/src/main/resources/mods.xml");
            document = sr.read("E:\\wuhl\\Index_Start_1.usml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        System.out.println(root.getName());
        //TODO 完成PlayFile
        List  list = document.selectNodes("/Service/WorkflowSet/Workflow");
        System.out.println("size:========"+list.size());
        Iterator iter = list.iterator();
        if (iter.hasNext()) {
            Element ownerElement = (Element) iter.next();
            System.out.println("ownerElement:"+ownerElement);
            System.out.println(ownerElement.getName());
            bulidXML(ownerElement, node,"1");
            //bulidXML(ownerElement,,"1");
           // PlayFile(ownerElement,"1",new HashMap<String, String>());
          //   GetDTMF(ownerElement,"2",list);


         //TODO 节点分割的地方需要 存子节点自信息
        /*    Map<String,String> map = new HashMap<>();
            map.put("son","PlayFile");
            map.put("ItemExpression","1");
            BranchItem(Branch_Node4,"5",map);
            Map<String,String> map1 = new HashMap<>();
            map1.put("son","PlayFile");
            map1.put("ItemExpression","2");
            BranchItem(Branch_Node4,"6",map1);

            Map<String,String> map2 = new HashMap<>();
            map2.put("son","PlayVoiceByTTS");
            map2.put("ItemExpression","3");
            map2.put("TxtString","差一点就大功告成了");

            BranchItem(Branch_Node4,"7",map2);

            Branch_Node4.addElement("Default");*/

        }
        // 调用下面的静态方法完成xml的写出
         saveDocument(document, new File("E:\\wuhl\\Index_Start_5.usml"));
    }

    public static void Java_Write_XML() throws IOException {
        /*
         * 1.java写xml
         */

        // 生成xml的第一行 <?xml version="1.0" encoding="UTF-8"?>
        Document document = DocumentHelper.createDocument();

        // 添加一个元素,作为根元素students
        Element root = document.addElement("students");

        // 根元素下的一个元素student
        Element student = root.addElement("student");
        // 在student标签里添加属性
        student.addAttribute("id", "it001");

        // 为其添加元素
        Element name = student.addElement("name");
        Element name1 = student.addElement("name");
        Element sex = student.addElement("sex");
        name.setText("小红");
        name1.setText("小明");
        sex.setText("男");

        // 上面的操作都在内存中

        // 调用下面的静态方法完成xml的写出
        saveDocument(document, new File("students.xml"));
    }

    // 下面的为固定代码---------可以完成java对XML的写,改等操作
    //https://blog.csdn.net/yeluo414/article/details/73293522
    public static void saveDocument(Document document, File xmlFile) throws IOException {
        Writer osWrite = new OutputStreamWriter(new FileOutputStream(xmlFile));// 创建输出流
        OutputFormat format = OutputFormat.createPrettyPrint(); // 获取输出的指定格式
        format.setEncoding("UTF-8");// 设置编码 ，确保解析的xml为UTF-8格式
        XMLWriter writer = new XMLWriter(osWrite, format);// XMLWriter
        // 指定输出文件以及格式
        writer.write(document);// 把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)
        writer.flush();
        writer.close();
    }

}
