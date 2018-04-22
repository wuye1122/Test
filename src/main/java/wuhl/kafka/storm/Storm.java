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
          // �����緢�ڵ㲢���䲢�������ò�����������Ƹö����ڼ�Ⱥ�е��߳�����
         topologyBuilder.setSpout("SimpleSpout", new FakeLogReaderSpout());
          // �������ݴ���ڵ㲢���䲢������ָ���ýڵ�����緢�ڵ�Ĳ���Ϊ�����ʽ��
          //topologyBuilder.setSpout("SimpleSpout", spout, ConfigUtils.TOPO_SPOUT_NUM);

          //����ר��                                          
          topologyBuilder.setBolt("SkillGroupComputeBolt", new CallLogCreatorBolt()).shuffleGrouping("SimpleSpout");
          topologyBuilder.setBolt("SkillGroupMongoBolt", new CallLogCounterBolt()).shuffleGrouping("SkillGroupComputeBolt");

	      LocalCluster cluster = new LocalCluster();
          System.out.println(config);

	      cluster.submitTopology("LogAnalyserStorm", config, topologyBuilder.createTopology());
	      Thread.sleep(10000);
	      cluster.shutdown();
	   }

}
