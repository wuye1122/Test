package wuhl.kafka.po;



import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ZookeeperWatcher implements Watcher ,Serializable {
    /** ԭ�Ӽ�����������ͳ��process�����õĴ��� */
    private AtomicInteger count = new AtomicInteger();//Ĭ�ϴ�0��ʼ
    /** sessionʧЧʱ�� */
    private int session_timeout = 120*1000; //120��
    /** zookeeper���������ӵ�ַ */
    private String connection_add =
            "192.168.110.135:2181,192.168.110.136:2181,192.168.110.137:2181";
    /** �������ݸ�·��  */
    private String root_path = "/watcher";
    /** ���������ֽڵ�·�� */
    private String children_path = "/watcher/children";
    /** zookeeperʵ������ */
    private ZooKeeper zoo = null;
    /** �ź����������������̵߳ȴ��ͻ�������zookeeper����ɹ���֪ͨ���߳����¼���ִ�� */
    private CountDownLatch countDownLactch = new CountDownLatch(1);

    private String log_main = "��main��:";
    public ZookeeperWatcher(){}

    public void connectZookeeper(){
        try {
            close();
            zoo = new ZooKeeper(connection_add, session_timeout, this);
            countDownLactch.await();//�ȴ��ͻ��˳ɹ�����zookeeper�������ż�������ִ��
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * �ͷ�����
     * @throws InterruptedException
     */
    public void close() {
        if(zoo != null){
            try {
                zoo.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * watcher��ص��ǿͻ������������״̬���¼�����
     * ��ڵ㷢���ı�ʱ���յ���֮zookeeper��������watch��֪ͨ��
     * (��ʱ�ͻ����൱��һ��watcher(���zookeeper���ֽ�),
     * ʵ��Watcher����Ϊ�˼����������Ľڵ������Ƿ������(��صĶ���Ϊwatch���ͻ��˿���Ϊwatcher.))
     * һ���ͻ��˿����ж��watcher.
     */
    @Override
    public void process(WatchedEvent event) {
        System.out.println("��ʼִ��process����-----event:"+event);
        delayMillis(1000);
        if(event == null){ return;}
        //ȡ������״̬
        KeeperState state = event.getState();
        //ȡ���¼�����
        EventType eventType = event.getType();
        //��һ���ڵ�·���������
        String nodePath = event.getPath();
        String log_process = "Watcher-��  "+count.incrementAndGet()+" ��";
        System.out.println(log_process+"�յ�Watcher��֪ͨ");
        System.out.println(log_process+"����״̬��"+state);
        System.out.println(log_process+"�¼����ͣ�"+eventType);

        connectZookeeperState(state , eventType , log_process , nodePath);
    }

    /**
     * �жϿͻ�������zookeeper���������״̬
     * @param state �������˷��ص�״̬����
     * @param eventType �¼����Ͷ���
     * @param log_process �ռǱ�ʶ����ʶ��process������ִ�е��ռ�
     * @param nodePath �����仯�Ľڵ�
     */
    public void connectZookeeperState(Event.KeeperState state,
                                      Event.EventType eventType , String log_process , String nodePath){
        if(KeeperState.SyncConnected == state ){//���ӳɹ�
            nodeEventType(eventType, log_process , nodePath);
        }
        else if(KeeperState.Disconnected == state){
            System.out.println(log_process+"�ͻ�������zookeeper��������ʧ��");
        }
        else if(KeeperState.Expired == state){
            System.out.println(log_process+"�ͻ�����zookeeper�������˻Ựʧ��");
        }
        else if(KeeperState.AuthFailed == state){
            System.out.println(log_process+"Ȩ����֤ʧ��");
        }
        System.out.println("------------------------------------");
    }

    /**
     * �жϽڵ���¼�����
     * @param eventType �¼����Ͷ���
     * @param log_process �ռǱ�ʶ����ʶ��process������ִ�е��ռ�
     */
    public void nodeEventType(EventType eventType,String log_process,String nodePath ){
        // û���κνڵ㣬��ʾ�������ӳɹ�(�ͻ�����������˴������ӳɹ���û���κνڵ���Ϣ)
        if(EventType.None == eventType){
            System.out.println(log_process+"�ɹ�����zookeeper������");
            countDownLactch.countDown(); // ֪ͨ�������߳̿��Լ���ִ��
        }
        else if(EventType.NodeCreated == eventType){ //���������˴����ڵ��ʱ�򴥷�
            System.out.println(log_process+" zookeeper����˴����µĽڵ�");
            delayMillis(2000);
            //zookeeper����˴���һ���µĽڵ�󲢶�����м��,���������ŶԸýڵ���м��,û�д˴��뽫�����ڼ�ظýڵ�
            exists(nodePath,true);
        }
        else if(EventType.NodeDataChanged == eventType){ //����ظýڵ�����ݷ��������ʱ�򴥷�
            System.out.println(log_process+"�ڵ�����ݸ���");
            delayMillis(2000);
            //���������ŶԸýڵ���м��,û�д˴��뽫�����ڼ�ظýڵ�
            String updateNodeData = readNodeData(nodePath,true);
        }
        else if(EventType.NodeChildrenChanged == eventType){
            // ��Ӧ���������ֻ�ܼ�ظ��ڵ��һ���ڵ������磺�ڸ��ڵ�ֱ�Ӵ���һ���ڵ㣬
            //����ɾ��һ���ڵ�ʱ���������޸�һ���ڵ�����ݣ����ᴥ�������������ڵ�ʱҲ���ᴥ����
            System.out.println("�ӽڵ㷢�����");
            delayMillis(2000);
            System.out.println(log_process + "�ӽڵ��б�" + this.getChildren(root_path, true));
        }
        else if(EventType.NodeDeleted == eventType){
            System.out.println(log_process+"�ڵ㣺"+nodePath+"��ɾ��");
        }

        System.out.println("-------------------------------------");
    }

    /**
     * �жϽڵ��Ƿ����
     * @param nodePath �ڵ�ȫ·��
     * @param needWatch
     */
    public Stat exists(String nodePath, boolean needWatch) {
        try {
            return zoo.exists(nodePath, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ��ȡ���ýڵ��µ�һ���ӽڵ�,���needWatchΪtrue�������ظýڵ��µ�һ���ڵ�
     * (���۸ýڵ��Ƿ����ӽڵ㣬���Ըýڵ��һ���ӽڵ���м��)
     * @param path �ڵ�ȫ·��
     * @param needWatch �Ƿ���Ҫ����watche���
     * @return
     */
    private List<String> getChildren(String path, boolean needWatch) {
        try {
            return zoo.getChildren(path, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ��ȡ�ڵ������
     * @param nodePath �ڵ��ȫ·��
     * @param needWatch �Ƿ���Ҫwatch true:��Ҫ,false:����Ҫ
     * @return
     */
    private String readNodeData(String nodePath,boolean needWatch) {
        String data = "";
        try {
            //zookeeper watch�¼�ֻ����һ��,//zookeeper watch�¼�ֻ����һ��,
            //����2��Ϊtrueʱ��ʾ�����Ե�ȥ��أ���ص�watcherʵ��Ϊ��һ��watcher����
            //������һ�ֳ����Լ�صķ������д���һ��Watcher����
            zoo.getData(nodePath, needWatch, null);
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
        return data;
    }

    /**
     * ���߶��ٺ���
     * @param millis
     */
    public void delayMillis(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ����һ���µĽڵ�
     * @param path �ڵ��ȫ·��
     * @param data �ڵ����������
     * @param watcher Watcher��ؽڵ��һ��ʵ������
     * @return true�������ɹ�,false������ʧ��
     */
    public boolean createPath(String path , String data , Watcher watcher){
        Stat stat = null;
        try {
            //�ڴ����ڵ�֮ǰ�жϽڵ��Ƿ���ڣ����۽ڵ��Ƿ���ڣ�ȷ���ýڵ㱻��أ��Ӷ��ﵽ�ڴ����ڵ��watcher�ܵõ��������˷��ص�������Ϣ��
            if(watcher == null){
                stat = exists(path, true);
                // ���ü�أ���Ϊzookeeper�ļ�ض���һ���Եģ�Ҫ������Եļ�أ��ڶ���������Ҫ����Ϊture��
                //zoo.exists(path, true) ;
            }else{
                stat = zoo.exists(path, watcher) ; // ���ߴ���һ���µ�Watcher�������������ǣ�
                //Ϊtrueʱwatcher��������һ����ص������Ķ��󣬶��ڱ�������˵��this����.
            }
            if(stat == null){
                zoo.create(path, //Ҫ�����Ľڵ�·��
                        data.getBytes(), //�ڵ�洢����������
                        Ids.OPEN_ACL_UNSAFE, // �ڵ�Ȩ������
                        CreateMode.PERSISTENT //�洢�ڵ�ķ�ʽ���־û�ģʽ��
                );
            }else{
                System.out.println("�ڵ��Ѿ����ڣ��޷��ٴ���");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * ���½ڵ������
     * @param nodePath �ڵ��ȫ·��
     * @param data Ҫ���µ�����
     * @return true�����³ɹ�,false������ʧ��
     */
    public boolean setDate(String nodePath , String data ){
        try {
            Stat stat = zoo.setData(nodePath, data.getBytes(), -1); //�������а汾
            System.out.println(log_main+"�������ݳɹ���path��"
                    + nodePath + ", stat: " +stat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ɾ���ڵ�
     * @param path �ֽڵ�ȫ·��
     */
    public void deleteNode(String path){
        try {
            zoo.delete(path, -1); // -1 �������а汾��
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * ɾ�����в������ݵĽڵ�
     * @param neetWatch �Ƿ���Ҫwatcher
     */
    public void deleteAllTestPath(boolean neetWatch){
        if(this.exists(children_path, neetWatch) != null){
            deleteNode(children_path);
        }
        if(this.exists(root_path, neetWatch) != null){
            this.deleteNode(root_path);
        }
    }

    /**
     * <readFile>�������ƣ�</readFile>����zookeeper���</BR>
     * <readFile>��Ҫ˵��:</readFile>��Ҫ�ǲ���watch����</BR>
     * @param args
     */
    public static void main(String[] args) {
        //����ZookeeperWatcherʵ��,Ϊ�˶Է������˽��нڵ���
        ZookeeperWatcher watcher = new ZookeeperWatcher();
        //����zookeeper������
        watcher.connectZookeeper();
        //������в��Խڵ�
        watcher.deleteAllTestPath(false);

        if(watcher.createPath(watcher.root_path, "���ڵ������", null) == true){
            watcher.delayMillis(1*1000); // ��Ϣһ��

//			//��ȡ�մ����Ľڵ�����
            System.out.println(watcher.log_main+"---------read root path------");
//			// �����ڵ�ɹ���process�����Ѿ��Ըýڵ���ж�ȡ���ݣ����Ըýڵ���м�ز�����
////			watcher.readNodeData(watcher.root_path, true);
//			//��ȡ�ӽڵ㣨ʵ�ʵ�Ŀ���ǶԸø��ڵ��µ��ӽڵ���м��,�����ӽڵ��Ƿ���ڣ�
            System.out.println(watcher.log_main+"----- read children path ----");
            watcher.getChildren(watcher.root_path, true);
//			//��������
//			watcher.setDate(watcher.root_path, "���¸��ڵ��������Ϣ");
//			watcher.delayMillis(2000);
//			//�����ӽڵ�
//			watcher.createPath(watcher.children_path, "�ӽڵ����������", null);
//			watcher.delayMillis(2000);
//			//�ӽڵ�����ݱ��
//			watcher.setDate(watcher.children_path, "�ӽڵ�����ݱ��");
        }

        watcher.delayMillis(50*60*1000);
        watcher.deleteAllTestPath(false);
        watcher.close(); //�ͷ�����
    }

    /**
     * �ܽ᣺
     * Watcher�Ƕ�zookeeper�������Ľڵ���м�أ�zookeeper��watcherֻ��һ���Եļ�ؽڵ㣬���ܳ����Եļ�ء�
     * �����Խڵ���г����Եļ�أ���Ҫ����true����һ���µ�Watcher��������������true����ʾ��صĶ���Ϊ��һ��
     * ��ص�Watcher�����������˵���ǵ�ǰ����һ�������ʵ����watcher��������ʹ��Դ����zookeeper-3.4.5.jar
     * zookeeper��apiֻ�ܶԸýڵ��Լ��ýڵ��һ���ӽڵ���м�ء���ص��¼�������Ҫ�У�
     * 	1.EventType.NodeCreated:������һ���µĽڵ�ʱ����(Ϊ��ʹ�ڴ����ڵ�ʱ�Ըýڵ���м�أ������ٴ���֮ǰ���ã�
     * 	  zoo.exists(path, watch)Orzoo.exists(path, watcher)�������жϽڵ��Ƿ���ڣ����۸ýڵ��Ƿ���ڶ��Ըýڵ���м��
     * 	2.EventType.NodeDataChanged:  ����صĽڵ����ݸ���ʱ������
     *  3.EventType.NodeDeleted��                 ��ɾ����صĽڵ�ʱ����
     *  4.EventType.NodeDataChanged��     ����صĽڵ�Ĵ���һ���µ��ӽڵ����ɾ���ýڵ��µ�һ���ӽڵ�ʱ������
     *    (�������ɾ�������Ӽ�����or����һ���ӽڵ������ʱ�������κη��¼�)Ҫ���ظýڵ��һ���ӽڵ㣬�ڴ���һ���ӽڵ�֮ǰ
     *    ���ã�zoo.getChildren(path, watch) or zoo.getChildren(path, watcher)�����������µ��ӽڵ���м�ء�
     *
     */

    /**
     * �����������⣺��ζԸ��ڵ��µ������ӽڵ���м�أ�������Խ��м�ء�����true��watch�������ʲô����
     * ע�����ʹ��zookeeper�ͻ��˲����ڵ�ı�����磺ʹ��java������zookeeper������������java���봴��ʱ
     * ��/watcher���м�أ��ڵ���getChildren�������ֶ����µ�һ���ӽڵ���м�ء�����ʹ��ZkCli.sh����shell
     * ����zookeeperʱ�������/watcher�ڵ�ı�������ֻ�ܼ�ظýڵ��һ���ӽڵ㡣���ܼ�ض����ӽڵ㡣
     * ���java�ڴ���ʱ��·��Ϊ/watcher/children/a ���еݹ鴴�������Ը�·���µ����нڵ�����ӽڵ���м�ء�Ӧ�þ���ʵ�����������������⡣
     */

}
