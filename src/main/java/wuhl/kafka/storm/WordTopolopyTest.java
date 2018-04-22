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
	 * 数据流分组
	 * shuffleGrouping:主要订阅作用
	 *                 告诉storm 将后面输入的spout/bolt  发射的tuple随机均匀分发给前者bolt
	 *       
	 *                  
	 *                  
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


        Config config = new Config();
        config.setDebug(true);
        
		  TopologyBuilder topologyBuilder = new TopologyBuilder();
		  
		  
		  //单词分发spout
		  SentenceSpout spout=new SentenceSpout();
          topologyBuilder.setSpout("SentenceSpout", spout);       
          //测试专用                                          
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
