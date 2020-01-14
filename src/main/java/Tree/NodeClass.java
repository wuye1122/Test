package Tree;


import Tree.Ivr.NodeData;
import Tree.Ivr.Tree;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NodeClass {


    /* 语音播放组件
       <IO_Node Name="1?IO 节点" ProcessError="false" Expanded="true">
        <OutVarNameSet>
          <ResultVarName>m_PrevResult</ResultVarName>
          <ResultVarName>m_LastError</ResultVarName>
        </OutVarNameSet>
        <PlayFile FileName="Voice\getDTMF.wav" Rate="6" CanBreak="true" PlayCount="1" BreakList="" WaitTimeOnce="5" />
      </IO_Node>*/
    public static void PlayFile( Element ownerElement,String index,List<Tree.Node> list,Map<String,String> map ){
        org.dom4j.Element IO_Node = ownerElement.addElement("IO_Node");
        //TODO 这个节点需要根据流程值进行更改
        IO_Node.addAttribute("Name",index+"?IO 节点");
        IO_Node.addAttribute("ProcessError","false");
        IO_Node.addAttribute("Expanded","true");

        org.dom4j.Element OutVarNameSet = IO_Node.addElement("OutVarNameSet");

        org.dom4j.Element ResultVarName = OutVarNameSet.addElement("ResultVarName");
        ResultVarName.addText("m_PrevResult");
        org.dom4j.Element ResultVarName1 = OutVarNameSet.addElement("ResultVarName");
        ResultVarName1.addText("m_LastError");

        org.dom4j.Element PlayFile = IO_Node.addElement("PlayFile");
        //TODO  FileName   PlayCount  WaitTimeOnce
        System.out.println("================:"+map.toString());
        //节点名称=====:【PlayFile】:NodeData{id='svgVoiceBean2', parentId='svgPutKeyBean1', value='PlayFile',
        // map={id=svgVoiceBean2, parentId=svgPutKeyBean1, PlayCount=1, WaitTimeOnce=1, desc=, FileName=Voice/111.wav, name=PlayFile, nodeName=播放语音, TxtString=, nodeContent=播放语音文件1, type=file}}
        PlayFile.addAttribute("FileName",StringUtils.isNotBlank(map.get("FileName"))?map.get("FileName"):"Voice\\PV_Welcome.wav");
        PlayFile.addAttribute("PlayCount",StringUtils.isNotBlank(map.get("PlayCount"))?map.get("PlayCount"):"1");
        PlayFile.addAttribute("WaitTimeOnce",StringUtils.isNotBlank(map.get("WaitTimeOnce"))?map.get("WaitTimeOnce"):"5");
        PlayFile.addAttribute("Rate","6");
        PlayFile.addAttribute("CanBreak","true");
        PlayFile.addAttribute("BreakList","");

        //语音下面不会接很多个儿子
        for(int i=0;i<list.size();i++){
            //list = 3
            //bulidXML(list.get(i).getValue(), Branch_Node4,list.get(i),String.valueOf(i));
            System.out.println("list.get(i).getValue():"+list.get(i).getValue()+"=====");
            // bulidXML(String type, Element element, Tree.Node node,String index)
            Tree.Node node= list.get(i);
            bulidXML(PlayFile,node,index);
        }
    }



/*PlayVoiceByTTS
   <IO_Node Name="1?IO 节点" ProcessError="false" Expanded="true">

        <InputVarNameSet>
          <VarName>m_LastError</VarName>
        </InputVarNameSet>
        <OutVarNameSet>
          <ResultVarName>m_PrevResult</ResultVarName>
          <ResultVarName>m_LastError</ResultVarName>
        </OutVarNameSet>
        <PlayVoiceByTTS TxtString="您好！ 请稍后再拨" CanUseConvertAudioFile="false" CanBreak="true" PlayCount="1" WaitTimeOnce="5" BreakList="" />
      </IO_Node>*/

    public static void PlayVoiceByTTS( Element ownerElement,String index,List<Tree.Node> list,Map<String ,String > map){
        org.dom4j.Element IO_Node = ownerElement.addElement("IO_Node");
        //TODO 这个节点需要根据流程值进行更改
        IO_Node.addAttribute("Name",index+"?IO 节点");
        IO_Node.addAttribute("ProcessError","false");
        IO_Node.addAttribute("Expanded","true");

        org.dom4j.Element InputVarNameSet = IO_Node.addElement("InputVarNameSet");

        org.dom4j.Element ResultVarName0 = InputVarNameSet.addElement("VarName");
        ResultVarName0.addText("m_LastError");

        org.dom4j.Element OutVarNameSet = IO_Node.addElement("OutVarNameSet");

        org.dom4j.Element ResultVarName = OutVarNameSet.addElement("ResultVarName");
        ResultVarName.addText("m_PrevResult");
        org.dom4j.Element ResultVarName1 = OutVarNameSet.addElement("ResultVarName");
        ResultVarName1.addText("m_LastError");


        //        <PlayVoiceByTTS TxtString="您好！ 请稍后再拨" CanUseConvertAudioFile="false" CanBreak="true" PlayCount="1" WaitTimeOnce="5" BreakList="" />
        org.dom4j.Element PlayVoiceByTTS = IO_Node.addElement("PlayVoiceByTTS");
        //TODO  FileName   PlayCount  WaitTimeOnce
        PlayVoiceByTTS.addAttribute("TxtString",StringUtils.isNotBlank(map.get("TxtString"))?map.get("TxtString"):"默认播放测试tts");
        PlayVoiceByTTS.addAttribute("PlayCount",StringUtils.isNotBlank(map.get("PlayCount"))?map.get("PlayCount"):"1");
        PlayVoiceByTTS.addAttribute("WaitTimeOnce",StringUtils.isNotBlank(map.get("WaitTimeOnce"))?map.get("WaitTimeOnce"):"5");

        PlayVoiceByTTS.addAttribute("CanUseConvertAudioFile","false");
        PlayVoiceByTTS.addAttribute("CanBreak","true");
        PlayVoiceByTTS.addAttribute("BreakList","");

        //语音下面不会接很多个儿子

        for(int i=0;i<list.size();i++){
            //list = 3
            //bulidXML(list.get(i).getValue(), Branch_Node4,list.get(i),String.valueOf(i));
            System.out.println("list.get(i).getValue():"+list.get(i).getValue()+"=====");
            // bulidXML(String type, Element element, Tree.Node node,String index)
            Tree.Node node= list.get(i);
            System.out.println("*********:"+node.getName());
            if("GetDTMF".equals(node.getName())){
                bulidXML(ownerElement,node,index);
            }else{
                bulidXML(PlayVoiceByTTS,node,index);

            }
        }
    }


    /*  按键的GetDTMF节点
     <IO_Node Name="2?IO 节点" ProcessError="false" Expanded="true">
        <OutVarNameSet>
          <VarName>m_DTMFBUF</VarName>
          <ResultVarName>m_PrevResult</ResultVarName>
          <ResultVarName>m_LastError</ResultVarName>
        </OutVarNameSet>
        <GetDTMF DTMFVar="m_DTMFBUF" Count="1" IsClearDTMFBuffer="false" TimeoutSecond="15" BetweenTimeout="1" ReturnEndChar="false" EndFlag="#" />
      </IO_Node>*/
    public static void GetDTMF(Element ownerElement, String index, List<Tree.Node> list, Map<String ,String > map){
        org.dom4j.Element IO_Node = ownerElement.addElement("IO_Node");
        //TODO 这个节点需要根据流程值进行更改
        IO_Node.addAttribute("Name",index+"?IO 节点");
        IO_Node.addAttribute("ProcessError","false");
        IO_Node.addAttribute("Expanded","true");

        org.dom4j.Element OutVarNameSet = IO_Node.addElement("OutVarNameSet");

        org.dom4j.Element VarName = OutVarNameSet.addElement("VarName");
        VarName.addText("m_DTMFBUF");
        org.dom4j.Element ResultVarName = OutVarNameSet.addElement("ResultVarName");
        ResultVarName.addText("m_PrevResult");
        org.dom4j.Element ResultVarName1 = OutVarNameSet.addElement("ResultVarName");
        ResultVarName1.addText("m_LastError");

        // <GetDTMF DTMFVar="m_DTMFBUF" Count="1" IsClearDTMFBuffer="false" TimeoutSecond="15" BetweenTimeout="1" ReturnEndChar="false" EndFlag="#" />

        //节点名称:【GetDTMF】:NodeData{id='svgJudgeBean1', parentId='svgVoiceBean1', value='GetDTMF',
        // map={EndFlag=1, id=svgJudgeBean1, parentId=svgVoiceBean1, desc=, BetweenTimeout=#, TimeoutSecond=1, name=GetDTMF, nodeName=按键判断, keyCount=3, nodeContent=按键判断, type=putKey}}
        org.dom4j.Element GetDTMF = IO_Node.addElement("GetDTMF");
        //TODO  FileName   PlayCount  WaitTimeOnce
        GetDTMF.addAttribute("DTMFVar","m_DTMFBUF");
        GetDTMF.addAttribute("Count",StringUtils.isNotBlank(map.get("keyCount"))?map.get("keyCount"):"1");
        GetDTMF.addAttribute("IsClearDTMFBuffer","false");
        GetDTMF.addAttribute("TimeoutSecond",StringUtils.isNotBlank(map.get("TimeoutSecond"))?map.get("TimeoutSecond"):"15");
        GetDTMF.addAttribute("BetweenTimeout",StringUtils.isNotBlank(map.get("BetweenTimeout"))?map.get("BetweenTimeout"):"1");
        GetDTMF.addAttribute("ReturnEndChar","false");
        GetDTMF.addAttribute("EndFlag",StringUtils.isNotBlank(map.get("EndFlag"))?map.get("EndFlag"):"#");

        Element Branch_Node3 = ownerElement.addElement("Branch_Node");
        Branch_Node3.addAttribute("NAME","3?IO 节点");
        Branch_Node3.addAttribute("VarName","m_PrevResult");
        Branch_Node3.addAttribute("Expanded","true");
        Branch_Node3.addElement("Default");
        Element BranchItem =  Branch_Node3.addElement("BranchItem");
        BranchItem.addAttribute("Operator","Equal");
        BranchItem.addAttribute("ItemExpression","No_Error");
        Element Branch_Node4 =  BranchItem.addElement("Branch_Node");
        Branch_Node4.addAttribute("NAME","4?IO 节点");
        Branch_Node4.addAttribute("VarName","m_DTMFBUF");
        Branch_Node4.addAttribute("Expanded","true");
        Branch_Node4.addElement("Default");

        for(int i=0;i<list.size();i++){
            //list = 3
            //bulidXML(list.get(i).getValue(), Branch_Node4,list.get(i),String.valueOf(i));
            System.out.println("list.get(i).getValue():"+list.get(i).getValue()+"=====");
            // bulidXML(String type, Element element, Tree.Node node,String index)
            Tree.Node node= list.get(i);
            bulidXML(Branch_Node4,node,index);
        }


    }


    //  GetDTMF(Element ownerElement, String index,List<Tree.Node> list, Map<String ,String > map){
    public static void Case(Element ownerElement, String index,List<Tree.Node> list,Map<String,String> map){
        org.dom4j.Element BranchItem = ownerElement.addElement("BranchItem");
        //TODO 这个节点需要根据流程值进行更改
        BranchItem.addAttribute("Operator",StringUtils.isNotBlank(map.get("Operator"))?map.get("Operator"):"Equal");
        BranchItem.addAttribute("ItemExpression",map.get("ItemExpression"));
        //TODO 需要根据传入的 map.get(son) 来判断进行什么函数
        for(int i=0;i<list.size();i++){
            //list = 3
            //bulidXML(list.get(i).getValue(), Branch_Node4,list.get(i),String.valueOf(i));
            System.out.println("list.get(i).getValue():"+list.get(i).getValue()+"=====");
            // bulidXML(String type, Element element, Tree.Node node,String index)
            Tree.Node node= list.get(i);
            bulidXML(BranchItem,node,index);
        }
    }
    public static void bulidXML(Element element, Tree.Node<NodeData> node,String index) {
        System.out.println("=============node:"+node.getChilds());
        String type = node.getName();

        if("Start".equals(type)){
            if(node.getChilds().size()!=0){
              type=node.getChilds().get(0).getName();
              node=node.getChilds().get(0);
            }else{
                return;
            }
        }
        switch (type){
            //授权资源不足
            case "PlayVoiceByTTS" :
                PlayVoiceByTTS(element,index,node.getChilds(),node.getMap());
                break;
            case "PlayFile" :
                PlayFile(element,index,node.getChilds(),node.getMap());
                break;
            case "Case" :
                Case(element,index,node.getChilds(),node.getMap());
                break;
            case "GetDTMF" :
                GetDTMF(element,index, node.getChilds(),node.getMap());
                break;
            default :
                break;
        }
    }



}
