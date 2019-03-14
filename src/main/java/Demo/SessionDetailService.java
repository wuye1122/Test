package Demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import Demo.SessionDetail;
public class SessionDetailService {

    private static final long serialVersionUID = 5666955117958045349L;
    private static Logger logger = Logger.getLogger(SessionDetailService.class);
    public final static String QUEUEOFF = "500";
    public final static String ALERTOFF = "501";
    public final static String AGENTOFF = "255";
    public final static String USEROFF = "254";
    public final static String BACKIVR = "31";
    public final static String ALLOFF = "23";
    public final static String IVROFF = "3";
    public final static String MAKE_CONFERENCE = "9";//发起会议
    public final static String MAKE_CONSULT = "6";//发起咨询
    public final static String CALLIN = "1";//呼入
    public final static String CALLOUT = "2";//呼出
    public final static String CALLOUT_CALERTING = "105";//外呼客户振铃
    public final static String ABNORMAL = "-1";//异常数据呼叫类型
    //无坐席服务相关
    public final static String NOAGENT="5"; //无坐席服务call_type类型
    public final static String LICENSEOFF="401"; //授权不足
    public final static String LOCALALERTOFF="502"; //主叫振铃放弃
    public final static String REMOTEALERTOFF="503"; //被叫振铃放弃
    public final static String LOCALALLFAIL="402"; //主叫呼叫失败
    public final static String REMOTECALLFAIL="403"; //被叫呼叫失败
    public final static String LOCALOFF="255"; //主叫挂断
    public final static String REMOTEOFF="254"; //被叫挂断



    public Map<String,SessionDetail> computeSessionDetail(String data) {
        logger.info("进入SessionDetailService的computeSessionDetail()方法...");
        JSONObject param = JSON.parseObject(data.substring(data.indexOf(":")+1));
        String topic = data.substring(0,data.indexOf(":"));
        Map<String,SessionDetail> result=new HashMap<String, SessionDetail>();
        //过滤掉异常数据
        String call_type = param.getString("call_type");
        if(StaticsUtils.CALL_DETAIL.equals(topic)&& StaticsUtils.topicFilterSessionDetail(param)){
            if(NOAGENT.equals(call_type)){
                result.put(topic, this.computeSessionDetailNoAgent(param));
            }else{
                result.put(topic, this.computeSessionDetail(param));
            }
        }else if(StaticsUtils.NEW_R_AGS_E.equals(topic)&&(!ABNORMAL.equals(call_type))){  //call_type=-1的不统计    2016-08-22
            result.put(topic, this.computeByNewRAGSE(param));
        }else if(StaticsUtils.SD_CALL_RESULT.equals(topic)){  //外拨相关结果数据
            result.put(topic, this.computeBySdCallResult(param));
        }

        return result;
    }

