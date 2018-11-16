package Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeChild<E> {

        private static class SonNode
        {
            //��¼��ǰ�ڵ��λ��
            private int pos;
            private String id;
            private String parenid;
            private SonNode next;
            public SonNode(int pos , SonNode next,String id,String parenid)
            {
                this.pos = pos;
                this.next = next;
                this.id = id;
                this.parenid = parenid;

            }
        }
        public static class Node<T>
        {
            T data;
            //��¼��һ���ӽڵ�
            SonNode first;
            public Node(T data)
            {
                this.data = data;
                this.first = null;
            }
            @Override
            public String toString()
            {
                if (first != null)
                {
                    return "TreeChild$Node[data=" + data + ", first="
                            + first.pos + "]";
                }
                else
                {
                    return "TreeChild$Node[data=" + data + ", first=-1]";
                }
            }
        }
        private final int DEFAULT_TREE_SIZE = 100;
        private int treeSize = 0;
        //ʹ��һ��Node[]��������¼����������нڵ�
        private Node<E>[] nodes;
        //��¼�ڵ���
        private int nodeNums;
        //��ָ�����ڵ㴴����
        public TreeChild(E data)
        {
            treeSize = DEFAULT_TREE_SIZE;
            nodes = new Node[treeSize];
            nodes[0] = new Node<E>(data);
            nodeNums++;
        }
        //��ָ�����ڵ㡢ָ��treeSize������
        public TreeChild(E data ,int treeSize)
        {
            this.treeSize = treeSize;
            nodes = new Node[treeSize];
            nodes[0] = new Node<E>(data);
            nodeNums++;
        }
        //Ϊָ���ڵ�����ӽڵ�
        public void addNode(E data , Node parent,String id,String parentid)
        {
            for (int i = 0 ; i < treeSize ; i++)
            {
                //�ҵ������е�һ��Ϊnull��Ԫ�أ���Ԫ�ر����½ڵ�
                if (nodes[i] == null)
                {
                    //�����½ڵ㣬����ָ������Ԫ����������
                    nodes[i] = new Node(data);
                    if (parent.first == null)
                    {
                        parent.first = new SonNode(i , null,id,parentid);
                    }
                    else
                    {
                        SonNode next = parent.first;
                        while (next.next != null)
                        {
                            next = next.next;
                        }
                        next.next = new SonNode(i , null,id,parentid);
                    }
                    nodeNums++;
                    return;
                }
            }
            throw new RuntimeException("�����������޷�����½ڵ�");
        }
        //�ж����Ƿ�Ϊ�ա�
        public boolean empty()
        {
            //���ڵ��Ƿ�Ϊnull
            return nodes[0] == null;
        }
        //���ظ��ڵ�
        public Node<E> root()
        {
            //���ظ��ڵ�
            return nodes[0];
        }
        //����ָ���ڵ㣨��Ҷ�ӽڵ㣩�������ӽڵ㡣
        public List<Node<E>> children(Node parent)
        {
            List<Node<E>> list = new ArrayList<Node<E>>();
            //��ȡparent�ڵ�ĵ�һ���ӽڵ�
            SonNode next = parent.first;
            //���ź���������������һ�����ӽڵ�
            while (next != null)
            {
                //��Ӻ������еĽڵ�
                list.add(nodes[next.pos]);
                next = next.next;
            }
            return list;
        }
        //����ָ���ڵ㣨��Ҷ�ӽڵ㣩�ĵ�index���ӽڵ㡣
        public Node<E> child(Node parent , int index)
        {
            //��ȡparent�ڵ�ĵ�һ���ӽڵ�
            SonNode next = parent.first;
            //���ź���������������һ�����ӽڵ�
            for (int i = 0 ; next != null  ; i++)
            {
                if (index == i)
                {
                    return nodes[next.pos];
                }
                next = next.next;
            }
            return null;
        }
        //���ظ�������ȡ�
        public int deep()
        {
            //��ȡ���������
            return deep(root());
        }
        //����һ���ݹ鷽����ÿ�����������Ϊ������������������ + 1
        private int deep(Node node)
        {
            if (node.first == null)
            {
                return 1;
            }
            else
            {
                //��¼������������������
                int max = 0;
                SonNode next = node.first;
                //���ź���������������һ�����ӽڵ�
                while (next != null)
                {
                    //��ȡ�����ӽڵ�Ϊ�������������
                    int tmp = deep(nodes[next.pos]);
                    if (tmp > max)
                    {
                        max = tmp;
                    }
                    next = next.next;
                }
                //��󷵻������������������� + 1
                return max + 1;
            }
        }
        //���ذ���ָ��ֵ�Ľڵ㡣
        public int pos(Node node)
        {
            for (int i = 0 ; i < treeSize ; i++)
            {
                //�ҵ�ָ���ڵ�
                if (nodes[i] == node)
                {
                    return i;
                }
            }
            return -1;
        }

        public static void main(String[] args)
        {
            TreeChild<String> tp = new TreeChild<String>("root");
            TreeChild.Node root = tp.root();
            System.out.println("���ڵ㣺" + root);
            tp.addNode("�ڵ�1" , root,"id1","parentid1");
            tp.addNode("�ڵ�2" , root,"id2","parentid2");
            tp.addNode("�ڵ�3" , root,"id3","parentid3");
            System.out.println("����ӽڵ��ĸ��ڵ㣺" + root);
            System.out.println("���������:" + tp.deep());
            //��ȡ���ڵ�������ӽڵ�
            List<Node<String>> nodes = tp.children(root);
            System.out.println("���ڵ�ĵ�һ���ӽڵ㣺" + nodes.get(0));
            //Ϊ���ڵ�ĵ�һ���ӽڵ�����һ���ӽڵ�
            tp.addNode("�ڵ�4" , nodes.get(0),"id4","parentid4");
            System.out.println("���ڵ�ĵ�һ���ӽڵ㣺" + nodes.get(0));
            System.out.println("���������:" + tp.deep());
        }
    }


