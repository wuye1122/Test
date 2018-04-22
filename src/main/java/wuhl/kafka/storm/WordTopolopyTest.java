package wuhl.kafka.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class WordTopolopyTest {

	/**
	 * @author wuhl
	 * void
	 * 
	 * 
	 * ����������
	 * shuffleGrouping:��Ҫ��������
	 *                 ����storm �����������spout/bolt  �����tuple������ȷַ���ǰ��bolt
	 *       
	 *                  
	 *                  
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


        Config config = new Config();
        config.setDebug(true);
        
		  TopologyBuilder topologyBuilder = new TopologyBuilder();
		  
		  
		  //���ʷַ�spout
		  SentenceSpout spout=new SentenceSpout();
          topologyBuilder.setSpout("SentenceSpout", spout);       
          //����ר��                                          
          topologyBuilder.setBolt("SplitSentenceBolt", new SplitSentenceBolt()).shuffleGrouping("SentenceSpout");
          topologyBuilder.setBolt("WordSentenceBolt", new WordSentenceBolt()).shuffleGrouping("SplitSentenceBolt");
          topologyBuilder.setBolt("ReportBolt", new ReportBolt()).shuffleGrouping("WordSentenceBolt");

       
	      LocalCluster cluster = new LocalCluster();
          System.out.println(config);

	      cluster.submitTopology("SentenceStorm", config, topologyBuilder.createTopology());
	      try {
			Thread.sleep(10000);
			cluster.killTopology("SentenceStorm");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      cluster.shutdown();
	}

}
