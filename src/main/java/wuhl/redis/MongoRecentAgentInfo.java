package wuhl.redis;


import com.alibaba.fastjson.annotation.JSONField;

public class MongoRecentAgentInfo {
	//�����ϵ��
	private String  entId;//��ҵid
	@JSONField(name="agent_id")
	private String  agentId;//��ϯ����

	@JSONField(name="agent_name")
	private String  agentName;//��ϯ����

	@JSONField(name="start_time")
	private String startTime;//��ʼʱ��

	@JSONField(name="skill_id")
	private String skillId;//������id

	@JSONField(name="skill_name")
	private String skillName;//����������

	@JSONField(name="remote_url")
	private String remoteUrl;//�ͻ�����

	@JSONField(name="score")
	private String score;//����

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
