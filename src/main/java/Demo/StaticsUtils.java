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
     * 渠道定义
     */
    public static final int ALL = 0;//所有渠道
    public static final int TEL = 1;//电话
    public static final int WEBCHAT = 2;//web im
    public static final int WECHAT = 3;//微信
    public static final int VIDEO = 4;//web 视频
    public static final int APPIM = 5;//app im
    public static final int APP_BUTEL = 6;//app 视频
    public static final int WECHAT_BUTEL = 7;//微信视频
    /**
     *ChatLog的聊天类型
     */
    public static final int AGENT_TYPE = 0;//客户坐席聊天
    public static final int ROBOT_TYPE = 1;//客户机器人聊天
    public static final int CHAT_ANSWER = 1;//聊天库命中
    public static final int WORK_ANSWER = 2;//业务库命中
    public static final int FALSE_ANSWER = 3;//回答失败
    /**
     * 机器人聊天的事件类型
     */
    public static final String ROBOT_TRANS = "robotTransferAgent";//机器人转坐席事件
    public static final String ROBOT_IMSTAR = "robotImStart";//机器人开始事件
    public static final String ROBOT_IMEND  = "robotImEnd";//机器人结束事件
    /**
     * 呼叫类型定义
     */
    public static final int CALL_IN = 0;//呼入
    public static final int CALL_OUT = 1;//呼出
    public static final int SMART_DIAL=3;//外拨
    /**
     * topic标识
     */
    public static final String CALL_DETAIL = "call_detail";//话路流水明细表
    public static final String NEW_R_AGS_E = "new_r_ags_e";//坐席状态表
    public static final String AGENT_STATE_DETAIL = "agentStateDetail";//其他渠道的明细
    public static final String T_SRVAPPRAISE = "t_srvappraise";//满意度topic
    public static final String CHAT_LOG = "chatLog";//其他渠道的消息日志
    public static final String IVR_MESSAGE = "ivr_message";//留言明细渠道
    public static final String SD_CALL_RESULT = "sd_call_result";

    //微信、webchat、视频事件描述定义
    public static final String IM_START = "imStart";//会话开始
    public static final String IM_END = "imEnd";//会话结束
    public static final String BUTEL_ALERTING = "butelAlerting";//视频振铃
    public static final String BUTEL_CONNECTED = "butelConnected";//接通
    public static final String BUTEL_END = "butelEnd";//视频结束
    public static final String QUEUE_START = "queueStart";//排队开始
    public static final String QUEUE_CANCEL = "queueCancel";//排队放弃
    public static final String TRANSFERSEND = "transferSend";//转移
    public static final String BUTEL_ALERTING_ABORT = "butelAlertingAbort";//振铃放弃
    public static final String SATISFY_ANSWERED = "satisfyAnswered";//满意度评价事件
    public static final String TransferSend ="transferSend";//坐席转坐席事件
    public static final String RobotTransferAgent="robotTransferAgent";//机器人转坐席事件
    /**
     * 计算时间戳
     *
     * flag标识不同的表
     * 3表示半小时 4表示小时 5表示天
     *
     * channel标识渠道  话务渠道的时间要乘以1000
     *
     * String
     * 张辰熇
     * 2016年3月7日
     * @throws ParseException
     */
    public static String computeTime(String time,int flag,String channel) {
        //话务渠道的时间乘以1000
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
                //周
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
     * 添加mongo入库的错误信息
     *
     * void
     * 张辰熇
     * 2016年3月16日
     */
    public static void addErrorMessage(MongoCollection collection, String collection_name, JSONObject message, String errorMessage){
        logger.info("入库"+collection_name+"发生错误...失败消息为:"+message);

        Document obj = new Document();
        //操作时间
        obj.put("start_time", System.currentTimeMillis());
        //文档名
        obj.put("collection_name", collection_name);
        //消息名
        obj.put("message", message);
        //默认未处理
        obj.put("is_handled", 0);
        //错误类型
        obj.put("error_type", errorMessage);

        collection.insertOne(obj);

        logger.error("添加了错误信息:"+obj);
    }

    /**
     * 计算其他渠道的时长
     *
     * String
     * 张辰熇
     * 2016年3月16日
     */
    public static Map<String,String> computeDuration(MongoCollection collection, JSONObject param){
        Map<String,String> durations = new HashMap<String,String>();
        String eventType = param.getString("eventType");
        //机器人转坐席事件
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
        //排队开始事件
        if(eventType.equals(QUEUE_START)){
            //筛选条件
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("imSessionId"));

            //更新项
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("queueStart",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

            //排队取消
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
            //视频振铃
            if(StringUtils.isNotBlank(returnObj.getString("butelAlerting"))){
                durations.put("queueDuration", (Long.parseLong(returnObj.getString("butelAlerting"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
            }
        }


        //排队取消事件
        if(eventType.equals(QUEUE_CANCEL)){
            //筛选条件
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("imSessionId"));

            //更新项
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("queueCancel",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

            //排队开始
            if(StringUtils.isNotBlank(returnObj.getString("queueStart"))){
                durations.put("queueDuration", (Long.parseLong(returnObj.getString("queueCancel"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
            }
        }

        //imStart事件
        if(eventType.equals(IM_START)){
            //筛选条件
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("imSessionId"));
            Document updateResult = new Document();
            //BasicDBObject updateResult = new BasicDBObject();
            updateResult.put("sessionId", param.getString("imSessionId"));
            updateResult.put("channel", param.getString("channel"));
            updateResult.put("robotTransTime", param.getString("time"));
            collection.insertOne(updateResult);

            //更新项
            BasicDBObject allCondition = new BasicDBObject();
//			allCondition.append("$set", new BasicDBObject("imStart",param.getString("time")));
            //微信渠道
            if(param.getString("channel").equals(StaticsUtils.WECHAT+"") || param.getString("channel").equals(StaticsUtils.WECHAT_BUTEL+"")){
                allCondition.append("$set", new BasicDBObject("weChatStart",param.getString("time")));

                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.upsert(true);
                options.returnDocument(ReturnDocument.AFTER);
                Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

                //排队开始
                if(StringUtils.isNotBlank(returnObj.getString("queueStart"))){
                    durations.put("queueDuration", (Long.parseLong(returnObj.getString("weChatStart"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
                }


                //imEnd
                if(StringUtils.isNotBlank(returnObj.getString("weChatEnd"))){
                    durations.put("weChatDuration", (Long.parseLong(returnObj.getString("weChatEnd"))-Long.parseLong(returnObj.getString("weChatStart")))/1000l+"");
                }
            }else if(param.getString("channel").equals(StaticsUtils.APPIM+"")){//appim渠道
                allCondition.append("$set", new BasicDBObject("appImChatStart",param.getString("time")));

                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.upsert(true);
                options.returnDocument(ReturnDocument.AFTER);
                Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

                //排队开始
                if(StringUtils.isNotBlank(returnObj.getString("queueStart"))){
                    durations.put("queueDuration", (Long.parseLong(returnObj.getString("appImChatStart"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
                }
                //imEnd
                if(StringUtils.isNotBlank(returnObj.getString("appImChatEnd"))){
                    durations.put("appImDuration", (Long.parseLong(returnObj.getString("appImChatEnd"))-Long.parseLong(returnObj.getString("appImChatStart")))/1000l+"");
                }

            }else{//webChat渠道
                allCondition.append("$set", new BasicDBObject("webChatStart",param.getString("time")));

                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.upsert(true);
                options.returnDocument(ReturnDocument.AFTER);
                Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

                //排队开始
                if(StringUtils.isNotBlank(returnObj.getString("queueStart"))){
                    durations.put("queueDuration", (Long.parseLong(returnObj.getString("webChatStart"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
                }
                //imEnd
                if(StringUtils.isNotBlank(returnObj.getString("webChatEnd"))){
                    durations.put("webChatDuration", (Long.parseLong(returnObj.getString("webChatEnd"))-Long.parseLong(returnObj.getString("webChatStart")))/1000l+"");
                }
            }

        }

        //imEnd事件
        if(eventType.equals(IM_END)){
            //筛选条件
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("imSessionId"));
            //更新项
            BasicDBObject allCondition = new BasicDBObject();
            Document returnObj =null;
            //微信渠道
            if(param.getString("channel").equals(StaticsUtils.WECHAT+"")||param.getString("channel").equals(StaticsUtils.WECHAT_BUTEL+"")){
                allCondition.append("$set", new BasicDBObject("weChatEnd",param.getString("time")));
                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.upsert(true);
                options.returnDocument(ReturnDocument.AFTER);
                returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);
                //文本坐席等待时长
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
                //文本坐席等待时长
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
            //客户第一次发送消息给坐席的时间，，用于计算有效服务量
            durations.put("cusChatFirst", returnObj.getString("cusChatFirst"));

        }

        //振铃开始事件
        if(eventType.equals(BUTEL_ALERTING)){
            //筛选条件
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("butelSessionId"));
            //更新项
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("butelAlerting",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);

            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);
            //排队开始
            if(StringUtils.isNotBlank(returnObj.getString("queueStart"))){
                durations.put("queueDuration", (Long.parseLong(returnObj.getString("butelAlerting"))-Long.parseLong(returnObj.getString("queueStart")))/1000l+"");
            }
            //振铃放弃
            if(StringUtils.isNotBlank(returnObj.getString("butelAlertingAbort"))){
                durations.put("alertDuration", (Long.parseLong(returnObj.getString("butelAlertingAbort"))-Long.parseLong(returnObj.getString("butelAlerting")))/1000l+"");
            }
            //视频接通
            if(StringUtils.isNotBlank(returnObj.getString("butelConnected"))){
                durations.put("alertDuration", (Long.parseLong(returnObj.getString("butelConnected"))-Long.parseLong(returnObj.getString("butelAlerting")))/1000l+"");
            }

        }

        //振铃放弃事件
        if(eventType.equals(BUTEL_ALERTING_ABORT)){
            //筛选条件
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("butelSessionId"));

            //更新项
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("butelAlertingAbort",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

            //振铃开始
            if(StringUtils.isNotBlank(returnObj.getString("butelAlerting"))){
                durations.put("alertDuration", (Long.parseLong(returnObj.getString("butelAlertingAbort"))-Long.parseLong(returnObj.getString("butelAlerting")))/1000l+"");
            }
        }

        //视频连接事件
        if(eventType.equals(BUTEL_CONNECTED)){
            //筛选条件
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("butelSessionId"));

            //更新项
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("butelConnected",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);

            //振铃开始
            if(StringUtils.isNotBlank(returnObj.getString("butelAlerting"))){
                durations.put("alertDuration", (Long.parseLong(returnObj.getString("butelConnected"))-Long.parseLong(returnObj.getString("butelAlerting")))/1000l+"");
            }
            //视频结束
            if(StringUtils.isNotBlank(returnObj.getString("butelEnd"))){
                durations.put("butelChatDuration", (Long.parseLong(returnObj.getString("butelEnd"))-Long.parseLong(returnObj.getString("butelConnected")))/1000l+"");
            }
        }

        //视频结束事件
        if(eventType.equals(BUTEL_END)){
            //筛选条件
            BasicDBObject updateCondition=new BasicDBObject();
            updateCondition.put("sessionId", param.getString("butelSessionId"));

            //更新项
            BasicDBObject allCondition = new BasicDBObject();
            allCondition.append("$set", new BasicDBObject("butelEnd",param.getString("time")));
            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.upsert(true);
            options.returnDocument(ReturnDocument.AFTER);
            Document returnObj = (Document)collection.findOneAndUpdate(updateCondition, allCondition, options);
            //视频连接
            if(StringUtils.isNotBlank(returnObj.getString("butelConnected"))){
                durations.put("butelChatDuration", (Long.parseLong(returnObj.getString("butelEnd"))-Long.parseLong(returnObj.getString("butelConnected")))/1000l+"");
            }
        }
        return durations;
    }

    /**
     * 计算其他渠道的时长
     *
     * String
     * 张辰熇
     * 2016年3月16日
     */
    public static void inserIMCusMsg(MongoCollection collection,JSONObject param){

        BasicDBObject updateCondition=new BasicDBObject();
        updateCondition.put("sessionId", param.getString("imSessionId"));
        FindIterable<Document> findObj=collection.find(updateCondition);

        //没查到数据
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
                logger.debug("当前im["+param.getString("imSessionId")+"]的最早客户发送消息给坐席时间："+param.getString("time"));
                setObject.append("cusChatFirst", param.getString("time"));
                allCondition.put("$set", setObject);
                collection.findOneAndUpdate(updateCondition, allCondition);
            }
        }
    }
    /**
     * 计算文本渠道坐席最早和客户聊天的信息
     *
     * String
     * chenjm
     * 2017年11月01日
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
                logger.debug("当前im["+param.getString("imSessionId")+"]的最早坐席发送消息给坐席时间："+param.getString("time"));
                setObject.append("agentChatFirst", param.getString("time"));
                allCondition.put("$set", setObject);
                collection.findOneAndUpdate(updateCondition, allCondition);
            }
        }
    }

    /**
     * 坐席登录时间没有渠道 所以过滤掉
     * butelAlerting前面的imStart事件也被过滤了
     * boolean
     * 张辰熇
     * 2016年3月19日
     */
    public static boolean topicFilter(JSONObject param){
        //过滤其他渠道数据坐席登陆登出 butel登陆登出 渠道是4的imStart imEnd事件
        String eventType = param.getString("eventType");
        if(StringUtils.isNotBlank(eventType)){
            if(eventType.equals("chatLogin") || eventType.endsWith("butelLogin") || eventType.equals("chatLogout") || eventType.equals("butelLogout") || (eventType.equals("imStart") && param.getString("channel").equals(VIDEO+"")) || (eventType.equals("imEnd") && param.getString("channel").equals(VIDEO+""))){
                return false;
            }else{
                return true;
            }
        }
        //过滤call_type=-1的数据
        String callType = param.getString("call_type");
        if(StringUtils.isNotBlank(callType)){
            if(callType.equals("-1")){
                return false;
            }else{
                return true;
            }
        }
        //坐席间的内呼需要过滤
        if("205".equals(param.getString("status"))&&"13".equals(param.getString("start_type"))){
            return false;
        }
        return true;
    }
    /**
     * 坐席登录时间没有渠道 所以过滤掉
     * butelAlerting前面的imStart事件也被过滤了
     * boolean
     * session_detail的话路不过滤坐席间内呼数据，其他统计需过滤
     * 孙尧
     * 2017年3月1日
     */
    public static boolean topicFilterSessionDetail(JSONObject param){
        //过滤其他渠道数据坐席登陆登出 butel登陆登出 渠道是4的imStart imEnd事件
        String eventType = param.getString("eventType");
        if(StringUtils.isNotBlank(eventType)){
            if(eventType.equals("chatLogin") || eventType.endsWith("butelLogin") || eventType.equals("chatLogout") || eventType.equals("butelLogout") || (eventType.equals("imStart") && param.getString("channel").equals(VIDEO+"")) || (eventType.equals("imEnd") && param.getString("channel").equals(VIDEO+""))){
                return false;
            }else{
                return true;
            }
        }
        //过滤call_type=-1的数据
        String callType = param.getString("call_type");
        if(StringUtils.isNotBlank(callType)){
            if(callType.equals("-1")){
                return false;
            }else{
                return true;
            }
        }
        //坐席间的内呼需要过滤
//		if("205".equals(param.getString("status"))&&"13".equals(param.getString("start_type"))){
//			return false;
//		}
        return true;
    }
    /**
     * 判断是否为外线的事件
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
     * 204振铃事件的
     */
    public static Map<String,Integer> insertAT_TL_OB_RN(MongoCollection collection,JSONObject param,int timeLevel){
        Map<String,Integer> durations = new HashMap<String,Integer>();
        BasicDBObject updateCondition=new BasicDBObject();
        updateCondition.put("session_id", param.getString("session_id"));
        updateCondition.put("item",timeLevel);
        //更新项
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
     * 得到给定日期所在周的周一
     * @param nowDate
     * @return
     */
    public static Date getMonday(Date nowDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        System.out.println("要计算日期为:" + simpleDateFormat.format(calendar.getTime())); // 输出要计算日期
        calendar.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = calendar.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date monday = calendar.getTime();
        String imptimeBegin = simpleDateFormat.format(monday);
        //System.out.println("所在周星期一的日期：" + imptimeBegin);
        return monday;
    }

    /**
     * 得到月的第一天
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
