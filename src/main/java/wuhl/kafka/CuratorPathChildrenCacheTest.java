package wuhl.kafka;

import URL.StringU;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class CuratorPathChildrenCacheTest {

    public static void main(String[] args) throws Exception {
        String path = "/storm/config/MESSAGE_TOPIC";
        CuratorFramework client = getClient();
        System.out.println(new String(client.getData().forPath(path)));
        //new String
        List<String> list = client.getChildren().forPath("/storm/config");
        for(String s:list){
            System.out.println("节点："+s);
        }
 /*       System.out.println(client.checkExists().forPath("/storm/config"));              ;
        System.out.println(client.checkExists().forPath("/storm/config/MESSAGE_TOPIC"));              ;
        System.out.println(client.checkExists().forPath("/storm/config/ENTID_MESSAGE"));      */
   /*      System.out.println(null==client.checkExists().forPath("/storm/config/ENTID_MESSAGE"));
         client.delete().forPath("/storm/config/ENTID_MESSAGE");
         System.out.println(null==client.checkExists().forPath("/storm/config/ENTID_MESSAGE"));*/

        if(null==client.checkExists().forPath("/storm/config/ENTID_MESSAGE")){
            System.out.println("当前节点不存在，请重新创建");
            client.create().forPath("/storm/config/ENTID_MESSAGE","001".getBytes());
            System.out.println(new String(client.getData().forPath("/storm/config/ENTID_MESSAGE")));
        }else{
            System.out.println("当前节点已经存在，更新中...");
            System.out.println(new String(client.getData().forPath("/storm/config/ENTID_MESSAGE")));
            String str=new String(client.getData().forPath("/storm/config/ENTID_MESSAGE"));
            if(!str.contains("002")){
                str=str+":"+"002";
            }
            client.setData().forPath("/storm/config/ENTID_MESSAGE",str.getBytes());
            System.out.println("更新之后数据:"+new String(client.getData().forPath("/storm/config/ENTID_MESSAGE")));


        }

    }

    public static  void addNode(String path) throws Exception{

        CuratorFramework client = getClient();
        client.create().withMode(CreateMode.PERSISTENT).forPath(path);
    }
        public static  void testWater()throws Exception{
        CuratorFramework client = getClient();
        String parentPath = "/dps/temp";

      /*  PathChildrenCache pathChildrenCache = new PathChildrenCache(client,parentPath,true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("事件类型："  + event.getType() + "；操作节点：" + event.getData().getPath());
            }
        });
*/
        String path = "/dps/temp/c";
        client.create().withMode(CreateMode.PERSISTENT).forPath(path);
        System.out.println( client.checkExists().forPath(path));
        Thread.sleep(10000); // 此处需留意，如果没有现成睡眠则无法触发监听事件
        client.delete().forPath("/dps/temp/slave-1@10.130.41.222:8081");
        Thread.sleep(15000);
    }
    private static CuratorFramework getClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("kafka001:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .build();
        client.start();
        return client;
    }



}
