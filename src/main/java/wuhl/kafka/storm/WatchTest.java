package wuhl.kafka.storm;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

//���zookkeeper�ڵ�仯
public class WatchTest implements Watcher {
    public static String ENTID;

    public static String PATH;

    /** ����ԭ�ӱ��� */
    AtomicInteger seq = new AtomicInteger();
    /** ����sessionʧЧʱ�� */
    private static final int SESSION_TIMEOUT = 10000;
    /** zookeeper��������ַ */
    private static final String CONNECTION_ADDR = "kafka001:2181,kafka002:2181,kafka003:2181";
    /** zk��·������ */
    private static final String PARENT_PATH = "/storm/config/ENTID_MESSAGE_TEST";
    /** zk��·������ */
    private static final String CHILDREN_PATH = "/storm/config/ENTID_MESSAGE_TEST/a";
    /** �����ʶ */
    private static final String LOG_PREFIX_OF_MAIN = "��Main��";
    /** zk���� */
    private ZooKeeper zk = null;
    /** �ź������ã����ڵȴ�zookeeper���ӽ���֮�� ֪ͨ���������������ִ�� */
    private CountDownLatch connectedSemaphore = new CountDownLatch(1);


    /**
     * ����ZK����
     * @param connectAddr ZK��������ַ�б�
     * @param sessionTimeout Session��ʱʱ��
     */
    public void createConnection(String connectAddr, int sessionTimeout) {
        this.releaseConnection();
        try {
            zk = new ZooKeeper(connectAddr, sessionTimeout, this);
            System.out.println(LOG_PREFIX_OF_MAIN + "��ʼ����ZK������");
            connectedSemaphore.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * �ر�ZK����
     */
    public void releaseConnection() {
        if (this.zk != null) {
            try {
                this.zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * �����ڵ�
     * @param path �ڵ�·��
     * @param data ��������
     * @return
     */
    public boolean createPath(String path, String data,boolean watch) {
        try {
            //���ü��(����zookeeper�ļ�ض���һ���Ե����� ÿ�α������ü��)
            this.zk.exists(path, watch);
            System.out.println(LOG_PREFIX_OF_MAIN + "�ڵ㴴���ɹ�, Path: " +
                    this.zk.create(	/**·��*/
                            path,
                            /**����*/
                            data.getBytes(),
                            /**���пɼ�*/
                            Ids.OPEN_ACL_UNSAFE,
                            /**���ô洢*/
                            CreateMode.PERSISTENT ) +
                    ", content: " + data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * ��ȡָ���ڵ���������
     * @param path �ڵ�·��
     * @return
     */
    public String readData(String path, boolean needWatch) {
        try {
            return new String(this.zk.getData(path, needWatch, null));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * ����ָ���ڵ���������
     * @param path �ڵ�·��
     * @param data ��������
     * @return
     */
    public boolean writeData(String path, String data) {
        try {
            System.out.println(LOG_PREFIX_OF_MAIN + "�������ݳɹ���path��" + path + ", stat: " +
                    this.zk.setData(path, data.getBytes(), -1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * ɾ��ָ���ڵ�
     *
     * @param path
     *            �ڵ�path
     */
    public void deleteNode(String path) {
        try {
            this.zk.delete(path, -1);
            System.out.println(LOG_PREFIX_OF_MAIN + "ɾ���ڵ�ɹ���path��" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * �ж�ָ���ڵ��Ƿ����
     * @param path �ڵ�·��
     */
    public Stat exists(String path, boolean needWatch) {
        try {
            return this.zk.exists(path, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * ��ȡ�ӽڵ�
     * @param path �ڵ�·��
     */
    private List<String> getChildren(String path, boolean needWatch) {
        try {
            return this.zk.getChildren(path, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * ɾ�����нڵ�
     */
    public void deleteAllTestPath() {
        if(this.exists(CHILDREN_PATH, false) != null){
            this.deleteNode(CHILDREN_PATH);
        }
        if(this.exists(PARENT_PATH, false) != null){
            this.deleteNode(PARENT_PATH);
        }
    }

    /**
     * �յ�����Server��Watcher֪ͨ��Ĵ���
     */
    @Override
    public void process(WatchedEvent event) {

        System.out.println("���� process ����������event = " + event);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (event == null) {
            return;
        }

        // ����״̬
        Event.KeeperState keeperState = event.getState();
        // �¼�����
        EventType eventType = event.getType();
        // ��Ӱ���path
        String path = event.getPath();

        String logPrefix = "��Watcher-" + this.seq.incrementAndGet() + "��";


        System.out.println(logPrefix + "�յ�Watcher֪ͨ");
        System.out.println(logPrefix + "����״̬:\t" + keeperState.toString());
        System.out.println(logPrefix + "�¼�����:\t" + eventType.toString());


        if (KeeperState.SyncConnected == keeperState) {
            // �ɹ�������ZK������
            if (EventType.None == eventType) {
                System.out.println(logPrefix + "�ɹ�������ZK������");
                connectedSemaphore.countDown();
            }
            //�����ڵ�
            else if (EventType.NodeCreated == eventType) {
                System.out.println(logPrefix + "�ڵ㴴��");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.exists(path, true);
            }
            //���½ڵ�
            else if (EventType.NodeDataChanged == eventType) {

                System.out.println("======�仯��·��:"+event.getPath());
                System.out.println(logPrefix + "�ڵ����ݸ���");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ENTID=this.readData(PARENT_PATH, true);
                PATH=event.getPath();
                System.out.println(logPrefix + "��������: " + this.readData(PARENT_PATH, true));
            }
            //�����ӽڵ�
            else if (EventType.NodeChildrenChanged == eventType) {
                System.out.println(logPrefix + "�ӽڵ���");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(logPrefix + "�ӽڵ��б�" + this.getChildren(PARENT_PATH, true));
            }
            //ɾ���ڵ�
            else if (EventType.NodeDeleted == eventType) {
                System.out.println(logPrefix + "�ڵ� " + path + " ��ɾ��");
            }
            else ;
        }
        else if (KeeperState.Disconnected == keeperState) {
            System.out.println(logPrefix + "��ZK�������Ͽ�����");
        }
        else if (KeeperState.AuthFailed == keeperState) {
            System.out.println(logPrefix + "Ȩ�޼��ʧ��");
        }
        else if (KeeperState.Expired == keeperState) {
            System.out.println(logPrefix + "�ỰʧЧ");
        }
        else ;


        System.out.println("--------------------------------------------");


    }


    /**
     * &lt;B&gt;�������ƣ�&lt;/B&gt;����zookeeper���&lt;BR&gt;
     * &lt;B&gt;��Ҫ˵����&lt;/B&gt;��Ҫ����watch����&lt;BR&gt;
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {


        //����watcher
        WatchTest zkWatch = new WatchTest();
        //��������
        zkWatch.createConnection(CONNECTION_ADDR, SESSION_TIMEOUT);
        //System.out.println(zkWatch.zk.toString());

        Thread.sleep(1000);

        zkWatch.getChildren(PARENT_PATH, true);
        System.out.println("�Ѿ�����Ͻڵ�");
        zkWatch.writeData(PARENT_PATH, "aaa" + "");
        Thread.sleep(1000);
        zkWatch.writeData(PARENT_PATH, "bbb" + "");
        Thread.sleep(1000);
        //��ȡ֮ǰ��Ҫ�ȼ��
      /*  System.out.println("---------------------- read children path ----------------------------");
        zkWatch.getChildren(PARENT_PATH, true);
        // ��������
        zkWatch.writeData(PARENT_PATH, System.currentTimeMillis() + "");

        Thread.sleep(1000);
*/


        // ��ȡ�ӽڵ�

        // ����ڵ�
        //zkWatch.deleteAllTestPath();

   /*     if (zkWatch.createPath(PARENT_PATH, System.currentTimeMillis() + "",true)) {

            Thread.sleep(1000);

            //Thread.sleep(1000);

            //zkWatch.writeData(CHILDREN_PATH, System.currentTimeMillis() + "");
        }*/


        // ��ȡ����
       System.out.println("---------------------- read parent ----------------------------");
        zkWatch.readData(PARENT_PATH, true);


        // ��������
        zkWatch.writeData(PARENT_PATH, System.currentTimeMillis() + "");

        Thread.sleep(1000);

        // ��ȡ�ӽڵ�
   /*     System.out.println("---------------------- read children path ----------------------------");
        zkWatch.getChildren(PARENT_PATH, true);

        // �����ӽڵ�
        zkWatch.createPath(CHILDREN_PATH, System.currentTimeMillis() + "",true);*/

        //Thread.sleep(5000);
        // ����ڵ�
        //zkWatch.deleteAllTestPath();
        Thread.sleep(1000);
        System.out.println(WatchTest.PATH);
        System.out.println(WatchTest.ENTID);

        zkWatch.releaseConnection();


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        WatchTest zkWatch = new WatchTest();
                        //��������
                        zkWatch.createConnection(CONNECTION_ADDR, SESSION_TIMEOUT);
                        //System.out.println(zkWatch.zk.toString());
                        zkWatch.readData(PARENT_PATH, true);
                        Thread.sleep(10000);
                        System.out.println("================");

                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }

                }
            }
        });
      t.start();

    }
}
