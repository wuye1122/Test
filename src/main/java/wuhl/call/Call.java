package wuhl.call;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;


import IO.HttpClientUtil;
import IO.Md5Util;

public class Call implements Callable<String>{

	//����ģʽ ֻ����һ������
	
	
	
	private int id;  

	private String url;
	
	private String path;
    public Call(int id,String url,String path) {  
        this.id = id;  
        this.url = url;  
        this.path = path;  

    }  


	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("����ִ�е��߳�id"+id);
		
		String url = this.url;
		String path = this.path;
		Thread.sleep(1000);
		System.out.println("��ǰ�̵߳�url��path:"+url+"----"+path);
	/*	File file = new File(path);
		System.out.println(file.getName());
		String md5 = Md5Util.getMd5ByFile(file);
		System.out.println(md5);
		  Map<String,String> postParam = new HashMap<String,String>();   
	        postParam.put("md5", md5);  
      		postParam.put("adapterName", file.getName());
      		HttpClientUtil util=  new HttpClientUtil();
      		System.out.println("�½����ӣ�"+util);
      		System.out.println("url��"+url);
      		System.out.println("path��"+path);

		Map<String,Object> resultMap=util.upload(url, file, postParam);*/
          
		return this.url;
	}

}
