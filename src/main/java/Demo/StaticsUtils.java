package Demo;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bson.BasicBSONObject;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bson.BasicBSONObject;
import org.bson.Document;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;


public class StaticsUtils {

    private static Logger logger = Logger.getLogger(StaticsUtils.class);

    /**
     * ��������
     */
    public static final int ALL = 0;//��������
    public static final int TEL = 1;//�绰
    public static final int WEBCHAT = 2;//web im
    public static final int WECHAT = 3;//΢��
    public static final int VIDEO = 4;//web ��Ƶ
    public static final int APPIM = 5;//app im
    public static final int APP_BUTEL = 6;//app ��Ƶ
    public static final int WECHAT_BUTEL = 7;//΢����Ƶ
    /**
     *ChatLog����������
     */
    public static final int AGENT_TYPE = 0;//�ͻ���ϯ����
    public static final int ROBOT_TYPE = 1;//�ͻ�����������
    public static final int CHAT_ANSWER = 1;//���������
    public static final int WORK_ANSWER = 2;//ҵ�������
    public static final int FALSE_ANSWER = 3;//�ش�ʧ��
    /**
     * ������������¼�����
     */
    public static final String ROBOT_TRANS = "robotTransferAgent";//������ת��ϯ�¼�
    public static final String ROBOT_IMSTAR = "robotImStart";//�����˿�ʼ�¼�
    public static final String ROBOT_IMEND  = "robotImEnd";//�����˽����¼�
    /**
     * �������Ͷ���
     */
    public static final int CALL_IN = 0;//����
    public static final int CALL_OUT = 1;//����
    public static final int SMART_DIAL=3;//�Ⲧ
    /**
     * topic��ʶ
     */
    public static final String CALL_DETAIL = "call_detail";//��·��ˮ��ϸ��
    public static final String NEW_R_AGS_E = "new_r_ags_e";//��ϯ״̬��
    public static final String AGENT_STATE_DETAIL = "agentStateDetail";//������������ϸ
    public static final String T_SRVAPPRAISE = "t_srvappraise";//�����topic
    public static final String CHAT_LOG = "chatLog";//������������Ϣ��־
    public static final String IVR_MESSAGE = "ivr_message";//������ϸ����
    public static final String SD_CALL_RESULT = "sd_call_result";

