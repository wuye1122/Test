package wuhl.kafka;

import com.alibaba.fastjson.JSON;


public class DataPushThread  implements Runnable {

    private String msg;

    private Long   ms;
    public DataPushThread(String msg,Long ms) {
        this.msg = msg;
        this.ms = ms;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        System.out.println("正在处理本次推送任务,参数为" + JSON.toJSONString(msg));
        try {
            //利用反射，将推送的参数指定到不同的适配器，由适配器自己做相应处理并推送
                //模拟推送延迟
                Thread.sleep(ms);
                return ;
        } catch (Exception e) {
            System.out.println("推送失败,本次调用参数为:" + JSON.toJSONString(msg));
            e.printStackTrace();
            return;
        }finally {
            System.out.println("此次数据处理的时间【"+Long.valueOf(System.currentTimeMillis() - start)+"ms】参数："+JSON.toJSONString(msg));
        }
    }
}
