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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class NodeClassAll {

    private static final Logger logger = LoggerFactory.getLogger(NodeClassAll.class);

    /* 语音播放组件
       <IO_Node Name="1?IO 节点" ProcessError="false" Expanded="true">
        <OutVarNameSet>
          <ResultVarName>m_PrevResult</ResultVarName>
          <ResultVarName>m_LastError</ResultVarName>
        </OutVarNameSet>
        <PlayFile FileName="Voice\getDTMF.wav" Rate="6" CanBreak="true" PlayCount="1" BreakList="" WaitTimeOnce="5" />
      </IO_Node>*/
    public static void PlayFile(Element ownerElement, String index, List<Tree.Node> list,Tree.Node<NodeData> nodeAll ){
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
        //节点名称=====:【PlayFile】:NodeData{id='svgVoiceBean2', parentId='svgPutKeyBean1', value='PlayFile',
        // map={id=svgVoiceBean2, parentId=svgPutKeyBean1, PlayCount=1, WaitTimeOnce=1, desc=, FileName=Voice/111.wav, name=PlayFile, nodeName=播放语音, TxtString=, nodeContent=播放语音文件1, type=file}}
        PlayFile.addAttribute("FileName",StringUtils.isNotBlank(nodeAll.getMap().get("voiceName"))?nodeAll.getMap().get("voiceName"):"Voice\\PV_Welcome.wav");
        PlayFile.addAttribute("PlayCount",StringUtils.isNotBlank(nodeAll.getMap().get("PlayCount"))?nodeAll.getMap().get("PlayCount"):"1");
        PlayFile.addAttribute("WaitTimeOnce",StringUtils.isNotBlank(nodeAll.getMap().get("WaitTimeOnce"))?nodeAll.getMap().get("WaitTimeOnce"):"5");
        PlayFile.addAttribute("Rate","6");
        //语音中非法按键 IllegalBtn
        if(!(nodeAll.getGlobalMap()==null)&&(!(nodeAll.getGlobalMap().get("IllegalBtn")==null))){

            String CanBreak = nodeAll.getGlobalMap().get("IllegalBtn").get("playingStatus");
           //不打断
           if("notBreak".equals(CanBreak)){
               PlayFile.addAttribute("CanBreak","false");
            }else{
               PlayFile.addAttribute("CanBreak","true");
           }
        }else{
            //
            PlayFile.addAttribute("CanBreak","true");
        }
        PlayFile.addAttribute("BreakList","");

        //语音下面不会接很多个儿子
        for(int i=0;i<list.size();i++){
            //list = 3
            //bulidXML(list.get(i).getValue(), Branch_Node4,list.get(i),String.valueOf(i));
            System.out.println("list.get(i).getValue():"+list.get(i).getValue()+"=====");
            // bulidXML(String type, Element element, Tree.Node node,String index)
            Tree.Node node= list.get(i);

            switch (node.getName()){
                //授权资源不足
                case "PlayVoiceByTTS" :
                    bulidXML(ownerElement,node,index);
                    break;
                case "PalyVoice" :
                    bulidXML(ownerElement,node,index);
                    break;
                case "PutKey" :
                    bulidXML(ownerElement,node,index);
                    break;
                default :
                    bulidXML(PlayFile,node,index);
                    break;
            }
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

    public static void PlayVoiceByTTS( Element ownerElement,String index,List<Tree.Node> list,Tree.Node<NodeData> nodeAll){
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
        PlayVoiceByTTS.addAttribute("TxtString",StringUtils.isNotBlank(nodeAll.getMap().get("TxtString"))?nodeAll.getMap().get("TxtString"):"默认播放测试tts");
        PlayVoiceByTTS.addAttribute("PlayCount",StringUtils.isNotBlank(nodeAll.getMap().get("PlayCount"))?nodeAll.getMap().get("PlayCount"):"1");
        PlayVoiceByTTS.addAttribute("WaitTimeOnce",StringUtils.isNotBlank(nodeAll.getMap().get("WaitTimeOnce"))?nodeAll.getMap().get("WaitTimeOnce"):"5");

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
            switch (node.getName()){
                //授权资源不足
                case "PlayVoiceByTTS" :
                    bulidXML(ownerElement,node,index);
                    break;
                case "PlayFile" :
                    bulidXML(ownerElement,node,index);
                    break;
                case "PutKey" :
                    bulidXML(ownerElement,node,index);
                    break;
                case "ReferceNode" :
                    bulidXML(ownerElement,node,index);
                    break;
                case "ComputeNode" :
                    bulidXML(ownerElement,node,index);
                    break;
                default :
                    bulidXML(PlayVoiceByTTS,node,index);
                    break;
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
    public static void GetDTMF(Element ownerElement, String index, List<Tree.Node> list, Tree.Node<NodeData> nodeAll){
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
        GetDTMF.addAttribute("Count",StringUtils.isNotBlank(nodeAll.getMap().get("keyCount"))?nodeAll.getMap().get("keyCount"):"1");
        GetDTMF.addAttribute("IsClearDTMFBuffer","false");
        GetDTMF.addAttribute("TimeoutSecond",StringUtils.isNotBlank(nodeAll.getMap().get("TimeoutSecond"))?nodeAll.getMap().get("TimeoutSecond"):"15");
        GetDTMF.addAttribute("BetweenTimeout",StringUtils.isNotBlank(nodeAll.getMap().get("BetweenTimeout"))?nodeAll.getMap().get("BetweenTimeout"):"1");
        GetDTMF.addAttribute("ReturnEndChar","false");
        GetDTMF.addAttribute("EndFlag",StringUtils.isNotBlank(nodeAll.getMap().get("EndFlag"))?nodeAll.getMap().get("EndFlag"):"#");

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
    public static void Case(Element ownerElement, String index,List<Tree.Node> list,Tree.Node<NodeData> nodeAll){
        org.dom4j.Element BranchItem = ownerElement.addElement("BranchItem");
        //TODO 这个节点需要根据流程值进行更改
        BranchItem.addAttribute("Operator",StringUtils.isNotBlank(nodeAll.getMap().get("Operator"))?nodeAll.getMap().get("Operator"):"Equal");
        BranchItem.addAttribute("ItemExpression",nodeAll.getMap().get("ItemExpression"));
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

  /*  <Compute_Node Name="设置_转人工技能组信息" Language="VBScript" TimeoutSecond="120" ProcessError="false" SafeMode="false">
        <OutVarNameSet>
          <ResultVarName>m_PrevResult</ResultVarName>
        </OutVarNameSet>
        <Content>'*************************************************************
            '功能：设置转人工座席的信息：
            '-------------------------------------------------------------
            '---SLEESession OutPut说明----------------------------------------------------------------------------------
            '		SLEESession("SkillGroup")		 	 '企业座席技能组信息，为本文档的必选项
'------------------------------------------------------------------------------------------------
        '开 发 人：赵静
        '开发日期：2008-07-10
        '修 改 人：
        '修改日期：
        '修改内容：
        '*************************************************************
    SLEESession("SkillGroup") = "人工服务"
    MsgBox "SLEESession(SkillGroup) =  " &amp;  SLEESession("SkillGroup")
</Content>
      </Compute_Node>*/


    //<Compute_Node Name="1?设置Http调用参数" Language="VBScript" TimeoutSecond="120" ProcessError="false" SafeMode="false">
    //计算节点 Compute_Node
    public static void ComputeNode( Element ownerElement,String index,String context,String Name){
        org.dom4j.Element Compute_Node = ownerElement.addElement("Compute_Node");
        Compute_Node.addAttribute("Name",index+"?"+Name);
        Compute_Node.addAttribute("Language","VBScript");
        Compute_Node.addAttribute("TimeoutSecond","120");
        Compute_Node.addAttribute("ProcessError","false");
        Compute_Node.addAttribute("SafeMode","false");
        org.dom4j.Element OutVarNameSet = Compute_Node.addElement("OutVarNameSet");
        org.dom4j.Element ResultVarName = OutVarNameSet.addElement("ResultVarName");
        ResultVarName.addText("m_PrevResult");
        org.dom4j.Element Content = Compute_Node.addElement("Content");

        /*StringBuffer sb  = new StringBuffer();
        sb.append("SLEESession(\"SkillGroup\") = \"");
        Map<String,String> map = new HashMap<String,String>();
        map.put("SkillGroup","sdadasdd");
        System.out.println("转坐席 技能组:"+map.get("SkillGroup"));
        logger.debug("转坐席 技能组:"+map.get("SkillGroup"));
        if(StringUtils.isNotBlank(map.get("SkillGroup"))){
            sb.append(map.get("SkillGroup")).append("\"");
        }else{
            sb.append("人工服务").append("\"");
        }

        Content.addText(sb.toString());
        Content.addCDATA("\r\n");//空格
        String aa=(" MsgBox \"SLEESession(SkillGroup) =  \" &  SLEESession(\"SkillGroup\")");
        Content.addText(aa);
*/
        Content.addCDATA(context);
        //计算节点下面没有儿子
    }


    //引用节点 ReferceNode
    public static void ReferceNode( Element ownerElement,String index,List<Tree.Node> list,Tree.Node<NodeData> nodeAll){

        String refecName =nodeAll.getMap().get("type");
        if(StringUtils.isNotBlank(refecName)){
            if("CMSAgent".equals(refecName)){
                //需要在在ImportServiceSet 添加引用的文件
                Element ServiceElement = ownerElement.getParent();
                while(null!=ServiceElement&&!ServiceElement.getName().equals("Service")){
                    ServiceElement = ServiceElement.getParent();
                }
                org.dom4j.Element ServiceName = ServiceElement.addElement("ServiceName");
                ServiceName.addText("CMSAgent.usml");
                logger.debug("根目录："+ServiceElement);

                //增加技能组
                String SkillGroup ="";
                System.out.println("转坐席 技能组:"+nodeAll.getMap().get("skillGroupName"));
                logger.debug("转坐席 技能组:"+nodeAll.getMap().get("skillGroupName"));
                if(StringUtils.isNotBlank(nodeAll.getMap().get("skillGroupName"))){
                    SkillGroup="SLEESession(\"SkillGroup\") = \""+nodeAll.getMap().get("skillGroupName")+"\"\n" +
                            "MsgBox \"SLEESession(SkillGroup) = \" & SLEESession(\"SkillGroup\")";
                }else{
                    SkillGroup="SLEESession(\"SkillGroup\") = \"人工服务\"\n" +
                            "MsgBox \"SLEESession(SkillGroup) = \" & SLEESession(\"SkillGroup\")";
                }
                ComputeNode(ownerElement,index,SkillGroup,"指定技能组");
                org.dom4j.Element ReferceNode = ownerElement.addElement("ReferceNode");
                //TODO 这个节点需要根据流程值进行更改
                ReferceNode.addAttribute("Name","转移服务：&#xA;CMSAgent.usml");
                ReferceNode.addAttribute("ToService","true");
                ReferceNode.addAttribute("ServiceName","CMSAgent.usml");
                org.dom4j.Element OutVarNameSet = ReferceNode.addElement("OutVarNameSet");
                org.dom4j.Element ResultVarName = OutVarNameSet.addElement("ResultVarName");
                ResultVarName.addText("m_PrevResult");
                org.dom4j.Element ResultVarName1 = OutVarNameSet.addElement("ResultVarName");
                ResultVarName1.addText("m_LastError");
                org.dom4j.Element Daemon = ReferceNode.addElement("Daemon");
                org.dom4j.Element UserEventSet = Daemon.addElement("UserEventSet");
            }
            //http 存在儿子节点
            if("HTTP_Service".equals(refecName)){
                //需要在在ImportServiceSet 添加引用的文件
                Element ServiceElement = ownerElement.getParent();
                while(null!=ServiceElement&&!ServiceElement.getName().equals("Service")){
                    ServiceElement = ServiceElement.getParent();
                }
                org.dom4j.Element ServiceName = ServiceElement.addElement("ServiceName");
                ServiceName.addText("HTTP_Service.usml");
                logger.debug("根目录："+ServiceElement);
                //调用接口

                StringBuffer initHttp = new StringBuffer();
                initHttp.append("非http地址如何传递:"+nodeAll.getMap().get("httpUrl"));

                ComputeNode(ownerElement,index,initHttp.toString(),"设置Http调用参数");
                org.dom4j.Element ReferceNode = ownerElement.addElement("ReferceNode");
                //TODO 这个节点需要根据流程值进行更改
                ReferceNode.addAttribute("Name","转移服务：&#xA;HTTP_Service.usml");
                ReferceNode.addAttribute("ToService","true");
                ReferceNode.addAttribute("ServiceName","HTTP_Service.usml");
                org.dom4j.Element OutVarNameSet = ReferceNode.addElement("OutVarNameSet");
                org.dom4j.Element ResultVarName = OutVarNameSet.addElement("ResultVarName");
                ResultVarName.addText("m_PrevResult");
                org.dom4j.Element ResultVarName1 = OutVarNameSet.addElement("ResultVarName");
                ResultVarName1.addText("m_LastError");
                org.dom4j.Element Daemon = ReferceNode.addElement("Daemon");
                org.dom4j.Element UserEventSet = Daemon.addElement("UserEventSet");

                StringBuffer resultHttp = new StringBuffer();
                resultHttp.append("非工作时间接口如何解决......");
                ComputeNode(ownerElement,index,resultHttp.toString(),"判断调用接口结果");
            }

            if("JudgeWorkTime".equals(refecName)){
                //需要在在ImportServiceSet 添加引用的文件
                //需要在在ImportServiceSet 添加引用的文件
                Element ServiceElement = ownerElement.getParent();
                while(null!=ServiceElement&&!ServiceElement.getName().equals("Service")){
                    ServiceElement = ServiceElement.getParent();
                }
                org.dom4j.Element ServiceName = ServiceElement.addElement("ServiceName");
                ServiceName.addText("HTTP_Service"+".usml");
                logger.debug("根目录："+ServiceElement);
                //调用接口

                StringBuffer initHttp = new StringBuffer();
                initHttp.append("entid = SLEESession(\"EnterpriseId\")\r\n" +
                        "Msgbox \"Dnis= \"& SLEESession(\"Dnis\")\r\n" +
                        "Msgbox \"Ani= \"&   SLEESession(\"Ani\")\r\n" +
                        "enterID = SLEESession(\"Dnis\") '当前接入号修改当前接入号\r\n" +
                        "'SLEESession(\"WebAddess\") =\"http://open.ccod.com/ivr/workTime/judgeWorkTime.do?enterpriseID=\" &entid & \"&enterID=\" & enterID \r\n" +
                        "SLEESession(\"WebAddess\") =\"");
                System.out.println("调用接口地址:"+nodeAll.getMap().get("httpUrl"));
                if(StringUtils.isNotBlank(nodeAll.getMap().get("httpUrl"))){
                    initHttp.append(nodeAll.getMap().get("httpUrl"));
                }else{
                    initHttp.append("请填写正确的地址....");
                }
                initHttp.append("?enterpriseID=\" &entid & \"&enterID=\" & enterID \r\n" +
                        "Msgbox \"SLEESession(EnterpriseId) = \"& SLEESession(\"EnterpriseId\")\r\n" +
                        "Msgbox \"enterID= \"& enterID\r\n" +
                        "Msgbox \"SLEESession(WebAddess) = \"& SLEESession(\"WebAddess\")");

                ComputeNode(ownerElement,index,initHttp.toString(),"设置Http调用参数");
                org.dom4j.Element ReferceNode = ownerElement.addElement("ReferceNode");
                //TODO 这个节点需要根据流程值进行更改
                ReferceNode.addAttribute("Name","转移服务：&#xA;HTTP_Service.usml");
                ReferceNode.addAttribute("ToService","true");
                ReferceNode.addAttribute("ServiceName","HTTP_Service.usml");
                org.dom4j.Element OutVarNameSet = ReferceNode.addElement("OutVarNameSet");
                org.dom4j.Element ResultVarName = OutVarNameSet.addElement("ResultVarName");
                ResultVarName.addText("m_PrevResult");
                org.dom4j.Element ResultVarName1 = OutVarNameSet.addElement("ResultVarName");
                ResultVarName1.addText("m_LastError");
                org.dom4j.Element Daemon = ReferceNode.addElement("Daemon");
                org.dom4j.Element UserEventSet = Daemon.addElement("UserEventSet");

                StringBuffer resultHttp = new StringBuffer();
                resultHttp.append("'*********************************************************************************************************\n" +
                        "'功能：设置调用Http接口的相关参数\n" +
                        "'------SLEESession InPut-------------------------------------\r\n" +
                        "'\t\tSLEESession(\"Success\")          \t    ——HTTP请求返回的结果，1为成功，0为失败；\n" +
                        "'------OnPut-------------------------------------\r\n" +
                        "'\t\tm_Success                               ——HTTP请求返回的结果，1为成功，0为失败；\n" +
                        "'--------------------------------------------------------------------------------------------------------\n" +
                        "'开 发 人：赵静\r\n" +
                        "'开发日期：2008年7月13日\r\n" +
                        "'修 改 人：{\"code\":\"0\",\"isWorkTime\":1,\"return\":\"0\"}\r\n" +
                        "'修改日期： \r\n" +
                        "'修改内容：\r\n" +
                        "'*********************************************************************************************************\n" +
                        "MsgBox \"SLEESession(Success) = \" & SLEESession(\"Success\")\r\n" +
                        "If SLEESession(\"Success\") = \"0\" Then \r\n" +
                        "     m_Flag1=\"0\"\r\n" +
                        "     MsgBox \"SLEESession(Success) = 调用接口失败=======\" \r\n" +
                        "End If\r\n" +
                        "'{\"code\":\"0\",\"isWorkTime\":null,\"return\":\"0\"}\r\n" +
                        "MsgBox \"SLEESession(sResult)\"  & SLEESession(\"sResult\")\r\n" +
                        "adArr = Split(SLEESession(\"sResult\"),\",\")\r\n" +
                        "\tMsgBox \"adArr = \" & adArr\r\n" +
                        "If UBound(adArr) > 0 Then\r\n" +
                        "\tSucc = Split(adArr(0),\":\")\r\n" +
                        "\tUserN = Split(adArr(1),\":\")\r\n" +
                        "\tUserR = Split(adArr(2),\":\")\r\n" +
                        "\t\r\n" +
                        "\t\r\n" +
                        "\tMsgBox \"adArr(0) = \" & adArr(0)\r\n" +
                        "\tMsgBox \"adArr(1)= \" & adArr(1)\r\n" +
                        "\tMsgBox \"adArr(2)= \" & adArr(2)\r\n" +
                        "\t\r\n" +
                        "\tMsgBox \"Succ = \" & Succ\r\n" +
                        "\tMsgBox \"UserN= \" & UserN\r\n" +
                        "\tMsgBox \"UserR= \" & UserR\r\n" +
                        "\tsucc = Split(Succ(1),\"\"\"\")\r\n" +
                        "\tusern = Split(UserN(1),\"\"\"\")\r\n" +
                        "\tuser = Split(UserR(1),\"\"\"\")\r\n" +
                        "\tMsgBox \"Succ(1) = \" & Succ(1)\r\n" +
                        "\tMsgBox \"UserN(1)= \" & UserN(1)\n" +
                        "\tMsgBox \"UserR(1)= \" & UserR(1)\r\n" +
                        "\tm_Success = succ(1)\r\n" +
                        "\tm_isTime = usern(1)\r\n" +
                        "\tm_Result1 = user(1)\r\n" +
                        "\tMsgBox \"m_isTime =\" & m_isTime\r\n" +
                        "    MsgBox \"m_Success =\" & m_Success\r\n" +
                        "\tMsgBox \"m_Result1 =\" & m_Result1\r\n" +
                        "\t\r\n" +
                        "End If\r\n" +
                        "If m_Success = \"0\" Then \r\n" +
                        "\tUserN = Split(adArr(1),\":\")\r\n" +
                        "\tuser = Split(UserN(1),\"\"\"\")\r\n" +
                        "\tm_isTime = user(1)\r\n" +
                        "\t\r\n" +
                        "\t\tMsgBox \"=========m_isTime =\" & m_isTime\r\n" +
                        "\t\r\n" +
                        "\t If m_isTime = \"1\" Then\r\n" +
                        "\t \r\n" +
                        "\t   m_Flag = \"1\"\r\n" +
                        "\t Else\r\n" +
                        "\t   m_Flag = \"0\"\r\n" +
                        "\t End If\t\t\r\n" +
                        "\t m_Flag1=\"1\"\r\n" +
                        "Else\r\n" +
                        "     m_Flag1=\"0\"\r\n" +
                        "End If\r\n" +
                        "MsgBox \"=========m_Flag =\" & m_Flag\r\n" +
                        "MsgBox \"=========m_Flag1 =\" & m_Flag1\r\n" +
                        "MsgBox \"m_isTime =\" & m_isTime\r\n" +
                        "MsgBox \"m_Success =\" & m_Success\r\n" +
                        "MsgBox \"SLEESession(sResult) = \" & SLEESession(\"sResult\")\r\n" +
                        "MsgBox \"m_WorkTime =\" & m_WorkTime");

                ComputeNode(ownerElement,index,resultHttp.toString(),"判断调用接口结果");

                for(int i=0;i<list.size();i++){
                    //list = 3
                    //bulidXML(list.get(i).getValue(), Branch_Node4,list.get(i),String.valueOf(i));
                    System.out.println("list.get(i).getValue():"+list.get(i).getValue()+"=====");
                    // bulidXML(String type, Element element, Tree.Node node,String index)
                    Tree.Node node= list.get(i);
                    bulidXML(ownerElement,node,index);
                }
            }
            if("VoiceMail".equals(refecName)){
                //需要在在ImportServiceSet 添加引用的文件
                //需要在在ImportServiceSet 添加引用的文件
                Element ServiceElement = ownerElement.getParent();
                while(null!=ServiceElement&&!ServiceElement.getName().equals("Service")){
                    ServiceElement = ServiceElement.getParent();
                }
                org.dom4j.Element ServiceName = ServiceElement.addElement("ServiceName");
                ServiceName.addText("VoiceMail.usml");
                logger.debug("根目录："+ServiceElement);
                //调用接口
                org.dom4j.Element ReferceNode = ownerElement.addElement("ReferceNode");
                //TODO 这个节点需要根据流程值进行更改
                ReferceNode.addAttribute("Name","转移服务：&#xA;VoiceMail.usml");
                ReferceNode.addAttribute("ToService","true");
                ReferceNode.addAttribute("ServiceName","VoiceMail.usml");
                org.dom4j.Element OutVarNameSet = ReferceNode.addElement("OutVarNameSet");
                org.dom4j.Element ResultVarName = OutVarNameSet.addElement("ResultVarName");
                ResultVarName.addText("m_PrevResult");
                org.dom4j.Element ResultVarName1 = OutVarNameSet.addElement("ResultVarName");
                ResultVarName1.addText("m_LastError");
                org.dom4j.Element Daemon = ReferceNode.addElement("Daemon");
                org.dom4j.Element UserEventSet = Daemon.addElement("UserEventSet");


                for(int i=0;i<list.size();i++){
                    //list = 3
                    //bulidXML(list.get(i).getValue(), Branch_Node4,list.get(i),String.valueOf(i));
                    System.out.println("list.get(i).getValue():"+list.get(i).getValue()+"=====");
                    // bulidXML(String type, Element element, Tree.Node node,String index)
                    Tree.Node node= list.get(i);
                    bulidXML(ownerElement,node,index);
                }
            }

        }else{
            logger.debug("引用ReferceNode失败，获取referceNode的name为空:"+nodeAll.getMap().get("Service"));
        }

        //ReferceNode 节点下面是不会有孩子的

        //ReferceNode 根据引用节点不同Content不同
        //如何引用的是CMSAgent.usml


    }

    public static void bulidXML(Element element, Tree.Node<NodeData> node,String index) {
        String type = node.getName();
        if("Start".equals(type)){
            if(node.getChilds().size()!=0){
             /*   type=node.getChilds().get(0).getName();
                node=node.getChilds().get(0);*/
                for(int i = 0;i<node.getChilds().size();i++){
                    bulidXML(element,node.getChilds().get(i),i+"");
                }
            }else{
                return;
            }
        }
        switch (type){
            //授权资源不足
            case "PlayVoiceByTTS" :
                PlayVoiceByTTS(element,index,node.getChilds(),node);
                break;
            case "PalyVoice" :
                PlayFile(element,index,node.getChilds(),node);
                break;
                //TODO  case
            case "Case" :
                Case(element,index,node.getChilds(),node);
                break;
            case "PutKey" :
                GetDTMF(element,index, node.getChilds(),node);
                break;
            case "CMSAgent" :
                ReferceNode(element,index, node.getChilds(),node);
                break;
            case "JudgeWorkTime" :
                ReferceNode(element,index, node.getChilds(),node);
                break;
            case "VoiceMail" :
                ReferceNode(element,index, node.getChilds(),node);
                break;
            case "HTTP_Service" :
                ReferceNode(element,index, node.getChilds(),node);
                break;
            default :
                break;
        }
    }

}
