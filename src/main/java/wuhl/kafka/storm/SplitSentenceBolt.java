package wuhl.kafka.storm;

import java.util.Map;


import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class SplitSentenceBolt extends BaseRichBolt {

	private OutputCollector collector;
	
	/*BashRichSpoutl�� -->ISpout  IComponent 
	 * 
	 * 
	 * IComponent :declareOutputFields ͨ����ǰ��������strom��� ��Ҫ���з����stream
	 *             ���������� ÿһ�������е�tuple����ĳЩ�ֶ�
	 *             
	 * IBolt:prepare ׼����Դ ͨ������ ���ݿ����� 
	 * 
	 * execute����storm�ж����ֶ� ���н������䵽��һ��bolt
	 * 
	 * topolopy :��������ʱ�� ���� ����bolt�� spout����������л� 
	 *           Ȼ���͵���Ⱥ����  spout����boltʵ�����޷�ʵ����ʵ������ ���׳��쳣 NoSerializableException
	 *           topolopy���Ჿ��ʧ��
	 *           
	 *           ����ڹ��캯������ʵ���� �������ͺͿ����л��Ķ�����и�ֵ ��prepare����ʵ����
	 *           ���������л��Ķ��� 
     */	
	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
             String sentence= tuple.getStringByField("sentence");
		    System.out.println("���д�ӡSplitSentenceBolt���:"+sentence);
             String[] word= sentence.split(" ");
             for(String w :word){
            	 this.collector.emit(new Values(w));
             }
	}

	
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector=collector;

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("word"));
	}

	

}
