package wuhl.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class OffsetTask {
	private static Log logger = LogFactory.getLog(OffsetTask.class);
	
	 
     public static void main(String args[]){
    	 List<String> list=new ArrayList<String>();
         String  topicAndTopology="call_detail:CallDetailTopology,new_r_ags_e:RAGSETopology,agentStateDetail:AgentStateDetailTopology,chatLog:ChatLogTopology,t_srvappraise:TSrvappraiseTopology,ivr_message:IVRMESSAGETopology";
         list= Arrays.asList(topicAndTopology.split(","));
     }
	
	public void  executeTask(List<String> list){
		logger.info("����OffsetTask.executeTask()����,��ʼ��ȡƫ��������");
	//	OffsetUtil.init();
		long start=System.currentTimeMillis();
		
		for(String str:list){
			try{
				//OffsetPo offsetPo=new OffsetPo();
		/*		offsetPo.setTime(start);*/
				String topic=str.split(":")[0];
				String topoName=str.split(":")[1];
				
				//long[] offset=OffsetUtil.getOffset(topic, topoName);
				
			/*	offsetPo.setTopic(topic);
				offsetPo.setTopo(topoName);
				offsetPo.setLastOffset(offset[0]);
				offsetPo.setStormOffset(offset[1]);*/
				
			}catch(Exception e){
				logger.error("ƫ����["+str+"]���ʧ�ܣ�",e);
			}
		}
		logger.info("OffsetTask.executeTask()��ʱ����ִ����ɣ�����ʱ"+(System.currentTimeMillis()-start)+"(ms)");
	}

}
