package Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class LogUtil
{
  private static Logger logger = Logger.getLogger(LogUtil.class);

  public static String getLogByLine(String path, String[] key)
  {
    StringBuffer sb = new StringBuffer("");
    logger.debug("正在进入LogUtil.getLogByLine()....");

    if (StringUtils.isBlank(path)) {
      return "path不能为空";
    }
    FileReader reader = null;
    BufferedReader br = null;
    try
    {
      reader = new FileReader(path);
      br = new BufferedReader(reader);
      String str = null;
      while ((str = br.readLine()) != null)
      {
        if (containstr(str, key))
        {
          sb.append(str + System.getProperty("line.separator"));
        }
      }
    }
    catch (Exception e) {
      logger.debug("读取日志异常", e);
      return "读取日志异常";
    }
    finally
    {
      try {
        if (br != null) {
          br.close();
        }
        if (reader != null)
          reader.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    return sb.toString();
  }

  public static boolean containstr(String str, String[] s)
  {
    boolean flag = true;
    for (int i = 0; i < s.length; i++) {
      if (!str.contains(s[i])) {
        flag = false;
      }
    }
    return flag;
  }

  public static void main(String[] args)
  {
  }

  public static void getNumOfDps()
  {
    List list = new ArrayList();
    List list1 = new ArrayList();
    Map map = new HashMap();
    try
    {
      StringBuffer sb = new StringBuffer("");

      FileReader reader = new FileReader(
        "C:\\Users\\Administrator.USER-20161101FI\\Desktop\\800企业\\jvm-default.log.20180328.1352");
      BufferedReader br = new BufferedReader(reader);

      String str = null;

      while ((str = br.readLine()) != null) {
        sb.append(str + "/n");

        if (str.contains("推送后接收的返回值，result")) {
          list1.add("result");
        }

        if (str.contains("DEBUG"))
        {
          String start = str.substring(20, 23);

          String key = str.substring(str.indexOf("["), 
            str.indexOf("]") + 1);

          if (map.containsKey(key)) {
            list.add((String)map.get(key));
            map.remove(key);
          } else {
            map.put(key, start);
          }
        }
        else
        {
          String end = str.substring(20, 23);
          String key = str.substring(str.indexOf("["), 
            str.indexOf("]") + 1);

          if (map.containsKey(key)) {
            String value = String.valueOf(Integer.valueOf(end).intValue() - 
              Integer.valueOf((String)map.get(key)).intValue());

            if (Integer.valueOf(value).intValue() < 0)
            {
              String val = 
                String.valueOf(Integer.valueOf(value).intValue() + 1000);

              map.put(key, val);
            }
            else {
              map.put(key, value);
            }

            list.add((String)map.get(key));
            map.remove(key);
          }

        }

      }

      System.out.println("map：" + map.size());
      br.close();
      reader.close();
      System.out.println(list1.size());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}