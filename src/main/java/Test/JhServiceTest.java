package Test;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

public class JhServiceTest {


    public static String change(String value){

        JSONObject obj = new JSONObject();
        if(value.startsWith("|")){
            value=  value.substring(1,value.length()-1);
            System.out.println("value :"+value);
            // logger.debug("绑定的数据："+value);
        }
        String []keyArr =value.split("\\|",-1);
        System.out.println(Arrays.asList(keyArr).toString());
        //logger.debug(Arrays.asList(keyArr).toString());
        for(int i=0;i<keyArr.length;i++){
            if(i%2==0){
                if(i+1<keyArr.length){
                    obj.put(keyArr[i],keyArr[i+1]);
                }
            }
        }
        System.out.println(""+obj.toString());
        return obj.toString();
    }
    public static void main(String[] args) {

        String  value = "CaseID|120180705000155264|Cust_Name|22|Sex|南|CycleDay|1|Due_Amt|4487.00|Case_Amt|10001.00|QUEUE|T0101|STATUS||STATUS1|021|STATUS2|0565|NUMATTEMPTS|1|RECORDKEY|2_1_1_256_4151381_3|";
        String  value1 = "CaseID|120180705000155264|Cust_Name|22|Sex|no|CycleDay|1|Due_Amt|4487.00|Case_Amt|10001.00|QUEUE|T0101|STATUS||STATUS1|021|STATUS2|0565|NUMATTEMPTS|1|RECORDKEY|2_1_1_256_4151381_3|";
        String  value3 ="|key1|value2|key3|value4|";
        String  value4 ="|key1|value2|key3||";
        String  value5 ="|key1|value2||value4|";
        String  value6 ="|value2|key3||";
        String  value7 ="|key1|value2|value4||";
        JhServiceTest.change(value1);
        System.out.println("value6======="+JhServiceTest.change(value6));
        JhServiceTest.change(value7);




    }
}
