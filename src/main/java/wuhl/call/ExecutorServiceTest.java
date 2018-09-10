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
 		System.out.println("��ǰ�߳�id: "+ Thread.currentThread().getId()  );
 		System.out.println("��ǰ�߳�name :"+ Thread.currentThread().getName() );
        // ����10������ִ��  
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
            // ʹ��ExecutorServiceִ��Callable���͵����񣬲������������future������  
        System.out.println(i);
/*        	Call call = new Call(i,list.get(i).get("url"),list.get(i).get("path"));
*/        	Call call = new Call(i,"url","path");
            Future<String> future = executorService.submit(call);  
            // ������ִ�н���洢��List��  
            resultList.add(future);  
        }  
        executorService.shutdown();  

        // ��������Ľ��  
        for (Future<String> fs : resultList) {  
            try {  
            	fs.get();
               // System.out.println("��ӡ�߳�ִ�н����"+fs.get()); // ��ӡ�����̣߳�����ִ�еĽ��  
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
