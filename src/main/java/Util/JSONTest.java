package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;


import wuhl.kafka.po.TestJsonNoSerialiable;
import wuhl.kafka.po.TestJsonPo;

public class JSONTest {

	/**
	 * @author wuhl
	 * void
	 * 
	 * 1 ���л� serializable :�Ѷ������Ϣת��Ϊ���Դ洢�ʹ���Ĺ��� �Ƶ�����ƽ̨���Է����л�
	 *       
	 *                      poʵ��serialiable �벻ʵ�ֵ�po���жԱ�
	 *       
	 *                      java.io.NotSerializableException: wuhl.kafka.po.TestJsonNoSerialiable
	 *                   
	 *                      ����Ҫ��fastjson�����л��ͷ����л�������Ҫ���ر����ã�Ψһ��Ҫ���ǣ������л��������java bean�淶��
     *
     *                       ��fastjson��1.2.21�汾���ṩ��һ�������gson�����ԣ�
     *                        ֧�ַ����л�ʱʹ�ö����ͬ���ֶ����ƣ�ʹ�õķ�ʽ������
     *                        JSONField��alternateNames��
     *                        https://www.w3cschool.cn/fastjson/fastjson-jsonfield.html
     *                         ӳ�����ֶ�
     *                        @JSONField(alternateNames={"USER","NAME"})
	 *                      
     *                        ��ͨ��ӳ��
     *                        @JSONField(name="ID")
     *                        
     *                     
	 *                         
	 * 2 io �ļ�·�����⣺ win:  \\ ���� /
	 * 
	 *                 �Ƿ����exists    �����ļ�createNewFile 
	 *                 ����һ��Ŀ¼mkdir  �����༯mkdirs 
	 * 
	 *          
	 */
	//serialiable
	public void writeObject() {
		//TestJsonPo po = new TestJsonPo();
		TestJsonNoSerialiable po=new TestJsonNoSerialiable();
		po.address = "��ַ";
		po.name = "���л���po";
		po.number = 10;
		po.SSN = 5;
	
		try {
			
			File f=new File("E:\\workspace\\Test\\src\\main\\java\\Util\\json.ser");
			if(f.exists()==false){
				f.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(f);
			ObjectOutputStream write = new ObjectOutputStream(out);
			write.writeObject(po);
			out.close();
			write.close();
			System.out.println("serialiable is show in the disk");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// deSerialiable
	public void readObject() {
		//TestJsonPo po = null;
		TestJsonNoSerialiable po=null;

		try {
			File f=new File("E:\\workspace\\Test\\src\\main\\java\\Util\\json.ser");
			
			FileInputStream in = new FileInputStream(f);
			
			ObjectInputStream read = new ObjectInputStream(in);
		//	po = (TestJsonPo) read.readObject();
			po = (TestJsonNoSerialiable) read.readObject();
			in.close();
			read.close();
			System.out.println("deSerialiable is show in the object:"+po);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}
	
	public void testFileIsExist(){
		try {
			String path = "E:\\workspace\\Test\\src\\main\\java\\Util\\serializable.ser";
			String path1 = "E:/workspace/Test/src/main/java/Util/serializable";

			
			path = "text.txt";
			File f = new File(path1);
			
			//����������Ϊ������ʾΪ null
			System.out.println(f.getPath());
			
			//SecurityException - ����޷����������ϵͳ����ֵ��
			//��������ڵ�linuxϵͳ ���� winϵͳ���л�ȡ·��
			//ͨ�����ݵ�ǰ�û�Ŀ¼����ĳһ���·����
			System.out.println(f.getAbsolutePath());
			
			//getCanonicalFilen�ܹ�����.. .����ʽ
			System.out.println(f.getCanonicalFile());

		/*	System.out.println("�Ƿ����:" + f.exists());
			System.out.println("�Ƿ���Ŀ¼:" + f.isDirectory());
			System.out.println("�Ƿ����ļ�:" + f.isFile());*/
			
			//System.out.println(f.createNewFile());
			//System.out.println(f.mkdir());
			System.out.println(f.exists());
			FileInputStream in = new FileInputStream(path1);
			/*
			 * if(f.exists()==false){ f.createNewFile(); }
			 */
			System.out.println(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getCurrentFilePath(){
		System.out.println("this.getClass().getResource():"+this.getClass().getResource("/"));
		File f=new File(this.getClass().getResource("/").getPath());
		File f1=new File(this.getClass().getResource("").getPath());
        System.out.println("f1  ���̣�"+f);
        System.out.println("f1  �ࣺ"+f1);
		File f2=new File("");
        try {
			System.out.println("f2  :"+f2.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("f3  :"+System.getProperty("user.dir"));
		System.out.println("f4  :"+System.getProperty("java.class.path"));
		/*
	    System.out.println("java.version: "+System.getProperty("java.version"));
	    System.out.println("java.class.path: "+System.getProperty("java.class.path"));
	    System.out.println("file.separator: "+System.getProperty("file.separator"));
	    System.out.println("line.separator: "+System.getProperty("line.separator"));
	    System.out.println("path.separator: "+System.getProperty("path.separator"));
	    //��ȡ��ǰ��Ŀ��·�����ַ�����
	    //���·����ȡ relativelyPath
	    System.out.println(System.getProperty("Test"))                   ;
	    String relativelyPath=System.getProperty("user.dir");
	    System.out.println("1���·����ȡ��"+relativelyPath);
	    System.out.println("\\һ��б����ת���ַ��� "+relativelyPath+"\\save");
	    System.out.println("/һ��б����ת���ַ��� "+relativelyPath+"/save");*/
		//System.getProperty();
		
	}
	
	public void knowJson(){
		HashMap map1=new HashMap();
		map1.put("current", "5");
		map1.put("waiting", "20");

		TestJsonPo po = new TestJsonPo();
		//TestJsonNoSerialiable po=new TestJsonNoSerialiable();
		po.address = "��ַ";
		po.name = "���л���po";
		po.number = 10;
		po.SSN = 5;
		po.setObj(map1);
		//string ->jsonobject
		String jsonStr="{\"address\":\"��ַ\",\"name\":\"���л���po\",\"number\":10}";	
		TestJsonNoSerialiable msg = JSON.parseObject(jsonStr,TestJsonNoSerialiable.class);
        System.out.println("jsonStr->object:"+msg); 
		
		//���л� object->string
        String	json=JSON.toJSONString(po);
		System.out.println("popopo:"+json);
		
		//�����л� String->po
		TestJsonNoSerialiable po1=JSON.parseObject(json, TestJsonNoSerialiable.class);
		System.out.println(po1.toString());

		
		//�����л� String ->JSONObject ��ʾ�ĺ�stringһ������ ʵ�ʲ�ͬ
		JSONObject object= JSON.parseObject(json);
		System.out.println(object);
		System.out.println(object.get("address"));
		System.out.println(object.get("name"));
		System.out.println(object.get("number"));


         //���ͷ����л� 
		Map<String,Object> map=JSON.parseObject(json, new TypeReference<Map<String,Object>>(){});
		System.out.println(map.toString());
		System.out.println(map.get("address"));
		System.out.println(map.get("name"));
		System.out.println(map.get("number"));

		//FastJSON����ֱ�Ӷ��������͸�ʽ������ȱʡ������£�FastJSON�ὫDateת��long��
		//toJSONBytes(Object arg0, SerializerFeature... arg1) 
		/*
		 * ת����string ���������˫����: SerializerFeature.UseSingleQuotes
		 * 
		 * ʹ�����ڸ�ʽ:SerializerFeature.WriteDateUseDateFormat
		 * 
		 * 
		 * 
		 * */
		
         String a=JSON.toJSONString(new Date());
         String b=JSON.toJSONString(new Date(),SerializerFeature.WriteDateUseDateFormat);
         String c=JSON.toJSONStringWithDateFormat(new Date(),"yyyy-MM-dd");
         System.out.println(a);
         System.out.println(b);
         System.out.println(c.toString());
         
        //mongodb �����������  docment��������α����ѭ�� ==������ֱ��ת����List<po>
         List<TestJsonPo> list=new ArrayList<TestJsonPo>();
         for(int i=0;i<5;i++){
        	 TestJsonPo p=new TestJsonPo();
         
        		p.address = "��ַ"+i;
        		p.name = "���л���po"+i;
        		p.number =i;
        		p.SSN = i;
        		list.add(p);
         }
          System.out.println(list);
         
          String ll=  JSON.toJSONString(list);
          System.out.println(ll);
          
          JSONArray llArray= JSON.parseArray(ll);
          System.out.println(llArray);
          
          List<TestJsonPo> ll1=  JSON.parseObject(ll,new TypeReference<List<TestJsonPo>>(){}   ) ;
	      System.out.println(ll1); 
	      //StringUtils
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//���л�
		//new JSONTest().writeObject();
		//�����л�
		//new JSONTest().readObject();
		//�����ļ��������
		//new JSONTest().testFileIsExist();
        //��ȡ�ļ����·��
		//new JSONTest().getCurrentFilePath();

		//json����ʹ��
		new JSONTest().knowJson();
	}

	
}
