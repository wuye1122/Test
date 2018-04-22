package wuhl.kafka.storm;

import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.Config;
public class Storm {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) throws Exception{
	      //Create Config instance for cluster configuration
		

        Config config = new Config();
      //  config.setDebug(false);
        config.setDebug(true);
		  TopologyBuilder topologyBuilder = new TopologyBuilder();
          // 设置喷发节点并分配并发数，该并发数将会控制该对象在集群中的线程数。
         topologyBuilder.setSpout("SimpleSpout", new FakeLogReaderSpout());
          // 设置数据处理节点并分配并发数。指定该节点接收喷发节点的策略为随机方式。
          //topologyBuilder.setSpout("SimpleSpout", spout, ConfigUtils.TOPO_SPOUT_NUM);

          //测试专用                                          
          topologyBuilder.setBolt("SkillGroupComputeBolt", new CallLogCreatorBolt()).shuffleGrouping("SimpleSpout");
          topologyBuilder.setBolt("SkillGroupMongoBolt", new CallLogCounterBolt()).shuffleGrouping("SkillGroupComputeBolt");

	      LocalCluster cluster = new LocalCluster();
          System.out.println(config);

	      cluster.submitTopology("LogAnalyserStorm", config, topologyBuilder.createTopology());
	      Thread.sleep(10000);
	      cluster.shutdown();
	   }

}
