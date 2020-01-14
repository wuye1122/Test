package Tree;

import IO.FileCopyUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.lang.StringUtils;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.*;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Properties;

public class ivrUsml {

    public static void read() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
          //  InputStream in = new FileInputStream(new File("E:\\JUC\\Index_GetDTMF_PlayFile.usml"));

            InputStream in = new FileInputStream(new File("E:\\JUC\\桌面\\其他桌面文件\\JUC\\800企业\\201810\\IVR自助配置\\test\\Index_Start.usml"));

            Document doc = builder.parse(in);
            // root <university>
            System.out.println(doc.getDocumentElement().getTagName());
            Element root = doc.getDocumentElement();

            System.out.println("root====:"+root.getTagName());

            NodeList collegeNodes = root.getChildNodes();
            System.out.println("collegeNodes====:"+collegeNodes.getLength());

            for (int i = 0; i < collegeNodes.getLength(); i++) {
             /*   System.out.println("第【"+i+"】"+collegeNodes.item(i));
                System.out.println("性质:"+collegeNodes.item(i).getNodeType());*/
                if(collegeNodes.item(i).getNodeType()==Node.ELEMENT_NODE&&collegeNodes.item(i).getNodeName().equals("WorkflowSet")){
                    for(int j = 0 ; j<collegeNodes.item(i).getChildNodes().getLength();j++){
                        System.out.println("第【"+i+"】【"+j+"】node:"+collegeNodes.item(i).getChildNodes().item(j));
                        System.out.println("第【"+i+"】【"+j+"】map:"+collegeNodes.item(i).getChildNodes().item(j).getAttributes());

                        NamedNodeMap map = collegeNodes.item(i).getChildNodes().item(j).getAttributes();
                        if(null!=map){
                            for(int z=0;z<map.getLength();z++){
                                System.out.println("map=================:"+map.item(z));
                                if(null!=map.getNamedItem("VarName")){
                                    System.out.println(map.getNamedItem("VarName").getNodeName());
                                    System.out.println(map.getNamedItem("VarName").getNodeValue());
                                }

                            }
                        }
                        System.out.println("第【"+i+"】【"+j+"】nodeValue:"+collegeNodes.item(i).getChildNodes().item(j).getNodeValue());
                        System.out.println("第【"+i+"】【"+j+"】nodeNode:"+collegeNodes.item(i).getChildNodes().item(j).getNodeName());
                    }

                }
                if(collegeNodes.item(i).getNodeType()==Node.TEXT_NODE){
                    System.out.println("text======="+collegeNodes.item(i).getNodeValue());
                }
                System.out.println("长度:"+collegeNodes.item(i).getChildNodes().getLength());

            }
            System.out.println(doc.getDocumentElement().hasAttribute("FileName"));
            if (root == null){ return;}
            System.out.println("root:"+root.toString());
            System.out.println("getName:"+root.getAttribute("name"));
            System.out.println("==============================================");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void write() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            InputStream in = new FileInputStream(new File("E:\\JUC\\Index_Start_1.usml"));
            Document doc = builder.parse(in);
            // root <university>
            Element root = doc.getDocumentElement();
            if (root == null) {return;}
            // 修改属性
            NodeList collegeNodes = root.getChildNodes();
            if (collegeNodes != null) {
                for (int i = 0; i < collegeNodes.getLength() - 1; i++) {
                    // 删除节点
                    Node college = collegeNodes.item(i);
                    if (college.getNodeType() == Node.ELEMENT_NODE&& college.getNodeName().equals("WorkflowSet")) {
                        System.out.println(college.getNodeName());
                        NodeList childNodes  =  college.getChildNodes();
                        System.out.println(childNodes.getLength());
                        for(int j=0;j<childNodes.getLength();j++){
                            Node temp = childNodes.item(j);
                            if(temp.getNodeName().equals("Workflow")){
                               //TODO 需要抽象出方法
                              //  temp.appendChild(NodeClass.PlayFile(doc));
                                Element IO_Node = doc.createElement("IO_Node");
                                //TODO 这个节点需要根据流程值进行更改
                                IO_Node.setAttribute("Name","1?IO 节点");
                                IO_Node.setAttribute("ProcessError","false");
                                IO_Node.setAttribute("Expanded","true");
                                Element OutVarNameSet = doc.createElement("OutVarNameSet");
                                Element ResultVarName = doc.createElement("ResultVarName");
                                ResultVarName.setTextContent("m_PrevResult");
                                Element ResultVarName1 = doc.createElement("ResultVarName");
                                ResultVarName1.setTextContent("m_LastError");


                                OutVarNameSet.appendChild(ResultVarName);
                                OutVarNameSet.appendChild(ResultVarName1);
                                Element PlayFile = doc.createElement("PlayFile");
                                //TODO  FileName   PlayCount  WaitTimeOnce
                                PlayFile.setAttribute("FileName","Voice\\PV_Welcome.wav");
                                PlayFile.setAttribute("PlayCount","1");
                                PlayFile.setAttribute("WaitTimeOnce","5");
                                PlayFile.setAttribute("Rate","6");
                                PlayFile.setAttribute("CanBreak","true");
                                PlayFile.setAttribute("BreakList","");
                                IO_Node.appendChild(OutVarNameSet);
                                IO_Node.appendChild(PlayFile);
                            //    temp.appendChild(IO_Node);

                            }

                        }
                    }
                }
            }
            // 将修改后的文档保存到文件
         TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transFormer = transFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
          //  transFormer.setOutputProperty("encoding", "utf-8");
            transFormer.setOutputProperty("indent", "yes");
            File file = new File("E:\\JUC\\Index_Start_2.usml");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            StreamResult xmlResult = new StreamResult(out);
            transFormer.transform(domSource, xmlResult);
           System.out.println(file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void saveDocument(org.dom4j.Document document, File xmlFile) throws IOException {
        Writer osWrite = new OutputStreamWriter(new FileOutputStream(xmlFile));// 创建输出流
        OutputFormat format = OutputFormat.createPrettyPrint(); // 获取输出的指定格式
        //  format.setEncoding("UTF-8");// 设置编码 ，确保解析的xml为UTF-8格式
        XMLWriter writer = new XMLWriter(osWrite, format);// XMLWriter
        // 指定输出文件以及格式
        writer.write(document);// 把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        ivrUsml.write();

     /*   File file = new File("E:\\JUC\\桌面\\其他桌面文件\\JUC\\800企业\\201810\\IVR自助配置\\test\\Index_Start_2.usml");
        File file1 = new File("E:\\JUC\\桌面\\其他桌面文件\\JUC\\800企业\\201810\\IVR自助配置\\test\\Index_Start_11.usml");

        System.out.println(file.length());
        System.out.println(file1.length());

        System.out.println(file.compareTo(file1));

        System.out.println(file.getFreeSpace());
        System.out.println(file1.getFreeSpace());
        System.out.println(file1.canExecute());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file.canExecute());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
*/



    }
}

