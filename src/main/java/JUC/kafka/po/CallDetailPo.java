package JUC.kafka.po;

import java.io.Serializable;

public class CallDetailPo implements Serializable{

	/**
	 * @author JUC
	 * void
	 */

	    private String session_id;
	    private String agent_id;
	    private String agent_name;
	    private String area_id;
	    private int call_type;
	    private String skill_id;
	    private String skill_name;
	    private String agent_dn;
	    private String local_url;
	    private String remote_url;
	    private String start_time;
	    private String end_time;
	    private int status;
	    private int duration;
	    private int start_type;
	    private int end_type;
	    private String record_addr;
	    private String orig_skill_id;
	    private String accept_skill_id;
	    private String end_time_halfhour;
	    private String campaign_id;
	    private String skill_type;
	    private String region_id;
	    private String ent_id;
	    private String _id;
	    private int serial_num;
	    private int state_num;
	    private int is_handled;
	    private int is_release;
	    private int global_serial_num;
	    private String main_serviceid;
	    private String sub_serviceid;
	    private int has_alerting_event;
	    private String local_url_wx;
	    private String remote_url_wx;
	    public void setSession_id(String session_id) {
	         this.session_id = session_id;
	     }
	     public String getSession_id() {
	         return session_id;
	     }

	    public void setAgent_id(String agent_id) {
	         this.agent_id = agent_id;
	     }
	     public String getAgent_id() {
	         return agent_id;
	     }

	    public void setAgent_name(String agent_name) {
	         this.agent_name = agent_name;
	     }
	     public String getAgent_name() {
	         return agent_name;
	     }

	    public void setArea_id(String area_id) {
	         this.area_id = area_id;
	     }
	     public String getArea_id() {
	         return area_id;
	     }

	    public void setCall_type(int call_type) {
	         this.call_type = call_type;
	     }
	     public int getCall_type() {
	         return call_type;
	     }

	    public void setSkill_id(String skill_id) {
	         this.skill_id = skill_id;
	     }
	     public String getSkill_id() {
	         return skill_id;
	     }

	    public void setSkill_name(String skill_name) {
	         this.skill_name = skill_name;
	     }
	     public String getSkill_name() {
	         return skill_name;
	     }

	    public void setAgent_dn(String agent_dn) {
	         this.agent_dn = agent_dn;
	     }
	     public String getAgent_dn() {
	         return agent_dn;
	     }


	    public void setRemote_url(String remote_url) {
	         this.remote_url = remote_url;
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

	    public void setStart_time(String start_time) {
	         this.start_time = start_time;
	     }
	     public String getStart_time() {
	         return start_time;
	     }

	    public void setEnd_time(String end_time) {
	         this.end_time = end_time;
	     }
	     public String getEnd_time() {
	         return end_time;
	     }

	    public void setStatus(int status) {
	         this.status = status;
	     }
	     public int getStatus() {
	         return status;
	     }

	    public void setDuration(int duration) {
	         this.duration = duration;
	     }
	     public int getDuration() {
	         return duration;
	     }

	    public void setStart_type(int start_type) {
	         this.start_type = start_type;
	     }
	     public int getStart_type() {
	         return start_type;
	     }

	    public void setEnd_type(int end_type) {
	         this.end_type = end_type;
	     }
	     public int getEnd_type() {
	         return end_type;
	     }

	    public void setRecord_addr(String record_addr) {
	         this.record_addr = record_addr;
	     }
	     public String getRecord_addr() {
	         return record_addr;
	     }

	    public void setOrig_skill_id(String orig_skill_id) {
	         this.orig_skill_id = orig_skill_id;
	     }
	     public String getOrig_skill_id() {
	         return orig_skill_id;
	     }

	    public void setAccept_skill_id(String accept_skill_id) {
	         this.accept_skill_id = accept_skill_id;
	     }
	     public String getAccept_skill_id() {
	         return accept_skill_id;
	     }

	    public void setEnd_time_halfhour(String end_time_halfhour) {
	         this.end_time_halfhour = end_time_halfhour;
	     }
	     public String getEnd_time_halfhour() {
	         return end_time_halfhour;
	     }

	    public void setCampaign_id(String campaign_id) {
	         this.campaign_id = campaign_id;
	     }
	     public String getCampaign_id() {
	         return campaign_id;
	     }

	    public void setSkill_type(String skill_type) {
	         this.skill_type = skill_type;
	     }
	     public String getSkill_type() {
	         return skill_type;
	     }

	    public void setRegion_id(String region_id) {
	         this.region_id = region_id;
	     }
	     public String getRegion_id() {
	         return region_id;
	     }

	    public void setEnt_id(String ent_id) {
	         this.ent_id = ent_id;
	     }
	     public String getEnt_id() {
	         return ent_id;
	     }

	    public void set_id(String _id) {
	         this._id = _id;
	     }
	     public String get_id() {
	         return _id;
	     }

	    public void setSerial_num(int serial_num) {
	         this.serial_num = serial_num;
	     }
	     public int getSerial_num() {
	         return serial_num;
	     }

	    public void setState_num(int state_num) {
	         this.state_num = state_num;
	     }
	     public int getState_num() {
	         return state_num;
	     }

	    public void setIs_handled(int is_handled) {
	         this.is_handled = is_handled;
	     }
	     public int getIs_handled() {
	         return is_handled;
	     }

	    public void setIs_release(int is_release) {
	         this.is_release = is_release;
	     }
	     public int getIs_release() {
	         return is_release;
	     }

	    public void setGlobal_serial_num(int global_serial_num) {
	         this.global_serial_num = global_serial_num;
	     }
	     public int getGlobal_serial_num() {
	         return global_serial_num;
	     }

	    public void setMain_serviceid(String main_serviceid) {
	         this.main_serviceid = main_serviceid;
	     }
	     public String getMain_serviceid() {
	         return main_serviceid;
	     }

	    public void setSub_serviceid(String sub_serviceid) {
	         this.sub_serviceid = sub_serviceid;
	     }
	     public String getSub_serviceid() {
	         return sub_serviceid;
	     }

	    public void setHas_alerting_event(int has_alerting_event) {
	         this.has_alerting_event = has_alerting_event;
	     }
	     public int getHas_alerting_event() {
	         return has_alerting_event;
	     }

	    public void setLocal_url_wx(String local_url_wx) {
	         this.local_url_wx = local_url_wx;
	     }
	     public String getLocal_url_wx() {
	         return local_url_wx;
	     }

	    public void setRemote_url_wx(String remote_url_wx) {
	         this.remote_url_wx = remote_url_wx;
	     }
	     public String getRemote_url_wx() {
	         return remote_url_wx;
	     }
		public CallDetailPo(String session_id, String agent_id) {
			super();
			this.session_id = session_id;
			this.agent_id = agent_id;
		}
	     
	     

}
