package wuhl.kafka.po;

public class TSrvappraisePo {

	/**
	 * @author wuhl
	 * void
	 */
	    private String sa_seid;
	    private String ent_id;
	    private String sa_time;
	    private String sa_agentid;
	    private String sa_agentname;
	    private String skill_id;
	    private String skill_name;
	    private int channel;
	    private String customerId;
	    private int is_handled;
	    private int sa_score;
	    public void setSa_seid(String sa_seid) {
	         this.sa_seid = sa_seid;
	     }
	     public String getSa_seid() {
	         return sa_seid;
	     }

	    public void setEnt_id(String ent_id) {
	         this.ent_id = ent_id;
	     }
	     public String getEnt_id() {
	         return ent_id;
	     }

	    public void setSa_time(String sa_time) {
	         this.sa_time = sa_time;
	     }
	     public String getSa_time() {
	         return sa_time;
	     }

	    public void setSa_agentid(String sa_agentid) {
	         this.sa_agentid = sa_agentid;
	     }
	     public String getSa_agentid() {
	         return sa_agentid;
	     }

	    public void setSa_agentname(String sa_agentname) {
	         this.sa_agentname = sa_agentname;
	     }
	     public String getSa_agentname() {
	         return sa_agentname;
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

	    public void setChannel(int channel) {
	         this.channel = channel;
	     }
	     public int getChannel() {
	         return channel;
	     }

	    public void setCustomerId(String customerId) {
	         this.customerId = customerId;
	     }
	     public String getCustomerId() {
	         return customerId;
	     }

	    public void setIs_handled(int is_handled) {
	         this.is_handled = is_handled;
	     }
	     public int getIs_handled() {
	         return is_handled;
	     }

	    public void setSa_score(int sa_score) {
	         this.sa_score = sa_score;
	     }
	     public int getSa_score() {
	         return sa_score;
	     }
}
