package jdk18;

import clojure.lang.Obj;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class lambda_demo1 {
     /* map ：forEach   (k,v)-->{sout}
        list: forEach   (k) -->{sout}
                        System.out::prntln
        Runnable:   ()->{}  [run()]

        Stream :所以的stream必须以lambda为参数
        16种形式
      */

    public static void main(String[] args) {
        //demo map
        Map<String,Integer> map = new HashMap<>();
        map.put("2222",111);
        map.put("1111",222);
        map.put(null,null);
        map.put("key",null);
        //demo list
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");


        //map for each remove the {}
        map.forEach((k,v)->System.out.println("k="+k+",v="+v));
        map.forEach((k,v)->{
            System.out.println(("k=" + k + ",v=" + v));
        });

        //list
        list.forEach(v->{
            System.out.println(v);
        });

        list.forEach(System.out::append);
        list.forEach(System.out::println);

        Runnable r = ()->System.out.println("this is a runnable");
        Runnable r1 =()->{
            System.out.println("this is two runnable");
        };
        new Thread(r).start();
        new Thread(r1).start();

        //simple filter
        List<String> result=list.stream()
                .filter(str->"ccc".equals(str)).collect(Collectors.toList());
        System.out.println(result);
        //1 stream 构造
        //数组 list
        Stream<String> s= Stream.of("a","b","c");
        String [] arrStr = new String[]{"a","b","c"};
        Stream.of(arrStr).forEach(System.out::println);
        Arrays.stream(arrStr).forEach(System.out::println);
        list.stream().forEach(System.out::println);

        //2 Stream只能被使用一次
        //不用重复使用
        String [] ss= s.toArray(String[]::new);

        try {
            List<String> l=   s.collect(Collectors.toList());
            List<String> l2=   s.collect(Collectors.toCollection(ArrayList::new));
            Set set= s.collect(Collectors.toSet());
            Stack stack=s.collect(Collectors.toCollection(Stack::new));
            String s3=s.collect(Collectors.joining()).toString();

        }catch (Exception e){
            System.out.println("only one time "+e);
        }


        //3 Stream map
        List<String> l3 =list.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<Integer> L4 =list.stream().map(String::length).collect(Collectors.toList());
        List<String> l5 = list.stream().map(n->n.concat("aaa")).collect(Collectors.toList());
        List<Object> l6 =new ArrayList<>();
        l6.add(l3);l6.add(L4);l6.add(l5);

        l6.forEach(x->{
            System.out.println(x);
        });
        //4 String filter


        //5 flatMap
        //6 limit
        //7 sort
        //8 peek
        //9 parallel 并行处理
        //10 max/min/distinct
        //11 match
        //12 reduce




    }

}
