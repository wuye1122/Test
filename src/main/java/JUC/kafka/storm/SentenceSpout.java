package JUC.kafka.storm;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class SentenceSpout extends BaseRichSpout {

	
	/*BashRichSpoutl�� -->ISpout  IComponent 
	 * 
	 * 
	 * IComponent :declareOutputFields ͨ����ǰ��������strom��� ��Ҫ���з����stream
	 *             ���������� ÿһ�������е�tuple����ĳЩ�ֶ�
	 *             
	 * ISpout:open spout��ʼ����� ������������ 
	 * 
	 *              ��������������map: strom��ص�������Ϣ
	 *              
	 *                        TopologyContext:�ṩtopolopy �����Ϣ  
	 *                        
	 *                        SpoutOutputCollector: ���ǽ��з���tuple�ķ���
	 * 
	 * nextTuple����spout�ĺ��ķ��� strom ͨ������������� �������collector����tuple
	 * 
     */	
	
	private SpoutOutputCollector collector;//���з���
	
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
		//tuple���з���
		
		//index++;
		Integer i=0;
		while(i<sentences.length){
			System.out.println("���з������SentenceSpout..tuple:"+sentences[i]);
			this.collector.emit(new Values(sentences[i]));
			i++;
		}
		
		/*for(int i=0;i<sentences.length;i++){
			System.out.println("���з������SentenceSpout..tuple:"+sentences[i]);
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
