package Tree;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.util.*;

import static Tree.ivrSax.java_XiuGai__XML;

public class ivrTreeList<E> {
    public ivrTreeList.Node<E>[] nodes;//���ڱ������ݴ洢

    public int treeSize;//�������ĳ���

    public int nodeNum;//�����ڵ����

    public final int MAX_SIZE = 100;//�������ĳ���


    public static class Node<T> {
        T data;
        String id;
        String parent;
        String name;

        public Node() {
        }

        public Node(String id, String name, T data, String parent) {
            this.id = id;
            this.data = data;
            this.parent = parent;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", id='" + id + '\'' +
                    ", parent='" + parent + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    ivrTreeList() {

    }
    ivrTreeList(int treeSize) {
        this.treeSize = treeSize;
        this.nodes = new ivrTreeList.Node[treeSize];
    }

    //��ָ�����ڵ㴴����
    ivrTreeList(String id, E data, String desc) {
        this.treeSize = MAX_SIZE;
        this.nodes = new ivrTreeList.Node[treeSize];
        // public Node(String id,String name,T data,String parent)
        nodes[0] = new ivrTreeList.Node<E>(id, desc, data, "");
        this.nodeNum++;
    }

    //��ָ�����ڵ㡢ָ��treeSize������
    ivrTreeList(String id, E data, int treeSize, String desc) {
        this.treeSize = treeSize;
        this.nodes = new ivrTreeList.Node[treeSize];
        // public Node(String id,String name,T data,String parent)
        nodes[0] = new ivrTreeList.Node<E>(id, desc, data, "");
        this.nodeNum++;
    }

    //��ȡָ���ڵ�����λ��
    public int index(ivrTreeList.Node node) {
        for (int i = 0; i < this.nodeNum; i++) {
            if (nodes[i] != null && nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }

    //��ȡָ���ڵ㸸�׽ڵ�����λ��
    public int getIndexParent(ivrTreeList.Node node) {
        for (int i = 0; i < this.nodeNum; i++) {
            if (nodes[i] != null && nodes[i].id.equals(node.parent)) {
                return i;
            }
        }
        return -1;
    }

    //Ϊָ���ڵ�����ӽڵ�
    public void addNode(String id, String desc, E data, String parent) {
        for (int i = 0; i < treeSize; i++) {
            //�ҵ�һ��Ϊnull�ڵ���Ϊ�µĽڵ�
            if (nodes[i] == null) {
                nodes[i] = new ivrTreeList.Node(id, desc, data, parent);
                nodeNum++;
                return;
            }
        }

    }

    //�ж����Ƿ�Ϊ�ա�
    public boolean isEmpty() {
        return nodes[0] == null;
    }

    //���ظ��ڵ�
    public ivrTreeList.Node getRoot() {
        return nodes[0];
    }

    //����ָ���ڵ㣨�Ǹ��ڵ㣩�ĸ��ڵ㡣
    public ivrTreeList.Node getParent(ivrTreeList.Node node) {

        for (int i = 0; i < this.nodeNum; i++) {
            this.nodes[i].id.equals(node.parent);
            return nodes[i];
        }
        return null;
    }

    //����ָ���ڵ㣨��Ҷ�ӽڵ㣩�������ӽڵ�
    public List<ivrTreeList.Node> getListNode(ivrTreeList.Node node) {
        List<ivrTreeList.Node> list = new ArrayList<ivrTreeList.Node>();
        //�ӽڵ� ����������
        for (int i = 0; i < treeSize; i++) {
            if (nodes[i] != null && nodes[i].parent.equals(node.id)) {
                list.add(nodes[i]);
            }
        }
        return list;
    }

    //��ȡָ���ڵ�����λ��
    public int pos(ivrTreeList.Node node) {
        for (int i = 0; i < this.nodeNum; i++) {
            if (nodes[i] != null && nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }

    //�ӽڵ��¼���Ǹ��׽ڵ���Ϣ -->index
    //�����ڵ�߶ȵ�ʱ�� ����һ�ڵ�һֱ������
    public int deep() {
        int high = 0;
        for (int i = 0; i < nodeNum; i++) {
            int def = 1;
            System.out.println(i + "" + nodes[i]);
            String m = nodes[i].parent;
            while (StringUtils.isNotBlank(m) && nodes[i] != null) {
                //TODO �ܿ��ܳ��ֿ�ָ��
                if (getIndexParent(nodes[i]) != -1) {
                    m = nodes[getIndexParent(nodes[i])].parent;
                }
                def++;
            }
            if (def > high) {
                high = def;
            }
        }
        return high;
    }
    //���ظ�������ȡ�


    @Override
    public String toString() {
        return "ivrTreeList{" +
                "nodes=" + Arrays.toString(nodes) +
                ", treeSize=" + treeSize +
                ", nodeNum=" + nodeNum +
                ", MAX_SIZE=" + MAX_SIZE +
                '}';
    }

    //�ݹ����
    public  String getClild(Map<String, List<Node>> map,String key,String leaf){
       // System.out.println("key:"+key);
        if(leaf.indexOf(key)!=-1){
            leaf = leaf.replace(key,"");
        }
        if(StringUtils.isBlank(leaf)||map.get(key)==null){
            return "�Ѿ�������ɣ�";
        }
        if(map.get(key).size()!=0){
            for(int i=0;i<map.get(key).size();i++){
                System.out.println(map.get(key).get(i).id);
                key = map.get(key).get(i).id;
                System.out.println("key:====="+key);
                return getClild(map,key,leaf);
            }
            return "ʲôʱ��Ż��˳���key��"+key;
        }else{
            System.out.println("key:"+key);
            return "";
        }
    }


    public static void main(String[] args) {

        String tree = "[{\"id\":\"svgStartBean1\",\"parentId\":\"\",\"name\":\"Start\",\"nodeName\":\"��ʼ\",\"nodeDesc\":\"\"},{\"id\":\"svgVoiceBean1\",\"parentId\":\"svgStartBean1\",\"name\":\"PlayVoiceByTTS\",\"nodeName\":\"����TTS\",\"desc\":\"\",\"type\":\"TTS\",\"FileName\":\"\",\"TxtString\":\"��������Ҫ����TTS����\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"����TTS\"},{\"id\":\"svgJudgeBean1\",\"parentId\":\"svgVoiceBean1\",\"name\":\"GetDTMF\",\"nodeName\":\"�����ж�\",\"desc\":\"\",\"type\":\"putKey\",\"keyCount\":\"3\",\"BetweenTimeout\":\"#\",\"EndFlag\":\"1\",\"TimeoutSecond\":\"1\",\"nodeContent\":\"�����ж�\"},{\"id\":\"svgPutKeyBean1\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"����1\",\"nodeDesc\":\"\",\"ItemExpression\":\"1\"},{\"id\":\"svgPutKeyBean2\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"����2\",\"nodeDesc\":\"\",\"ItemExpression\":\"2\"},{\"id\":\"svgVoiceBean2\",\"parentId\":\"svgPutKeyBean1\",\"name\":\"PlayFile\",\"nodeName\":\"��������\",\"desc\":\"\",\"type\":\"file\",\"FileName\":\"Voice/111.wav\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"���������ļ�1\"},{\"id\":\"svgVoiceBean3\",\"parentId\":\"svgPutKeyBean2\",\"name\":\"PlayFile\",\"nodeName\":\"��������2\",\"desc\":\"\",\"type\":\"file\",\"FileName\":\"Voice/222.wav\",\"TxtString\":\"\",\"PlayCount\":\"1\",\"WaitTimeOnce\":\"1\",\"nodeContent\":\"\"},{\"id\":\"svgPutKeyBean3\",\"parentId\":\"svgJudgeBean1\",\"name\":\"Case\",\"nodeName\":\"����3\",\"nodeDesc\":\"\",\"ItemExpression\":\"3\"},{\"id\":\"svgEndBean1\",\"parentId\":\"svgPutKeyBean3,svgVoiceBean2,svgVoiceBean3,\",\"name\":\"End\",\"nodeName\":\"����\",\"desc\":\"\"}]";

        JSONArray arry = JSON.parseArray(tree);
        ivrTreeList ivrTreeList = new ivrTreeList(arry.size());
        System.out.println("arry.size()" + arry.size());
        for (int i = 0; i < arry.size(); i++) {
            //System.out.println(arry.get(i));
            JSONObject jsonObject = JSONObject.parseObject(arry.get(i).toString());
            Map<String, String> map = new HashMap<String, String>();
            for (String s : jsonObject.keySet()) {
                map.put(s, jsonObject.getString(s));
            }
            ivrTreeList.addNode(map.get("id"), map.get("name"), map, map.get("parentId"));

        }
        System.out.println("ivrTreeList.nodes.length:" + ivrTreeList.nodes.length);
        System.out.println(ivrTreeList.nodes.toString());
        for (int i = 0; i < ivrTreeList.nodes.length; i++) {
            if (ivrTreeList.nodes[i].name.equals("End") || ivrTreeList.nodes[i].name.equals("Start")) {
                System.out.println("��ǰ�ڵ㡾" + ivrTreeList.nodes[i].name + "����" + ivrTreeList.nodes[i]);
            }
            System.out.println("��ǰ�ڵ㸸�׽ڵ㣺" + ivrTreeList.getIndexParent(ivrTreeList.nodes[i]));
            System.out.println(ivrTreeList.nodes[i]);
        }

        String parentId = "";//Ҷ�ӽڵ�
        for (int i = 0; i < ivrTreeList.nodes.length; i++) {
            if (ivrTreeList.nodes[i].name.equals("End") || ivrTreeList.nodes[i].name.equals("Start")) {
                System.out.println("��ǰ�ڵ㡾" + ivrTreeList.nodes[i].name + "����" + ivrTreeList.nodes[i]);
            }
            System.out.println("��ǰ�ڵ㸸�׽ڵ㣺" + ivrTreeList.getIndexParent(ivrTreeList.nodes[i]));
            System.out.println(ivrTreeList.nodes[i]);
        }


        Map<String, List<Node>> map = new LinkedHashMap<>();
        String leaf = "";
        String root = "";

        //���� parent : son1 son2 son3....n
        //���ӽڵ�д���Ӧlist
        for (int i = 0; i < ivrTreeList.nodes.length; i++) {
            if (!ivrTreeList.nodes[i].name.equals("End") && !ivrTreeList.nodes[i].name.equals("Start")) {
                if (map.get(ivrTreeList.nodes[i].parent) == null) {
                    List<Node> list = new ArrayList<>();
                    map.put(ivrTreeList.nodes[i].parent, list);
                }
            } else {
                if (ivrTreeList.nodes[i].name.equals("End")) {
                    leaf = ivrTreeList.nodes[i].parent;
                }
                if (ivrTreeList.nodes[i].name.equals("Start")) {
                    root = ivrTreeList.nodes[i].id;
                }
            }

        }

        for (int i = 0; i < ivrTreeList.nodes.length; i++) {
            if (!ivrTreeList.nodes[i].name.equals("End") && !ivrTreeList.nodes[i].name.equals("Start")) {
                map.get(ivrTreeList.nodes[i].parent).add(ivrTreeList.nodes[i]);
            }
        }
        System.out.println("=====�������½ڵ�=======" + map.toString());
        System.out.println("=====�������½ڵ� ����=======" + map.size());


        for (Map.Entry<String, List<Node>> m : map.entrySet()) {
            System.out.println("key : " + m.getKey());
            System.out.println("value : " + m.getValue());
        }

        System.out.println("=====Ҷ�ӽڵ�=======" + leaf);
        System.out.println("=====���ڵ�=======" + root);


        List<String> listKey = new ArrayList<>();

        //ʧ��
       System.out.println(new ivrTreeList().getClild(map,root,leaf));

        //<Map<String,String>>
        Map<String,String> mapParent = new HashMap<>();
        for (int i = 0; i < ivrTreeList.nodes.length; i++) {
            if(ivrTreeList.nodes[i].name.equals("Start")||ivrTreeList.nodes[i].name.equals("End")){
                System.out.println("��ǰ�ڵ�ڵ���:"+ivrTreeList.nodes[i].id+"���׽ڵ���:"+ivrTreeList.nodes[i].parent+"���׽ڵ��Ӧ�Ķ��Ӹ���:"+ivrTreeList.nodes[i].name);
            }else{
                System.out.println("��ǰ�ڵ�ڵ���:"+ivrTreeList.nodes[i].id+"���׽ڵ���:"+ivrTreeList.nodes[i].parent+"���׽ڵ��Ӧ�Ķ��Ӹ���:"+map.get(ivrTreeList.nodes[i].parent).size());
            }
            mapParent.put(ivrTreeList.nodes[i].id,ivrTreeList.nodes[i].parent);

        }

        System.out.println("====parentMap:"+mapParent);
        List<Stack> listStack = new ArrayList<>();
        //���ű��� ��ÿ����  ѹ��ջ����
        String [] leafArry = leaf.split(",");
        for(int i=0;i<leafArry.length;i++){
            System.out.println("Ҷ��====="+leafArry[i]);
            Stack<String> stack = new Stack();
            String m = leafArry[i];
            while(StringUtils.isNotBlank(mapParent.get(m))){
                stack.push(m);
                m = mapParent.get(m);
            }
            listStack.add(stack);
        }
        for(int i=0;i<listStack.size();i++){
            Stack<String> stack = listStack.get(i);
            System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiii:"+i);
            while(!stack.isEmpty()){
                System.out.println(stack.pop());
            }
            System.out.println("========================:"+i);

        }



















    }


       /* try {
          //  ivrSax.java_XiuGai__XML(map,leaf);
        } catch (IOException e) {
            e.printStackTrace();
        }*/





}
