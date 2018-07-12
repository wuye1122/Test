package wuhl.kafka;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class CuratorPathChildrenCacheTest {

    public static void main(String[] args) throws Exception {
       CuratorPathChildrenCacheTest.addNode("/dps/temp/b");
        String path = "/dps/temp";
        CuratorFramework client = getClient();
        Stat stat = new Stat();
        System.out.println(client.getData().forPath(path+"/b"));
        System.out.println(client.getChildren().forPath(path));
        //client.delete().forPath(path+"/a");
        System.out.println("删除之后节点.....");
        System.out.println(client.getChildren().forPath(path));
        List<String> list = client.getChildren().forPath(path);
        for(String s:list){
            System.out.println("节点："+s);
        }
    }

    public static  void addNode(String path) throws Exception{

        CuratorFramework client = getClient();
        client.create().withMode(CreateMode.PERSISTENT).forPath(path);
    }
        public static  void testWater()throws Exception{
        CuratorFramework client = getClient();
        String parentPath = "/dps/temp";

        PathChildrenCache pathChildrenCache = new PathChildrenCache(client,parentPath,true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("事件类型："  + event.getType() + "；操作节点：" + event.getData().getPath());
            }
        });

        String path = "/dps/temp/c";
        client.create().withMode(CreateMode.PERSISTENT).forPath(path);
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
