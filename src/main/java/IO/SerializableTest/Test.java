package IO.SerializableTest;

import org.apache.commons.lang.StringUtils;
import scala.collection.parallel.ParIterableLike;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String xiaoying="0101490464\n" +
                "0101490402\n" +
                "0101190416\n" +
                "0101290287\n" +
                "0101290511\n" +
                "0101190415\n" +
                "0101490467\n" +
                "0101490466\n" +
                "0101190371\n" +
                "0101490403\n" +
                "0101490463\n" +
                "0101490410\n" +
                "0101190415\n" +
                "0101490401\n" +
                "0101490313\n" +
                "0101290221\n" +
                "0101490465\n" +
                "0101290280\n" +
                "0101490461\n" +
                "0101490401\n" +
                "0101190415\n" +
                "0101490410\n" +
                "0101290424";
       String []  num=  xiaoying.split("\\n");
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<num.length;i++){
            if(map.get(num[i])==null){
               map.put(num[i],1);
            }else{
                Integer value = map.get(num[i]).intValue()+1;
                map.put(num[i],value);
                System.out.println("ÖØ¸´ÆóÒµ£º"+num[i]);

            }
        }
        System.out.println(map.toString());
        System.out.println(map.size());

    }
}
