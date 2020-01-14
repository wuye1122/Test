package Tree;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

public class ivrTree<E> {

    public Node<E> [] nodes ;//���ڱ������ݴ洢

    public int  treeSize;//�������ĳ���

    public int nodeNum;//�����ڵ����

    public final  int  MAX_SIZE = 100;//�������ĳ���


    public static class Node<T>{
          T data;
          int parent;
          String name;
          public Node(){
          }
          public Node(String name,T data,int parent){
              this.data=data;
              this.parent=parent;
              this.name=name;
          }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", parent=" + parent +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    //��ָ�����ڵ㴴����
    ivrTree(E data,String desc){
       this.treeSize=MAX_SIZE;
       this.nodes = new Node[treeSize];
       nodes[0]=new Node<E>(desc,data,-1);
       this.nodeNum++;
    }

    //��ָ�����ڵ㡢ָ��treeSize������
    ivrTree(E data,int treeSize,String desc){
        this.treeSize=treeSize;
        this.nodes = new Node[treeSize];
        nodes[0]=new Node<E>(desc,data,-1);
        this.nodeNum++;
    }

    //��ȡָ���ڵ�����λ��
    public int pos(Node node){
        for(int i=0;i<this.nodeNum;i++){
            if(nodes[i]!=null&&nodes[i]==node){
                return i;
            }
        }
        return -1;
    }

    //Ϊָ���ڵ�����ӽڵ�
   public void addNode(String desc,E data,Node node){

        for(int i=0;i<treeSize;i++){
            //�ҵ�һ��Ϊnull�ڵ���Ϊ�µĽڵ�
            if(nodes[i]==null){
                nodes[i]=new Node(desc,data,pos(node));
                nodeNum++;
                return;
            }
        }

   }

    //�ж����Ƿ�Ϊ�ա�
   public boolean isEmpty(){
       return nodes[0]==null;
   }

    //���ظ��ڵ�
    public Node getRoot(){
        return nodes[0];
    }

    //����ָ���ڵ㣨�Ǹ��ڵ㣩�ĸ��ڵ㡣
     public Node getParent(Node node){
        if(node.parent!=-1) {
            return nodes[node.parent];
        }else{
            return null;
        }
     }
    //����ָ���ڵ㣨��Ҷ�ӽڵ㣩�������ӽڵ�
    public List<Node> getListNode(Node node){
        List<Node> list = new ArrayList<Node>();
        //�ӽڵ� ����������
        for(int i =0;i<treeSize;i++){
           if(nodes[i]!=null&&nodes[i].parent==pos(node)){
               list.add(nodes[i]);
           }
        }
        return list;
    }

    //�ӽڵ��¼���Ǹ��׽ڵ���Ϣ -->index
    //�����ڵ�߶ȵ�ʱ�� ����һ�ڵ�һֱ������
     public  int  deep(){
        int high = 0;
        for(int i = 0;i < nodeNum;i++){
            int def=1;
            System.out.println(i+""+nodes[i]);
            int m = nodes[i].parent;
            while(m!=-1&&nodes[i]!=null){
                m=nodes[m].parent;
                def++;
            }
            if(def>high){
                high=def;
            }
        }
        return high;
    }
    //���ظ�������ȡ�


    @Override
    public String toString() {
        return "ivrTree{" +
                "nodes=" + Arrays.toString(nodes) +
                ", treeSize=" + treeSize +
                ", nodeNum=" + nodeNum +
                ", MAX_SIZE=" + MAX_SIZE +
                '}';
    }

