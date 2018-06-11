package Test;

import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.Logger;

import wuhl.kafka.po.AgentStateDetailPo;

import IO.HttpRequest;

public class Http {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*   String dcms="http://localhost:8085/dcmsStatics/report/DialerSessionDetail/gotoDialerSessionDetail";
	       String param="";
	       System.out.println(HttpRequest.sendGet(dcms,param));
	         */
		List<AgentStateDetailPo> l = new ArrayList<AgentStateDetailPo>();
		
		AgentStateDetailPo po = new AgentStateDetailPo();
		po.setAgentId("111");
		l.add(po);
		AgentStateDetailPo po2 = new AgentStateDetailPo();
		po2.setAgentId("222");
		l.add(po2);

		AgentStateDetailPo po3 = po;
		po3.setAgentId("444");
		l.add(po3);
		l.add(po);
		System.out.println(l.toString());
		for(int i=0;i<l.size();i++){
			if(l.get(i).getAgentId().equals("444")){
				for(int j=0;j<2;j++){
					AgentStateDetailPo po4 = (AgentStateDetailPo)l.get(i);
					po4.setAgentId(String.valueOf(j));
		            l.add(po4);
		            System.out.println(l.size());
				}
			}
		
		}
		System.out.println("-----"+l.size());

		for(AgentStateDetailPo o : l){
			System.out.println("-----"+o.toString());

		}

	}

}
