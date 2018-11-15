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
	
	/*BashRichSpoutl类 -->ISpout  IComponent 
	 * 
	 * 
	 * IComponent :declareOutputFields 通过当前方法告诉strom组件 将要进行发射的stream
	 *             发射哪种流 每一个流当中的tuple包含某些字段
	 *             
	 * IBolt:prepare 准备资源 通常用来 数据库连接 
	 * 
	 * execute：从storm中订阅字段 进行解析发射到下一个bolt
	 * 
	 * topolopy :进行拓扑时候 首先 所有bolt和 spout都会进行序列化 
	 *           然后发送到集群当中  spout或者bolt实例化无法实例的实例变量 将抛出异常 NoSerializableException
	 *           topolopy将会部署失败
	 *           
	 *           最好在构造函数当中实例化 基本类型和可序列化的对象进行赋值 在prepare当中实例化
	 *           不可以序列化的对象 
     */	
	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
             String sentence= tuple.getStringByField("sentence");
		    System.out.println("进行打印SplitSentenceBolt语句:"+sentence);
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
