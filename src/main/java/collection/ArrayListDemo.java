package collection;

import java.lang.reflect.Array;
import java.util.*;

public class ArrayListDemo {

    public static void main(String[] args) {

        //标识接口RandomAccess ： ArrayList LinkedList
        System.out.println("======================RandomAccess");

        List arrayList=new ArrayList();

         List linkedList= new LinkedList();
        System.out.println(arrayList instanceof RandomAccess);
        System.out.println(linkedList instanceof RandomAccess);


        //arryList排序
         arrayList.add(4);
         arrayList.add(3);
         arrayList.add(1);
        //数组排序+集合排序+ 数组和集合直接转换
        Collections.sort(arrayList);
        System.out.println("======================Arrays.asList(list.toArray)");
        System.out.println("排序之后 arryList:"+arrayList);
        System.out.println("转换成数组："+arrayList.toArray());
        System.out.println(Arrays.toString(arrayList.toArray()));
        List list = Arrays.asList(arrayList.toArray());
        System.out.println("转化之后list:"+list.toString());

        //交集  并集  差集  去重并集合
        System.out.println("======================交集  并集  差集  去重并集合");
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

        //并集： 1 2 3 3 4

        //交集：  3

        //差集： 12

        //去重 1 2 3 4
        System.out.println("=======================system.arraycopy && arrays.copyOf");
      /*  System.arraycopy(); src index dest index copy个数
        Arrays.copyOf()*/
        String[] a = new String[10];
        a[0]="1";
        a[1]="2";
        a[2]="3";
        a[6]="8";
        System.out.println(a.length);
        System.out.println("转换之前：a"+Arrays.asList(a).toString());
         System.arraycopy(a,2,a,3,5);
        System.out.println("转换之后：a"+Arrays.asList(a).toString());

        //List  add
        String[] src = new String[4];
        src[0]="a";
        src[1]="b";
        src[2]="c";
        System.out.println("未加之前src:"+Arrays.asList(src).toString());
        //从指定索引增加一个元素，首先扩容，将索引之后所有元素copy到新元素里面
        System.arraycopy(src,1,src,2,2);
        System.out.println("add index --> src:"+Arrays.asList(src).toString());


        //测试 Arrays.copyof()
        String  []dest =Arrays.copyOf(src,2);
        System.out.println("dest:"+Arrays.asList(dest).toString());
        String  []dest1 =Arrays.copyOf(src,src.length);
        System.out.println("dest1:"+Arrays.asList(dest1).toString());
        String  []dest2 =Arrays.copyOf(src,src.length*2);
        System.out.println("dest2:"+Arrays.asList(dest2).toString());

        //Arrays.copyOf 内部原理就是System.arraycopy  源码拿出来
        // 第一个参数需要copy的数组 ：第二个参数copy数组长度
        // 首先 使用反射动态创建一个数组 Arrays.newInstance()
        System.out.println("=====================Arrays.copyOf()源码分析");
        Class newType = String[].class;
        System.out.println("newType:"+newType);
        int newLength =10;
        System.out.println(((Object)newType == (Object)Object[].class));
        String[] copy = ((Object)newType == (Object)Object[].class)
                ? (String[]) new Object[newLength]
                : (String[]) Array.newInstance(newType.getComponentType(), newLength);
        System.out.println("newType.getComponentType():"+newType.getComponentType());
        System.out.println("copy-length:"+copy.length);

        System.out.println("=====================Arrays.copyOf()源码分析");
        //内部实现 先不考虑数组类型

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
