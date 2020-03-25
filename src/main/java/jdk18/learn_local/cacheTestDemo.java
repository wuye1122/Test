package jdk18.learn_local;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.io.BsonOutput;

import java.util.*;
import java.util.stream.Collectors;

public class cacheTestDemo {




    public static void main(String[] args) {
        //过滤一个集合 condition = filter(xxx : d -> definition.getHookId().equals(d.getSuperHookId()) )
            List<Integer>  definitions = new ArrayList<>();
            definitions.add(1);
            definitions.add(2);
            definitions.add(2);

            Integer a=2;
            List<Integer> subDefList = definitions.stream().filter(d -> a.equals(d)).collect(Collectors.toList());
            System.out.println(definitions.toString());
            System.out.println(subDefList.toString());

        //
        Map<String,Integer> map = new HashMap<>();
        map.put("aa",22);
        map.put("bb",33);
        map.put("cc",22);
        if (map != null) {
            map.forEach((d, c) -> {
                System.out.println("::"+d);
                System.out.println("--"+c);
            });
        }

        if (map != null) {
            map.forEach((d, c) ->{
            });
        }

        if (map != null) {
            map.forEach((d, c) ->Optional.ofNullable(c).ifPresent(e->{
                //过滤并且对其进行操作 if c!=null e
                System.out.println("e::"+e);
                System.out.println("d::"+e);
                System.out.println("c::"+e);

            }));
        }

      /*  if (map != null) {
            map.forEach((d, c) -> taskExecutor.execute(
                    () -> Optional.ofNullable(c).ifPresent(imc -> messageSender.send(d, imc)))
            );
        }*/



    }
}
