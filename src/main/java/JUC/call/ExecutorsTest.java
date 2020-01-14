package JUC.call;

import java.util.concurrent.*;

public class ExecutorsTest {

    //四种线程池  LinkedBlockingQueue   SynchronousQueue  DelayedWorkQueue

    public static void main(String[] args) {

        ExecutorService threadPoolSingle = Executors.newSingleThreadExecutor();
        ExecutorService threadPoolFix = Executors.newFixedThreadPool(4);
        ExecutorService threadPoolCache = Executors.newCachedThreadPool();
        ScheduledExecutorService  threadPoolSchedule = Executors.newScheduledThreadPool(5);
/*
       newFixedThreadPool 四个人轮流干活 谁空闲谁去干活
                          创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
         ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>())

       newCachedThreadPool   创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
          ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>())

        newScheduledThreadPool   创建一个定长线程池，支持定时及周期性任务执行
         (corePoolSize, Integer.MAX_VALUE, 0, TimeUnit.NANOSECONDS,new DelayedWorkQueue()
          和定时任务类似 延迟几秒执行 可以设置周期

      */

        for(int i=0;i<10;i++){
            final int index = i;
            //threadPool.submit 等待线程返回结果 cache会不断创建线程大小
            threadPoolCache.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index+"======"+Thread.currentThread().getName());
                    System.out.println(2/0);
                  try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