    /**
     * 外拨相关的数据明细入库
     * @param param
     * @return
     */
    private SessionDetail computeBySdCallResult(JSONObject param) {
        logger.info("进入SessionDetailService的computeBySdCallResult()方法...");
        SessionDetail result = new SessionDetail();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = 0L;
        try {
            Date startTime = sdf.parse(param.getString("begintime"));
            time = startTime.getTime()/1000;//转换成秒级，以call_detail的时间保持一致，方便统一入库
        } catch (Exception e) {
            logger.error("时间转换异常:"+e.getMessage());
            e.printStackTrace();
        }
        result.setStart_time(String.valueOf(time));
        result.setIvr_time(param.getInteger("ivrtimes"));//外拨里的IVR时长
        result.setTransfer_agent_time(param.getInteger("transferagenttime"));//转坐席时长
        result.setCampaign_id(param.getString("campaiginid"));//外拨活动标示
        result.setEntId(param.getString("entid"));
        result.setAlter_time(param.getInteger("altertime"));
        result.setCall_type(3);//外拨的呼叫类型
        result.setDial_times(param.getInteger("dialtimes"));//外拨呼叫总时长
        result.setAlter_time(param.getInteger("altertime"));//外拨的客户振铃时长
        result.setTransfer_altering_time(param.getInteger("transferalteringtime"));//转坐席响铃时长
        result.setTransfer_queue_time(param.getInteger("transferqueuetime"));//转坐席排队时长
        result.setDialNumber(param.getInteger("dialnumber"));
        if(StringUtils.isNotBlank(param.getString("custphone"))){
            result.setRemote_url(param.getString("custphone"));
        }
        //备用字段的添加
        if(StringUtils.isNotBlank(param.getString("exdata1"))){
            result.setExdata1(param.getString("exdata1"));
        }
        if(StringUtils.isNotBlank(param.getString("exdata2"))){
            result.setExdata2(param.getString("exdata2"));
        }
        if(StringUtils.isNotBlank(param.getString("exdata3"))){
            result.setExdata3(param.getString("exdata3"));
        }
        if(StringUtils.isNotBlank(param.getString("exdata4"))){
            result.setExdata4(param.getString("exdata4"));
        }
        if(StringUtils.isNotBlank(param.getString("exdata5"))){
            result.setExdata5(param.getString("exdata5"));
        }
        if(StringUtils.isNotBlank(param.getString("exdata6"))){
            result.setExdata6(param.getString("exdata6"));
        }
        if(StringUtils.isNotBlank(param.getString("exdata7"))){
            result.setExdata7(param.getString("exdata7"));
        }
        if(StringUtils.isNotBlank(param.getString("exdata8"))){
            result.setExdata8(param.getString("exdata8"));
        }
        if(StringUtils.isNotBlank(param.getString("exdata9"))){
            result.setExdata9(param.getString("exdata9"));
        }
        if(StringUtils.isNotBlank(param.getString("exdata10"))){
            result.setExdata10(param.getString("exdata10"));
        }
        result.setEnd_type(param.getString("callresult"));
        result.setSession_id(param.getString("sessionid"));
        return result;
    }

    public SessionDetail computeByNewRAGSE(JSONObject param) {
        logger.info("进入SessionDetailService的computeByNewRAGSE()方法...");
        SessionDetail result = new SessionDetail();
        int status = Integer.parseInt(param.getString("status"));
        switch (status){
            case 106 :
                result = compute106(param);
                break;
            default :
                result = null;
                break;
        }
        return result;
    }

    private SessionDetail compute106(JSONObject param){
        logger.info("进入SessionDetailService的compute106()方法...");
        if(ABNORMAL.equals(param.getString("call_type"))){
            return null;
        }
        SessionDetail po = new SessionDetail();
        po.setEntId(param.getString("ent_id"));
        po.setSession_id(param.getString("session_id"));
        po.setGlobal_serial_num(param.getInteger("global_serial_num"));
//		po.setStart_time(param.getString("start_time"));
//		po.setEnd_time(param.getString("end_time"));
        po.setCall_type(-1);

        po.setAcw_duration(param.getInteger("duration"));

        return po;
    }

    public SessionDetail computeSessionDetailNoAgent(JSONObject param) {
        logger.info("进入SessionDetailService的computeSessionDetailNoAgent()方法...");
        SessionDetail result = new SessionDetail();
        int status = Integer.parseInt(param.getString("status"));
        switch (status){
            //授权资源不足
            case 201 :
                result = compute201NoAgent(param);
                break;
            case 204 :
                result = compute204NoAgent(param);
                break;
            case 205 :
                result = compute205NoAgent(param);
                break;
            default :
                result = null;
                break;
        }
        return result;
    }


    public SessionDetail computeSessionDetail(JSONObject param) {
        logger.info("进入SessionDetailService的computeSessionDetail()方法...");
        SessionDetail result = new SessionDetail();
//		SessionDetail po = JSONObject.toJavaObject(param, SessionDetail.class);
        int status = Integer.parseInt(param.getString("status"));
        switch (status){
            //IVR
            case 301 :
                result = compute301(param);
                break;
            case 202 ://人工服务准备入队
                result = compute202(param);
                break;
            case 204 ://session振铃
                result = compute204(param);
                break;
            case 205 ://session通话
                result = compute205(param);
                break;
            case 206 ://Session保持状态
                result = compute206(param);
                break;
            case 207 ://session咨询
                result = compute207(param);
                break;
            case 208 ://session会议
                result = compute208(param);
                break;
            default :
                result = null;
                break;
        }


        return result;
    }

    private SessionDetail compute201NoAgent(JSONObject param){
        logger.info("进入SessionDetailService的compute201NoAgent()方法...");
        if("401".equals(param.getString("start_type"))&&"401".equals(param.getString("end_type"))){
            SessionDetail po=buildTypePoNoAgent(param);
            po.setEnd_type(LICENSEOFF); //授权不足
            return po;
        }else{
            return null;
        }

    }

