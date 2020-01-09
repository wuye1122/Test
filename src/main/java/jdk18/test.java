package jdk18;

import org.apache.commons.lang.StringUtils;

import java.net.URI;

public class test {

    public static boolean isSuccess(int a){
        try{
            Integer status = a;
            if(null!=status&&(status >= 200 && status < 300)){
                return true;
            }else{
                throw  new Exception("this is a success access!");
            }
        }catch (Exception e){
            System.out.println("e:"+e.getMessage());
            return false;
        }

    }
    public static void main(String[] args) {

        System.out.println("boolean:"+isSuccess(20));

        // request 2


        //request3
        System.out.println("2222");
    }
}
