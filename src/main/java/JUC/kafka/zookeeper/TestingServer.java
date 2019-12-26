package JUC.kafka.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;

public class TestingServer {


    public static final String MACHINE_IDENTIFICATION="";
    public static final String CONNECT_URL = "node1:2181,ndoe2:2181,node3:2181";
    public static final int TIMEOUT = 10000;
    static CuratorFramework client = null;

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





}
