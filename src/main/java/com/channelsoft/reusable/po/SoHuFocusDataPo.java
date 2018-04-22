package com.channelsoft.reusable.po;

public class SoHuFocusDataPo {

	private String record_id;  //session IVR鎻愪緵
	private String bigcode;  //IVR
	private String extcode;  //IVR
	private String ani;      //IVR  涓诲彨鍙风爜
	private String dni;		 //IVR  杞潗甯殑璇濋渶瑕佹洿鏂�琚彨鍙风爜
	private String called_type; //IVR   1鏄绾�  2銆�鏄浆鍧愬腑  鏍规嵁姝ゅ瓧娈靛垽鏂槸鍚︽埅鍙栦簨浠�
	private String start_time;  //IVR
	private String end_time;    //CTI 
	private String call_fee;    // 榛樿鍊奸兘鏄�
	private String caller_time; // end_time - start_time 
	private String called_time; // end_time - answer_time
	private String call_result; // 鎸搩鏀惧純\鎺掗槦鏀惧純杩斿洖3 ,鎺ラ�杩斿洖0,  濡傛灉called_type鏄� 鐩存帴鍏VR浼犳潵鐨�
	private String call_result_str; //鍚屼笂 
	private String tel_tape_address; // 褰曢煶娴佹按鍙�session:agentid:entid
	private String ani_province; //IVR
	private String ani_city; //IVR
	private String timestamp;//IVR
	private String begin_connect_time; //IVR
	private String answer_time; //CTI  鍧愬腑鎺ヨ捣鏃堕棿
	private String call_log;  //IVR
	private String group_id;  //IVR
	private String time_sum_prompt; //IVR
	private String time_sum_tranfer;//answer_time - begin_connect_time  濡傛灉answer_time=0 杩斿洖0
	private String tranfer_times;// IVR璁℃暟
	private String hangup; //杞绾�IVR  1鐢ㄦ埛鎸�銆�琚彨鎸�  杞潗甯� CTI
	private String talk_id; //sessionID
	private String satisfy; //IVR 
	private String work_id; //sessionID
	private String call_type; //IVR 
	private String callout_record_id; //鍛煎叆鏃朵负绌�  澶栧懠鎺ュ彛浼�
	private String sign;  //鐢熸垚MD5
	private String transfer_0_tyte; //IVR
	private String queue; //CTI 
	private String entId; //闄勫姞瀛楁锛岀湅鎯呭喌浠ュ悗浣跨敤
	
