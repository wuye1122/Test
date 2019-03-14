package Demo;

import java.io.Serializable;

public class SessionDetail implements Serializable{
	private static final long serialVersionUID = -2192730604473435862L;


	public SessionDetail(String entId) {
		this.entId = entId;
	}

	private String entId;
	private String session_id;
	private String agent_id;
	private String agent_name;
	private String agent_dn;
	private String skill_id;
	private String skill_name;
	private String start_time;
	private String end_time;
	private int call_type;
	private String local_url;
	private String remote_url;
	//主被叫外显号
	private String local_url_wx;
	private String remote_url_wx;
	private int ivr_duration;
	private int alert_duration;
	//呼出场景坐席振铃时长
	private int alert_duration_agent;
	private int queue_duration;
	private int talk_duration;
	private int acw_duration;
	private String end_type;
	private String campaign_id;
	private String region_id;
	private String orig_skill_id;
	private String accept_skill_id;
	private String session_detail;
	private int stateNum;
	private int serial_num;
	private int global_serial_num;
	private String exdata1;//备用字段1
	private String exdata2;//
	private String exdata3;//
	private String exdata4;//
	private String exdata5;//
	private String exdata6;//
	private String exdata7;//
	private String exdata8;//
	private String exdata9;//
	private String exdata10;//备用字段10
	private int ivr_time;//ivr总时长
	private int transfer_agent_time;//转坐席总时长
	private String beginTime;//外拨的呼叫开始时间
	private int dial_times;//外拨的呼叫总时长
	private int alter_time;//外拨里的客户响铃时长
	private int transfer_queue_time;//转坐席排队时长
	private int transfer_altering_time;//转坐席响铃时长
	//外拨次数
	private int dialNumber;
	
	public int getDialNumber() {
		return dialNumber;
	}
	public void setDialNumber(int dialNumber) {
		this.dialNumber = dialNumber;
	}
	public int getTransfer_agent_time() {
		return transfer_agent_time;
	}
	public void setTransfer_agent_time(int transfer_agent_time) {
		this.transfer_agent_time = transfer_agent_time;
	}
	public int getDial_times() {
		return dial_times;
	}
	public void setDial_times(int dial_times) {
		this.dial_times = dial_times;
	}
	public int getAlter_time() {
		return alter_time;
	}
	public void setAlter_time(int alter_time) {
		this.alter_time = alter_time;
	}
	public int getTransfer_queue_time() {
		return transfer_queue_time;
	}
	public void setTransfer_queue_time(int transfer_queue_time) {
		this.transfer_queue_time = transfer_queue_time;
	}
	public int getTransfer_altering_time() {
		return transfer_altering_time;
	}
	public void setTransfer_altering_time(int transfer_altering_time) {
		this.transfer_altering_time = transfer_altering_time;
	}
	
	public String getExdata1() {
		return exdata1;
	}
	public void setExdata1(String exdata1) {
		this.exdata1 = exdata1;
	}
	public String getExdata2() {
		return exdata2;
	}
	public void setExdata2(String exdata2) {
		this.exdata2 = exdata2;
	}
	public String getExdata3() {
		return exdata3;
	}
	public void setExdata3(String exdata3) {
		this.exdata3 = exdata3;
	}
	public String getExdata4() {
		return exdata4;
	}
	public void setExdata4(String exdata4) {
		this.exdata4 = exdata4;
	}
	public String getExdata5() {
		return exdata5;
	}
	public void setExdata5(String exdata5) {
		this.exdata5 = exdata5;
	}
	public String getExdata6() {
		return exdata6;
	}
	public void setExdata6(String exdata6) {
		this.exdata6 = exdata6;
	}
	public String getExdata7() {
		return exdata7;
	}
	public void setExdata7(String exdata7) {
		this.exdata7 = exdata7;
	}
	public String getExdata8() {
		return exdata8;
	}
	public void setExdata8(String exdata8) {
		this.exdata8 = exdata8;
	}
	public String getExdata9() {
		return exdata9;
	}
	public void setExdata9(String exdata9) {
		this.exdata9 = exdata9;
	}
	public String getExdata10() {
		return exdata10;
	}
	public void setExdata10(String exdata10) {
		this.exdata10 = exdata10;
	}
	
	
	
