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

    //��������json, ���� �����ļ���
    public static void createUsml(String obj,String name){
        LinkedList<NodeData> l = new LinkedList<>();
        //��ʼ����

        JSONObject obj1= JSON.parseObject(obj);
        System.out.println("jsonObject1:====="+obj1);
        System.out.println("=======================forms===========================");

        //�ڵ�����
        JSONObject formsobj = JSON.parseObject(obj1.getString("forms"));
        System.out.println("����ͼ�ڵ���ϸ��Ϣformsobj : "+formsobj);
        Set<String > set =  formsobj.keySet();
        for(String s:set){
            System.out.println("forms:key:��"+s+"��value��"+formsobj.getString(s)+"��");
        }

        System.out.println("=======================nodes===========================");
        //��������
        JSONObject nodeObj =  JSON.parseObject(obj1.getString("nodes"));

        for(String s :nodeObj.keySet()){
            System.out.println("nodes:key:��"+s+"��value��"+nodeObj.getString(s)+"��");
        }
        System.out.println("�ڵ���Ϣnodes����======:"+nodeObj.keySet().size());


        System.out.println("=======================connections===========================");

        //������������ϵ
        Map<String,String> connectionsMap = new HashMap<String,String>();
        JSONArray connections = JSON.parseArray(obj1.getString("connections"));
        System.out.println("connections:"+connections);
        for(int i=0;i<connections.size();i++){
            JSONObject con = JSONObject.parseObject(connections.get(i).toString());
            if(StringUtils.isNotBlank(con.getString("target"))&&StringUtils.isNotBlank(con.getString("source"))){

                if(con.getString("source").startsWith("PutKey")){
                    //�������߱������
                    System.out.println("******:"+con);
                    String caseTemp =con.getString("source").concat(con.getString("label"));//ȥ�� ����-
                    connectionsMap.put(caseTemp,con.getString("source"));
                    connectionsMap.put(con.getString("target"),caseTemp);
                }else{
                    connectionsMap.put(con.getString("target"),con.getString("source"));
                }

            }
            //����ǰ��� ������ putKey -->case 1 --->
        }
        System.out.println("===================map===================:"+connectionsMap.toString());


        Map<String,Map<String,String>> GlobalMap = new HashMap<String,Map<String,String>>();
        List<Map<String,String>> newList = new ArrayList<Map<String,String>>();
        //��������forms������ȫ�ֱ���
        for(String s :formsobj.keySet()){
            //   List<DataPushTaskPo> taskList=  JSON.parseObject(dataArray,new TypeReference<List<DataPushTaskPo>>(){}) ;
            Map<String,String> map = JSONObject.parseObject(formsobj.getString(s),new TypeReference<Map<String,String>>(){});
            //�������� �ѽڵ���Ϣ��������  ��nodes������Ϣ���䵽form���棬�����������ߵĸ��׽ڵ�
            if(nodeObj.keySet().contains(s)){
                JSONObject node=JSONObject.parseObject(nodeObj.getString(s));
                map.put("type",node.getString("type"));
                map.put("text",node.getString("text"));
                map.put("eleId",node.getString("eleId"));
                map.put("parentId",connectionsMap.get(node.getString("eleId")));

                //�ǹ���ʱ���ж������ȡ caseֵ
               if(null!=map.get("parentId")&&map.get("parentId").equals("2")){
                    JSONArray time = JSON.parseArray(obj1.getString("connections"));
                    for(int i=0;i<connections.size();i++){
                        JSONObject con = JSONObject.parseObject(connections.get(i).toString());
                        if(con.getString("label").contains("����ʱ��")&&con.getString("target").equals(node.getString("eleId"))){
                            System.out.println("con:===="+con);
                            map.put("case",con.getString("sourceAnchorType"));
                            map.put("desc",con.getString("label"));

                        }
                    }
                }

                if(node.getString("eleId").startsWith("PutKey")){
                    if(StringUtils.isNotBlank(map.get("keyObjs"))){{
                        JSONArray keyArray = JSON.parseArray(map.get("keyObjs"));
                        map.put("keyCount",String.valueOf(keyArray.size()));
                       }
                    }
                }
                System.out.println("======map:key��"+s+"��map:"+map.toString());
            }else{
                System.out.println("ȫ������:"+s);
                GlobalMap.put(s,map);
            }
            newList.add(map);
        }

        //����һ��newList keyObjs
        List<Map<String,String>> treeList = new ArrayList<Map<String,String>>();

        System.out.println("newList:=============="+newList.toString());
        for(int i=0;i<newList.size();i++){

            if("JudgeWorkTime".equals(newList.get(i).get("type"))){
                newList.get(i).put("httpUrl","picc");
            }

             if("PutKey".equals(newList.get(i).get("type"))){
                 //����������Ҫ���һ�������ļ�
                   //{parentId=PutKey-36000a00����-4, text=���������ļ�, voiceName=Welcome-1.wav, eleId=PalyVoice-92ea9823, type=PalyVoice},
                 Map<String,String> oldmap =newList.get(i);
                 Map<String,String> newmap = new HashMap<>();
                 String voiceName1 = oldmap.get("voiceName");
                 String parentId1=  oldmap.get("parentId");
                 String eleId1 = parentId1+"voice";
                 newmap.put("eleId",eleId1);
                 newmap.put("parentId",parentId1);
                 newmap.put("type","PalyVoice");
                 newmap.put("voiceName",voiceName1);
                 treeList.add(newmap);
                 oldmap.put("parentId",eleId1);
               //  treeList.add(oldmap);

                //��������ֵ
                 JSONArray keyArray = JSON.parseArray(newList.get(i).get("keyObjs"));
                 for(int j=0;j<keyArray.size();j++){
                     JSONObject formsobj1 = JSON.parseObject(keyArray.getString(j));
                     //{"eleId":"PutKey-36000a00_1","parentId":"PutKey-36000a00","type":"Case","nodeName":"����1","nodeDesc":"","ItemExpression":"1"},
                    //"nodeId":"RepeatListen-92ea9820","tod o":"toPlayAgain","value":"0"
                     String parentId  = newList.get(i).get("eleId");
                     String eleId = parentId+"����-"+formsobj1.getString("value");
                     Map<String,String> map = new HashMap<>();
                     map.put("type","Case");
                     map.put("eleId",eleId);
                     map.put("parentId",parentId);
                     map.put("nodeName","����-"+formsobj1.getString("value"));
                     map.put("nodeDesc",formsobj1.getString("todo"));
                     map.put("ItemExpression",formsobj1.getString("value"));
                     System.out.println("����case:"+parentId+"map:"+map.toString());
                     treeList.add(map);
                 }

             }
              treeList.add(newList.get(i));



         }
        System.out.println("treeList:=============="+treeList.toString());
        System.out.println("GlobalMap:=============="+GlobalMap.toString());

        String tree2 = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"��ʼ\",\"nodeDesc\":\"\"},{\"id\":\"svgVoiceBean1\",\"parentId\":\"svgStartBean1\",\"name\":\"PlayVoiceByTTS\",\"nodeName\":\"����TTS\",\"desc\":\"\",\"type\":\"TTS\",\"FileName\":\"\",\"TxtString\":\"��������Ҫ����TTS����\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"����TTS\"},{\"id\":\"svgJudgeBean1\",\"parentId\":\"svgVoiceBean1\",\"name\":\"GetDTMF\",\"nodeName\":\"�����ж�\",\"desc\":\"\",\"type\":\"putKey\",\"keyCount\":\"3\",\"BetweenTimeout\":\"#\",\"EndFlag\":\"1\",\"TimeoutSecond\":\"1\",\"nodeContent\":\"�����ж�\"},{\"id\":\"svgPutKeyBean1\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"����1\",\"nodeDesc\":\"\",\"ItemExpression\":\"1\"},{\"id\":\"svgPutKeyBean2\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"����2\",\"nodeDesc\":\"\",\"ItemExpression\":\"2\"},{\"id\":\"svgVoiceBean2\",\"parentId\":\"svgPutKeyBean1\",\"name\":\"PlayFile\",\"nodeName\":\"��������\",\"desc\":\"\",\"type\":\"file\",\"FileName\":\"Voice/111.wav\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"���������ļ�1\"},{\"id\":\"svgVoiceBean3\",\"parentId\":\"svgPutKeyBean2\",\"name\":\"PlayFile\",\"nodeName\":\"��������2\",\"desc\":\"\",\"type\":\"file\",\"FileName\":\"Voice/222.wav\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"\"},{\"id\":\"svgPutKeyBean3\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"����3\",\"nodeDesc\":\"\",\"ItemExpression\":\"3\"},{\"id\":\"svgEndBean1\",\"parentId\":\"svgPutKeyBean3,svgVoiceBean2,svgVoiceBean3,\",\"name\":\"End\",\"nodeName\":\"����\",\"desc\":\"\"}]";

        String tree = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"��ʼ\",\"nodeDesc\":\"\"},{\"id\":\"svgEndBean1\",\"parentId\":\"svgPutKeyBean3,svgVoiceBean2,svgVoiceBean3,\",\"name\":\"End\",\"nodeName\":\"����\",\"desc\":\"\"}]";
        String tree1 = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"��ʼ\",\"nodeDesc\":\"\"},{\"id\":\"svgVoiceBean2\",\"parentId\":\"svgStartBean1\",\"name\":\"PlayFile\",\"nodeName\":\"��������\",\"desc\":\"\",\"type\":\"file\",\"FileName\":\"Voice/111.wav\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"���������ļ�1\"},{\"id\":\"svgEndBean1\",\"parentId\":\"svgPutKeyBean3,svgVoiceBean2,svgVoiceBean3,\",\"name\":\"End\",\"nodeName\":\"����\",\"desc\":\"\"}]";
        String tree3 = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"��ʼ\",\"nodeDesc\":\"\"},{\"id\":\"svgVoiceBean1\",\"parentId\":\"svgStartBean1\",\"name\":\"PlayVoiceByTTS\",\"nodeName\":\"�����ڵ�1\",\"desc\":\"\",\"type\":\"TTS\",\"FileName\":\"\",\"TxtString\":\"123123\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"\"},{\"id\":\"svgVoiceBean3\",\"parentId\":\"svgVoiceBean1\",\"name\":\"PlayVoiceByTTS\",\"nodeName\":\"�����ڵ�3\",\"desc\":\"\",\"type\":\"TTS\",\"FileName\":\"\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"\"}]\n";
        System.out.print("================##########################3333"+tree3);
        for (int i = 0; i < treeList.size(); i++) {
            //ȫ�ֱ���������������
            if(StringUtils.isNotBlank(treeList.get(i).get("eleId"))||StringUtils.isNotBlank(treeList.get(i).get("parentId"))){
                l.add(new NodeData(treeList.get(i).get("eleId"),treeList.get(i).get("parentId"), treeList.get(i).get("type"),treeList.get(i),GlobalMap));
            }
        }
        System.out.println("LinkedList:"+l.toString());
        for(int i=0;i<l.size();i++){
            System.out.println("�ڵ�����=====:��"+l.get(i).getValue()+"��:");
        }
        Tree t = new Tree();
        t.head = new Tree.Node();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getParentId() == null || "".equals(l.get(i).getParentId())) {
                //��ʼ�ڵ�,�ҵ����Ƴ�
                t.head = new Tree.Node(l.get(i).getId(), l.get(i).getValue(),l.get(i).getMap(),l.get(i).getValue());
                l.remove(i);
                break;
            }
        }
        Tree.buildChild(t.head, l);
        try {
            ivrSaxXml.java_XiuGai__XML((Tree.Node) t.head,name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        System.out.println("===================================");
        //String obj1 = "{\"nodes\":{\"1\":{\"id\":\"1\",\"eleId\":\"1\",\"type\":\"Start\",\"text\":\"���̿�ʼ\",\"x\":339,\"y\":12,\"w\":80,\"h\":32},\"2\":{\"id\":\"2\",\"eleId\":\"2\",\"type\":\"JudgeWorkTime\",\"text\":\"����ʱ���ж�\",\"x\":327,\"y\":95,\"w\":104,\"h\":32},\"PutKey-36000a00\":{\"id\":\"PutKey-36000a00\",\"eleId\":\"PutKey-36000a00\",\"type\":\"PutKey\",\"text\":\"��������\",\"x\":339,\"y\":198,\"w\":80,\"h\":32},\"VoiceMail-3a8bd3b0\":{\"id\":\"VoiceMail-3a8bd3b0\",\"eleId\":\"VoiceMail-3a8bd3b0\",\"type\":\"VoiceMail\",\"text\":\"�绰����\",\"x\":626,\"y\":141,\"w\":80,\"h\":32},\"CMSAgent-723db150\":{\"id\":\"CMSAgent-723db150\",\"eleId\":\"CMSAgent-723db150\",\"type\":\"CMSAgent\",\"text\":\"·�ɵ�������\",\"x\":59,\"y\":270,\"w\":104,\"h\":32}},\"connections\":[{\"source\":\"1\",\"sourceAnchorType\":\"Bottom\",\"target\":\"2\",\"targetAnchorType\":\"Top\",\"label\":\"\"},{\"source\":\"2\",\"sourceAnchorType\":\"Bottom\",\"target\":\"PutKey-36000a00\",\"targetAnchorType\":\"Top\",\"label\":\"����ʱ����\"},{\"source\":\"2\",\"sourceAnchorType\":\"Right\",\"target\":\"VoiceMail-3a8bd3b0\",\"targetAnchorType\":\"Top\",\"label\":\"����ʱ����\"},{\"source\":\"PutKey-36000a00\",\"sourceAnchorType\":\"Bottom\",\"target\":\"CMSAgent-723db150\",\"targetAnchorType\":\"Top\",\"label\":\"����-0\"}],\"forms\":{\"1\":{\"nodeNum\":1,\"isWellCome\":\"N\",\"voiceId\":\"\",\"voiceName\":\"\"},\"2\":{\"nodeNum\":2},\"IllegalBtn\":{\"playingStatus\":\"notBreak\",\"playedStatus\":\"playAgain\",\"outTime10s\":\"playAgain\",\"outTime30s\":\"hangUp\"},\"SrvAppraise\":{\"isOpenAccess\":\"N\",\"startAccessVoiceId\":\"\",\"startAccessVoiceName\":\"\",\"endAccessVoiceId\":\"\",\"endAccessVoiceName\":\"\"},\"QueueTimeout\":{\"isLeave\":\"N\",\"queueKey\":\"1\",\"queueOutTime\":\"3\",\"queueOutTimeTodo\":\"isLeave\",\"queueVoiceId\":\"\",\"queueVoiceName\":\"\",\"leaveStartVoiceId\":\"\",\"leaveStartVoiceName\":\"\",\"leaveEndVoiceId\":\"\",\"leaveEndVoiceName\":\"\"},\"PutKey-36000a00\":{\"nodeNum\":3,\"voiceId\":311164,\"voiceName\":\"leaveThinks.wav\",\"keyObjs\":[{\"value\":\"0\",\"todo\":\"toSkillGroup\",\"nodeId\":\"CMSAgent-723db150\"}]},\"VoiceMail-3a8bd3b0\":{\"nodeNum\":4,\"voiceStartId\":311167,\"voiceStartName\":\"end.wav\",\"voiceEndId\":311163,\"voiceEndName\":\"workTime.wav\"},\"CMSAgent-723db150\":{\"nodeNum\":6,\"skillGroupName\":\"aaa\"}}}\n";
        //XmlObject.createUsml(obj1,"Init");
        System.out.println("===================================");
        String obj2 = "{\"nodes\":{\"1\":{\"id\":\"1\",\"eleId\":\"1\",\"type\":\"Start\",\"text\":\"���̿�ʼ\",\"x\":339,\"y\":12,\"w\":80,\"h\":32},\"2\":{\"id\":\"2\",\"eleId\":\"2\",\"type\":\"JudgeWorkTime\",\"text\":\"����ʱ���ж�\",\"x\":327,\"y\":95,\"w\":104,\"h\":32},\"PutKey-36000a00\":{\"id\":\"PutKey-36000a00\",\"eleId\":\"PutKey-36000a00\",\"type\":\"PutKey\",\"text\":\"��������\",\"x\":325,\"y\":167,\"w\":80,\"h\":32},\"PalyVoice-57deeec0\":{\"id\":\"PalyVoice-57deeec0\",\"eleId\":\"PalyVoice-57deeec0\",\"type\":\"PalyVoice\",\"text\":\"���������ļ�\",\"x\":607,\"y\":142,\"w\":104,\"h\":32},\"RepeatListen-92ea9820\":{\"id\":\"RepeatListen-92ea9820\",\"eleId\":\"RepeatListen-92ea9820\",\"type\":\"RepeatListen\",\"text\":\"�ظ�����\",\"x\":59,\"y\":270,\"w\":80,\"h\":32},\"CMSAgent-92ea9821\":{\"id\":\"CMSAgent-92ea9821\",\"eleId\":\"CMSAgent-92ea9821\",\"type\":\"CMSAgent\",\"text\":\"·�ɵ�������\",\"x\":192,\"y\":297,\"w\":104,\"h\":32},\"PutKey-92ea9822\":{\"id\":\"PutKey-92ea9822\",\"eleId\":\"PutKey-92ea9822\",\"type\":\"PutKey\",\"text\":\"��������\",\"x\":377,\"y\":334,\"w\":80,\"h\":32},\"PalyVoice-92ea9823\":{\"id\":\"PalyVoice-92ea9823\",\"eleId\":\"PalyVoice-92ea9823\",\"type\":\"PalyVoice\",\"text\":\"���������ļ�\",\"x\":479,\"y\":270,\"w\":104,\"h\":32}},\"connections\":[{\"source\":\"1\",\"sourceAnchorType\":\"Bottom\",\"target\":\"2\",\"targetAnchorType\":\"Top\",\"label\":\"\"},{\"source\":\"2\",\"sourceAnchorType\":\"Bottom\",\"target\":\"PutKey-36000a00\",\"targetAnchorType\":\"Top\",\"label\":\"����ʱ����\"},{\"source\":\"2\",\"sourceAnchorType\":\"Right\",\"target\":\"PalyVoice-57deeec0\",\"targetAnchorType\":\"Top\",\"label\":\"����ʱ����\"},{\"source\":\"PutKey-36000a00\",\"sourceAnchorType\":\"Bottom\",\"target\":\"RepeatListen-92ea9820\",\"targetAnchorType\":\"Top\",\"label\":\"����-0\"},{\"source\":\"PutKey-36000a00\",\"sourceAnchorType\":\"Bottom\",\"target\":\"CMSAgent-92ea9821\",\"targetAnchorType\":\"Top\",\"label\":\"����-1\"},{\"source\":\"PutKey-36000a00\",\"sourceAnchorType\":\"Bottom\",\"target\":\"PutKey-92ea9822\",\"targetAnchorType\":\"Top\",\"label\":\"����-3\"},{\"source\":\"PutKey-36000a00\",\"sourceAnchorType\":\"Bottom\",\"target\":\"PalyVoice-92ea9823\",\"targetAnchorType\":\"Top\",\"label\":\"����-4\"}],\"forms\":{\"1\":{\"nodeNum\":1,\"isWellCome\":\"N\",\"voiceId\":311152,\"voiceName\":\"\"},\"2\":{\"nodeNum\":2},\"IllegalBtn\":{\"playingStatus\":\"notBreak\",\"playedStatus\":\"playAgain\",\"outTime10s\":\"playAgain\",\"outTime30s\":\"hangUp\"},\"SrvAppraise\":{\"isOpenAccess\":\"N\",\"startAccessVoiceId\":\"\",\"startAccessVoiceName\":\"\",\"endAccessVoiceId\":\"\",\"endAccessVoiceName\":\"\"},\"QueueTimeout\":{\"isLeave\":\"N\",\"queueKey\":\"1\",\"queueOutTime\":\"3\",\"queueOutTimeTodo\":\"isLeave\",\"queueVoiceId\":\"\",\"queueVoiceName\":\"\",\"leaveStartVoiceId\":\"\",\"leaveStartVoiceName\":\"\",\"leaveEndVoiceId\":\"\",\"leaveEndVoiceName\":\"\"},\"PutKey-36000a00\":{\"nodeNum\":3,\"voiceId\":311152,\"voiceName\":\"NotWorkTime.wav\",\"keyObjs\":[{\"value\":\"0\",\"todo\":\"toPlayAgain\",\"nodeId\":\"RepeatListen-92ea9820\"},{\"value\":\"1\",\"todo\":\"toSkillGroup\",\"nodeId\":\"CMSAgent-92ea9821\"},{\"value\":\"3\",\"todo\":\"toKeyNode\",\"nodeId\":\"PutKey-92ea9822\"},{\"value\":\"4\",\"todo\":\"toPlayVoice\",\"nodeId\":\"PalyVoice-92ea9823\"}]},\"PalyVoice-57deeec0\":{\"nodeNum\":18,\"voiceId\":\"\",\"voiceName\":\"\",\"endVoiceTodo\":\"hangUp\"},\"RepeatListen-92ea9820\":{\"nodeNum\":15},\"CMSAgent-92ea9821\":{\"nodeNum\":16,\"skillGroupName\":\"aaa\"},\"PutKey-92ea9822\":{\"nodeNum\":17,\"voiceId\":\"\",\"voiceName\":\"\",\"keyObjs\":[]},\"PalyVoice-92ea9823\":{\"nodeNum\":18,\"voiceId\":311151,\"voiceName\":\"Welcome-1.wav\",\"endVoiceTodo\":\"hangUp\"}}}\n";
         obj2 = "{\"nodes\":{\"1\":{\"id\":\"1\",\"eleId\":\"1\",\"type\":\"Start\",\"text\":\"���̿�ʼ\",\"x\":339,\"y\":12,\"w\":80,\"h\":32},\"2\":{\"id\":\"2\",\"eleId\":\"2\",\"type\":\"JudgeWorkTime\",\"text\":\"����ʱ���ж�\",\"x\":327,\"y\":95,\"w\":104,\"h\":32},\"PutKey-36000a00\":{\"id\":\"PutKey-36000a00\",\"eleId\":\"PutKey-36000a00\",\"type\":\"PutKey\",\"text\":\"��������\",\"x\":339,\"y\":198,\"w\":80,\"h\":32},\"CMSAgent-723db150\":{\"id\":\"CMSAgent-723db150\",\"eleId\":\"CMSAgent-723db150\",\"type\":\"CMSAgent\",\"text\":\"·�ɵ�������\",\"x\":59,\"y\":270,\"w\":104,\"h\":32}},\"connections\":[{\"source\":\"1\",\"sourceAnchorType\":\"Bottom\",\"target\":\"2\",\"targetAnchorType\":\"Top\",\"label\":\"\"},{\"source\":\"2\",\"sourceAnchorType\":\"Bottom\",\"target\":\"PutKey-36000a00\",\"targetAnchorType\":\"Top\",\"label\":\"����ʱ����\"},{\"source\":\"PutKey-36000a00\",\"sourceAnchorType\":\"Bottom\",\"target\":\"CMSAgent-723db150\",\"targetAnchorType\":\"Top\",\"label\":\"����-0\"}],\"forms\":{\"1\":{\"nodeNum\":1,\"isWellCome\":\"N\",\"voiceId\":\"\",\"voiceName\":\"\"},\"2\":{\"nodeNum\":2},\"IllegalBtn\":{\"playingStatus\":\"notBreak\",\"playedStatus\":\"playAgain\",\"outTime10s\":\"playAgain\",\"outTime30s\":\"hangUp\"},\"SrvAppraise\":{\"isOpenAccess\":\"N\",\"startAccessVoiceId\":\"\",\"startAccessVoiceName\":\"\",\"endAccessVoiceId\":\"\",\"endAccessVoiceName\":\"\"},\"QueueTimeout\":{\"isLeave\":\"N\",\"queueKey\":\"1\",\"queueOutTime\":\"3\",\"queueOutTimeTodo\":\"isLeave\",\"queueVoiceId\":\"\",\"queueVoiceName\":\"\",\"leaveStartVoiceId\":\"\",\"leaveStartVoiceName\":\"\",\"leaveEndVoiceId\":\"\",\"leaveEndVoiceName\":\"\"},\"PutKey-36000a00\":{\"nodeNum\":3,\"voiceId\":311164,\"voiceName\":\"leaveThinks.wav\",\"keyObjs\":[{\"value\":\"0\",\"todo\":\"toSkillGroup\",\"nodeId\":\"CMSAgent-723db150\"}]},\"CMSAgent-723db150\":{\"nodeNum\":6,\"skillGroupName\":\"aaa\"}}}\n";
         obj2 = "{\"nodes\":{\"1\":{\"id\":\"1\",\"eleId\":\"1\",\"type\":\"Start\",\"text\":\"���̿�ʼ\",\"x\":339,\"y\":12,\"w\":80,\"h\":32},\"2\":{\"id\":\"2\",\"eleId\":\"2\",\"type\":\"JudgeWorkTime\",\"text\":\"����ʱ���ж�\",\"x\":327,\"y\":95,\"w\":104,\"h\":32},\"PutKey-36000a00\":{\"id\":\"PutKey-36000a00\",\"eleId\":\"PutKey-36000a00\",\"type\":\"PutKey\",\"text\":\"��������\",\"x\":325,\"y\":167,\"w\":80,\"h\":32},\"PalyVoice-57deeec0\":{\"id\":\"PalyVoice-57deeec0\",\"eleId\":\"PalyVoice-57deeec0\",\"type\":\"PalyVoice\",\"text\":\"���������ļ�\",\"x\":607,\"y\":142,\"w\":104,\"h\":32},\"RepeatListen-92ea9820\":{\"id\":\"RepeatListen-92ea9820\",\"eleId\":\"RepeatListen-92ea9820\",\"type\":\"RepeatListen\",\"text\":\"�ظ�����\",\"x\":59,\"y\":270,\"w\":80,\"h\":32},\"CMSAgent-92ea9821\":{\"id\":\"CMSAgent-92ea9821\",\"eleId\":\"CMSAgent-92ea9821\",\"type\":\"CMSAgent\",\"text\":\"·�ɵ�������\",\"x\":192,\"y\":297,\"w\":104,\"h\":32},\"PutKey-92ea9822\":{\"id\":\"PutKey-92ea9822\",\"eleId\":\"PutKey-92ea9822\",\"type\":\"PutKey\",\"text\":\"��������\",\"x\":367,\"y\":284,\"w\":80,\"h\":32},\"PalyVoice-92ea9823\":{\"id\":\"PalyVoice-92ea9823\",\"eleId\":\"PalyVoice-92ea9823\",\"type\":\"PalyVoice\",\"text\":\"���������ļ�\",\"x\":479,\"y\":270,\"w\":104,\"h\":32},\"CMSAgent-e8d59710\":{\"id\":\"CMSAgent-e8d59710\",\"eleId\":\"CMSAgent-e8d59710\",\"type\":\"CMSAgent\",\"text\":\"·�ɵ�������\",\"x\":84,\"y\":432,\"w\":104,\"h\":32},\"PalyVoice-e8d5be20\":{\"id\":\"PalyVoice-e8d5be20\",\"eleId\":\"PalyVoice-e8d5be20\",\"type\":\"PalyVoice\",\"text\":\"���������ļ�\",\"x\":591,\"y\":431,\"w\":104,\"h\":32},\"PutKey-59165b30\":{\"id\":\"PutKey-59165b30\",\"eleId\":\"PutKey-59165b30\",\"type\":\"PutKey\",\"text\":\"��������\",\"x\":364,\"y\":480,\"w\":80,\"h\":32},\"CMSAgent-5fcbe2b0\":{\"id\":\"CMSAgent-5fcbe2b0\",\"eleId\":\"CMSAgent-5fcbe2b0\",\"type\":\"CMSAgent\",\"text\":\"·�ɵ�������\",\"x\":84,\"y\":552,\"w\":104,\"h\":32}},\"connections\":[{\"source\":\"1\",\"sourceAnchorType\":\"Bottom\",\"target\":\"2\",\"targetAnchorType\":\"Top\",\"label\":\"\"},{\"source\":\"2\",\"sourceAnchorType\":\"Bottom\",\"target\":\"PutKey-36000a00\",\"targetAnchorType\":\"Top\",\"label\":\"����ʱ����\"},{\"source\":\"2\",\"sourceAnchorType\":\"Right\",\"target\":\"PalyVoice-57deeec0\",\"targetAnchorType\":\"Top\",\"label\":\"����ʱ����\"},{\"source\":\"PutKey-36000a00\",\"sourceAnchorType\":\"Bottom\",\"target\":\"RepeatListen-92ea9820\",\"targetAnchorType\":\"Top\",\"label\":\"����-0\"},{\"source\":\"PutKey-36000a00\",\"sourceAnchorType\":\"Bottom\",\"target\":\"CMSAgent-92ea9821\",\"targetAnchorType\":\"Top\",\"label\":\"����-1\"},{\"source\":\"PutKey-36000a00\",\"sourceAnchorType\":\"Bottom\",\"target\":\"PutKey-92ea9822\",\"targetAnchorType\":\"Top\",\"label\":\"����-3\"},{\"source\":\"PutKey-36000a00\",\"sourceAnchorType\":\"Bottom\",\"target\":\"PalyVoice-92ea9823\",\"targetAnchorType\":\"Top\",\"label\":\"����-4\"},{\"source\":\"PutKey-92ea9822\",\"sourceAnchorType\":\"Bottom\",\"target\":\"CMSAgent-e8d59710\",\"targetAnchorType\":\"Top\",\"label\":\"����-0\"},{\"source\":\"PutKey-92ea9822\",\"sourceAnchorType\":\"Bottom\",\"target\":\"PalyVoice-e8d5be20\",\"targetAnchorType\":\"Top\",\"label\":\"����-1\"},{\"source\":\"PutKey-92ea9822\",\"sourceAnchorType\":\"Bottom\",\"target\":\"PutKey-59165b30\",\"targetAnchorType\":\"Top\",\"label\":\"����-3\"},{\"source\":\"PutKey-59165b30\",\"sourceAnchorType\":\"Bottom\",\"target\":\"CMSAgent-5fcbe2b0\",\"targetAnchorType\":\"Top\",\"label\":\"����-0\"}],\"forms\":{\"1\":{\"nodeNum\":1,\"isWellCome\":\"N\",\"voiceId\":311152,\"voiceName\":\"\"},\"2\":{\"nodeNum\":2},\"IllegalBtn\":{\"playingStatus\":\"notBreak\",\"playedStatus\":\"playAgain\",\"outTime10s\":\"playAgain\",\"outTime30s\":\"hangUp\"},\"SrvAppraise\":{\"isOpenAccess\":\"N\",\"startAccessVoiceId\":\"\",\"startAccessVoiceName\":\"\",\"endAccessVoiceId\":\"\",\"endAccessVoiceName\":\"\"},\"QueueTimeout\":{\"isLeave\":\"N\",\"queueKey\":\"1\",\"queueOutTime\":\"3\",\"queueOutTimeTodo\":\"isLeave\",\"queueVoiceId\":\"\",\"queueVoiceName\":\"\",\"leaveStartVoiceId\":\"\",\"leaveStartVoiceName\":\"\",\"leaveEndVoiceId\":\"\",\"leaveEndVoiceName\":\"\"},\"PutKey-36000a00\":{\"nodeNum\":3,\"voiceId\":311152,\"voiceName\":\"NotWorkTime.wav\",\"keyObjs\":[{\"value\":\"0\",\"todo\":\"toPlayAgain\",\"nodeId\":\"RepeatListen-92ea9820\"},{\"value\":\"1\",\"todo\":\"toSkillGroup\",\"nodeId\":\"CMSAgent-92ea9821\"},{\"value\":\"3\",\"todo\":\"toKeyNode\",\"nodeId\":\"PutKey-92ea9822\"},{\"value\":\"4\",\"todo\":\"toPlayVoice\",\"nodeId\":\"PalyVoice-92ea9823\"}]},\"PalyVoice-57deeec0\":{\"nodeNum\":18,\"voiceId\":11724,\"voiceName\":\"���ڰ���ת����ϯ���Ժ�.wav\",\"endVoiceTodo\":\"hangUp\"},\"RepeatListen-92ea9820\":{\"nodeNum\":15},\"CMSAgent-92ea9821\":{\"nodeNum\":16,\"skillGroupName\":\"aaa\"},\"PutKey-92ea9822\":{\"nodeNum\":17,\"voiceId\":311152,\"voiceName\":\"NotWorkTime.wav\",\"keyObjs\":[{\"value\":\"0\",\"todo\":\"toSkillGroup\",\"nodeId\":\"CMSAgent-e8d59710\"},{\"value\":\"1\",\"todo\":\"toPlayVoice\",\"nodeId\":\"PalyVoice-e8d5be20\"},{\"value\":\"3\",\"todo\":\"toKeyNode\",\"nodeId\":\"PutKey-59165b30\"}]},\"PalyVoice-92ea9823\":{\"nodeNum\":18,\"voiceId\":311151,\"voiceName\":\"Welcome-1.wav\",\"endVoiceTodo\":\"hangUp\"},\"CMSAgent-e8d59710\":{\"nodeNum\":19,\"skillGroupName\":\"aaa\"},\"PalyVoice-e8d5be20\":{\"nodeNum\":20,\"voiceId\":311145,\"voiceName\":\"Welcome-key-2.wav\",\"endVoiceTodo\":\"hangUp\"},\"PutKey-59165b30\":{\"nodeNum\":21,\"voiceId\":311152,\"voiceName\":\"NotWorkTime.wav\",\"keyObjs\":[{\"value\":\"0\",\"todo\":\"toSkillGroup\",\"nodeId\":\"CMSAgent-5fcbe2b0\"}]},\"CMSAgent-5fcbe2b0\":{\"nodeNum\":22,\"skillGroupName\":\"\"}}}\n";
          obj2="{\"nodes\":{\"1\":{\"id\":\"1\",\"eleId\":\"1\",\"type\":\"Start\",\"text\":\"���̿�ʼ\",\"x\":339,\"y\":12,\"w\":80,\"h\":32},\"2\":{\"id\":\"2\",\"eleId\":\"2\",\"type\":\"JudgeWorkTime\",\"text\":\"����ʱ���ж�\",\"x\":327,\"y\":95,\"w\":104,\"h\":32},\"PalyVoice-3ba3c290\":{\"id\":\"PalyVoice-3ba3c290\",\"eleId\":\"PalyVoice-3ba3c290\",\"type\":\"PalyVoice\",\"text\":\"���������ļ�\",\"x\":319,\"y\":204,\"w\":104,\"h\":32},\"PalyVoice-3f45c880\":{\"id\":\"PalyVoice-3f45c880\",\"eleId\":\"PalyVoice-3f45c880\",\"type\":\"PalyVoice\",\"text\":\"���������ļ�\",\"x\":604,\"y\":150,\"w\":104,\"h\":32}},\"connections\":[{\"source\":\"1\",\"sourceAnchorType\":\"Bottom\",\"target\":\"2\",\"targetAnchorType\":\"Top\",\"label\":\"\"},{\"source\":\"2\",\"sourceAnchorType\":\"Bottom\",\"target\":\"PalyVoice-3ba3c290\",\"targetAnchorType\":\"Top\",\"label\":\"����ʱ����\"},{\"source\":\"2\",\"sourceAnchorType\":\"Right\",\"target\":\"PalyVoice-3f45c880\",\"targetAnchorType\":\"Top\",\"label\":\"����ʱ����\"}],\"forms\":{\"1\":{\"nodeNum\":1,\"isWellCome\":\"N\",\"voiceId\":\"\",\"voiceName\":\"\"},\"2\":{\"nodeNum\":2},\"IllegalBtn\":{\"playingStatus\":\"notBreak\",\"playedStatus\":\"playAgain\",\"outTime10s\":\"playAgain\",\"outTime30s\":\"hangUp\"},\"SrvAppraise\":{\"isOpenAccess\":\"N\",\"startAccessVoiceId\":\"\",\"startAccessVoiceName\":\"\",\"endAccessVoiceId\":\"\",\"endAccessVoiceName\":\"\"},\"QueueTimeout\":{\"isLeave\":\"N\",\"queueKey\":\"1\",\"queueOutTime\":\"3\",\"queueOutTimeTodo\":\"isLeave\",\"queueVoiceId\":\"\",\"queueVoiceName\":\"\",\"leaveStartVoiceId\":\"\",\"leaveStartVoiceName\":\"\",\"leaveEndVoiceId\":\"\",\"leaveEndVoiceName\":\"\"},\"PalyVoice-3ba3c290\":{\"nodeNum\":8,\"voiceId\":\"\",\"voiceName\":\"\",\"endVoiceTodo\":\"hangUp\"},\"PalyVoice-3f45c880\":{\"nodeNum\":8,\"voiceId\":\"\",\"voiceName\":\"\",\"endVoiceTodo\":\"hangUp\"}}}\n";
        XmlObject.createUsml(obj2,"BiaoZhun");



    }

}
