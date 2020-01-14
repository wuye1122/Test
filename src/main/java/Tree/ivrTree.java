package Tree;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

public class ivrTree<E> {

    public Node<E> [] nodes ;//用于保存数据存储

    public int  treeSize;//整个树的长度

    public int nodeNum;//包含节点个数

    public final  int  MAX_SIZE = 100;//整个树的长度


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
    //以指定根节点创建树
    ivrTree(E data,String desc){
       this.treeSize=MAX_SIZE;
       this.nodes = new Node[treeSize];
       nodes[0]=new Node<E>(desc,data,-1);
       this.nodeNum++;
    }

    //以指定根节点、指定treeSize创建树
    ivrTree(E data,int treeSize,String desc){
        this.treeSize=treeSize;
        this.nodes = new Node[treeSize];
        nodes[0]=new Node<E>(desc,data,-1);
        this.nodeNum++;
    }

    //获取指定节点所在位置
    public int pos(Node node){
        for(int i=0;i<this.nodeNum;i++){
            if(nodes[i]!=null&&nodes[i]==node){
                return i;
            }
        }
        return -1;
    }

    //为指定节点添加子节点
   public void addNode(String desc,E data,Node node){

        for(int i=0;i<treeSize;i++){
            //找到一个为null节点作为新的节点
            if(nodes[i]==null){
                nodes[i]=new Node(desc,data,pos(node));
                nodeNum++;
                return;
            }
        }

   }

    //判断树是否为空。
   public boolean isEmpty(){
       return nodes[0]==null;
   }

    //返回根节点
    public Node getRoot(){
        return nodes[0];
    }

    //返回指定节点（非根节点）的父节点。
     public Node getParent(Node node){
        if(node.parent!=-1) {
            return nodes[node.parent];
        }else{
            return null;
        }
     }
    //返回指定节点（非叶子节点）的所有子节点
    public List<Node> getListNode(Node node){
        List<Node> list = new ArrayList<Node>();
        //子节点 包含几层树
        for(int i =0;i<treeSize;i++){
           if(nodes[i]!=null&&nodes[i].parent==pos(node)){
               list.add(nodes[i]);
           }
        }
        return list;
    }

    //子节点记录的是父亲节点信息 -->index
    //遍历节点高度的时候 给你一节点一直往上找
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
    //返回该树的深度。


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
        ivrtree.addNode("PlayFile1",PlayFile1,ivrtree.nodes[3]);//为什么指向-1因为这个Java引用在作怪
        ivrtree.addNode("PlayFile2",PlayFile2,ivrtree.nodes[4]);

        System.out.println("树长度:"+ivrtree.nodeNum);
        System.out.println("整棵树:"+ivrtree.toString());

        System.out.println("======================");

        for(int i=0;i<ivrtree.nodeNum;i++){
            System.out.println("树的节点:"+ivrtree.nodes[i]);
        }
        System.out.println("======================");
        int a = ivrtree.deep();
        System.out.println("树的高度："+a);
        System.out.println("查找父节点Case1Node："+ivrtree.getParent(Case1Node));
        System.out.println("树的跟节点："+ivrtree.getRoot());

        System.out.println("根据节点查询出父亲节点位置:"+ivrtree.pos(ivrtree.nodes[3]));

        List node = ivrtree.getListNode(ivrtree.nodes[2]);
        for(int i=0;i<node.size();i++){
            System.out.println("孩子树的节点:"+node.get(i));
        }
         SerializerFeature[] features = {
          SerializerFeature.WriteMapNullValue, // 输出空置字段
          SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
          SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
          SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
          SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
                 SerializerFeature.DisableCircularReferenceDetect //TODO 有了这句就没有$ref了

         };



    }
}