    public static void main(String[] args) {

        List<Map<String,String>> Start = new ArrayList<Map<String, String>>();
        List<Map<String,String>> PlayFile = new ArrayList<Map<String, String>>();
        List<Map<String,String>> GetDTMF = new ArrayList<Map<String, String>>();
        List<Map<String,String>> Case1 = new ArrayList<Map<String, String>>();
        List<Map<String,String>> Case2 = new ArrayList<Map<String, String>>();
        List<Map<String,String>> PlayFile1 = new ArrayList<Map<String, String>>();
        List<Map<String,String>> PlayFile2 = new ArrayList<Map<String, String>>();
        Map<String,String> PlayFileMap = new HashMap<String,String>();
        PlayFileMap.put("FileName","Voice\\xxxx.wav");
        PlayFileMap.put("PlayCount","2");
        PlayFileMap.put("WaitTimeOnce","1");
        PlayFile.add(PlayFileMap);

        Map<String,String> GetDTMFmap = new HashMap<String,String>();
        GetDTMFmap.put("Count","1");
        GetDTMFmap.put("BetweenTimeout","15");
        GetDTMFmap.put("EndFlag","#");
        GetDTMFmap.put("TimeoutSecond","15");
        GetDTMF.add(GetDTMFmap);

        Map<String,String> Case1map = new HashMap<String,String>();
        Case1map.put("ItemExpression","1");
        Case1.add(Case1map);


        Map<String,String> Case2map = new HashMap<String,String>();
        Case2map.put("ItemExpression","2");
        Case2.add(Case2map);

        Map<String,String> PlayFile1Map = new HashMap<String,String>();
        PlayFile1Map.put("FileName","Voice\\1.wav");
        PlayFile1Map.put("PlayCount","2");
        PlayFile1Map.put("WaitTimeOnce","1");
        PlayFile1.add(PlayFile1Map);


        Map<String,String> PlayFile2Map = new HashMap<String,String>();
        PlayFile2Map.put("FileName","Voice\\2.wav");
        PlayFile2Map.put("PlayCount","2");
        PlayFile2Map.put("WaitTimeOnce","1");
        PlayFile2.add(PlayFile2Map);




        ivrTree.Node Case1Node = new Node("Case1",PlayFile,2);

        ivrTree ivrtree = new ivrTree(Start,10,"Start");
        ivrtree.addNode("PlayFile",PlayFile,ivrtree.nodes[0]);
        ivrtree.addNode("GetDTMF",GetDTMF,ivrtree.nodes[1]);
        ivrtree.addNode("Case1",Case1,ivrtree.nodes[2]);
        ivrtree.addNode("Case2",Case2,ivrtree.nodes[2]);
        ivrtree.addNode("PlayFile1",PlayFile1,ivrtree.nodes[3]);//Ϊʲôָ��-1��Ϊ���Java����������
        ivrtree.addNode("PlayFile2",PlayFile2,ivrtree.nodes[4]);

        System.out.println("������:"+ivrtree.nodeNum);
        System.out.println("������:"+ivrtree.toString());

        System.out.println("======================");

        for(int i=0;i<ivrtree.nodeNum;i++){
            System.out.println("���Ľڵ�:"+ivrtree.nodes[i]);
        }
        System.out.println("======================");
        int a = ivrtree.deep();
        System.out.println("���ĸ߶ȣ�"+a);
        System.out.println("���Ҹ��ڵ�Case1Node��"+ivrtree.getParent(Case1Node));
        System.out.println("���ĸ��ڵ㣺"+ivrtree.getRoot());

        System.out.println("���ݽڵ��ѯ�����׽ڵ�λ��:"+ivrtree.pos(ivrtree.nodes[3]));

        List node = ivrtree.getListNode(ivrtree.nodes[2]);
        for(int i=0;i<node.size();i++){
            System.out.println("�������Ľڵ�:"+node.get(i));
        }
         SerializerFeature[] features = {
          SerializerFeature.WriteMapNullValue, // ��������ֶ�
          SerializerFeature.WriteNullListAsEmpty, // list�ֶ����Ϊnull�����Ϊ[]��������null
          SerializerFeature.WriteNullNumberAsZero, // ��ֵ�ֶ����Ϊnull�����Ϊ0��������null
          SerializerFeature.WriteNullBooleanAsFalse, // Boolean�ֶ����Ϊnull�����Ϊfalse��������null
          SerializerFeature.WriteNullStringAsEmpty, // �ַ������ֶ����Ϊnull�����Ϊ""��������null
                 SerializerFeature.DisableCircularReferenceDetect //TODO ��������û��$ref��

         };



    }
}
