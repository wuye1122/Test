package wuhl.kafka.po;

public class AgentStateDetailPo {

	/**
	 * @author wuhl
	 * void
	 */

	    private String agentId;
	    private String channel;
	    private String content;
	    private String contentType;
	    private String csessionId;
	    private String customId;
	    private String direction;
	    private String entId;
	    private int is_handled;
	    private String imSessionId;
	    private long time;
	    private String ImType;
	    private String eventType;
	    public void setAgentId(String agentId) {
	         this.agentId = agentId;
	     }
	     public String getAgentId() {
	         return agentId;
	     }

	    public void setChannel(String channel) {
	         this.channel = channel;
	     }
	     public String getChannel() {
	         return channel;
	     }

	    public void setContent(String content) {
	         this.content = content;
	     }
	     public String getContent() {
	         return content;
	     }

	    public void setContentType(String contentType) {
	         this.contentType = contentType;
	     }
	     public String getContentType() {
	         return contentType;
	     }

	    public void setCsessionId(String csessionId) {
	         this.csessionId = csessionId;
	     }
	     public String getCsessionId() {
	         return csessionId;
	     }

	    public void setCustomId(String customId) {
	         this.customId = customId;
	     }
	     public String getCustomId() {
	         return customId;
	     }

	    public void setDirection(String direction) {
	         this.direction = direction;
	     }
	     public String getDirection() {
	         return direction;
	     }

	    public void setEntId(String entId) {
	         this.entId = entId;
	     }
	     public String getEntId() {
	         return entId;
	     }

	    public void setIs_handled(int is_handled) {
	         this.is_handled = is_handled;
	     }
	     public int getIs_handled() {
	         return is_handled;
	     }

	    public void setImSessionId(String imSessionId) {
	         this.imSessionId = imSessionId;
	     }
	     public String getImSessionId() {
	         return imSessionId;
	     }

	    public void setTime(long time) {
	         this.time = time;
	     }
	     public long getTime() {
	         return time;
	     }

	    public void setImType(String ImType) {
	         this.ImType = ImType;
	     }
	     public String getImType() {
	         return ImType;
	     }

	    public void setEventType(String eventType) {
	         this.eventType = eventType;
	     }
	     public String getEventType() {
	         return eventType;
	     }
		@Override
		public String toString() {
			return "AgentStateDetailPo [agentId=" + agentId + ", channel="
					+ channel + ", content=" + content + ", contentType="
					+ contentType + ", csessionId=" + csessionId
					+ ", customId=" + customId + ", direction=" + direction
					+ ", entId=" + entId + ", is_handled=" + is_handled
					+ ", imSessionId=" + imSessionId + ", time=" + time
					+ ", ImType=" + ImType + ", eventType=" + eventType + "]";
		}

	@Override
	public int hashCode() {
		return this.agentId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}


}
