package wuhl.redis;


import com.alibaba.fastjson.annotation.JSONField;

public class MongoRecentAgentInfo {
	//最近联系人
	private String  entId;//企业id
	@JSONField(name="agent_id")
	private String  agentId;//坐席工号

	@JSONField(name="agent_name")
	private String  agentName;//坐席名称

	@JSONField(name="start_time")
	private String startTime;//开始时间

	@JSONField(name="skill_id")
	private String skillId;//技能组id

	@JSONField(name="skill_name")
	private String skillName;//技能组名称

	@JSONField(name="remote_url")
	private String remoteUrl;//客户号码

	@JSONField(name="score")
	private String score;//评价

	public String getEntId() {
		return entId;
	}

	public String getAgentId() {
		return agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getSkillId() {
		return skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public String getScore() {
		return score;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "MongoRecentAgentInfo{" +
				"entId='" + entId + '\'' +
				", agentId='" + agentId + '\'' +
				", agentName='" + agentName + '\'' +
				", startTime='" + startTime + '\'' +
				", skillId='" + skillId + '\'' +
				", skillName='" + skillName + '\'' +
				", remoteUrl='" + remoteUrl + '\'' +
				", score='" + score + '\'' +
				'}';
	}
}
