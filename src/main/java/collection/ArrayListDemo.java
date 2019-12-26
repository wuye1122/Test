package collection;

import java.lang.reflect.Array;
import java.util.*;

public class ArrayListDemo {

    public static void main(String[] args) {

        //��ʶ�ӿ�RandomAccess �� ArrayList LinkedList
        System.out.println("======================RandomAccess");

        List arrayList=new ArrayList();

         List linkedList= new LinkedList();
        System.out.println(arrayList instanceof RandomAccess);
        System.out.println(linkedList instanceof RandomAccess);


        //arryList����
         arrayList.add(4);
         arrayList.add(3);
         arrayList.add(1);
        //��������+��������+ ����ͼ���ֱ��ת��
        Collections.sort(arrayList);
        System.out.println("======================Arrays.asList(list.toArray)");
        System.out.println("����֮�� arryList:"+arrayList);
        System.out.println("ת�������飺"+arrayList.toArray());
        System.out.println(Arrays.toString(arrayList.toArray()));
        List list = Arrays.asList(arrayList.toArray());
        System.out.println("ת��֮��list:"+list.toString());

        //����  ����  �  ȥ�ز�����
        System.out.println("======================����  ����  �  ȥ�ز�����");
        List<Integer> list1= new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2= new ArrayList<>();

        list2.add(3);
        list2.add(4);

       // list1.addAll(list2);
       //  list1.retainAll(list2);
           list2.removeAll(list1);
           list1.addAll(list2);
        for(Integer  i: list1){
            System.out.println("i:"+i);
        }

        //������ 1 2 3 3 4

        //������  3

        //��� 12

        //ȥ�� 1 2 3 4
        System.out.println("=======================system.arraycopy && arrays.copyOf");
      /*  System.arraycopy(); src index dest index copy����
        Arrays.copyOf()*/
        String[] a = new String[10];
        a[0]="1";
        a[1]="2";
        a[2]="3";
        a[6]="8";
        System.out.println(a.length);
        System.out.println("ת��֮ǰ��a"+Arrays.asList(a).toString());
         System.arraycopy(a,2,a,3,5);
        System.out.println("ת��֮��a"+Arrays.asList(a).toString());

        //List  add
        String[] src = new String[4];
        src[0]="a";
        src[1]="b";
        src[2]="c";
        System.out.println("δ��֮ǰsrc:"+Arrays.asList(src).toString());
        //��ָ����������һ��Ԫ�أ��������ݣ�������֮������Ԫ��copy����Ԫ������
        System.arraycopy(src,1,src,2,2);
        System.out.println("add index --> src:"+Arrays.asList(src).toString());


        //���� Arrays.copyof()
        String  []dest =Arrays.copyOf(src,2);
        System.out.println("dest:"+Arrays.asList(dest).toString());
        String  []dest1 =Arrays.copyOf(src,src.length);
        System.out.println("dest1:"+Arrays.asList(dest1).toString());
        String  []dest2 =Arrays.copyOf(src,src.length*2);
        System.out.println("dest2:"+Arrays.asList(dest2).toString());

        //Arrays.copyOf �ڲ�ԭ�����System.arraycopy  Դ���ó���
        // ��һ��������Ҫcopy������ ���ڶ�������copy���鳤��
        // ���� ʹ�÷��䶯̬����һ������ Arrays.newInstance()
        System.out.println("=====================Arrays.copyOf()Դ�����");
        Class newType = String[].class;
        System.out.println("newType:"+newType);
        int newLength =10;
        System.out.println(((Object)newType == (Object)Object[].class));
        String[] copy = ((Object)newType == (Object)Object[].class)
                ? (String[]) new Object[newLength]
                : (String[]) Array.newInstance(newType.getComponentType(), newLength);
        System.out.println("newType.getComponentType():"+newType.getComponentType());
        System.out.println("copy-length:"+copy.length);

        System.out.println("=====================Arrays.copyOf()Դ�����");
        //�ڲ�ʵ�� �Ȳ�������������

        System.out.println("src:"+Arrays.asList(src).toString());
        String [] src1=  Arrays.copyOf(src,src.length);
        System.out.println("src1:"+Arrays.asList(src1).toString());
        String [] src2=  Arrays.copyOf(src,src.length-1);
        System.out.println("src2:"+Arrays.asList(src2).toString());
        String [] src3=  Arrays.copyOf(src,src.length+1);
        System.out.println("src3:"+Arrays.asList(src3).toString());

        System.arraycopy(src,0,src,0,Math.min(src.length,src.length+1));
        System.out.println("src====:"+Arrays.asList(src).toString());


    }
}
