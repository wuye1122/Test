package Tree.Ivr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Xml {


    public static void main(String[] args) {
        // NodeData(String id, String parentId, String value) {
        LinkedList<NodeData> l = new LinkedList<>();

        String tree2 = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"开始\",\"nodeDesc\":\"\"},{\"id\":\"svgVoiceBean1\",\"parentId\":\"svgStartBean1\",\"name\":\"PlayVoiceByTTS\",\"nodeName\":\"播放TTS\",\"desc\":\"\",\"type\":\"TTS\",\"FileName\":\"\",\"TxtString\":\"这里是需要播放TTS内容\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"播放TTS\"},{\"id\":\"svgJudgeBean1\",\"parentId\":\"svgVoiceBean1\",\"name\":\"GetDTMF\",\"nodeName\":\"按键判断\",\"desc\":\"\",\"type\":\"putKey\",\"keyCount\":\"3\",\"BetweenTimeout\":\"#\",\"EndFlag\":\"1\",\"TimeoutSecond\":\"1\",\"nodeContent\":\"按键判断\"},{\"id\":\"svgPutKeyBean1\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"按键1\",\"nodeDesc\":\"\",\"ItemExpression\":\"1\"},{\"id\":\"svgPutKeyBean2\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"按键2\",\"nodeDesc\":\"\",\"ItemExpression\":\"2\"},{\"id\":\"svgVoiceBean2\",\"parentId\":\"svgPutKeyBean1\",\"name\":\"PlayFile\",\"nodeName\":\"播放语音\",\"desc\":\"\",\"type\":\"file\",\"FileName\":\"Voice/111.wav\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"播放语音文件1\"},{\"id\":\"svgVoiceBean3\",\"parentId\":\"svgPutKeyBean2\",\"name\":\"PlayFile\",\"nodeName\":\"播放语音2\",\"desc\":\"\",\"type\":\"file\",\"FileName\":\"Voice/222.wav\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"\"},{\"id\":\"svgPutKeyBean3\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"按键3\",\"nodeDesc\":\"\",\"ItemExpression\":\"3\"},{\"id\":\"svgEndBean1\",\"parentId\":\"svgPutKeyBean3,svgVoiceBean2,svgVoiceBean3,\",\"name\":\"End\",\"nodeName\":\"结束\",\"desc\":\"\"}]";

        String tree = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"开始\",\"nodeDesc\":\"\"},{\"id\":\"svgEndBean1\",\"parentId\":\"svgPutKeyBean3,svgVoiceBean2,svgVoiceBean3,\",\"name\":\"End\",\"nodeName\":\"结束\",\"desc\":\"\"}]";

        String tree1 = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"开始\",\"nodeDesc\":\"\"},{\"id\":\"svgVoiceBean2\",\"parentId\":\"svgStartBean1\",\"name\":\"PlayFile\",\"nodeName\":\"播放语音\",\"desc\":\"\",\"type\":\"file\",\"FileName\":\"Voice/111.wav\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"播放语音文件1\"},{\"id\":\"svgEndBean1\",\"parentId\":\"svgPutKeyBean3,svgVoiceBean2,svgVoiceBean3,\",\"name\":\"End\",\"nodeName\":\"结束\",\"desc\":\"\"}]";
        String tree3 = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"开始\",\"nodeDesc\":\"\"},{\"id\":\"svgVoiceBean1\",\"parentId\":\"svgStartBean1\",\"name\":\"PlayVoiceByTTS\",\"nodeName\":\"语音节点1\",\"desc\":\"\",\"type\":\"TTS\",\"FileName\":\"\",\"TxtString\":\"123123\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"\"},{\"id\":\"svgVoiceBean3\",\"parentId\":\"svgVoiceBean1\",\"name\":\"PlayVoiceByTTS\",\"nodeName\":\"语音节点3\",\"desc\":\"\",\"type\":\"TTS\",\"FileName\":\"\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"\"}]\n";
        JSONArray arry = JSON.parseArray(tree3);
        System.out.println("arry.size()" + arry.size());
        for (int i = 0; i < arry.size(); i++) {
            //System.out.println(arry.get(i));
            JSONObject jsonObject = JSONObject.parseObject(arry.get(i).toString());
           Map<String, String> map = new HashMap<String, String>();
            for (String s : jsonObject.keySet()) {
                map.put(s, jsonObject.getString(s));
            }
          /*  if(!"End".equals(jsonObject.getString("name"))){
                l.add(new NodeData(jsonObject.getString("id"),jsonObject.getString("parentId"), jsonObject.getString("name"),map));
            }*/
        }

        System.out.println("LinkedList:"+l.toString());
        for(int i=0;i<l.size();i++){
            System.out.println("节点名称=====:【"+l.get(i).getValue()+"】:"+l.get(i));
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
            ivrSaxXml.java_XiuGai__XML((Tree.Node) t.head,"voiceMail");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
