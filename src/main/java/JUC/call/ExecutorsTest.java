package JUC.call;

import java.util.concurrent.*;

public class ExecutorsTest {

    //�����̳߳�  LinkedBlockingQueue   SynchronousQueue  DelayedWorkQueue

    public static void main(String[] args) {

        ExecutorService threadPoolSingle = Executors.newSingleThreadExecutor();
        ExecutorService threadPoolFix = Executors.newFixedThreadPool(4);
        ExecutorService threadPoolCache = Executors.newCachedThreadPool();
        ScheduledExecutorService  threadPoolSchedule = Executors.newScheduledThreadPool(5);
/*
       newFixedThreadPool �ĸ��������ɻ� ˭����˭ȥ�ɻ�
                          ����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ�
         ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>())

       newCachedThreadPool   ����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ�
          ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>())

        newScheduledThreadPool   ����һ�������̳߳أ�֧�ֶ�ʱ������������ִ��
         (corePoolSize, Integer.MAX_VALUE, 0, TimeUnit.NANOSECONDS,new DelayedWorkQueue()
          �Ͷ�ʱ�������� �ӳټ���ִ�� ������������

      */

        for(int i=0;i<10;i++){
            final int index = i;
            //threadPool.submit �ȴ��̷߳��ؽ�� cache�᲻�ϴ����̴߳�С
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
