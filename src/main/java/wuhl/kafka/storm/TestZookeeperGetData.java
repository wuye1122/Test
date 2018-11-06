package wuhl.kafka.storm;

import org.apache.zookeeper.*;

import java.io.IOException;

public class TestZookeeperGetData {

    public static void main(String[] args) throws IOException {

        ZooKeeper zk = null;
        try {

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            System.out.println("��ʼ����ZooKeeper...");

            // ������ZooKeeper������������zk
            String address = "10.130.29.226:2181";
            int sessionTimeout = 3000;
            zk = new ZooKeeper(address, sessionTimeout, new Watcher() {
                // ������б��������¼�
                public void process(WatchedEvent event) {
                    if (event.getType() == null || "".equals(event.getType())) {
                        return;
                    }
                    System.out.println("�Ѿ�������" + event.getType() + "�¼���");
                }
            });

            System.out.println("ZooKeeper���Ӵ����ɹ���");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // ������Ŀ¼�ڵ�
            // ·��Ϊ/tmp_root_path
            // �ڵ�����Ϊ�ַ���"���Ǹ�Ŀ¼/tmp_root_path"
            // ����ģʽΪCreateMode.PERSISTENT
            System.out.println("��ʼ������Ŀ¼�ڵ�/tmp_root_path...");
            zk.create("/storm/config/ENTID_MESSAGE", "���Ǹ�Ŀ¼/tmp_root_path".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("��Ŀ¼�ڵ�/tmp_root_path�����ɹ���");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // ������һ����Ŀ¼�ڵ�
            // ·��Ϊ/tmp_root_path/childPath1
            // �ڵ�����Ϊ�ַ���"���ǵ�һ����Ŀ¼/tmp_root_path/childPath1"
            // ����ģʽΪCreateMode.PERSISTENT
            System.out.println("��ʼ������һ����Ŀ¼�ڵ�/tmp_root_path/childPath1...");
            zk.create("/storm/config/childPath1",
                    "���ǵ�һ����Ŀ¼/tmp_root_path/childPath1".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("��һ����Ŀ¼�ڵ�/tmp_root_path/childPath1�����ɹ���");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // �����ڶ�����Ŀ¼�ڵ�
            // ·��Ϊ/tmp_root_path/childPath2
            // �ڵ�����Ϊ�ַ���"���ǵڶ�����Ŀ¼/tmp_root_path/childPath2"
            // ����ģʽΪCreateMode.PERSISTENT
            System.out.println("��ʼ�����ڶ�����Ŀ¼�ڵ�/tmp_root_path/childPath2...");
            zk.create("/storm/config/childPath2",
                    "���ǵڶ�����Ŀ¼/tmp_root_path/childPath2".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("�ڶ�����Ŀ¼�ڵ�/tmp_root_path/childPath2�����ɹ���");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // ��ȡ�ڶ�����Ŀ¼�ڵ�/tmp_root_path/childPath2�ڵ�����
            System.out.println("��ʼ��ȡ�ڶ�����Ŀ¼�ڵ�/tmp_root_path/childPath2�ڵ�����...");
            System.out.println(new String(zk.getData(
                    "/storm/config/ENTID_MESSAGE_TEST", true, null)));
            System.out.println("�ڶ�����Ŀ¼�ڵ�/tmp_root_path/childPath2�ڵ����ݻ�ȡ�ɹ���");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // �޸ĵ�һ����Ŀ¼�ڵ�/tmp_root_path/childPath1����
            System.out.println("��ʼ�޸ĵ�һ����Ŀ¼�ڵ�/tmp_root_path/childPath1����...");
            zk.setData("/tmp_root_path/childPath1",
                    "�����޸����ݺ�ĵ�һ����Ŀ¼/tmp_root_path/childPath1".getBytes(), -1);
            System.out.println("�޸ĵ�һ����Ŀ¼�ڵ�/tmp_root_path/childPath1���ݳɹ���");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // �޸ĵڶ�����Ŀ¼�ڵ�/tmp_root_path/childPath2����
            System.out.println("��ʼ�޸ĵڶ�����Ŀ¼�ڵ�/tmp_root_path/childPath2����...");
            zk.setData("/storm/config/ENTID_MESSAGE_TEST",
                    "�����޸����ݺ�ĵڶ�����Ŀ¼/tmp_root_path/childPath2".getBytes(), -1);
            System.out.println("�޸ĵڶ�����Ŀ¼�ڵ�/tmp_root_path/childPath2���ݳɹ���");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // ɾ����һ����Ŀ¼�ڵ�
            System.out.println("��ʼɾ����һ����Ŀ¼�ڵ�/tmp_root_path/childPath1...");
            zk.delete("/storm/config/childPath1", -1);
            System.out.println("��һ����Ŀ¼�ڵ�/tmp_root_path/childPath1ɾ���ɹ���");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // ɾ���ڶ�����Ŀ¼�ڵ�
            System.out.println("��ʼɾ���ڶ�����Ŀ¼�ڵ�/tmp_root_path/childPath2...");
            zk.delete("/tmp_root_path/childPath2", -1);
            System.out.println("�ڶ�����Ŀ¼�ڵ�/tmp_root_path/childPath2ɾ���ɹ���");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // ɾ����Ŀ¼�ڵ�
            System.out.println("��ʼɾ����Ŀ¼�ڵ�/tmp_root_path...");
            zk.delete("/tmp_root_path", -1);
            System.out.println("��Ŀ¼�ڵ�/tmp_root_pathɾ���ɹ���");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // �ر�����
            if (zk != null) {
                try {
                    zk.close();
                    System.out.println("�ͷ�ZooKeeper���ӳɹ���");

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }


}
