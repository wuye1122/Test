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
	 * 1 序列化 serializable :把对象的信息转换为可以存储和传输的过程 移到其他平台可以反序列化
	 *       
	 *                      po实现serialiable 与不实现的po进行对比
	 *       
	 *                      java.io.NotSerializableException: wuhl.kafka.po.TestJsonNoSerialiable
	 *                   
	 *                      不需要，fastjson的序列化和反序列化都不需要做特别配置，唯一的要求是，你序列化的类符合java bean规范。
     *
     *                       在fastjson在1.2.21版本中提供了一个借鉴自gson的特性，
     *                        支持反序列化时使用多个不同的字段名称，使用的方式是配置
     *                        JSONField的alternateNames。
     *                        https://www.w3cschool.cn/fastjson/fastjson-jsonfield.html
     *                         映射多个字段
     *                        @JSONField(alternateNames={"USER","NAME"})
	 *                      
     *                        普通的映射
     *                        @JSONField(name="ID")
     *                        
     *                     
	 *                         
	 * 2 io 文件路径问题： win:  \\ 或者 /
	 * 
	 *                 是否存在exists    创建文件createNewFile 
	 *                 创建一级目录mkdir  创建多集mkdirs 
	 * 
	 *          
	 */
	//serialiable
	public void writeObject() {
		//TestJsonPo po = new TestJsonPo();
		TestJsonNoSerialiable po=new TestJsonNoSerialiable();
		po.address = "地址";
		po.name = "序列化的po";
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
			
			//如果传入参数为空则显示为 null
			System.out.println(f.getPath());
			
			//SecurityException - 如果无法访问所需的系统属性值。
			//或根据所在的linux系统 还是 win系统进行获取路径
			//通过根据当前用户目录分析某一相对路径名
			System.out.println(f.getAbsolutePath());
			
			//getCanonicalFilen能够解析.. .的形式
			System.out.println(f.getCanonicalFile());

		/*	System.out.println("是否存在:" + f.exists());
			System.out.println("是否是目录:" + f.isDirectory());
			System.out.println("是否是文件:" + f.isFile());*/
			
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
        System.out.println("f1  工程："+f);
        System.out.println("f1  类："+f1);
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
	    //获取当前项目根路径几种方法：
	    //相对路径获取 relativelyPath
	    System.out.println(System.getProperty("Test"))                   ;
	    String relativelyPath=System.getProperty("user.dir");
	    System.out.println("1相对路径获取："+relativelyPath);
	    System.out.println("\\一个斜线是转义字符： "+relativelyPath+"\\save");
	    System.out.println("/一个斜线是转义字符： "+relativelyPath+"/save");*/
		//System.getProperty();
		
	}
	
	public void knowJson(){
		HashMap map1=new HashMap();
		map1.put("current", "5");
		map1.put("waiting", "20");

		TestJsonPo po = new TestJsonPo();
		//TestJsonNoSerialiable po=new TestJsonNoSerialiable();
		po.address = "地址";
		po.name = "序列化的po";
		po.number = 10;
		po.SSN = 5;
		po.setObj(map1);
		//string ->jsonobject
		String jsonStr="{\"address\":\"地址\",\"name\":\"序列化的po\",\"number\":10}";	
		TestJsonNoSerialiable msg = JSON.parseObject(jsonStr,TestJsonNoSerialiable.class);
        System.out.println("jsonStr->object:"+msg); 
		
		//序列化 object->string
        String	json=JSON.toJSONString(po);
		System.out.println("popopo:"+json);
		
		//反序列化 String->po
		TestJsonNoSerialiable po1=JSON.parseObject(json, TestJsonNoSerialiable.class);
		System.out.println(po1.toString());

		
		//反序列化 String ->JSONObject 显示的和string一样但是 实质不同
		JSONObject object= JSON.parseObject(json);
		System.out.println(object);
		System.out.println(object.get("address"));
		System.out.println(object.get("name"));
		System.out.println(object.get("number"));


         //泛型反序列化 
		Map<String,Object> map=JSON.parseObject(json, new TypeReference<Map<String,Object>>(){});
		System.out.println(map.toString());
		System.out.println(map.get("address"));
		System.out.println(map.get("name"));
		System.out.println(map.get("number"));

		//FastJSON可以直接对日期类型格式化，在缺省的情况下，FastJSON会将Date转成long。
		//toJSONBytes(Object arg0, SerializerFeature... arg1) 
		/*
		 * 转化成string 单引号替代双引号: SerializerFeature.UseSingleQuotes
		 * 
		 * 使用日期格式:SerializerFeature.WriteDateUseDateFormat
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
         
        //mongodb 查出来的数据  docment里面根据游标进行循环 ==》可以直接转换成List<po>
         List<TestJsonPo> list=new ArrayList<TestJsonPo>();
         for(int i=0;i<5;i++){
        	 TestJsonPo p=new TestJsonPo();
         
        		p.address = "地址"+i;
        		p.name = "序列化的po"+i;
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
		//序列化
		//new JSONTest().writeObject();
		//反序列化
		//new JSONTest().readObject();
		//测试文件相关属性
		//new JSONTest().testFileIsExist();
        //获取文件相对路径
		//new JSONTest().getCurrentFilePath();

		//json测试使用
		new JSONTest().knowJson();
	}

	
}
