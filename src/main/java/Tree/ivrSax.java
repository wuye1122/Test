package Tree;

import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import Tree.Ivr.Tree;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import static Tree.NodeClass.PlayFile;

public class ivrSax {
    public static void main(String[] args) throws IOException {

        //Java_Write_XML(); //javaдxml
        /* Map<String,List<ivrTreeList.Node>> nodeMap = new LinkedHashMap<>();
         */
        java_XiuGai__XML(); // java�޸�xml

  /*     String str ="svgPutKeyBean3,svgVoiceBean2,svgVoiceBean3,";
       String target = "svgPutKeyBean3,";
        System.out.println(str);
        System.out.println(str.indexOf(target)!=-1);
        str = str.replace("svgPutKeyBean3,","");
        str = str.replace("svgVoiceBean2,","");
        str = str.replace("svgVoiceBean3,","");
        System.out.println(str);
        System.out.println(StringUtils.isBlank(str));*/



    }

    public static void java_XiuGai__XML() throws IOException {
        /*
         * 2.java�޸�xml
         */
        // ����SAXReader����
        SAXReader sr = new SAXReader(); // ��Ҫ����jar��:dom4j
        // ����xml
        Document document = null;
        try {
            //  Document document =saxReader.read("D:/workspace/dom4/src/main/resources/mods.xml");
            document = sr.read("E:\\JUC\\Index_Start_1.usml");
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

           // PlayFile(ownerElement,"1",new HashMap<String, String>());
            // GetDTMF(ownerElement,"2",new ArrayList<Tree.Node>());
            Element Branch_Node3 = ownerElement.addElement("Branch_Node");
            Branch_Node3.addAttribute("NAME","3?IO �ڵ�");
            Branch_Node3.addAttribute("VarName","m_PrevResult");
            Branch_Node3.addAttribute("Expanded","true");

            Element BranchItem =  Branch_Node3.addElement("BranchItem");
            BranchItem.addAttribute("Operator","Equal");
            BranchItem.addAttribute("ItemExpression","No_Error");

            Element Branch_Node4 =  BranchItem.addElement("Branch_Node");
            Branch_Node4.addAttribute("NAME","4?IO �ڵ�");
            Branch_Node4.addAttribute("VarName","m_DTMFBUF");
            Branch_Node4.addAttribute("Expanded","true");

            //TODO �ڵ�ָ�ĵط���Ҫ ���ӽڵ�����Ϣ
            /*Map<String,String> map = new HashMap<>();
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

            BranchItem(Branch_Node4,"7",map2);*/

            Branch_Node4.addElement("Default");
            Branch_Node3.addElement("Default");
          /*  Element IO_Node = ownerElement.addElement("IO_Node");
            //TODO ����ڵ���Ҫ��������ֵ���и���
            IO_Node.addAttribute("Name","1?IO �ڵ�");
            IO_Node.addAttribute("ProcessError","false");
            IO_Node.addAttribute("Expanded","true");

            Element OutVarNameSet = IO_Node.addElement("OutVarNameSet");

            Element ResultVarName = OutVarNameSet.addElement("ResultVarName");
            ResultVarName.addText("m_PrevResult");
            Element ResultVarName1 = OutVarNameSet.addElement("ResultVarName");
            ResultVarName1.addText("m_LastError");

            Element PlayFile = IO_Node.addElement("PlayFile");
            //TODO  FileName   PlayCount  WaitTimeOnce
            PlayFile.addAttribute("FileName","Voice\\PV_Welcome.wav");
            PlayFile.addAttribute("PlayCount","1");
            PlayFile.addAttribute("WaitTimeOnce","5");
            PlayFile.addAttribute("Rate","6");
            PlayFile.addAttribute("CanBreak","true");
            PlayFile.addAttribute("BreakList","");
*/
            /*         ownerElement.setText("Tshinghua");
            Element dateElement = ownerElement.addElement("date");
            dateElement.setText("2004-09-11");
            dateElement.addAttribute("type", "Gregorian calendar");*/
        }
        // ��������ľ�̬�������xml��д��
        saveDocument(document, new File("E:\\JUC\\Index_Start_5.usml"));
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
