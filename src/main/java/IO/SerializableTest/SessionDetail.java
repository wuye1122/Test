package IO.SerializableTest;

import Test.MongodbSessionId;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;

public class SessionDetail  {

    private String _id;
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
    private int stateNum;
    private int serial_num;
    private String global_serial_num;
    private String user_data;
    private String end_reason;
    //外显号
    private String dest_ani;
    //轮次
    private String turn;
    private String campaign_name;
    //外拨数据
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
    private String session_detail;



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

    public int getTransfer_agent_time() {
        return transfer_agent_time;
    }

    public void setTransfer_agent_time(int transfer_agent_time) {
        this.transfer_agent_time = transfer_agent_time;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
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

    public String getUser_data() {
        return user_data;
    }

    public void setUser_data(String user_data) {
        this.user_data = user_data;
    }

    public String getEnd_reason() {
        return end_reason;
    }

    public void setEnd_reason(String end_reason) {
        this.end_reason = end_reason;
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
    public String getGlobal_serial_num() {
        return global_serial_num;
    }
    public void setGlobal_serial_num(String global_serial_num) {
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


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getCampaign_name() {
        return campaign_name;
    }

    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }

    public String getDest_ani() {
        return dest_ani;
    }

    public void setDest_ani(String dest_ani) {
        this.dest_ani = dest_ani;
    }

    @Override
    public String toString() {
        return "SessionDetail{" +
                "_id='" + _id + '\'' +
                ", entId='" + entId + '\'' +
                ", session_id='" + session_id + '\'' +
                ", agent_id='" + agent_id + '\'' +
                ", agent_name='" + agent_name + '\'' +
                ", agent_dn='" + agent_dn + '\'' +
                ", skill_id='" + skill_id + '\'' +
                ", skill_name='" + skill_name + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", call_type=" + call_type +
                ", local_url='" + local_url + '\'' +
                ", remote_url='" + remote_url + '\'' +
                ", local_url_wx='" + local_url_wx + '\'' +
                ", remote_url_wx='" + remote_url_wx + '\'' +
                ", ivr_duration=" + ivr_duration +
                ", alert_duration=" + alert_duration +
                ", alert_duration_agent=" + alert_duration_agent +
                ", queue_duration=" + queue_duration +
                ", talk_duration=" + talk_duration +
                ", acw_duration=" + acw_duration +
                ", end_type='" + end_type + '\'' +
                ", campaign_id='" + campaign_id + '\'' +
                ", region_id='" + region_id + '\'' +
                ", orig_skill_id='" + orig_skill_id + '\'' +
                ", accept_skill_id='" + accept_skill_id + '\'' +
                ", session_detail='" + session_detail + '\'' +
                ", stateNum=" + stateNum +
                ", serial_num=" + serial_num +
                ", global_serial_num='" + global_serial_num + '\'' +
                ", user_data='" + user_data + '\'' +
                ", end_reason='" + end_reason + '\'' +
                ", dest_ani='" + dest_ani + '\'' +
                ", turn='" + turn + '\'' +
                ", campaign_name='" + campaign_name + '\'' +
                ", exdata1='" + exdata1 + '\'' +
                ", exdata2='" + exdata2 + '\'' +
                ", exdata3='" + exdata3 + '\'' +
                ", exdata4='" + exdata4 + '\'' +
                ", exdata5='" + exdata5 + '\'' +
                ", exdata6='" + exdata6 + '\'' +
                ", exdata7='" + exdata7 + '\'' +
                ", exdata8='" + exdata8 + '\'' +
                ", exdata9='" + exdata9 + '\'' +
                ", exdata10='" + exdata10 + '\'' +
                ", ivr_time=" + ivr_time +
                ", transfer_agent_time=" + transfer_agent_time +
                ", beginTime='" + beginTime + '\'' +
                ", dial_times=" + dial_times +
                ", alter_time=" + alter_time +
                ", transfer_queue_time=" + transfer_queue_time +
                ", transfer_altering_time=" + transfer_altering_time +
                '}';
    }


    public String getKey() {

        return  (StringUtils.isBlank(_id)?0:1)+""+
                (StringUtils.isBlank(entId)?0:1)+""+
                 (StringUtils.isBlank(session_id)?0:1)+""+
                (StringUtils.isBlank(agent_id)?0:1)+""+
                (StringUtils.isBlank(agent_name)?0:1)+""+
                (StringUtils.isBlank(agent_dn)?0:1)+""+
                (StringUtils.isBlank(skill_id)?0:1)+""+
                (StringUtils.isBlank(skill_name)?0:1)+""+
                (StringUtils.isBlank(start_time)?0:1)+""+
                (StringUtils.isBlank(end_time)?0:1)+""+
                (call_type==0?0:1)+""+
                (StringUtils.isBlank(local_url)?0:1)+""+
                (StringUtils.isBlank(remote_url)?0:1)+""+
                (StringUtils.isBlank(local_url_wx)?0:1)+""+
                (StringUtils.isBlank(remote_url_wx)?0:1)+""+
                ((ivr_duration==0)?0:1)+""+
                ((alert_duration==0)?0:1)+""+
                ((alert_duration_agent==0)?0:1)+""+
                ((queue_duration==0)?0:1)+""+
                ((talk_duration==0)?0:1)+""+
                ((acw_duration==0)?0:1)+""+
                (StringUtils.isBlank(end_type)?0:1)+""+
                (StringUtils.isBlank(campaign_id)?0:1)+""+
                (StringUtils.isBlank(region_id)?0:1)+""+
                (StringUtils.isBlank(orig_skill_id)?0:1)+""+
                (StringUtils.isBlank(accept_skill_id)?0:1)+""+
                ((stateNum==0)?0:1)+""+
                ((serial_num==0)?0:1)+""+
                (StringUtils.isBlank(global_serial_num)?0:1)+""+
                (StringUtils.isBlank(user_data)?0:1)+""+
                (StringUtils.isBlank(end_reason)?0:1)+""+
                (StringUtils.isBlank(dest_ani)?0:1)+""+
                (StringUtils.isBlank(turn)?0:1)+""+
                (StringUtils.isBlank(campaign_name)?0:1)+""+
                (StringUtils.isBlank(exdata1)?0:1)+""+
                (StringUtils.isBlank(exdata2)?0:1)+""+
                (StringUtils.isBlank(exdata3)?0:1)+""+
                (StringUtils.isBlank(exdata4)?0:1)+""+
                (StringUtils.isBlank(exdata5)?0:1)+""+
                (StringUtils.isBlank(exdata6)?0:1)+""+
                (StringUtils.isBlank(exdata7)?0:1)+""+
                (StringUtils.isBlank(exdata8)?0:1)+""+
                (StringUtils.isBlank(exdata9)?0:1)+""+
                (StringUtils.isBlank(exdata10)?0:1)+""+
                ((ivr_time==0)?0:1)+""+
                ((transfer_agent_time==0)?0:1)+""+
                ((dial_times==0)?0:1)+""+
                ((alter_time==0)?0:1)+""+
                ((transfer_queue_time==0)?0:1)+""+
                ((transfer_altering_time==0)?0:1)+""+
                (StringUtils.isBlank(beginTime)?0:1)+""+
                (StringUtils.isBlank(session_detail)?0:2)+"";

    }


    //比较两个string 将不同的输出
    public void show(String key,String key1){

        try {
            Class clazz = Class.forName("IO.SerializableTest.SessionDetail");//根据类名获得其对应的Class对象 写上你想要的类名就是了 注意是全名 如果有包的话要加上 比如java.Lang.String
            Field[] fields = clazz.getDeclaredFields();//根据Class对象获得属性 私有的也可以获得

            StringBuffer keyBuff = new StringBuffer();
            StringBuffer key1Buff = new StringBuffer();

            for(int i=0;i<fields.length;i++){
                if(!(key.charAt(i)==key1.charAt(i))){
                    if(key.charAt(i)=='0'){
                        keyBuff.append(fields[i].getName()+":空,");
                    }else{
                        keyBuff.append(fields[i].getName()+":不为空,");
                    }
                    if(key1.charAt(i)=='0'){
                        key1Buff.append(fields[i].getName()+":空,");
                    }else{
                        key1Buff.append(fields[i].getName()+":不为空,");
                    }

                }
            }
            System.out.println("结果:"+keyBuff);
            System.out.println("结果:"+key1Buff);

            System.out.println("====:"+fields.length);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
//        JSONObject J=JSONObject.parseObject(session_id);
  //      Object J1 = JSONObject.parse(session_id);
 //       System.out.println("J"+J);
 //       System.out.println("J1"+J1);
        //TODO _ID是否可以去掉 "_id":ObjectId("5b8621776ee635139d0245e2")
       String ss= "{\"start_time\":\"1535517032000\",\"end_time\":\"1535517032000\",\"agent_id\":\"25796574\",\"agent_name\":\"25796574\",\"agent_dn\":\"SIP:25796574\",\"local_url\":\"SIP:25796574\",\"region_id\":\"010\",\"session_id\":\"7447693886824501911\",\"skill_id\":\"1000003096\",\"end_reason\":\"0\",\"dest_ani\":\"075528254065\",\"skill_name\":\"人工服务\",\"global_serial_num\":\";2;\",\"call_type\":1,\"acw_duration\":0,\"alert_duration\":0,\"alert_duration_agent\":0,\"queue_duration\":0,\"talk_duration\":0,\"ivr_duration\":0,\"ivr_time\":0,\"transfer_agent_time\":0,\"dial_times\":0,\"transfer_queue_time\":0,\"transfer_altering_time\":0,\"alter_time\":0,,\"session_detail\":[{\"_id\":\"DCS-CLOUD2:0101490467:25796574:SIP:25796574::1535517032:1535517032:204:1\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:25796574\",\"agent_id\":\"25796574\",\"agent_name\":\"25796574\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"075528254065\",\"duration\":0,\"end_reason\":\"0\",\"end_time\":\"1535517032\",\"end_time_halfhour\":\"1535517000\",\"end_type\":2,\"ent_id\":\"0101490467\",\"global_serial_num\":2,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":-1,\"local_url\":\"SIP:25796574\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"NoRecordAddr\",\"region_id\":\"010\",\"remote_url\":\"\",\"remote_url_wx\":\"\",\"serial_num\":1,\"session_id\":\"7447693886824501911\",\"skill_id\":\"1000003096\",\"skill_name\":\"人工服务\",\"skill_type\":\"\",\"start_time\":\"1535517032\",\"start_type\":2,\"state_num\":1,\"status\":204,\"sub_serviceid\":\"1\"}]}";
       JSONObject J=JSONObject.parseObject(ss);
        System.out.println(J);
       SessionDetail id3 = (SessionDetail) JSONObject.parseObject(ss,SessionDetail.class);
       System.out.println("id3:"+id3.toString());
        System.out.println("id3:"+id3.getKey());


        String ss1= "{\n" +
                "    \"_id\" : ObjectId(\"5b8621776ee63526bb676ee5\"),\n" +
                "    \"session_id\" : \"7447693886824501911\",\n" +
                "    \"global_serial_num\" : \";5;3;\",\n" +
                "    \"acw_duration\" : 0,\n" +
                "    \"alert_duration\" : 15,\n" +
                "    \"alert_duration_agent\" : 0,\n" +
                "    \"queue_duration\" : 0,\n" +
                "    \"talk_duration\" : 0,\n" +
                "    \"ivr_duration\" : 0,\n" +
                "    \"ivr_time\" : 0,\n" +
                "    \"transfer_agent_time\" : 0,\n" +
                "    \"dial_times\" : 0,\n" +
                "    \"transfer_queue_time\" : 0,\n" +
                "    \"transfer_altering_time\" : 0,\n" +
                "    \"alter_time\" : 0,\n" +
                "    \"start_time\" : \"1535517032000\",\n" +
                "    \"remote_url\" : \"TEL:15501862826\",\n" +
                "    \"end_time\" : \"1535517047000\",\n" +
                "    \"agent_id\" : \"25796574\",\n" +
                "    \"agent_name\" : \"25796574\",\n" +
                "    \"agent_dn\" : \"SIP:25796574\",\n" +
                "    \"end_type\" : \"501\",\n" +
                "    \"local_url\" : \"SIP:25796574\",\n" +
                "    \"region_id\" : \"010\",\n" +
                "    \"skill_id\" : \"1000003096\",\n" +
                "    \"skill_name\" : \"人工服务\",\n" +
                "    \"end_reason\" : \"0\",\n" +
                "    \"session_detail\" : [ \n" +
                "        {\n" +
                "            \"_id\" : \"DCS-CLOUD2:0101490467:25796574:SIP:25796574:TEL:15501862826:1535517032:1535517047:204:2\",\n" +
                "            \"accept_skill_id\" : \"\",\n" +
                "            \"agent_dn\" : \"SIP:25796574\",\n" +
                "            \"agent_id\" : \"25796574\",\n" +
                "            \"agent_name\" : \"25796574\",\n" +
                "            \"area_id\" : \"\",\n" +
                "            \"call_type\" : 1,\n" +
                "            \"campaign_id\" : \"\",\n" +
                "            \"dest_ani\" : \"075528254065\",\n" +
                "            \"duration\" : 15,\n" +
                "            \"end_reason\" : \"0\",\n" +
                "            \"end_time\" : \"1535517047\",\n" +
                "            \"end_time_halfhour\" : \"1535517000\",\n" +
                "            \"end_type\" : 501,\n" +
                "            \"ent_id\" : \"0101490467\",\n" +
                "            \"global_serial_num\" : 3,\n" +
                "            \"has_alerting_event\" : 0,\n" +
                "            \"is_handled\" : 0,\n" +
                "            \"is_release\" : 1,\n" +
                "            \"local_url\" : \"SIP:25796574\",\n" +
                "            \"local_url_wx\" : \"\",\n" +
                "            \"main_serviceid\" : \"1\",\n" +
                "            \"orig_skill_id\" : \"\",\n" +
                "            \"record_addr\" : \"NoRecordAddr\",\n" +
                "            \"region_id\" : \"010\",\n" +
                "            \"remote_url\" : \"TEL:15501862826\",\n" +
                "            \"remote_url_wx\" : \"\",\n" +
                "            \"serial_num\" : 2,\n" +
                "            \"session_id\" : \"7447693886824501911\",\n" +
                "            \"skill_id\" : \"1000003096\",\n" +
                "            \"skill_name\" : \"人工服务\",\n" +
                "            \"skill_type\" : \"\",\n" +
                "            \"start_time\" : \"1535517032\",\n" +
                "            \"start_type\" : 105,\n" +
                "            \"state_num\" : 2,\n" +
                "            \"status\" : 204,\n" +
                "            \"sub_serviceid\" : \"1\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"call_type\" : 1\n" +
                "}";

        String temp = ss1.replace("(","").replace(")","");
        temp = temp.trim().replace("\n","").replace(" ","").replace("ObjectId","");
        System.out.println("temp:"+temp);

        String id5 = "{\"_id\":\"5b8621776ee63526bb676ee5\",\"session_id\":\"7447693886824501911\",\"global_serial_num\":\";5;3;\",\"acw_duration\":0,\"alert_duration\":15,\"alert_duration_agent\":0,\"queue_duration\":0,\"talk_duration\":0,\"ivr_duration\":0,\"ivr_time\":0,\"transfer_agent_time\":0,\"dial_times\":0,\"transfer_queue_time\":0,\"transfer_altering_time\":0,\"alter_time\":0,\"start_time\":\"1535517032000\",\"remote_url\":\"TEL:15501862826\",\"end_time\":\"1535517047000\",\"agent_id\":\"25796574\",\"agent_name\":\"25796574\",\"agent_dn\":\"SIP:25796574\",\"end_type\":\"501\",\"local_url\":\"SIP:25796574\",\"region_id\":\"010\",\"skill_id\":\"1000003096\",\"skill_name\":\"人工服务\",\"end_reason\":\"0\",\"session_detail\":[{\"_id\":\"DCS-CLOUD2:0101490467:25796574:SIP:25796574:TEL:15501862826:1535517032:1535517047:204:2\",\"accept_skill_id\":\"\",\"agent_dn\":\"SIP:25796574\",\"agent_id\":\"25796574\",\"agent_name\":\"25796574\",\"area_id\":\"\",\"call_type\":1,\"campaign_id\":\"\",\"dest_ani\":\"075528254065\",\"duration\":15,\"end_reason\":\"0\",\"end_time\":\"1535517047\",\"end_time_halfhour\":\"1535517000\",\"end_type\":501,\"ent_id\":\"0101490467\",\"global_serial_num\":3,\"has_alerting_event\":0,\"is_handled\":0,\"is_release\":1,\"local_url\":\"SIP:25796574\",\"local_url_wx\":\"\",\"main_serviceid\":\"1\",\"orig_skill_id\":\"\",\"record_addr\":\"NoRecordAddr\",\"region_id\":\"010\",\"remote_url\":\"TEL:15501862826\",\"remote_url_wx\":\"\",\"serial_num\":2,\"session_id\":\"7447693886824501911\",\"skill_id\":\"1000003096\",\"skill_name\":\"人工服务\",\"skill_type\":\"\",\"start_time\":\"1535517032\",\"start_type\":105,\"state_num\":2,\"status\":204,\"sub_serviceid\":\"1\"}],\"call_type\":1}\n";
        SessionDetail id6 = (SessionDetail) JSONObject.parseObject(temp,SessionDetail.class);

        System.out.println("id6:"+id6.toString());
        System.out.println(id3.getKey());
        System.out.println(id3.getKey().length());
        System.out.println("id6:"+id6.getKey());
        System.out.println(id6.getKey().length());
        id3.show(id3.getKey(),id6.getKey());

        String ss2="{\n" +
                "    \"_id\" : ObjectId(\"5b8621776ee63526bb676ee5\"),\n" +
                "    \"session_id\" : \"7447693886824501911\",\n" +
                "    \"global_serial_num\" : \";5;3;\",\n" +
                "    \"acw_duration\" : 0,\n" +
                "    \"alert_duration_agent\" : 0,\n" +
                "    \"queue_duration\" : 0,\n" +
                "    \"talk_duration\" : 0,\n" +
                "    \"ivr_duration\" : 0,\n" +
                "    \"ivr_time\" : 0,\n" +
                "    \"transfer_agent_time\" : 0,\n" +
                "    \"dial_times\" : 0,\n" +
                "    \"transfer_queue_time\" : 0,\n" +
                "    \"transfer_altering_time\" : 0,\n" +
                "    \"alter_time\" : 0,\n" +
                "    \"start_time\" : \"1535517032000\",\n" +
                "    \"remote_url\" : \"TEL:15501862826\",\n" +
                "    \"end_time\" : \"1535517047000\",\n" +
                "    \"agent_id\" : \"25796574\",\n" +
                "    \"agent_name\" : \"25796574\",\n" +
                "    \"agent_dn\" : \"SIP:25796574\",\n" +
                "    \"end_type\" : \"501\",\n" +
                "    \"local_url\" : \"SIP:25796574\",\n" +
                "    \"region_id\" : \"010\",\n" +
                "    \"skill_id\" : \"1000003096\",\n" +
                "    \"skill_name\" : \"人工服务\",\n" +
                "    \"end_reason\" : \"0\",\n" +
                "    \"session_detail\" : [ \n" +
                "        {\n" +
                "            \"_id\" : \"DCS-CLOUD2:0101490467:25796574:SIP:25796574:TEL:15501862826:1535517032:1535517047:204:2\",\n" +
                "            \"accept_skill_id\" : \"\",\n" +
                "            \"agent_dn\" : \"SIP:25796574\",\n" +
                "            \"agent_id\" : \"25796574\",\n" +
                "            \"agent_name\" : \"25796574\",\n" +
                "            \"area_id\" : \"\",\n" +
                "            \"call_type\" : 1,\n" +
                "            \"campaign_id\" : \"\",\n" +
                "            \"dest_ani\" : \"075528254065\",\n" +
                "            \"duration\" : 15,\n" +
                "            \"end_reason\" : \"0\",\n" +
                "            \"end_time\" : \"1535517047\",\n" +
                "            \"end_time_halfhour\" : \"1535517000\",\n" +
                "            \"end_type\" : 501,\n" +
                "            \"ent_id\" : \"0101490467\",\n" +
                "            \"global_serial_num\" : 3,\n" +
                "            \"has_alerting_event\" : 0,\n" +
                "            \"is_handled\" : 0,\n" +
                "            \"is_release\" : 1,\n" +
                "            \"local_url\" : \"SIP:25796574\",\n" +
                "            \"local_url_wx\" : \"\",\n" +
                "            \"main_serviceid\" : \"1\",\n" +
                "            \"orig_skill_id\" : \"\",\n" +
                "            \"record_addr\" : \"NoRecordAddr\",\n" +
                "            \"region_id\" : \"010\",\n" +
                "            \"remote_url\" : \"TEL:15501862826\",\n" +
                "            \"remote_url_wx\" : \"\",\n" +
                "            \"serial_num\" : 2,\n" +
                "            \"session_id\" : \"7447693886824501911\",\n" +
                "            \"skill_id\" : \"1000003096\",\n" +
                "            \"skill_name\" : \"人工服务\",\n" +
                "            \"skill_type\" : \"\",\n" +
                "            \"start_time\" : \"1535517032\",\n" +
                "            \"start_type\" : 105,\n" +
                "            \"state_num\" : 2,\n" +
                "            \"status\" : 204,\n" +
                "            \"sub_serviceid\" : \"1\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"call_type\" : 1\n" +
                "}";
        String temp1 = ss2.replace("(","").replace(")","");
        temp1 = temp1.trim().replace("\n","").replace(" ","").replace("ObjectId","");
        SessionDetail id7 = (SessionDetail) JSONObject.parseObject(temp1,SessionDetail.class);

        id3.show(id3.getKey(),id7.getKey());

        System.out.println("id7:"+id7.toString());
        System.out.println("id7:"+id7.getKey());



    }


}