	public int getIvr_time() {
		return ivr_time;
	}
	public void setIvr_time(int ivr_time) {
		this.ivr_time = ivr_time;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLocal_url_wx() {
		return local_url_wx;
	}
	public void setLocal_url_wx(String local_url_wx) {
		this.local_url_wx = local_url_wx;
	}
	public String getRemote_url_wx() {
		return remote_url_wx;
	}
	public void setRemote_url_wx(String remote_url_wx) {
		this.remote_url_wx = remote_url_wx;
	}
	public int getGlobal_serial_num() {
		return global_serial_num;
	}
	public void setGlobal_serial_num(int global_serial_num) {
		this.global_serial_num = global_serial_num;
	}
	public int getSerial_num() {
		return serial_num;
	}
	public void setSerial_num(int serial_num) {
		this.serial_num = serial_num;
	}
	public int getAlert_duration_agent() {
		return alert_duration_agent;
	}
	public void setAlert_duration_agent(int alert_duration_agent) {
		this.alert_duration_agent = alert_duration_agent;
	}
	public int getStateNum() {
		return stateNum;
	}
	public void setStateNum(int stateNum) {
		this.stateNum = stateNum;
	}
	public String getEntId() {
		return entId;
	}
	public void setEntId(String entId) {
		this.entId = entId;
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
	public int getCall_type() {
		return call_type;
	}
	public void setCall_type(int call_type) {
		this.call_type = call_type;
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
	public int getIvr_duration() {
		return ivr_duration;
	}
	public void setIvr_duration(int ivr_duration) {
		this.ivr_duration = ivr_duration;
	}
	public int getAlert_duration() {
		return alert_duration;
	}
	public void setAlert_duration(int alert_duration) {
		this.alert_duration = alert_duration;
	}
	public int getQueue_duration() {
		return queue_duration;
	}
	public void setQueue_duration(int queue_duration) {
		this.queue_duration = queue_duration;
	}
	public int getTalk_duration() {
		return talk_duration;
	}
	public void setTalk_duration(int talk_duration) {
		this.talk_duration = talk_duration;
	}
	public int getAcw_duration() {
		return acw_duration;
	}
	public void setAcw_duration(int acw_duration) {
		this.acw_duration = acw_duration;
	}
	public String getEnd_type() {
		return end_type;
	}
	public void setEnd_type(String end_type) {
		this.end_type = end_type;
	}
	public String getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getOrig_skill_id() {
		return orig_skill_id;
	}
	public void setOrig_skill_id(String orig_skill_id) {
		this.orig_skill_id = orig_skill_id;
	}
	public String getAccept_skill_id() {
		return accept_skill_id;
	}
	public void setAccept_skill_id(String accept_skill_id) {
		this.accept_skill_id = accept_skill_id;
	}
	public String getSession_detail() {
		return session_detail;
	}
	public void setSession_detail(String session_detail) {
		this.session_detail = session_detail;
	}
	public SessionDetail(){
		
	}
	public SessionDetail(String entId, String session_id, String agent_id,
			String agent_name, String agent_dn, String skill_id,
			String skill_name, String start_time, String end_time,
			int call_type, String local_url, String remote_url,
			String local_url_wx, String remote_url_wx, int ivr_duration,
			int alert_duration, int alert_duration_agent, int queue_duration,
			int talk_duration, int acw_duration, String end_type,
			String campaign_id, String region_id, String orig_skill_id,
			String accept_skill_id, String session_detail, int stateNum,
			int serial_num, int global_serial_num, String exdata1,
			String exdata2, String exdata3, String exdata4, String exdata5,
			String exdata6, String exdata7, String exdata8, String exdata9,
			String exdata10, int ivr_time, int transfer_agent_time,
			String beginTime, int dial_times, int alter_time,
			int transfer_queue_time, int transfer_altering_time) {
		super();
		this.entId = entId;
		this.session_id = session_id;
		this.agent_id = agent_id;
		this.agent_name = agent_name;
		this.agent_dn = agent_dn;
		this.skill_id = skill_id;
		this.skill_name = skill_name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.call_type = call_type;
		this.local_url = local_url;
		this.remote_url = remote_url;
		this.local_url_wx = local_url_wx;
		this.remote_url_wx = remote_url_wx;
		this.ivr_duration = ivr_duration;
		this.alert_duration = alert_duration;
		this.alert_duration_agent = alert_duration_agent;
		this.queue_duration = queue_duration;
		this.talk_duration = talk_duration;
		this.acw_duration = acw_duration;
		this.end_type = end_type;
		this.campaign_id = campaign_id;
		this.region_id = region_id;
		this.orig_skill_id = orig_skill_id;
		this.accept_skill_id = accept_skill_id;
		this.session_detail = session_detail;
		this.stateNum = stateNum;
		this.serial_num = serial_num;
		this.global_serial_num = global_serial_num;
		this.exdata1 = exdata1;
		this.exdata2 = exdata2;
		this.exdata3 = exdata3;
		this.exdata4 = exdata4;
		this.exdata5 = exdata5;
		this.exdata6 = exdata6;
		this.exdata7 = exdata7;
		this.exdata8 = exdata8;
		this.exdata9 = exdata9;
		this.exdata10 = exdata10;
		this.ivr_time = ivr_time;
		this.transfer_agent_time = transfer_agent_time;
		this.beginTime = beginTime;
		this.dial_times = dial_times;
		this.alter_time = alter_time;
		this.transfer_queue_time = transfer_queue_time;
		this.transfer_altering_time = transfer_altering_time;
	}


	@Override
	public String toString() {
		return "SessionDetail{" +
				"entId='" + entId + '\'' +
				'}';
	}
}
