package wuhl.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;



public class HashMapTest {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {/*
		// TODO Auto-generated method stub
		logger.debug("���ı�ʶ:" + centerNum + "����ѯ��" + listCfgPo.size() + "��ƽ̨");
		Map<String,AgentYgCallInfo> map = new HashMap<String, AgentYgCallInfo>();
		for (CfgCenterPlatformPo po : listCfgPo) {
			// ����Զ�̽ӿ�
			logger.debug("��ʼ����Զ�̽ӿ�.....");
			try {
				if (StringUtils.isBlank(po.getEntidInfo()) || StringUtils.isBlank(po.getCenterUrl())) {
					//return new CallDetailResultPo("1", null,"���ı�ʶ���ô���", 0);
					continue;
				}
				List<AgentYgCallInfo> list = callService.callDetailDataByCenterId(po, queryDate);
				if (list == null) {
					continue;
				}
				for (AgentYgCallInfo info : list) {
					if (map.containsKey(info.getAgentId())) {
						AgentYgCallInfo temp = map.get(info.getAgentId());
						AgentYgCallInfo o = new AgentYgCallInfo();
						o.setCallNum(String.valueOf(Integer.parseInt(temp.getCallNum()) + Integer.parseInt(info.getCallNum())));
						o.setCallTime(temp.getCallTime().add(info.getCallTime()));
						o.setAgentId(temp.getAgentId());
						o.setDate(temp.getDate());
						map.put(info.getAgentId(), o);
;							} else {
						map.put(info.getAgentId(), info);
					}
				}
			} catch (Exception e) {
				logger.error("�����쳣,ƽ̨url=" + po.getCenterUrl(), e);
				continue;
			}
			List<AgentYgCallInfo> returnList = new ArrayList<AgentYgCallInfo>(map.size());
			for (Map.Entry<String, AgentYgCallInfo> m : map.entrySet()) {
				returnList.add(m.getValue());
			}
			logger.debug("��ʼ����Զ�̽ӿ����.....");
			return new CallDetailResultPo("0", returnList, "��ѯ�ɹ�", returnList.size());
		}*/
	}

}
