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
         * 2.java�޸�xml
         */
        // ����SAXReader����
        SAXReader sr = new SAXReader(); // ��Ҫ����jar��:dom4j
        // ����xml
        Document document = null;
        try {
            //  Document document =saxReader.read("D:/workspace/dom4/src/main/resources/mods.xml");
            document = sr.read("E:\\wuhl\\Index_Start_1.usml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        System.out.println(root.getName());
        //TODO ���PlayFile
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
            System.out.println("��Ŀ¼��"+ServiceElement);
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
        // ��������ľ�̬�������xml��д��
    }


    public static void java_XiuGai__XML(Tree.Node node) throws IOException {
        /*
         * 2.java�޸�xml
         */
        // ����SAXReader����
        SAXReader sr = new SAXReader(); // ��Ҫ����jar��:dom4j
        // ����xml
        Document document = null;
        try {
          //  Document document =saxReader.read("D:/workspace/dom4/src/main/resources/mods.xml");
            document = sr.read("E:\\wuhl\\Index_Start_1.usml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        System.out.println(root.getName());
        //TODO ���PlayFile
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


         //TODO �ڵ�ָ�ĵط���Ҫ ���ӽڵ�����Ϣ
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
            map2.put("TxtString","��һ��ʹ󹦸����");

            BranchItem(Branch_Node4,"7",map2);

            Branch_Node4.addElement("Default");*/

        }
        // ��������ľ�̬�������xml��д��
         saveDocument(document, new File("E:\\wuhl\\Index_Start_5.usml"));
    }

    public static void Java_Write_XML() throws IOException {
        /*
         * 1.javaдxml
         */

        // ����xml�ĵ�һ�� <?xml version="1.0" encoding="UTF-8"?>
        Document document = DocumentHelper.createDocument();

        // ���һ��Ԫ��,��Ϊ��Ԫ��students
        Element root = document.addElement("students");

        // ��Ԫ���µ�һ��Ԫ��student
        Element student = root.addElement("student");
        // ��student��ǩ���������
        student.addAttribute("id", "it001");

        // Ϊ�����Ԫ��
        Element name = student.addElement("name");
        Element name1 = student.addElement("name");
        Element sex = student.addElement("sex");
        name.setText("С��");
        name1.setText("С��");
        sex.setText("��");

        // ����Ĳ��������ڴ���

        // ��������ľ�̬�������xml��д��
        saveDocument(document, new File("students.xml"));
    }

    // �����Ϊ�̶�����---------�������java��XML��д,�ĵȲ���
    //https://blog.csdn.net/yeluo414/article/details/73293522
    public static void saveDocument(Document document, File xmlFile) throws IOException {
        Writer osWrite = new OutputStreamWriter(new FileOutputStream(xmlFile));// ���������
        OutputFormat format = OutputFormat.createPrettyPrint(); // ��ȡ�����ָ����ʽ
        format.setEncoding("UTF-8");// ���ñ��� ��ȷ��������xmlΪUTF-8��ʽ
        XMLWriter writer = new XMLWriter(osWrite, format);// XMLWriter
        // ָ������ļ��Լ���ʽ
        writer.write(document);// ��documentд��xmlFileָ�����ļ�(����Ϊ���������ļ������´������ļ�)
        writer.flush();
        writer.close();
    }

}
