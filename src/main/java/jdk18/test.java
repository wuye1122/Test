package jdk18;

import Test.UrlEncode;
import Test.UrlEncoderTest;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;

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
    public static void main(String[] args) throws UnsupportedEncodingException {

        //request 4.5.6.7.8


         //slack
        //https://www.atlassian.net/login?redirectCount=1&dest-url=/wiki/&application=confluence
        System.out.println(URLDecoder.decode("https://id.atlassian.com/login?continue=" +
                "" +
                "https%3A%2F%2Fid.atlassian.com%2Foidc%2Foauth%2Fauthorize%3Fresponse_type%3Dcode%26redirect_uri%3Dhttps%253A%252F%252Fauth.atlassian.com%252Flogin%252Fcallback%26state%3Dt62c8l5gGAbdn9WckA6NowBkjvctVNH8%26client_id%3DRP6QeEK_26C7q49Yf&prompt=login", "utf8"));
        System.out.println(URLDecoder.decode("https%3A%2F%2Fauth.atlassian.com%2Flogin%2Fcallback"));


        System.out.println(URLDecoder.decode("https://id.atlassian.com/login?continue=https%3A%2F%2Fzoomdev.atlassian.net%2Flogin%3FredirectCount%3D1%26dest-url%3Dhttps%253A%252F%252Fzoomdev.atlassian.net%252Fplugins%252Fservlet%252Foauth%252Fauthorize%253Foauth_token%253DTu140ntXAtTvU5XW2ueD6pSt1Ux5puAh%26application%3Djira&application=jira", "utf8"));
        System.out.println(URLDecoder.decode("https%3A%2F%2Fzoomdev.atlassian.net%2Fplugins%2Fservlet%2Foauth%2Fauthorize%3Foauth_token%3DTu140ntXAtTvU5XW2ueD6pSt1Ux5puAh"));

        //https://id.atlassian.com/login?continue=https%3A%2F%2Fzoomdev.atlassian.net%2Flogin%3FredirectCount%3D1%26dest-url%3Dhttps%253A%252F%252Fzoomdev.atlassian.net%252Fplugins%252Fservlet%252Foauth%252Fauthorize%253Foauth_token%253DTu140ntXAtTvU5XW2ueD6pSt1Ux5puAh%26application%3Djira&application=jira
        //jira slack
        System.out.println(URLDecoder.decode("https%253A%252F%252Fauth.atlassian.com%252Flogin%252Fcallback"));

        System.out.println(URLDecoder.decode("?response_type%3Dcode%26redirect_uri%3Dhttps%253A%252F%252Fauth.atlassian.com%252Flogin%252Fcallback%26state%3D92ZAajzSZN45drAEVkmp1xYkoWA-JMv8%26client_id%3DRP6QeEK_26C7q49Yf&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaWdudXAiLCJpYXQiOjE1ODU3NTYyMDgsImV4cCI6MTU4NTc1NjMyOCwidXNlcklkIjoiNWRmOTliMWFmMTZiNjgwY2I1YmQ4ZmMzIiwibG9naW5UeXBlIjoiZ29vZ2xlWW9sb0xvZ2luIiwibWFya2VkVmVyaWZpZWQiOiJmYWxzZSIsInNjb3BlIjoiTG9naW4ifQ.jrJiA4UPMNFZyXvq9SHZluyddnCx4gLLlr9iWacKl5g"));
        System.out.println(URLDecoder.decode("https%3A//ll-lemon.atlassian.net/projects/LL%3FselectedItem%3Dcom.atlassian.plugins.atlassian-connect-plugin%3Ajira-slack-integration__project-config-page%26subscriptionId%3D707176%26project.id%3D10000%26channel.id%3DD010GE40UBS%26parentComponent%3Dproject_config","utf8"));
    }
}