    private SessionDetail compute204NoAgent(JSONObject param){
        logger.info("进入SessionDetailService的compute204NoAgent()方法...");
        SessionDetail po=buildTypePoNoAgent(param);
        String start_type=param.getString("start_type");
        String end_type=param.getString("end_type");
        if("500".equals(start_type)&&"501".equals(end_type)){
            po.setEnd_type(LOCALALLFAIL);//主叫呼叫失败
        }else if("1".equals(start_type)){
            po.setAlert_duration_agent(param.getInteger("duration"));//主叫振铃时长
            if("501".equals(end_type)){
                po.setEnd_type(LOCALALERTOFF);//主叫振铃挂断
            }
        }else if("105".equals(start_type)){
            if("501".equals(end_type)){
                po.setEnd_type(REMOTECALLFAIL);//被叫呼叫失败
            }else if("502".equals(end_type)){
                po.setAlert_duration(param.getInteger("duration"));//被叫振铃时长
                po.setEnd_type(REMOTEALERTOFF);//被叫振铃挂断
            }else if("503".equals(end_type)){
                po.setAlert_duration(param.getInteger("duration"));//被叫振铃时长
                po.setEnd_type(LOCALALERTOFF);//主叫振铃挂断
            }else{
                po.setAlert_duration(param.getInteger("duration"));//被叫振铃时长
            }
        }
        return po;
    }

    private SessionDetail compute205NoAgent(JSONObject param){
        logger.info("进入SessionDetailService的compute205NoAgent()方法...");
        SessionDetail po=buildTypePoNoAgent(param);
        po.setTalk_duration(param.getInteger("duration")); //通话时长
        if(LOCALOFF.equals(param.getString("end_type"))||REMOTEOFF.equals(param.getString("end_type"))){
            po.setEnd_type(param.getString("end_type"));
        }
        return po;
    }

    private SessionDetail compute301(JSONObject param){
        logger.info("进入SessionDetailService的compute301()方法...");

        String endType = param.getString("end_type");
        SessionDetail po = new SessionDetail();
        po.setEntId(param.getString("ent_id"));
        po.setSession_id(param.getString("session_id"));
        po.setStart_time(param.getString("start_time"));
        po.setEnd_time(param.getString("end_time"));
        po.setCall_type(param.getInteger("call_type"));
        po.setRegion_id(param.getString("region_id"));
        po.setGlobal_serial_num(param.getInteger("global_serial_num"));
        po.setSession_detail(param.toString());

        po.setIvr_duration(param.getInteger("duration"));
        po.setAcw_duration(0);
        po.setAlert_duration(0);
        po.setQueue_duration(0);
        po.setTalk_duration(0);
        po.setAcw_duration(0);
        po.setLocal_url(StringUtils.isBlank(param.getString("local_url"))?"":param.getString("local_url"));
        po.setRemote_url(StringUtils.isBlank(param.getString("remote_url"))?"":param.getString("remote_url"));
        if(IVROFF.equals(endType)){
            po.setEnd_type(param.getString("end_type"));
//			po.setLocal_url(param.getString("local_url"));
//			po.setRemote_url(param.getString("remote_url"));
        }
        return po;
    }

    private SessionDetail compute202(JSONObject param){
        logger.info("进入SessionDetailService的compute202()方法...");

        String endType = param.getString("end_type");
        SessionDetail po = this.buildAnyTypePo(param);

        po.setIvr_duration(0);
        po.setAcw_duration(0);
        po.setAlert_duration(0);
        po.setQueue_duration(param.getInteger("duration"));
        po.setTalk_duration(0);
        po.setAcw_duration(0);

        if(QUEUEOFF.equals(endType)){
            po.setEnd_type(endType);
            po.setSkill_id(param.getString("skill_id"));
            po.setSkill_name(param.getString("skill_name"));
            po.setOrig_skill_id(param.getString("orig_skill_id"));
            po.setAccept_skill_id(param.getString("accept_skill_id"));
            po.setLocal_url(param.getString("local_url"));
            po.setRemote_url(param.getString("remote_url"));
        }
        return po;
    }

