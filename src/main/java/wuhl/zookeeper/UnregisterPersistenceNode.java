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

		//����ָ��·����ȡ�־û��ڵ�
		
		
	}
	 //��ʼ��
	   public void init() throws Exception {
	        client = CuratorFrameworkFactory
	                .builder()
	                .connectString(CONNECT_URL)
	                .sessionTimeoutMs(TIMEOUT)
	                .retryPolicy(new RetryNTimes(5, 5000))
	                .build();
	        // �ͻ���ע�������������������
	/*        client.getConnectionStateListenable()
	                .addListener(clientListener);*/
	        client.start();
	        // ���ӳɹ��󣬲Ž�����һ���Ĳ���
	   /*     countDownLatch.await();*/
	    }
	
	 //��ȡָ��·���ڵ��б�
	 private static List<String> nodesList(CuratorFramework client, String parentPath) throws Exception {
	        return client.getChildren().forPath(parentPath);
	    }
	
	
	  //������ʱ�ڵ�
	 public void registerTempNode(String entId) {
		 System.out.println("����ZookeeperServiceImpl.registerTempNodeTree()...");
	        String path = TEMP_ZNODE + "/" + MACHINE_IDENTIFICATION + "/" + entId;
	        try {
	            if(null == client.checkExists().forPath(path)){
	                //������ʱ�ڵ�
	                client.create()
	                        .creatingParentsIfNeeded()
	                        .withMode(CreateMode.EPHEMERAL)
	                        .forPath(path);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	  //ɾ���ڵ�
	  public void unregisterPersistenceNode(String machineIdentification) {
	        System.out.println("����ZookeeperServiceImpl.unregisterPersistenceNode()...");
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
	
	  //�����־û��ڵ�
	  public void registerPersistenceNode(String entId) {
		  System.out.println("����ZookeeperServiceImpl.registerPersistenceNodeTree()...");
	        String path = PERSISTENCE_ZNODE + "/" + MACHINE_IDENTIFICATION + "/" + entId;
	        try {
	            if(null == client.checkExists().forPath(path)){
	                //�����־û��ڵ�
	                client.create()
	                        .creatingParentsIfNeeded()
	                        .forPath(path);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
