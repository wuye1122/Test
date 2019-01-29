package JUC.kafka.storm;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class WordSentenceBolt extends BaseRichBolt {

	private OutputCollector collector;
	private HashMap<String,Long> map=null;
	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub

		String word=tuple.getStringByField("word");
		Long count=map.get(word);
		if(count==null){
			count=0L;
		}
		count++;
		System.out.println("Êä³ö£º"+word+"ÊýÁ¿£º"+count);
		map.put(word, count);
		this.collector.emit(new Values(word,count));
	
	} 
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.map=new HashMap<String,Long>();
        this.collector=collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
		declarer.declare(new Fields("word","count"));
	}

	

}
