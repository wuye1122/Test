package Tree.Ivr;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Tree {

    public Node head;

    public static class Node<T> {
        private String id;
        private String name;
        private T value;
        private List<Node> childs = new ArrayList<>();
        private Map<String, String> map;
        private Map<String,Map<String,String>> globalMap;

        public Node() {
        }

        public Node(String id, T value,Map<String,String> map,String name) {
            this.id = id;
            this.value = value;
            this.map = map;
            this.name=name;
        }

        public List<Node> getChilds() {
            return childs;
        }

        public void setChilds(List<Node> childs) {
            this.childs = childs;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, Map<String, String>> getGlobalMap() {
            return globalMap;
        }

        public void setGlobalMap(Map<String, Map<String, String>> globalMap) {
            this.globalMap = globalMap;
        }
    }

    public static void main(String[] args) {

        // NodeData(String id, String parentId, String value) {
        LinkedList<NodeData> l = new LinkedList<>();
        l.add(new NodeData("b","a", "b"));
        l.add(new NodeData("e","b", "e"));
        l.add(new NodeData("f","c", "f"));
        l.add(new NodeData("c","b", "c"));
        l.add(new NodeData("d","b", "d"));
        l.add(new NodeData("a","", "a"));
        Tree t = new Tree();
        t.head = new Node();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getParentId() == null || "".equals(l.get(i).getParentId())) {
                //开始节点,找到后移除
                t.head = new Node(l.get(i).getId(), l.get(i).getValue(),l.get(i).getMap(),l.get(i).getValue());
                l.remove(i);
                break;
            }
        }
        buildChild(t.head, l);
        generateXML(t.head);
        System.out.println(l);

    }

    //构建一棵树节点
    public static void buildChild(Node pointer, LinkedList<NodeData> l) {
        for (int i = 0; i < l.size(); i++) {
            if (pointer.id.equals(l.get(i).getParentId())) {
                //子节点
                Node c = new Node(l.get(i).getId(), l.get(i).getValue(),l.get(i).getMap(),l.get(i).getValue());
                pointer.childs.add(c);
                l.remove(c);
                buildChild(c, l);
            }
        }
    }


    public static void generateXML(Node<NodeData> head) {
        System.out.println("节点名称："+head.value+"============<" + head.id + ">");
        while (head.childs != null && head.childs.size() != 0) {
            generateXML(head.childs.remove(0));
        }
        // System.out.println("</" + head.id + ">");
    }
}
