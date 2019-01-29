package Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import JUC.kafka.po.ParamPo;

public class rePushDps
{
  public static void main(String[] args)
  {
    String path;
    if (args.length > 0)
      path = args[0];
    else {
      path = "E:\\JUC\\����\\���������ļ�\\JUC\\800��ҵ\\2018��һ֮ǰ\\����ͷ���ӿ�\\jvm-default.log";
    }

    File file = new File(path);

    FileWriter fw = null;
    try
    {
      File rfile = new File("errorPush.txt");
      if (!rfile.exists())
      {
        System.out.println("�ļ������ڴ����ļ�");

        rfile.createNewFile();
      }
      fw = new FileWriter(rfile);
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
      System.out.println("�����ļ��쳣");
    }

    if (file.exists())
      try
      {
        StringBuffer sb = new StringBuffer("");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));

        String str = null;

        while ((str = br.readLine()) != null) {
          sb.append(str + "/n");
          if ((str.contains("DataPushThread")) && (str.contains("ERROR"))) {
            System.out.println(str);
            str = str.substring(str.indexOf("{"));
            System.out.println("���ղ��� : " + str);
            JSONObject obj = JSON.parseObject(str);
            String param = obj.get("param").toString();
            System.out.println("����url : " + obj.get("pushUrl"));
            ParamPo po = (ParamPo)JSONObject.parseObject(param, ParamPo.class);

            System.out.println("����paramPo : " + po);

            if (StringUtils.isNotBlank(po.getSessionId())) {
              po.setRequestId(Long.toHexString(Long.valueOf(po.getSessionId()).longValue()));
            }
            fw.write(str + System.getProperty("line.separator"));
            fw.write(po.toString() + System.getProperty("line.separator"));
            fw.write(obj.get("pushUrl").toString() + System.getProperty("line.separator"));
          }
        }

        br.close();
        fw.close();
      }
      catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    else {
      try {
        fw.write("��־�ļ�������");
        fw.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println("����ɨ�����");
  }
}