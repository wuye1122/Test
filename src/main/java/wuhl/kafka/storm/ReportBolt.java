package wuhl.kafka.storm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class ReportBolt extends BaseRichBolt {

	//cleanup ��topolopy֮�����е��ͷ���Դ �����ڼ�Ⱥ�����ǲ���ȫ��  ���ܱ�֤һ��ִ�� 
	private OutputCollector collector;
	
	private HashMap<String,Long> map=null;

	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
             String word=  tuple.getStringByField("word");
             Long count= tuple.getLongByField("count");
             this.map.put(word, count);  
		
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		// TODO Auto-generated method stub
        this.collector=collector;
	    this.map=new HashMap<String,Long>();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

	}

	public void cleanup(){
		//��map���б���
		// Set<Entry<String, Long>> set=this.map.entrySet();
			for(Map.Entry m:map.entrySet()){
				System.out.println("����: "+m.getKey()+"������: "+m.getValue());	
				}


	}

}
