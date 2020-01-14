package JUC.kafka.storm;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class SentenceSpout extends BaseRichSpout {

	
	/*BashRichSpoutl类 -->ISpout  IComponent 
	 * 
	 * 
	 * IComponent :declareOutputFields 通过当前方法告诉strom组件 将要进行发射的stream
	 *             发射哪种流 每一个流当中的tuple包含某些字段
	 *             
	 * ISpout:open spout初始化组件 会调用这个方法 
	 * 
	 *              其中三个参数：map: strom相关的配置信息
	 *              
	 *                        TopologyContext:提供topolopy 组件信息  
	 *                        
	 *                        SpoutOutputCollector: 则是进行发射tuple的方法
	 * 
	 * nextTuple：是spout的核心方法 strom 通过调用这个方法 像输出的collector发射tuple
	 * 
     */	
	
	private SpoutOutputCollector collector;//进行发射
	
	private String [] sentences={
		"my dog very cold",
		"i want to go home",
		"i do not leave away here",
		"i do not leave away here"

	};
	//private int index=0;
	@Override
	public void nextTuple() {
		// TODO Auto-generated method stub
		//tuple进行发射
		
		//index++;
		Integer i=0;
		while(i<sentences.length){
			System.out.println("进行发射语句SentenceSpout..tuple:"+sentences[i]);
			this.collector.emit(new Values(sentences[i]));
			i++;
		}
		
		/*for(int i=0;i<sentences.length;i++){
			System.out.println("进行发射语句SentenceSpout..tuple:"+sentences[i]);
			this.collector.emit(new Values(sentences[i]));
			
		}*/
		/*if(index>=sentences.length){
			
			index=0;
		}*/
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void open(Map config, TopologyContext context, SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
      this.collector=collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

		declarer.declare(new Fields("sentence"));
	}

	

}
