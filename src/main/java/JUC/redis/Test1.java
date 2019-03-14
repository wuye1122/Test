package JUC.redis;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.UUID;

public class Test1 {
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
    public static void main(String[] args) {

        //System.out.println(Test1.getUUID());

        String  value = "|key:1|key2:2|key4:4|";
        JSONObject obj = new JSONObject();
        String [] valueArr =value.split("\\|");
        System.out.println("valueArr："+Arrays.asList(valueArr).toString());
        for(int i=0;i<valueArr.length;i++){
            String []keyArr =valueArr[i].split(":");
            //    System.out.println("keyArr："+Arrays.asList(keyArr).toString());
            //   System.out.println("keyArr的长度："+keyArr.length);
            if(keyArr.length==2){
                String key1=keyArr[0];
                String value1=keyArr[1];
                if(StringUtils.isNotBlank(key1)){
                    obj.put(key1,value1);
                }
            }
        }

        String keyValue=obj.toString();
        //   System.out.println("需要绑定的value值【" + keyValue + "】");
    }
}