    private SessionDetail compute204(JSONObject param){
        logger.info("进入SessionDetailService的compute204()方法...");

        String endType = param.getString("end_type");
        String startType = param.getString("start_type");

        SessionDetail po = this.buildAnyTypePo(param);
        po.setIvr_duration(0);
        po.setAcw_duration(0);
        if(startType.equals(CALLIN)||startType.equals(CALLOUT_CALERTING)){
            po.setAlert_duration(param.getInteger("duration"));
        }
        if(startType.equals(CALLOUT)){
            po.setAlert_duration_agent(param.getInteger("duration"));
        }
        po.setQueue_duration(0);
        po.setTalk_duration(0);
        po.setAcw_duration(0);

//		if(ALERTOFF.equals(endType)){
        //振铃放弃的判定条件 不等於6，8，29
        if(!param.getString("start_type").equals("6") && !param.getString("start_type").equals("8") && !param.getString("start_type").equals("29")){
            po =buildTypePo(param,po);
        }
        if(param.getString("end_type").equals("501") && !param.getString("start_type").equals("6") && !param.getString("start_type").equals("8") && !param.getString("start_type").equals("29") && param.getString("is_release").equals("1")){
            po = this.buildEndTypePo(param,po);
        }
        return po;
    }

    private SessionDetail compute205(JSONObject param){
        logger.info("进入SessionDetailService的compute205()方法...");

        String endType = param.getString("end_type");

        //判断是內呼，不進行統計，只入庫session_detail字段
        if("13".equals(param.getString("start_type"))){
            return buildPo(param);
        }

        SessionDetail po = this.buildAnyTypePo(param);
        po =buildTypePo(param,po);
        po.setIvr_duration(0);
        po.setAcw_duration(0);
        po.setAlert_duration(0);
        po.setQueue_duration(0);
        po.setTalk_duration(param.getInteger("duration"));
        po.setAcw_duration(0);

        if(AGENTOFF.equals(endType)||USEROFF.equals(endType)){
            po = this.buildEndTypePo(param,po);
        }else if(BACKIVR.equals(endType)){
            po = this.buildEndTypePo(param,po);
            po.setEnd_type(USEROFF);
        }else if("29".equals(endType)){
            po = this.buildEndTypePo(param,po);
            //如果是单转，结束类型设置为坐席挂断
            po.setEnd_type(AGENTOFF);
        }
        return po;
    }

    private SessionDetail compute206(JSONObject param){
        logger.info("进入SessionDetailService的compute206()方法...");

        String endType = param.getString("end_type");

        SessionDetail po = this.buildAnyTypePo(param);
        po =buildTypePo(param,po);

        po.setIvr_duration(0);
        po.setAcw_duration(0);
        po.setAlert_duration(0);
        po.setQueue_duration(0);
        po.setTalk_duration(param.getInteger("duration"));
        po.setAcw_duration(0);

        if(AGENTOFF.equals(endType)||USEROFF.equals(endType)){
            po = this.buildEndTypePo(param,po);
        }
        return po;
    }

    private SessionDetail compute207(JSONObject param){
        logger.info("进入SessionDetailService的compute207()方法...");

        String startType = param.getString("start_type");
        String endType = param.getString("end_type");
        //判断是外线
        if(param.getString("agent_id").toUpperCase().contains("TEL:")||param.getString("agent_id").toUpperCase().contains("SIP:")){
            return buildPo(param);
        }

        SessionDetail po = this.buildAnyTypePo(param);

        po.setIvr_duration(0);
        po.setAcw_duration(0);
        po.setAlert_duration(0);
        po.setQueue_duration(0);
        if(startType.equals(MAKE_CONSULT)){
            po.setTalk_duration(param.getInteger("duration"));
            po =buildTypePo(param,po);
        }
        po.setAcw_duration(0);
        if(AGENTOFF.equals(endType)||USEROFF.equals(endType)){
            po = this.buildEndTypePo(param,po);
        }
        return po;
    }

