package Test;

public class ResultMessage {

	/**
	 * @author JUC
	 * void
	 */
		private String result;
		private String desc;

		public ResultMessage(String result, String desc) {
			this.result = result;
			this.desc = desc;
		}
		public ResultMessage(){
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}


	}


