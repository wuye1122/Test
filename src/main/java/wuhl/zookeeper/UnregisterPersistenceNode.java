package wuhl.zookeeper;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

public class UnregisterPersistenceNode {
	  public static final String TEMP_ZNODE = "/dps/temp";
	    public static final String PERSISTENCE_ZNODE = "/dps/persistence";
	    //slave.ids=slave-1@10.130.29.31:8080,slave-2@10.130.29.32:8080,slave-3@10.130.29.33:8080
	   //master@127.0.0.1:8087
	    public static final String MACHINE_IDENTIFICATION="";
	    public static final String CONNECT_URL = "10.130.29.34:2181";
	    public static final int TIMEOUT = 10000;
	    static CuratorFramework client = null;
	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//根据指定路径获取持久化节点
		
		
	}
	 //初始化
	   public void init() throws Exception {
	        client = CuratorFrameworkFactory
	                .builder()
	                .connectString(CONNECT_URL)
	                .sessionTimeoutMs(TIMEOUT)
	                .retryPolicy(new RetryNTimes(5, 5000))
	                .build();
	        // 客户端注册监听，进行连接配置
	/*        client.getConnectionStateListenable()
	                .addListener(clientListener);*/
	        client.start();
	        // 连接成功后，才进行下一步的操作
	   /*     countDownLatch.await();*/
	    }
	
	 //获取指定路径节点列表
	 private static List<String> nodesList(CuratorFramework client, String parentPath) throws Exception {
	        return client.getChildren().forPath(parentPath);
	    }
	
	
	  //创建临时节点
	 public void registerTempNode(String entId) {
		 System.out.println("进入ZookeeperServiceImpl.registerTempNodeTree()...");
	        String path = TEMP_ZNODE + "/" + MACHINE_IDENTIFICATION + "/" + entId;
	        try {
	            if(null == client.checkExists().forPath(path)){
	                //创建临时节点
	                client.create()
	                        .creatingParentsIfNeeded()
	                        .withMode(CreateMode.EPHEMERAL)
	                        .forPath(path);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	  //删除节点
	  public void unregisterPersistenceNode(String machineIdentification) {
	        System.out.println("进入ZookeeperServiceImpl.unregisterPersistenceNode()...");
	        machineIdentification = machineIdentification == null?"":"/" + machineIdentification;
	        String path = PERSISTENCE_ZNODE + machineIdentification;

	        try {
	            if(null != client.checkExists().forPath(path)) {
	                client.delete()
	                        .deletingChildrenIfNeeded()
	                        .forPath(path);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	  //创建持久化节点
	  public void registerPersistenceNode(String entId) {
		  System.out.println("进入ZookeeperServiceImpl.registerPersistenceNodeTree()...");
	        String path = PERSISTENCE_ZNODE + "/" + MACHINE_IDENTIFICATION + "/" + entId;
	        try {
	            if(null == client.checkExists().forPath(path)){
	                //创建持久化节点
	                client.create()
	                        .creatingParentsIfNeeded()
	                        .forPath(path);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
