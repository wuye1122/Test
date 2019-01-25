package wuhl.kafka.zookeeper;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.List;

public class SelectLeaderDemo   {
    protected static String PATH = "/dps/leader";
    private static final int CLIENT_QTY = 10;
    private static final String  CONNECT_STRING = "node1:2181,ndoe2:2181,node3:2181";

    public static void main(String[] args) throws Exception {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();

        try {
            for (int i = 0; i < CLIENT_QTY; i++) {
                CuratorFramework client
                        = CuratorFrameworkFactory.newClient(CONNECT_STRING, new ExponentialBackoffRetry(20000, 3));
                clients.add(client);
                String currentPath = PATH+"/"+i;
                LeaderLatch latch = new LeaderLatch(client, currentPath, "Client #" + i);
                latch.addListener(new LeaderLatchListener() {

                    @Override
                    public void isLeader() {
                        // TODO Auto-generated method stub
                        System.out.println("I am Leader");
                    }

                    @Override
                    public void notLeader() {
                        // TODO Auto-generated method stub
                        System.out.println("I am not Leader");
                    }
                });
                examples.add(latch);
                client.start();
                latch.start();
            }
            Thread.sleep(10000);
            LeaderLatch currentLeader = null;
            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    currentLeader = latch;
                }
            }
            System.out.println("current leader is " );
            System.out.println("release the leader " );
            if(null!=currentLeader){
                currentLeader.close();
            }

            Thread.sleep(5000);

            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    currentLeader = latch;
                }
            }
            System.out.println("current leader is " );
            System.out.println("release the leader ");
        } finally {
            for (LeaderLatch latch : examples) {
                if (null != latch.getState()) {
                    CloseableUtils.closeQuietly(latch);
                }
                }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
        }
    }
}
