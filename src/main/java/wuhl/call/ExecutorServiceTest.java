package wuhl.call;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class ExecutorServiceTest {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  //  ExecutorService executorService = Executors.newCachedThreadPool();  
	    
	    ExecutorService executorService = Executors.newFixedThreadPool (5);  

	   
	    
        List<Future<String>> resultList = new ArrayList<Future<String>>();  
 		System.out.println("当前线程id: "+ Thread.currentThread().getId()  );
 		System.out.println("当前线程name :"+ Thread.currentThread().getName() );
        // 创建10个任务并执行  
 	/*	List<Map<String,String>> list=new ArrayList<Map<String,String>>();
 		Map<String,String> map= new HashMap<String,String>();
 		map.put("url", "http://localhost:8087/dps2/download/add.do");
 		map.put("path","writeFile:\\1\\Test1.jar");
 		Map<String,String> map2= new HashMap<String,String>();
 		map2.put("url", "http://localhost:8087/dps2/download/add.do");
 		map2.put("path","writeFile:\\1\\Test2.jar");
 		Map<String,String> map3= new HashMap<String,String>();
 		map3.put("url", "http://localhost:8087/dps2/download/add.do");
 		map3.put("path","writeFile:\\1\\Test3.jar");
 		list.add(map);
 		list.add(map2);
 		list.add(map3);*/
 		
        for (int i = 0; i < 10; i++) {  
            // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中  
        System.out.println(i);
/*        	Call call = new Call(i,list.get(i).get("url"),list.get(i).get("path"));
*/        	Call call = new Call(i,"url","path");
            Future<String> future = executorService.submit(call);  
            // 将任务执行结果存储到List中  
            resultList.add(future);  
        }  
        executorService.shutdown();  

        // 遍历任务的结果  
        for (Future<String> fs : resultList) {  
            try {  
            	fs.get();
               // System.out.println("打印线程执行结果："+fs.get()); // 打印各个线程（任务）执行的结果  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } catch (ExecutionException e) {  
                executorService.shutdownNow();  
                e.printStackTrace();  
                return;  
            }  
        }  
    }  
  
}
