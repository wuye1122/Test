package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author�� wuhl
 * @date: 2018/5/2
 */
public class GetHtmlUrl {
    /**
     * Ҫ��������ҳ
     */
    String htmlUrl;
    /**
     * �������
     */
    ArrayList<String> hrefList = new ArrayList();
    /**
     * ��ҳ���뷽ʽ
     */
    String charSet;
    public GetHtmlUrl(String htmlUrl) {
        // TODO �Զ����ɵĹ��캯�����
        this.htmlUrl = htmlUrl;
    }
    /**
     * ��ȡ�������
     *
     * @throws IOException
     */
    public ArrayList<String> getHrefList() throws IOException {
        parser();
        return hrefList;
    }
    /**
     * ������ҳ����
     *
     * @return
     * @throws IOException
     */
    private void parser() throws IOException {
        URL url = new URL(htmlUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        String contenttype = connection.getContentType();
        charSet = getCharset(contenttype);
        //�ֶ���ӱ����ʽ
        charSet = "UTF-8";

        InputStreamReader isr = new InputStreamReader(
                connection.getInputStream(), charSet);
        BufferedReader br = new BufferedReader(isr);
        String str = null, rs = null;
        while ((str = br.readLine()) != null) {
            //  rs = getHref(str);
            if (str != null) {
                hrefList.add(str);
            }
        }
    }
    /**
     * ��ȡ��ҳ���뷽ʽ
     *
     * @param str
     */
    private String getCharset(String str) {
        Pattern pattern = Pattern.compile("charset=.*");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(0).split("charset=")[1];
        }
        return null;
    }
    /**
     * ��һ���ַ����ж�ȡ����
     *
     * @return
     */
    private String getHref(String str) {
        Pattern pattern = Pattern.compile("<a href=.*</a>");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(0);
        }return null;

    }
    public void toString(String htmlUrl){
        //4.��ȡͷ����Ϣ֮һ����ȡ����ͷ����Ϣ���ٱ���
        URL url;
        try {
            url = new URL(htmlUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Map<String, List<String>> headers =connection.getHeaderFields();
            for(Map.Entry<String,List<String>> entry : headers.entrySet()){
                System.out.println(entry.getKey()+" : ");
                for(String value : entry.getValue()){
                    System.out.println(value+" , ");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public  void  getSpringBoot(){

    }
    public static void main(String[] arg) throws IOException {
        GetHtmlUrl b = new GetHtmlUrl("https://mp.weixin.qq.com/s?__biz=MzI4MDYwMDc3MQ==&mid=2247483913&idx=1&sn=a7948371ead4e29fe9bee1f8a1302e78&chksm=ebb74ba5dcc0c2b3bf8b06e16bc9c7a5bc268e9830eb9d7ee9e3c936f4899e36160c353b161b&mpshare=1&scene=23&srcid=0508QZCtPDaoxExviEA6TL6i#rd");
        System.out.println(b);
        //  b.toString(b.htmlUrl);
        ArrayList<String> hrefList = b.getHrefList();
        System.out.println(hrefList.toString());

        for (int i = 0; i < hrefList.size(); i++){
            System.out.println(hrefList.get(i)+"<br></br>");
        };

       /* GetHtmlUrl a = new GetHtmlUrl("http://blog.didispace.com/Spring-Boot�����̳�");
        ArrayList<String> hrefList = a.getHrefList();
        for (int i = 0; i < hrefList.size(); i++){
        	if(hrefList.get(i).contains("springboot")||hrefList.get(i).contains("mybatis")||hrefList.get(i).contains("spring-boot")){
                System.out.println(hrefList.get(i)+"<br></br>");

        	}
        };*/
    }

}
