package Tree.Ivr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang.StringUtils;
import sun.awt.GlobalCursorManager;

import java.io.IOException;
import java.util.*;

public class XmlObject {


    public static void main(String[] args) {
        // NodeData(String id, String parentId, String value) {
        LinkedList<NodeData> l = new LinkedList<>();

        String obj = "{\n" +
                "\t\"nodes\": {\n" +
                "\t\t\"1\": {\n" +
                "\t\t\t\"x\": 270,\n" +
                "\t\t\t\"y\": 10,\n" +
                "\t\t\t\"id\": 1,\n" +
                "\t\t\t\"type\": \"Start\",\n" +
                "\t\t\t\"text\": \"流程开始\",\n" +
                "\t\t\t\"btnType\": \"warning\",\n" +
                "\t\t\t\"eleId\": \"Start-1\"\n" +
                "\t\t},\n" +
                "\t\t\"2\": {\n" +
                "\t\t\t\"x\": 258,\n" +
                "\t\t\t\"y\": 96,\n" +
                "\t\t\t\"id\": 2,\n" +
                "\t\t\t\"type\": \"JudgeWorkTime\",\n" +
                "\t\t\t\"text\": \"工作时间判断\",\n" +
                "\t\t\t\"btnType\": \"primary\",\n" +
                "\t\t\t\"eleId\": \"JudgeWorkTime-2\"\n" +
                "\t\t},\n" +
                "\t\t\"843ee010-e987-11e8-95dc-a7f800fea78b\": {\n" +
                "\t\t\t\"x\": 10,\n" +
                "\t\t\t\"y\": 10,\n" +
                "\t\t\t\"id\": \"843ee010-e987-11e8-95dc-a7f800fea78b\",\n" +
                "\t\t\t\"type\": \"PalyVoice\",\n" +
                "\t\t\t\"text\": \"播放语音文件\",\n" +
                "\t\t\t\"btnType\": \"primary\",\n" +
                "\t\t\t\"eleId\": \"PalyVoice-843ee010-e987-11e8-95dc-a7f800fea78b\"\n" +
                "\t\t},\n" +
                "\t\t\"8b1954b0-e987-11e8-95dc-a7f800fea78b\": {\n" +
                "\t\t\t\"x\": 10,\n" +
                "\t\t\t\"y\": 10,\n" +
                "\t\t\t\"id\": \"8b1954b0-e987-11e8-95dc-a7f800fea78b\",\n" +
                "\t\t\t\"type\": \"PalyVoice\",\n" +
                "\t\t\t\"text\": \"播放语音文件\",\n" +
                "\t\t\t\"btnType\": \"primary\",\n" +
                "\t\t\t\"eleId\": \"PalyVoice-8b1954b0-e987-11e8-95dc-a7f800fea78b\"\n" +
                "\t\t},\n" +
                "\t\t\"a973b770-e987-11e8-95dc-a7f800fea78b\": {\n" +
                "\t\t\t\"x\": 10,\n" +
                "\t\t\t\"y\": 10,\n" +
                "\t\t\t\"id\": \"a973b770-e987-11e8-95dc-a7f800fea78b\",\n" +
                "\t\t\t\"type\": \"VoiceMail\",\n" +
                "\t\t\t\"text\": \"电话留言\",\n" +
                "\t\t\t\"btnType\": \"primary\",\n" +
                "\t\t\t\"eleId\": \"VoiceMail-a973b770-e987-11e8-95dc-a7f800fea78b\"\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"connections\": [{\n" +
                "\t\t\"source\": \"Start-1\",\n" +
                "\t\t\"sourceAnchorType\": \"Bottom\",\n" +
                "\t\t\"target\": \"JudgeWorkTime-2\",\n" +
                "\t\t\"targetAnchorType\": \"Top\",\n" +
                "\t\t\"label\": \"\"\n" +
                "\t}, {\n" +
                "\t\t\"source\": \"JudgeWorkTime-2\",\n" +
                "\t\t\"sourceAnchorType\": \"Bottom\",\n" +
                "\t\t\"target\": \"VoiceMail-a973b770-e987-11e8-95dc-a7f800fea78b\",\n" +
                "\t\t\"targetAnchorType\": \"Top\",\n" +
                "\t\t\"label\": \"工作时间内\"\n" +
                "\t}, {\n" +
                "\t\t\"source\": \"VoiceMail-a973b770-e987-11e8-95dc-a7f800fea78b\",\n" +
                "\t\t\"sourceAnchorType\": \"Bottom\",\n" +
                "\t\t\"target\": \"PalyVoice-8b1954b0-e987-11e8-95dc-a7f800fea78b\",\n" +
                "\t\t\"targetAnchorType\": \"Top\",\n" +
                "\t\t\"label\": \"\"\n" +
                "\t}, {\n" +
                "\t\t\"source\": \"VoiceMail-a973b770-e987-11e8-95dc-a7f800fea78b\",\n" +
                "\t\t\"sourceAnchorType\": \"Bottom\",\n" +
                "\t\t\"target\": \"PalyVoice-843ee010-e987-11e8-95dc-a7f800fea78b\",\n" +
                "\t\t\"targetAnchorType\": \"Top\",\n" +
                "\t\t\"label\": \"\"\n" +
                "\t}],\n" +
                "\t\"forms\": {\n" +
                "\t\t\"1\": {\n" +
                "\t\t\t\"nodeNum\": 1,\n" +
                "\t\t\t\"isWellCome\": \"N\",\n" +
                "\t\t\t\"voiceId\": \"\",\n" +
                "\t\t\t\"voiceName\": \"\"\n" +
                "\t\t},\n" +
                "\t\t\"2\": {\n" +
                "\t\t\t\"nodeNum\": 2\n" +
                "\t\t},\n" +
                "\t\t\"IllegalBtn\": {\n" +
                "\t\t\t\"playingStatus\": \"notBreak\",\n" +
                "\t\t\t\"playedStatus\": \"playAgain\",\n" +
                "\t\t\t\"outTime10s\": \"playAgain\",\n" +
                "\t\t\t\"outTime30s\": \"hangUp\"\n" +
                "\t\t},\n" +
                "\t\t\"SrvAppraise\": {\n" +
                "\t\t\t\"isOpenAccess\": \"N\",\n" +
                "\t\t\t\"startAccessVoiceId\": \"\",\n" +
                "\t\t\t\"startAccessVoiceName\": \"\",\n" +
                "\t\t\t\"endAccessVoiceId\": \"\",\n" +
                "\t\t\t\"endAccessVoiceName\": \"\"\n" +
                "\t\t},\n" +
                "\t\t\"QueueTimeout\": {\n" +
                "\t\t\t\"isLeave\": \"N\",\n" +
                "\t\t\t\"queueKey\": \"1\",\n" +
                "\t\t\t\"queueOutTime\": \"3\",\n" +
                "\t\t\t\"queueOutTimeTodo\": \"isLeave\",\n" +
                "\t\t\t\"queueVoiceId\": \"\",\n" +
                "\t\t\t\"queueVoiceName\": \"\",\n" +
                "\t\t\t\"leaveStartVoiceId\": \"\",\n" +
                "\t\t\t\"leaveStartVoiceName\": \"\",\n" +
                "\t\t\t\"leaveEndVoiceId\": \"\",\n" +
                "\t\t\t\"leaveEndVoiceName\": \"\"\n" +
                "\t\t},\n" +
                "\t\t\"843ee010-e987-11e8-95dc-a7f800fea78b\": {\n" +
                "\t\t\t\"nodeNum\": 3,\n" +
                "\t\t\t\"voiceId\": \"001\",\n" +
                "\t\t\t\"voiceName\": \"111111111111111111111\",\n" +
                "\t\t\t\"endVoiceTodo\": \"queueMessage\"\n" +
                "\t\t},\n" +
                "\t\t\"8b1954b0-e987-11e8-95dc-a7f800fea78b\": {\n" +
                "\t\t\t\"nodeNum\": 4,\n" +
                "\t\t\t\"voiceId\": \"001\",\n" +
                "\t\t\t\"voiceName\": \"111111111111111111111\",\n" +
                "\t\t\t\"endVoiceTodo\": \"keyNode\"\n" +
                "\t\t},\n" +
                "\t\t\"a973b770-e987-11e8-95dc-a7f800fea78b\": {\n" +
                "\t\t\t\"nodeNum\": 5,\n" +
                "\t\t\t\"voiceStartId\": \"\",\n" +
                "\t\t\t\"voiceStartName\": \"\",\n" +
                "\t\t\t\"voiceEndId\": \"\",\n" +
                "\t\t\t\"voiceEndName\": \"\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        JSONObject obj1= JSON.parseObject(obj);
        System.out.println("jsonObject1:====="+obj1);

        //节点属性
        JSONObject formsobj = JSON.parseObject(obj1.getString("forms"));
        System.out.println("formsobj:"+formsobj);
        Set<String > set =  formsobj.keySet();
        for(String s:set){
            System.out.println("forms:key:【"+s+"】value【"+formsobj.getString(s)+"】");
        }



        //流程名字
       JSONObject nodeObj =  JSON.parseObject(obj1.getString("nodes"));
        System.out.println(nodeObj.keySet().toString());
        for(String s :nodeObj.keySet()){
            System.out.println("nodes:key:【"+s+"】value【"+nodeObj.getString(s)+"】");
        }

        //构建父子树关系
        Map<String,String> connectionsMap = new HashMap<String,String>();
        JSONArray connections = JSON.parseArray(obj1.getString("connections"));
        System.out.println("connections:"+connections);
        for(int i=0;i<connections.size();i++){
         JSONObject con = JSONObject.parseObject(connections.get(i).toString());
         if(StringUtils.isNotBlank(con.getString("target"))&&StringUtils.isNotBlank(con.getString("source"))){
             connectionsMap.put(con.getString("target"),con.getString("source"));
         }
        }
        System.out.println("流程树连线map:"+connectionsMap.toString());


        Map<String,Map<String,String>> GlobalMap = new HashMap<String,Map<String,String>>();
        List<Map<String,String>> newList = new ArrayList<Map<String,String>>();
        //nodes
        for(String s :formsobj.keySet()){
         //   List<DataPushTaskPo> taskList=  JSON.parseObject(dataArray,new TypeReference<List<DataPushTaskPo>>(){}) ;
            Map<String,String> map = JSONObject.parseObject(formsobj.getString(s),new TypeReference<Map<String,String>>(){});
            System.out.println("nodes:key:【"+s+"】value【"+formsobj.getString(s)+"】");
            if(nodeObj.keySet().contains(s)){
                System.out.println("需要增加key:"+s);
                JSONObject node=JSONObject.parseObject(nodeObj.getString(s));
                map.put("type",node.getString("type"));
                map.put("text",node.getString("text"));
                map.put("eleId",node.getString("eleId"));
                map.put("parentId",connectionsMap.get(node.getString("eleId")));
            }else{
                System.out.println("全局设置:"+s);
                GlobalMap.put(s,map);
            }

            newList.add(map);
        }
        System.out.println("newList:=============="+newList.toString());
        System.out.println("GlobalMap:=============="+GlobalMap.toString());

        String tree2 = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"开始\",\"nodeDesc\":\"\"},{\"id\":\"svgVoiceBean1\",\"parentId\":\"svgStartBean1\",\"name\":\"PlayVoiceByTTS\",\"nodeName\":\"播放TTS\",\"desc\":\"\",\"type\":\"TTS\",\"FileName\":\"\",\"TxtString\":\"这里是需要播放TTS内容\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"播放TTS\"},{\"id\":\"svgJudgeBean1\",\"parentId\":\"svgVoiceBean1\",\"name\":\"GetDTMF\",\"nodeName\":\"按键判断\",\"desc\":\"\",\"type\":\"putKey\",\"keyCount\":\"3\",\"BetweenTimeout\":\"#\",\"EndFlag\":\"1\",\"TimeoutSecond\":\"1\",\"nodeContent\":\"按键判断\"},{\"id\":\"svgPutKeyBean1\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"按键1\",\"nodeDesc\":\"\",\"ItemExpression\":\"1\"},{\"id\":\"svgPutKeyBean2\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"按键2\",\"nodeDesc\":\"\",\"ItemExpression\":\"2\"},{\"id\":\"svgVoiceBean2\",\"parentId\":\"svgPutKeyBean1\",\"name\":\"PlayFile\",\"nodeName\":\"播放语音\",\"desc\":\"\",\"type\":\"file\",\"FileName\":\"Voice/111.wav\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"播放语音文件1\"},{\"id\":\"svgVoiceBean3\",\"parentId\":\"svgPutKeyBean2\",\"name\":\"PlayFile\",\"nodeName\":\"播放语音2\",\"desc\":\"\",\"type\":\"file\",\"FileName\":\"Voice/222.wav\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"\"},{\"id\":\"svgPutKeyBean3\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"按键3\",\"nodeDesc\":\"\",\"ItemExpression\":\"3\"},{\"id\":\"svgEndBean1\",\"parentId\":\"svgPutKeyBean3,svgVoiceBean2,svgVoiceBean3,\",\"name\":\"End\",\"nodeName\":\"结束\",\"desc\":\"\"}]";

        String tree = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"开始\",\"nodeDesc\":\"\"},{\"id\":\"svgEndBean1\",\"parentId\":\"svgPutKeyBean3,svgVoiceBean2,svgVoiceBean3,\",\"name\":\"End\",\"nodeName\":\"结束\",\"desc\":\"\"}]";
        String tree1 = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"开始\",\"nodeDesc\":\"\"},{\"id\":\"svgVoiceBean2\",\"parentId\":\"svgStartBean1\",\"name\":\"PlayFile\",\"nodeName\":\"播放语音\",\"desc\":\"\",\"type\":\"file\",\"FileName\":\"Voice/111.wav\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"播放语音文件1\"},{\"id\":\"svgEndBean1\",\"parentId\":\"svgPutKeyBean3,svgVoiceBean2,svgVoiceBean3,\",\"name\":\"End\",\"nodeName\":\"结束\",\"desc\":\"\"}]";
        String tree3 = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"开始\",\"nodeDesc\":\"\"},{\"id\":\"svgVoiceBean1\",\"parentId\":\"svgStartBean1\",\"name\":\"PlayVoiceByTTS\",\"nodeName\":\"语音节点1\",\"desc\":\"\",\"type\":\"TTS\",\"FileName\":\"\",\"TxtString\":\"123123\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"\"},{\"id\":\"svgVoiceBean3\",\"parentId\":\"svgVoiceBean1\",\"name\":\"PlayVoiceByTTS\",\"nodeName\":\"语音节点3\",\"desc\":\"\",\"type\":\"TTS\",\"FileName\":\"\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"\"}]\n";

        System.out.println("tree2=================:"+tree2);

        JSONArray arry = JSON.parseArray(tree3);
        System.out.println("arry.size()" + arry.size());
        for (int i = 0; i < newList.size(); i++) {
            //全局变量不进行树构建
              if(StringUtils.isNotBlank(newList.get(i).get("eleId"))||StringUtils.isNotBlank(newList.get(i).get("parentId"))){
                  l.add(new NodeData(newList.get(i).get("eleId"),newList.get(i).get("parentId"), newList.get(i).get("type"),newList.get(i),GlobalMap));
              }
        }
        System.out.println("LinkedList:"+l.toString());
        for(int i=0;i<l.size();i++){
            System.out.println("节点名称=====:【"+l.get(i).getValue()+"】:");
        }
        Tree t = new Tree();
        t.head = new Tree.Node();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getParentId() == null || "".equals(l.get(i).getParentId())) {
                //开始节点,找到后移除
                t.head = new Tree.Node(l.get(i).getId(), l.get(i).getValue(),l.get(i).getMap(),l.get(i).getValue());
                l.remove(i);
                break;
            }
        }
        Tree.buildChild(t.head, l);
        try {
            ivrSaxXml.java_XiuGai__XML((Tree.Node) t.head,"voiceMailTest");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