    //΢�š�webchat����Ƶ�¼���������
    public static final String IM_START = "imStart";//�Ự��ʼ
    public static final String IM_END = "imEnd";//�Ự����
    public static final String BUTEL_ALERTING = "butelAlerting";//��Ƶ����
    public static final String BUTEL_CONNECTED = "butelConnected";//��ͨ
    public static final String BUTEL_END = "butelEnd";//��Ƶ����
    public static final String QUEUE_START = "queueStart";//�Ŷӿ�ʼ
    public static final String QUEUE_CANCEL = "queueCancel";//�Ŷӷ���
    public static final String TRANSFERSEND = "transferSend";//ת��
    public static final String BUTEL_ALERTING_ABORT = "butelAlertingAbort";//�������
    public static final String SATISFY_ANSWERED = "satisfyAnswered";//����������¼�
    public static final String TransferSend ="transferSend";//��ϯת��ϯ�¼�
    public static final String RobotTransferAgent="robotTransferAgent";//������ת��ϯ�¼�
    /**
     * ����ʱ���
     *
     * flag��ʶ��ͬ�ı�
     * 3��ʾ��Сʱ 4��ʾСʱ 5��ʾ��
     *
     * channel��ʶ����  ����������ʱ��Ҫ����1000
     *
     * String
     * �ų���
     * 2016��3��7��
     * @throws ParseException
     */
    public static String computeTime(String time,int flag,String channel) {
        //����������ʱ�����1000
        long inputTime = Long.parseLong(time);
        if(channel.equals(StaticsUtils.TEL+"")){
            inputTime = inputTime * 1000l;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = sdf.format(new Date(inputTime));

        String result = "";

        try {
            if(flag == 3){
                if(Integer.parseInt(timeStr.substring(14,16)) < 30){
                    result = sdf.parse(timeStr.substring(0,14)+"00:00").getTime()+"";
                }else{
                    result = sdf.parse(timeStr.substring(0,14)+"30:00").getTime()+"";
                }
            }else{
                if(flag == 4){
                    result = sdf.parse(timeStr.substring(0,14)+"00:00").getTime()+"";
                }
                if(flag == 5){
                    result = new SimpleDateFormat("yyyy-MM-dd").parse(timeStr.substring(0,10)).getTime()+"";
                }
                //��
                if(flag == 6){
                    result=getMonday(new Date(inputTime)).getTime()+"";
                }
                if(flag == 7){
                    result = getFirstOfMonth(new Date(inputTime)).getTime()+"";
                }
            }
        } catch (ParseException e) {
            logger.error(e);
        }
        return result;
    }

	/*public static String replaceString(String input){
		return input.replace("'", "\"").replace("u\"", "\"");
	}*/


    /**
     * ���mongo���Ĵ�����Ϣ
     *
     * void
     * �ų���
     * 2016��3��16��
     */
    public static void addErrorMessage(MongoCollection collection, String collection_name, JSONObject message, String errorMessage){
        logger.info("���"+collection_name+"��������...ʧ����ϢΪ:"+message);

        Document obj = new Document();
        //����ʱ��
        obj.put("start_time", System.currentTimeMillis());
        //�ĵ���
        obj.put("collection_name", collection_name);
        //��Ϣ��
        obj.put("message", message);
        //Ĭ��δ����
        obj.put("is_handled", 0);
        //��������
        obj.put("error_type", errorMessage);

        collection.insertOne(obj);

        logger.error("����˴�����Ϣ:"+obj);
    }

    /**
     * ��������������ʱ��
     *
     * String
     * �ų���
     * 2016��3��16��
     */
    public static Map<String,String> computeDuration(MongoCollection collection, JSONObject param){
        Map<String,String> durations = new HashMap<String,String>();
        String eventType = param.getString("eventType");
        //������ת��ϯ�¼�
        if(eventType.equals(ROBOT_TRANS)){
            BasicDBObject updateCondition = new BasicDBObject();
            updateCondition.put("sessionId", param.getString("imSessionId"));

            BasicDBObject allCondition = new BasicDBObject();
            BasicDBObject updateResult = new BasicDBObject();
            updateResult.put("robotTransTime", param.getString("time"));
            updateResult.put("channel", param.getString("channel"));
            updateResult.put("eventType", param.getString("eventType"));
            allCondition.append("$set", updateResult);
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            collection.findOneAndUpdate(updateCondition, allCondition, options);
        }
        //�Ŷӿ�ʼ�¼�
        if(eventType.equals(QUEUE_START)){
            //ɸѡ����
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("imSessionId"));

            //������
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("queueStart",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

            //�Ŷ�ȡ��
            if(StringUtils.isNotBlank(returnObj.getString("queueCancel"))){
                durations.put("queueDuration", (Long.parseLong(returnObj.getString("queueCancel"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+""+"");
            }
            //weChatStart
            if(StringUtils.isNotBlank(returnObj.getString("weChatStart"))){
                durations.put("queueDuration", (Long.parseLong(returnObj.getString("weChatStart"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
            }
            //webChatStart
            if(StringUtils.isNotBlank(returnObj.getString("appImChatStart"))){
                durations.put("queueDuration", (Long.parseLong(returnObj.getString("appImChatStart"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
            }
            //��Ƶ����
            if(StringUtils.isNotBlank(returnObj.getString("butelAlerting"))){
                durations.put("queueDuration", (Long.parseLong(returnObj.getString("butelAlerting"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
            }
        }


        //�Ŷ�ȡ���¼�
        if(eventType.equals(QUEUE_CANCEL)){
            //ɸѡ����
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("imSessionId"));

            //������
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("queueCancel",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

            //�Ŷӿ�ʼ
            if(StringUtils.isNotBlank(returnObj.getString("queueStart"))){
                durations.put("queueDuration", (Long.parseLong(returnObj.getString("queueCancel"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
            }
        }

        //imStart�¼�
        if(eventType.equals(IM_START)){
            //ɸѡ����
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("imSessionId"));
            Document updateResult = new Document();
            //BasicDBObject updateResult = new BasicDBObject();
            updateResult.put("sessionId", param.getString("imSessionId"));
            updateResult.put("channel", param.getString("channel"));
            updateResult.put("robotTransTime", param.getString("time"));
            collection.insertOne(updateResult);

            //������
            BasicDBObject allCondition = new BasicDBObject();
//			allCondition.append("$set", new BasicDBObject("imStart",param.getString("time")));
            //΢������
            if(param.getString("channel").equals(StaticsUtils.WECHAT+"") || param.getString("channel").equals(StaticsUtils.WECHAT_BUTEL+"")){
                allCondition.append("$set", new BasicDBObject("weChatStart",param.getString("time")));

                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.upsert(true);
                options.returnDocument(ReturnDocument.AFTER);
                Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

                //�Ŷӿ�ʼ
                if(StringUtils.isNotBlank(returnObj.getString("queueStart"))){
                    durations.put("queueDuration", (Long.parseLong(returnObj.getString("weChatStart"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
                }


                //imEnd
                if(StringUtils.isNotBlank(returnObj.getString("weChatEnd"))){
                    durations.put("weChatDuration", (Long.parseLong(returnObj.getString("weChatEnd"))-Long.parseLong(returnObj.getString("weChatStart")))/1000l+"");
                }
            }else if(param.getString("channel").equals(StaticsUtils.APPIM+"")){//appim����
                allCondition.append("$set", new BasicDBObject("appImChatStart",param.getString("time")));

                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.upsert(true);
                options.returnDocument(ReturnDocument.AFTER);
                Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

                //�Ŷӿ�ʼ
                if(StringUtils.isNotBlank(returnObj.getString("queueStart"))){
                    durations.put("queueDuration", (Long.parseLong(returnObj.getString("appImChatStart"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
                }
                //imEnd
                if(StringUtils.isNotBlank(returnObj.getString("appImChatEnd"))){
                    durations.put("appImDuration", (Long.parseLong(returnObj.getString("appImChatEnd"))-Long.parseLong(returnObj.getString("appImChatStart")))/1000l+"");
                }

            }else{//webChat����
                allCondition.append("$set", new BasicDBObject("webChatStart",param.getString("time")));

                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.upsert(true);
                options.returnDocument(ReturnDocument.AFTER);
                Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

                //�Ŷӿ�ʼ
                if(StringUtils.isNotBlank(returnObj.getString("queueStart"))){
                    durations.put("queueDuration", (Long.parseLong(returnObj.getString("webChatStart"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
                }
                //imEnd
                if(StringUtils.isNotBlank(returnObj.getString("webChatEnd"))){
                    durations.put("webChatDuration", (Long.parseLong(returnObj.getString("webChatEnd"))-Long.parseLong(returnObj.getString("webChatStart")))/1000l+"");
                }
            }

        }

        //imEnd�¼�
        if(eventType.equals(IM_END)){
            //ɸѡ����
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("imSessionId"));
            //������
            BasicDBObject allCondition = new BasicDBObject();
            Document returnObj =null;
            //΢������
            if(param.getString("channel").equals(StaticsUtils.WECHAT+"")||param.getString("channel").equals(StaticsUtils.WECHAT_BUTEL+"")){
                allCondition.append("$set", new BasicDBObject("weChatEnd",param.getString("time")));
                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.upsert(true);
                options.returnDocument(ReturnDocument.AFTER);
                returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);
                //�ı���ϯ�ȴ�ʱ��
                if(StringUtils.isNotBlank(returnObj.getString("agentChatFirst")) && StringUtils.isNotBlank(returnObj.getString("cusChaFirst"))){
                    durations.put("weChatWaitDuration", (Long.parseLong(returnObj.getString("agentChatFirst"))-Long.parseLong(returnObj.getString("cusChaFirst")))/1000l+"");
                }
                //imstart
                if(StringUtils.isNotBlank(returnObj.getString("weChatStart"))){
                    durations.put("weChatDuration", (Long.parseLong(returnObj.getString("weChatEnd"))-Long.parseLong(returnObj.getString("weChatStart")))/1000l+"");
                    durations.put("weChatProDuration", (Long.parseLong(returnObj.getString("weChatEnd"))-Long.parseLong(returnObj.getString("robotTransTime")))/1000l+"");

                }
            }else if(param.getString("channel").equals(StaticsUtils.APPIM+"")){
                allCondition.append("$set", new BasicDBObject("appImChatEnd",param.getString("time")));
                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.upsert(true);
                options.returnDocument(ReturnDocument.AFTER);
                returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);
                //�ı���ϯ�ȴ�ʱ��
                if(StringUtils.isNotBlank(returnObj.getString("agentChatFirst")) && StringUtils.isNotBlank(returnObj.getString("cusChaFirst"))){
                    durations.put("appImWaitDuration", (Long.parseLong(returnObj.getString("agentChatFirst"))-Long.parseLong(returnObj.getString("cusChaFirst")))/1000l+"");
                }
                //imstart
                if(StringUtils.isNotBlank(returnObj.getString("appImChatStart"))){
                    durations.put("appImDuration", (Long.parseLong(returnObj.getString("appImChatEnd"))-Long.parseLong(returnObj.getString("appImChatStart")))/1000l+"");
                    durations.put("appImProDuration", (Long.parseLong(returnObj.getString("appImChatEnd"))-Long.parseLong(returnObj.getString("robotTransTime")))/1000l+"");
                }
            } else{
                allCondition.append("$set", new BasicDBObject("webChatEnd",param.getString("time")));
                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.upsert(true);
                options.returnDocument(ReturnDocument.AFTER);
                returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

                if(StringUtils.isNotBlank(returnObj.getString("agentChatFirst")) && StringUtils.isNotBlank(returnObj.getString("cusChaFirst"))){
                    durations.put("webChatWaitDuration", (Long.parseLong(returnObj.getString("agentChatFirst"))-Long.parseLong(returnObj.getString("cusChaFirst")))/1000l+"");
                }
                //imstart
                if(StringUtils.isNotBlank(returnObj.getString("webChatStart"))){
                    durations.put("webChatDuration", (Long.parseLong(returnObj.getString("webChatEnd"))-Long.parseLong(returnObj.getString("webChatStart")))/1000l+"");
                    durations.put("webChatProDuration", (Long.parseLong(returnObj.getString("webChatEnd"))-Long.parseLong(returnObj.getString("robotTransTime")))/1000l+"");				}
            }
            //�ͻ���һ�η�����Ϣ����ϯ��ʱ�䣬�����ڼ�����Ч������
            durations.put("cusChatFirst", returnObj.getString("cusChatFirst"));

        }

        //���忪ʼ�¼�
        if(eventType.equals(BUTEL_ALERTING)){
            //ɸѡ����
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("butelSessionId"));
            //������
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("butelAlerting",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);

            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);
            //�Ŷӿ�ʼ
            if(StringUtils.isNotBlank(returnObj.getString("queueStart"))){
                durations.put("queueDuration", (Long.parseLong(returnObj.getString("butelAlerting"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
            }
            //�������
            if(StringUtils.isNotBlank(returnObj.getString("butelAlertingAbort"))){
                durations.put("alertDuration", (Long.parseLong(returnObj.getString("butelAlertingAbort"))-Long.parseLong(returnObj.getString("butelAlerting")))/1000l+"");
            }
            //��Ƶ��ͨ
            if(StringUtils.isNotBlank(returnObj.getString("butelConnected"))){
                durations.put("alertDuration", (Long.parseLong(returnObj.getString("butelConnected"))-Long.parseLong(returnObj.getString("butelAlerting")))/1000l+"");
            }

        }

        //��������¼�
        if(eventType.equals(BUTEL_ALERTING_ABORT)){
            //ɸѡ����
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("butelSessionId"));

            //������
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("butelAlertingAbort",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

            //���忪ʼ
            if(StringUtils.isNotBlank(returnObj.getString("butelAlerting"))){
                durations.put("alertDuration", (Long.parseLong(returnObj.getString("butelAlertingAbort"))-Long.parseLong(returnObj.getString("butelAlerting")))/1000l+"");
            }
        }

        //��Ƶ�����¼�
        if(eventType.equals(BUTEL_CONNECTED)){
            //ɸѡ����
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("butelSessionId"));

            //������
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("butelConnected",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

            //���忪ʼ
            if(StringUtils.isNotBlank(returnObj.getString("butelAlerting"))){
                durations.put("alertDuration", (Long.parseLong(returnObj.getString("butelConnected"))-Long.parseLong(returnObj.getString("butelAlerting")))/1000l+"");
            }
            //��Ƶ����
            if(StringUtils.isNotBlank(returnObj.getString("butelEnd"))){
                durations.put("butelChatDuration", (Long.parseLong(returnObj.getString("butelEnd"))-Long.parseLong(returnObj.getString("butelConnected")))/1000l+"");
            }
        }

        //��Ƶ�����¼�
        if(eventType.equals(BUTEL_END)){
            //ɸѡ����
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("butelSessionId"));

            //������
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("butelEnd",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);
            //��Ƶ����
            if(StringUtils.isNotBlank(returnObj.getString("butelConnected"))){
                durations.put("butelChatDuration", (Long.parseLong(returnObj.getString("butelEnd"))-Long.parseLong(returnObj.getString("butelConnected")))/1000l+"");
            }
        }
        return durations;
    }

    /**
     * ��������������ʱ��
     *
     * String
     * �ų���
     * 2016��3��16��
     */
    public static void inserIMCusMsg(MongoCollection collection,JSONObject param){

        BasicDBObject updateCondition=new BasicDBObject();
        updateCondition.put("sessionId", param.getString("imSessionId"));
        FindIterable<Document> findObj=collection.find(updateCondition);

        //û�鵽����
        if(!findObj.iterator().hasNext()){
            //BasicDBObject insertObj = new BasicDBObject();
            Document insertObj = new Document();
            insertObj.put("cusChatFirst",param.getString("time"));
            insertObj.put("sessionId", param.getString("imSessionId"));
            insertObj.put("channel",param.getString("channel"));


            collection.insertOne(insertObj);
        }else{
            MongoCursor<Document> cursor = findObj.iterator();
            Document existPo = cursor.next();
            BasicDBObject allCondition=new BasicDBObject();
            BasicDBObject setObject=new BasicDBObject();
            if(existPo.getString("cusChatFirst")==null || existPo.getString("cusChatFirst").compareTo(param.getString("time"))>0){
                logger.debug("��ǰim["+param.getString("imSessionId")+"]������ͻ�������Ϣ����ϯʱ�䣺"+param.getString("time"));
                setObject.append("cusChatFirst", param.getString("time"));
                allCondition.put("$set", setObject);
                collection.findOneAndUpdate(updateCondition, allCondition);
            }
        }
    }
    /**
     * �����ı�������ϯ����Ϳͻ��������Ϣ
     *
     * String
     * chenjm
     * 2017��11��01��
     */
    public static void inserIMAgentMsg(MongoCollection collection,JSONObject param){

        BasicDBObject updateCondition=new BasicDBObject();
        updateCondition.put("sessionId", param.getString("imSessionId"));
        FindIterable<Document> findObj=collection.find(updateCondition);
        MongoCursor<Document> cursor = findObj.iterator();
        Document existPo = cursor.next();
        if((existPo.getString("cusChatFirst") != null) && (param.getString("time").compareTo(existPo.getString("cusChatFirst")))>0 ) {
            if(existPo.getString("agentChatFirst") == null){//cusChatFirst
                BasicDBObject insertObj = new BasicDBObject();
                insertObj.put("agentChatFirst",param.getString("time"));
                insertObj.put("sessionId", param.getString("imSessionId"));
                insertObj.put("channel",param.getString("channel"));
                collection.insertOne(insertObj);
            }
            if(existPo.getString("agentChatFirst") !=null && existPo.getString("agentChatFirst").compareTo(param.getString("time"))>0){
                BasicDBObject allCondition=new BasicDBObject();
                BasicDBObject setObject=new BasicDBObject();
                logger.debug("��ǰim["+param.getString("imSessionId")+"]��������ϯ������Ϣ����ϯʱ�䣺"+param.getString("time"));
                setObject.append("agentChatFirst", param.getString("time"));
                allCondition.put("$set", setObject);
                collection.findOneAndUpdate(updateCondition, allCondition);
            }
        }
    }

    /**
     * ��ϯ��¼ʱ��û������ ���Թ��˵�
     * butelAlertingǰ���imStart�¼�Ҳ��������
     * boolean
     * �ų���
     * 2016��3��19��
     */
    public static boolean topicFilter(JSONObject param){
        //������������������ϯ��½�ǳ� butel��½�ǳ� ������4��imStart imEnd�¼�
        String eventType = param.getString("eventType");
        if(StringUtils.isNotBlank(eventType)){
            if(eventType.equals("chatLogin") || eventType.endsWith("butelLogin") || eventType.equals("chatLogout") || eventType.equals("butelLogout") || (eventType.equals("imStart") && param.getString("channel").equals(VIDEO+"")) || (eventType.equals("imEnd") && param.getString("channel").equals(VIDEO+""))){
                return false;
            }else{
                return true;
            }
        }
        //����call_type=-1������
        String callType = param.getString("call_type");
        if(StringUtils.isNotBlank(callType)){
            if(callType.equals("-1")){
                return false;
            }else{
                return true;
            }
        }
        //��ϯ����ں���Ҫ����
        if("205".equals(param.getString("status"))&&"13".equals(param.getString("start_type"))){
            return false;
        }
        return true;
    }
    /**
     * ��ϯ��¼ʱ��û������ ���Թ��˵�
     * butelAlertingǰ���imStart�¼�Ҳ��������
     * boolean
     * session_detail�Ļ�·��������ϯ���ں����ݣ�����ͳ�������
     * ��Ң
     * 2017��3��1��
     */
    public static boolean topicFilterSessionDetail(JSONObject param){
        //������������������ϯ��½�ǳ� butel��½�ǳ� ������4��imStart imEnd�¼�
        String eventType = param.getString("eventType");
        if(StringUtils.isNotBlank(eventType)){
            if(eventType.equals("chatLogin") || eventType.endsWith("butelLogin") || eventType.equals("chatLogout") || eventType.equals("butelLogout") || (eventType.equals("imStart") && param.getString("channel").equals(VIDEO+"")) || (eventType.equals("imEnd") && param.getString("channel").equals(VIDEO+""))){
                return false;
            }else{
                return true;
            }
        }
        //����call_type=-1������
        String callType = param.getString("call_type");
        if(StringUtils.isNotBlank(callType)){
            if(callType.equals("-1")){
                return false;
            }else{
                return true;
            }
        }
        //��ϯ����ں���Ҫ����
//		if("205".equals(param.getString("status"))&&"13".equals(param.getString("start_type"))){
//			return false;
//		}
        return true;
    }
    /**
     * �ж��Ƿ�Ϊ���ߵ��¼�
     * @param param
     * @return
     */
    public static boolean telFilter(JSONObject param){
        String agentId=param.getString("agent_id").toUpperCase();
        if(agentId.contains("TEL:")||agentId.contains("SIP:")){
            return false;
        }
        return true;
    }


    /**
     * 204�����¼���
     */
    public static Map<String,Integer> insertAT_TL_OB_RN(MongoCollection collection,JSONObject param,int timeLevel){
        Map<String,Integer> durations = new HashMap<String,Integer>();
        BasicDBObject updateCondition=new BasicDBObject();
        updateCondition.put("session_id", param.getString("session_id"));
        updateCondition.put("item",timeLevel);
        //������
        BasicDBObject allCondition = new BasicDBObject();
        if("204".equals(param.getString("status"))){
            allCondition.append("$inc", new BasicBSONObject("at_suc_ob_rn",param.getInteger("duration")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);
            if((returnObj.getInteger("at_suc_ob_ab") != null) &&(returnObj.getInteger("at_suc_ob_ab")>0)){
                durations.put("at_suc_ob_rn",returnObj.getInteger("at_suc_ob_rn"));
            }
        }else if ("205".equals(param.getString("status"))){
            allCondition.append("$inc", new BasicBSONObject("at_suc_ob_an",param.getInteger("duration")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document  returnObj= (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);
            if((returnObj.getInteger("at_suc_ob_rn") != null) && (returnObj.getInteger("at_suc_ob_rn")>0)){
                durations.put("at_suc_ob_rn", returnObj.getInteger("at_suc_ob_rn"));
            }
        }
        return durations;
    }
    /**
     * �õ��������������ܵ���һ
     * @param nowDate
     * @return
     */
    public static Date getMonday(Date nowDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        // �ж�Ҫ����������Ƿ������գ���������һ����������ģ����������⣬���㵽��һ��ȥ��
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);// ��õ�ǰ������һ�����ڵĵڼ���
        if (1 == dayWeek) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        System.out.println("Ҫ��������Ϊ:" + simpleDateFormat.format(calendar.getTime())); // ���Ҫ��������
        calendar.setFirstDayOfWeek(Calendar.MONDAY);// ����һ�����ڵĵ�һ�죬���й���ϰ��һ�����ڵĵ�һ��������һ
        int day = calendar.get(Calendar.DAY_OF_WEEK);// ��õ�ǰ������һ�����ڵĵڼ���
        calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);// ���������Ĺ��򣬸���ǰ���ڼ�ȥ���ڼ���һ�����ڵ�һ��Ĳ�ֵ
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date monday = calendar.getTime();
        String imptimeBegin = simpleDateFormat.format(monday);
        //System.out.println("����������һ�����ڣ�" + imptimeBegin);
        return monday;
    }

    /**
     * �õ��µĵ�һ��
     * @param date
     * @return
     */
    public static Date getFirstOfMonth(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstDay=calendar.getTime();
        String firstDayOfMonth = simpleDateFormat.format(firstDay);
        logger.info(firstDayOfMonth);
        return firstDay;
    }
}
