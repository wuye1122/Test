package Test;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;

public class dcmsRecord {


    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * 下载文件---返回下载后的文件存储路径
     *
     * @param url 文件地址
     * @param dir 存储目录
     * @param fileName 存储文件名
     * @return
     */
    public static void downloadHttpUrl(String url, String dir, String fileName) {
        try {

            File dirfile = new File(dir);
            if (!dirfile.exists()) {
                dirfile.mkdirs();
            }
            FileUtils.copyURLToFile(new URL(url), new File(dir+fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param savePath
     * @throws IOException
     */
    public static String downLoadFromUrl(String urlStr, String savePath) {
        try {

            URL url = new URL(urlStr);

            String fileName ="";
            //截取文件名
            System.out.println("下载录音地址:"+urlStr);
            String [] fileNameArr =  urlStr.split("/");
            System.out.println(fileNameArr.length);
            if(fileNameArr.length!=0){
                fileName=fileNameArr[fileNameArr.length-1];
                System.out.println("fileName:"+fileName);
            }


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            // 得到输入流
            InputStream inputStream = conn.getInputStream();
            // 获取自己数组
            byte[] getData = readInputStream(inputStream);

            // 文件保存位置
            File saveDir = new File(savePath);
            System.out.println("savePath:"+savePath);
            System.out.println(saveDir.exists());

            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            File file = new File(saveDir + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            // System.out.println("info:"+url+" download success");
            return saveDir + File.separator + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {

        String path ="/doublingRecord/http://58.61.143.76:8080/bxrecord/record/0000000001/sr_agent/20180823/TEL-02810010_1001_20180823002620_sr_a.wav";

        System.out.println(path.indexOf("http:"));
        System.out.println(path.indexOf("https:"));
        System.out.println("======================");
        //把地址截取出来
        String newUrl="";

        String fileName="";
        String url = "http://58.61.143.76:8080/bxrecord/record/0000000001/sr_agent/20180823/TEL-02810010_1001_20180823002620_sr_a.wav";
        String [] fileNameArr =  url.split("/");
        System.out.println(fileNameArr.length);
        if(fileNameArr.length!=0){
            fileName=fileNameArr[fileNameArr.length-1];
            System.out.println("fileName:"+fileName);
        }

        dcmsRecord.downLoadFromUrl("http://58.61.143.76:8080/bxrecord/record/0000000001/sr_agent/20180823/TEL-02810010_1001_20180823002620_sr_a.wav","E:\\JUC\\IDEA\\IdeaProjects\\LTBigdata\\dcmsRecord\\target\\dcmsRecord-2.2.8.1\\temp\\2018-12-10");
    }
}
