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
		logger.debug("中心标识:" + centerNum + "共查询到" + listCfgPo.size() + "个平台");
		Map<String,AgentYgCallInfo> map = new HashMap<String, AgentYgCallInfo>();
		for (CfgCenterPlatformPo po : listCfgPo) {
			// 调用远程接口
			logger.debug("开始调用远程接口.....");
			try {
				if (StringUtils.isBlank(po.getEntidInfo()) || StringUtils.isBlank(po.getCenterUrl())) {
					//return new CallDetailResultPo("1", null,"中心标识配置错误！", 0);
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
				logger.error("调用异常,平台url=" + po.getCenterUrl(), e);
				continue;
			}
			List<AgentYgCallInfo> returnList = new ArrayList<AgentYgCallInfo>(map.size());
			for (Map.Entry<String, AgentYgCallInfo> m : map.entrySet()) {
				returnList.add(m.getValue());
			}
			logger.debug("开始调用远程接口完成.....");
			return new CallDetailResultPo("0", returnList, "查询成功", returnList.size());
		}*/
	}

}