	public SoHuFocusDataPo(String record_id, String bigcode, String extcode, String ani, String dni, String called_type, String start_time,
			String end_time, String call_fee, String caller_time, String called_time, String call_result, String call_result_str, String tel_tape_address,
			String ani_province, String ani_city, String timestamp, String begin_connect_time, String answer_time, String call_log, String group_id, String time_sum_prompt, 
			String time_sum_tranfer, String transfer_times, String hangup, String talk_id, String satisfy, String work_id, String call_type, String callout_record_id, 
			String transfer_0_tyte, String queue, String entId)
	{
		this.ani = ani;
		this.ani_city = ani_city;
		this.ani_province = ani_province;
		this.answer_time = answer_time;
		this.begin_connect_time = begin_connect_time;
		this.bigcode = bigcode;
		this.call_fee = call_fee;
		this.call_log = call_log;
		this.call_result = call_result;
		this.call_result_str = call_result_str;
		this.call_type = called_type;
		this.called_time = caller_time;
		this.called_type = call_type;
		this.caller_time = caller_time;
		this.callout_record_id = callout_record_id;
		this.dni = dni;
		this.end_time = end_time;
		this.extcode = extcode;
		this.group_id = group_id;
		this.hangup = hangup;
		this.queue = queue;
		this.record_id = record_id;
		this.satisfy = satisfy;
		this.start_time = start_time;
		this.talk_id = talk_id;
		this.tel_tape_address = tel_tape_address;
		this.time_sum_prompt = time_sum_prompt;
		this.time_sum_tranfer = time_sum_tranfer;
		this.timestamp = timestamp;
		this.tranfer_times = transfer_times;
		this.transfer_0_tyte = transfer_0_tyte;
		this.work_id = work_id;
		this.entId = entId;  
	}
	
	
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getBigcode() {
		return bigcode;
	}
	public void setBigcode(String bigcode) {
		this.bigcode = bigcode;
	}
	public String getExtcode() {
		return extcode;
	}
	public void setExtcode(String extcode) {
		this.extcode = extcode;
	}
	public String getAni() {
		return ani;
	}
	public void setAni(String ani) {
		this.ani = ani;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCalled_type() {
		return called_type;
	}
	public void setCalled_type(String called_type) {
		this.called_type = called_type;
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
	public String getCall_fee() {
		return call_fee;
	}
	public void setCall_fee(String call_fee) {
		this.call_fee = call_fee;
	}
	public String getCaller_time() {
		return caller_time;
	}
	public void setCaller_time(String caller_time) {
		this.caller_time = caller_time;
	}
	public String getCalled_time() {
		return called_time;
	}
	public void setCalled_time(String called_time) {
		this.called_time = called_time;
	}
	public String getCall_result() {
		return call_result;
	}
	public void setCall_result(String call_result) {
		this.call_result = call_result;
	}
	public String getCall_result_str() {
		return call_result_str;
	}
	public void setCall_result_str(String call_result_str) {
		this.call_result_str = call_result_str;
	}
	public String getTel_tape_address() {
		return tel_tape_address;
	}
	public void setTel_tape_address(String tel_tape_address) {
		this.tel_tape_address = tel_tape_address;
	}
	public String getAni_province() {
		return ani_province;
	}
	public void setAni_province(String ani_province) {
		this.ani_province = ani_province;
	}
	public String getAni_city() {
		return ani_city;
	}
	public void setAni_city(String ani_city) {
		this.ani_city = ani_city;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getBegin_connect_time() {
		return begin_connect_time;
	}
	public void setBegin_connect_time(String begin_connect_time) {
		this.begin_connect_time = begin_connect_time;
	}
	public String getAnswer_time() {
		return answer_time;
	}
	public void setAnswer_time(String answer_time) {
		this.answer_time = answer_time;
	}
	public String getCall_log() {
		return call_log;
	}
	public void setCall_log(String call_log) {
		this.call_log = call_log;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getTime_sum_prompt() {
		return time_sum_prompt;
	}
	public void setTime_sum_prompt(String time_sum_prompt) {
		this.time_sum_prompt = time_sum_prompt;
	}
	public String getTime_sum_tranfer() {
		return time_sum_tranfer;
	}
	public void setTime_sum_tranfer(String time_sum_tranfer) {
		this.time_sum_tranfer = time_sum_tranfer;
	}
	public String getTranfer_times() {
		return tranfer_times;
	}
	public void setTranfer_times(String tranfer_times) {
		this.tranfer_times = tranfer_times;
	}
	public String getHangup() {
		return hangup;
	}
	public void setHangup(String hangup) {
		this.hangup = hangup;
	}
	public String getTalk_id() {
		return talk_id;
	}
	public void setTalk_id(String talk_id) {
		this.talk_id = talk_id;
	}
	public String getSatisfy() {
		return satisfy;
	}
	public void setSatisfy(String satisfy) {
		this.satisfy = satisfy;
	}
	public String getWork_id() {
		return work_id;
	}
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}
	public String getCall_type() {
		return call_type;
	}
	public void setCall_type(String call_type) {
		this.call_type = call_type;
	}
	public String getCallout_record_id() {
		return callout_record_id;
	}
	public void setCallout_record_id(String callout_record_id) {
		this.callout_record_id = callout_record_id;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTransfer_0_tyte() {
		return transfer_0_tyte;
	}
	public void setTransfer_0_tyte(String transfer_0_tyte) {
		this.transfer_0_tyte = transfer_0_tyte;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}


	public String getEntId() {
		return entId;
	}


	public void setEntId(String entId) {
		this.entId = entId;
	}

	
}
