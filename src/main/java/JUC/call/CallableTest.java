package JUC.call;

import java.util.concurrent.Callable;

public class CallableTest implements Callable<String>{

	//单利模式 只创建一个对象
	
	
	
	private int id;  

	private String url;
	
	private String path;
    public CallableTest(int id, String url, String path) {
        this.id = id;  
        this.url = url;  
        this.path = path;  

    }  


	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("正在执行的线程id"+id);
		
		String url = this.url;
		String path = this.path;
		Thread.sleep(1000);
		System.out.println("当前线程的url和path:"+url+"----"+path);
	/*	File file = new File(path);
		System.out.println(file.getName());
		String md5 = Md5Util.getMd5ByFile(file);
		System.out.println(md5);
		  Map<String,String> postParam = new HashMap<String,String>();   
	        postParam.put("md5", md5);  
      		postParam.put("adapterName", file.getName());
      		HttpClientUtil util=  new HttpClientUtil();
      		System.out.println("新建连接："+util);
      		System.out.println("url："+url);
      		System.out.println("path："+path);

		Map<String,Object> resultMap=util.upload(url, file, postParam);*/
          
		return this.url;
	}

}
