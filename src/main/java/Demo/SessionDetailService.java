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
    public final static String MAKE_CONFERENCE = "9";//�������
    public final static String MAKE_CONSULT = "6";//������ѯ
    public final static String CALLIN = "1";//����
    public final static String CALLOUT = "2";//����
    public final static String CALLOUT_CALERTING = "105";//����ͻ�����
    public final static String ABNORMAL = "-1";//�쳣���ݺ�������
    //����ϯ�������
    public final static String NOAGENT="5"; //����ϯ����call_type����
    public final static String LICENSEOFF="401"; //��Ȩ����
    public final static String LOCALALERTOFF="502"; //�����������
    public final static String REMOTEALERTOFF="503"; //�����������
    public final static String LOCALALLFAIL="402"; //���к���ʧ��
    public final static String REMOTECALLFAIL="403"; //���к���ʧ��
    public final static String LOCALOFF="255"; //���йҶ�
    public final static String REMOTEOFF="254"; //���йҶ�



    public Map<String,SessionDetail> computeSessionDetail(String data) {
        logger.info("����SessionDetailService��computeSessionDetail()����...");
        JSONObject param = JSON.parseObject(data.substring(data.indexOf(":")+1));
        String topic = data.substring(0,data.indexOf(":"));
        Map<String,SessionDetail> result=new HashMap<String, SessionDetail>();
        //���˵��쳣����
        String call_type = param.getString("call_type");
        if(StaticsUtils.CALL_DETAIL.equals(topic)&& StaticsUtils.topicFilterSessionDetail(param)){
            if(NOAGENT.equals(call_type)){
                result.put(topic, this.computeSessionDetailNoAgent(param));
            }else{
                result.put(topic, this.computeSessionDetail(param));
            }
        }else if(StaticsUtils.NEW_R_AGS_E.equals(topic)&&(!ABNORMAL.equals(call_type))){  //call_type=-1�Ĳ�ͳ��    2016-08-22
            result.put(topic, this.computeByNewRAGSE(param));
        }else if(StaticsUtils.SD_CALL_RESULT.equals(topic)){  //�Ⲧ��ؽ������
            result.put(topic, this.computeBySdCallResult(param));
        }

        return result;
    }

    /**
     * �Ⲧ��ص�������ϸ���
     * @param param
     * @return
     */
    private SessionDetail computeBySdCallResult(JSONObject param) {
        logger.info("����SessionDetailService��computeBySdCallResult()����...");
        SessionDetail result = new SessionDetail();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = 0L;
        try {
            Date startTime = sdf.parse(param.getString("begintime"));
            time = startTime.getTime()/1000;//ת�����뼶����call_detail��ʱ�䱣��һ�£�����ͳһ���
        } catch (Exception e) {
            logger.error("ʱ��ת���쳣:"+e.getMessage());
            e.printStackTrace();
        }
        result.setStart_time(String.valueOf(time));
        result.setIvr_time(param.getInteger("ivrtimes"));//�Ⲧ���IVRʱ��
        result.setTransfer_agent_time(param.getInteger("transferagenttime"));//ת��ϯʱ��
        result.setCampaign_id(param.getString("campaiginid"));//�Ⲧ���ʾ
        result.setEntId(param.getString("entid"));
        result.setAlter_time(param.getInteger("altertime"));
        result.setCall_type(3);//�Ⲧ�ĺ�������
        result.setDial_times(param.getInteger("dialtimes"));//�Ⲧ������ʱ��
        result.setAlter_time(param.getInteger("altertime"));//�Ⲧ�Ŀͻ�����ʱ��
        result.setTransfer_altering_time(param.getInteger("transferalteringtime"));//ת��ϯ����ʱ��
        result.setTransfer_queue_time(param.getInteger("transferqueuetime"));//ת��ϯ�Ŷ�ʱ��
        result.setDialNumber(param.getInteger("dialnumber"));
        if(StringUtils.isNotBlank(param.getString("custphone"))){
            result.setRemote_url(param.getString("custphone"));
        }
        //�����ֶε����
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
        logger.info("����SessionDetailService��computeByNewRAGSE()����...");
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
        logger.info("����SessionDetailService��compute106()����...");
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
        logger.info("����SessionDetailService��computeSessionDetailNoAgent()����...");
        SessionDetail result = new SessionDetail();
        int status = Integer.parseInt(param.getString("status"));
        switch (status){
            //��Ȩ��Դ����
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
        logger.info("����SessionDetailService��computeSessionDetail()����...");
        SessionDetail result = new SessionDetail();
//		SessionDetail po = JSONObject.toJavaObject(param, SessionDetail.class);
        int status = Integer.parseInt(param.getString("status"));
        switch (status){
            //IVR
            case 301 :
                result = compute301(param);
                break;
            case 202 ://�˹�����׼�����
                result = compute202(param);
                break;
            case 204 ://session����
                result = compute204(param);
                break;
            case 205 ://sessionͨ��
                result = compute205(param);
                break;
            case 206 ://Session����״̬
                result = compute206(param);
                break;
            case 207 ://session��ѯ
                result = compute207(param);
                break;
            case 208 ://session����
                result = compute208(param);
                break;
            default :
                result = null;
                break;
        }


        return result;
    }

    private SessionDetail compute201NoAgent(JSONObject param){
        logger.info("����SessionDetailService��compute201NoAgent()����...");
        if("401".equals(param.getString("start_type"))&&"401".equals(param.getString("end_type"))){
            SessionDetail po=buildTypePoNoAgent(param);
            po.setEnd_type(LICENSEOFF); //��Ȩ����
            return po;
        }else{
            return null;
        }

    }

    private SessionDetail compute204NoAgent(JSONObject param){
        logger.info("����SessionDetailService��compute204NoAgent()����...");
        SessionDetail po=buildTypePoNoAgent(param);
        String start_type=param.getString("start_type");
        String end_type=param.getString("end_type");
        if("500".equals(start_type)&&"501".equals(end_type)){
            po.setEnd_type(LOCALALLFAIL);//���к���ʧ��
        }else if("1".equals(start_type)){
            po.setAlert_duration_agent(param.getInteger("duration"));//��������ʱ��
            if("501".equals(end_type)){
                po.setEnd_type(LOCALALERTOFF);//��������Ҷ�
            }
        }else if("105".equals(start_type)){
            if("501".equals(end_type)){
                po.setEnd_type(REMOTECALLFAIL);//���к���ʧ��
            }else if("502".equals(end_type)){
                po.setAlert_duration(param.getInteger("duration"));//��������ʱ��
                po.setEnd_type(REMOTEALERTOFF);//��������Ҷ�
            }else if("503".equals(end_type)){
                po.setAlert_duration(param.getInteger("duration"));//��������ʱ��
                po.setEnd_type(LOCALALERTOFF);//��������Ҷ�
            }else{
                po.setAlert_duration(param.getInteger("duration"));//��������ʱ��
            }
        }
        return po;
    }

    private SessionDetail compute205NoAgent(JSONObject param){
        logger.info("����SessionDetailService��compute205NoAgent()����...");
        SessionDetail po=buildTypePoNoAgent(param);
        po.setTalk_duration(param.getInteger("duration")); //ͨ��ʱ��
        if(LOCALOFF.equals(param.getString("end_type"))||REMOTEOFF.equals(param.getString("end_type"))){
            po.setEnd_type(param.getString("end_type"));
        }
        return po;
    }

    private SessionDetail compute301(JSONObject param){
        logger.info("����SessionDetailService��compute301()����...");

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
        logger.info("����SessionDetailService��compute202()����...");

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
        logger.info("����SessionDetailService��compute204()����...");

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
        //����������ж����� �����6��8��29
        if(!param.getString("start_type").equals("6") && !param.getString("start_type").equals("8") && !param.getString("start_type").equals("29")){
            po =buildTypePo(param,po);
        }
        if(param.getString("end_type").equals("501") && !param.getString("start_type").equals("6") && !param.getString("start_type").equals("8") && !param.getString("start_type").equals("29") && param.getString("is_release").equals("1")){
            po = this.buildEndTypePo(param,po);
        }
        return po;
    }

    private SessionDetail compute205(JSONObject param){
        logger.info("����SessionDetailService��compute205()����...");

        String endType = param.getString("end_type");

        //�ж��ǃȺ������M�нyӋ��ֻ���session_detail�ֶ�
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
            //����ǵ�ת��������������Ϊ��ϯ�Ҷ�
            po.setEnd_type(AGENTOFF);
        }
        return po;
    }

    private SessionDetail compute206(JSONObject param){
        logger.info("����SessionDetailService��compute206()����...");

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
        logger.info("����SessionDetailService��compute207()����...");

        String startType = param.getString("start_type");
        String endType = param.getString("end_type");
        //�ж�������
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
        logger.info("����SessionDetailService��compute208()����...");

        String endType = param.getString("end_type");
        //�ж�������
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
     * ����״̬��Ҫ���µ��ֶε�po����
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
     * ��Ҫ���µ��ֶε�po����(���ָ߼���·������)
     * @param param
     * @return
     */
    private SessionDetail buildTypePo(JSONObject param,SessionDetail po){
        //�����������
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
     * ��·������Ҫ���µ��ֶε�po����
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
     * ��������ϯ����po�����ֶ�
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
     * ����po�����ֶ�,���ߺ��ں�ͨ�����ݣ���ͳ�ƣ����ǻ��¼��session_detail�ֶΣ�
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
