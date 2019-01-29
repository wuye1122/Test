package JUC.kafka.po;

public class SessionDetailPo {
	private String start_time;//开始时间
	private String end_time;//结束时间
	private String session_id;//会话标示
	private String agent_id;//坐席ID 
	private String agent_name;//坐席名称
	private String agent_dn;//坐席分机
	private String skill_id;//技能组标示
	private String skill_name;//技能组名称
	private String local_url;//
	private String remote_url;
	private String ivr_duration;
	private String alert_duration;
	private String alert_duration_agent;
	private String queue_duration;
	private String talk_duration;
	private String acw_duration;//
	private String end_type;//
	private String call_type;//呼叫类型

	//增加智能外拨字段
	private String dial_times;//外拨的呼叫总时长
	private String ivr_time;//ivr总时长
	private String alter_time;//外拨里的客户响铃时长
	private String transfer_agent_time;//转坐席总时长
	private String transfer_queue_time;//转坐席排队时长
	private String transfer_altering_time;//转坐席响铃时长

	public String getDial_times() {
		return dial_times;
	}

	public void setDial_times(String dial_times) {
		this.dial_times = dial_times;
	}

	public String getIvr_time() {
		return ivr_time;
	}

	public void setIvr_time(String ivr_time) {
		this.ivr_time = ivr_time;
	}

	public String getAlter_time() {
		return alter_time;
	}

	public void setAlter_time(String alter_time) {
		this.alter_time = alter_time;
	}

	public String getTransfer_agent_time() {
		return transfer_agent_time;
	}

	public void setTransfer_agent_time(String transfer_agent_time) {
		this.transfer_agent_time = transfer_agent_time;
	}

	public String getTransfer_queue_time() {
		return transfer_queue_time;
	}

	public void setTransfer_queue_time(String transfer_queue_time) {
		this.transfer_queue_time = transfer_queue_time;
	}

	public String getTransfer_altering_time() {
		return transfer_altering_time;
	}

	public void setTransfer_altering_time(String transfer_altering_time) {
		this.transfer_altering_time = transfer_altering_time;
	}

	public String getCall_type() {
		return call_type;
	}
	public void setCall_type(String call_type) {
		this.call_type = call_type;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getAgent_dn() {
		return agent_dn;
	}
	public void setAgent_dn(String agent_dn) {
		this.agent_dn = agent_dn;
	}
	public String getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(String skill_id) {
		this.skill_id = skill_id;
	}
	public String getSkill_name() {
		return skill_name;
	}
	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
	public String getLocal_url() {
		return local_url;
	}
	public void setLocal_url(String local_url) {
		this.local_url = local_url;
	}
	public String getRemote_url() {
		return remote_url;
	}
	public void setRemote_url(String remote_url) {
		this.remote_url = remote_url;
	}
	public String getIvr_duration() {
		return ivr_duration;
	}
	public void setIvr_duration(String ivr_duration) {
		this.ivr_duration = ivr_duration;
	}
	public String getAlert_duration() {
		return alert_duration;
	}
	public void setAlert_duration(String alert_duration) {
		this.alert_duration = alert_duration;
	}
	public String getAlert_duration_agent() {
		return alert_duration_agent;
	}
	public void setAlert_duration_agent(String alert_duration_agent) {
		this.alert_duration_agent = alert_duration_agent;
	}
	public String getQueue_duration() {
		return queue_duration;
	}
	public void setQueue_duration(String queue_duration) {
		this.queue_duration = queue_duration;
	}
	public String getTalk_duration() {
		return talk_duration;
	}
	public void setTalk_duration(String talk_duration) {
		this.talk_duration = talk_duration;
	}
	public String getAcw_duration() {
		return acw_duration;
	}
	public void setAcw_duration(String acw_duration) {
		this.acw_duration = acw_duration;
	}
	public String getEnd_type() {
		return end_type;
	}
	public void setEnd_type(String end_type) {
		this.end_type = end_type;
	}

	@Override
	public String toString() {
		return "SessionDetailPo [start_time=" + start_time + ", end_time="
				+ end_time + ", session_id=" + session_id + ", agent_id="
				+ agent_id + ", agent_name=" + agent_name + ", agent_dn="
				+ agent_dn + ", skill_id=" + skill_id + ", skill_name="
				+ skill_name + ", local_url=" + local_url + ", remote_url="
				+ remote_url + ", ivr_duration=" + ivr_duration
				+ ", alert_duration=" + alert_duration
				+ ", alert_duration_agent=" + alert_duration_agent
				+ ", queue_duration=" + queue_duration + ", talk_duration="
				+ talk_duration + ", acw_duration=" + acw_duration
				+ ", end_type=" + end_type + ", call_type=" + call_type
				+ ", dial_times=" + dial_times + ", ivr_time=" + ivr_time
				+ ", alter_time=" + alter_time + ", transfer_agent_time="
				+ transfer_agent_time + ", transfer_queue_time="
				+ transfer_queue_time + ", transfer_altering_time="
				+ transfer_altering_time + "]";
	}
	
	
	
}