    private SessionDetail compute208(JSONObject param){
        logger.info("进入SessionDetailService的compute208()方法...");

        String endType = param.getString("end_type");
        //判断是外线
        if(param.getString("agent_id").toUpperCase().contains("TEL:")||param.getString("agent_id").toUpperCase().contains("SIP:")){
            return buildPo(param);
        }

        SessionDetail po = this.buildAnyTypePo(param);

        po.setIvr_duration(0);
        po.setAcw_duration(0);
        po.setAlert_duration(0);
        po.setQueue_duration(0);
        if((param.getString("start_type").equals(MAKE_CONFERENCE))){
            po.setTalk_duration(param.getInteger("duration"));
            po=buildTypePo(param,po);
        }
        po.setAcw_duration(0);

        if(ALLOFF.equals(endType)||AGENTOFF.equals(endType)||USEROFF.equals(endType)){
            po = this.buildEndTypePo(param,po);
        }
        return po;
    }
    /**
     * 所有状态需要更新的字段的po对象
     * @param param
     * @return
     */
    private SessionDetail buildAnyTypePo(JSONObject param){
        SessionDetail po = new SessionDetail();
        po.setSession_id(param.getString("session_id"));
        po.setEntId(param.getString("ent_id"));
        po.setStart_time(param.getString("start_time"));
        po.setEnd_time(param.getString("end_time"));
        po.setCall_type(param.getInteger("call_type"));
        po.setRegion_id(param.getString("region_id"));
        po.setCampaign_id(param.getString("campaign_id"));
        po.setSession_detail(param.toString());
        po.setGlobal_serial_num(param.getInteger("global_serial_num"));
        return po;
    }

    /**
     * 需要更新的字段的po对象(部分高级话路不更新)
     * @param param
     * @return
     */
    private SessionDetail buildTypePo(JSONObject param,SessionDetail po){
        //如果不是外线
        if(!(param.getString("agent_id").toUpperCase().contains("TEL:")||param.getString("agent_id").toUpperCase().contains("SIP:"))){
            po.setLocal_url(param.getString("local_url"));
            po.setRemote_url(param.getString("remote_url"));
            po.setAgent_id(param.getString("agent_id"));
            po.setAgent_name(param.getString("agent_name"));
            po.setAgent_dn(param.getString("agent_dn"));
            po.setSkill_id(param.getString("skill_id"));
            po.setSkill_name(param.getString("skill_name"));
        }
        return po;
    }


    /**
     * 话路结束需要更新的字段的po对象
     * @param param
     * @return
     */
    private SessionDetail buildEndTypePo(JSONObject param,SessionDetail po){

        po.setSession_id(param.getString("session_id"));
        po.setEntId(param.getString("ent_id"));
        po.setEnd_type(param.getString("end_type"));
        po.setStart_time(param.getString("start_time"));
        po.setEnd_time(param.getString("end_time"));
        po.setCall_type(param.getInteger("call_type"));
        po.setRegion_id(param.getString("region_id"));
        po.setOrig_skill_id(param.getString("orig_skill_id"));
        po.setAccept_skill_id(param.getString("accept_skill_id"));

        return po;
    }

    /**
     * 更新无坐席服务po对象字段
     * @param param
     * @return
     */
    private SessionDetail buildTypePoNoAgent(JSONObject param){
        SessionDetail po = new SessionDetail();
        po.setSession_id(param.getString("session_id"));
        po.setEntId(param.getString("ent_id"));
        po.setStart_time(param.getString("start_time"));
        po.setEnd_time(param.getString("end_time"));
        po.setCall_type(param.getInteger("call_type"));
        po.setRegion_id(param.getString("region_id"));
        po.setCampaign_id(param.getString("campaign_id"));
        po.setSession_detail(param.toString());
        po.setGlobal_serial_num(param.getInteger("global_serial_num"));
        po.setOrig_skill_id(param.getString("orig_skill_id"));
        po.setAccept_skill_id(param.getString("accept_skill_id"));
        po.setLocal_url(param.getString("local_url"));
        po.setRemote_url(param.getString("remote_url"));
        po.setLocal_url_wx(param.getString("local_url_wx"));
        po.setRemote_url_wx(param.getString("remote_url_wx"));
        return po;
    }
    /**
     * 更新po对象字段,外线和内呼通话数据（不统计，但是会记录在session_detail字段）
     * @param param
     * @return
     */
    private SessionDetail buildPo(JSONObject param){
        SessionDetail po = new SessionDetail();
        po.setSession_id(param.getString("session_id"));
        po.setEntId(param.getString("ent_id"));
        po.setSession_detail(param.toString());
        po.setGlobal_serial_num(param.getInteger("global_serial_num"));
        return po;
    }
}
