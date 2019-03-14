package Test;

import Demo.DcmsLoginUser;
import Demo.SessionDetail;
import Demo.SessionDetailService;

import backtype.storm.tuple.Values;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.SchemaOutputResolver;
import java.util.*;

public class BitTest {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		 Set<String> topicSet = new HashSet<String>();
		topicSet.add("session_detail");
		topicSet.add("agentProxy");
		System.out.println("==============topicSet:"+topicSet.toString());
		System.out.println("==============topicSet.size:"+topicSet.size());


		//A*2^n
		System.out.println(9<<4);
		//�Ƿ�����ż��  ������ �������λһ����1
		System.out.println(1&1);
		System.out.println(2313124&1);
	    //����������  ��� ==(��������ֻ����0����1,��ͬȡ0��ͬȡ1,��ȫ��ԭʼ����һ��)
		int a=4;
		int b=3;
		System.out.println(a^0);
		System.out.println(a^1);
		System.out.println(a^b);
		System.out.println(b^a);
        a=a^b;
        b=a^b;
        a=a^b;
		System.out.println(a);
		System.out.println(b);
		System.out.println(a^-1);
		System.out.println(b^-1);
		System.out.println(Integer.toBinaryString(3));
		System.out.println(Integer.toBinaryString(-3));
		System.out.println(Integer.toBinaryString(-2));
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(-0));
		System.out.println(Integer.toBinaryString(1));
        //ȡ����ֵ (a^(a>>31))-(a>>31)
        int c=2;
        int d=-4;
        System.out.println(c>>31);//����
        System.out.println(d>>31);//����
        
        System.out.println(c^(c>>31));//0����
        System.out.println(d^(d>>31));//-1�������

        System.out.println(c^(c>>31)-(c>>31));//0����
        System.out.println((d^(d>>31))-(d>>31));//-1�������


	/*	String  aa = "";


		System.out.println("=======****======"+aa.replace("ְ��",""));
		System.out.println(aa.trim());

		aa="�Ϸ�ְ��";
		if(aa.contains("ְ��")){
			System.out.println("============="+aa.replace("ְ��",""));
		}
*/


		List<DcmsLoginUser> dcmsLoginUserList = new ArrayList<>();
		String serviceLineSkillName="";

		dcmsLoginUserList.add(new DcmsLoginUser("����","�Ϻ�  "));
		dcmsLoginUserList.add(new DcmsLoginUser("���",""));
/*
		dcmsLoginUserList.add(new DcmsLoginUser("���",""));

		dcmsLoginUserList.add(new DcmsLoginUser("����",""));

		dcmsLoginUserList.add(new DcmsLoginUser("���","�Ϻ�"));

		dcmsLoginUserList.add(new DcmsLoginUser("����","�Ϻ�"));

		dcmsLoginUserList.add(new DcmsLoginUser("����","����"));

		dcmsLoginUserList.add(new DcmsLoginUser("����","�Ϸ�"));

		dcmsLoginUserList.add(new DcmsLoginUser("����","����ְ��"));

		dcmsLoginUserList.add(new DcmsLoginUser("����","�Ϸ�ְ��"));*/


		try{
			if(dcmsLoginUserList.size()!=0){
				for(int i=0;i<dcmsLoginUserList.size();i++){
					System.out.println("i:================");
					System.out.println("i:"+i);
					System.out.println(dcmsLoginUserList.size()-1);
					System.out.println("size:"+dcmsLoginUserList.size());

					String serviceLine = dcmsLoginUserList.get(i).getServiceLine();
					String branchCenter = dcmsLoginUserList.get(i).getBranchCenter();

					if(StringUtils.isNotBlank(serviceLine)&&"����".equals(serviceLine)){
						if(StringUtils.isNotBlank(branchCenter)&&branchCenter.contains("ְ��")){
							serviceLineSkillName+="^��_"+branchCenter.replace("ְ��","");
						}else{
							if(StringUtils.isNotBlank(branchCenter)){
								serviceLineSkillName+="^��_"+branchCenter.trim();
							}else{
								serviceLineSkillName+="^��_";
							}
						}
					}
					if(StringUtils.isNotBlank(serviceLine)&&"����".equals(serviceLine)){
						serviceLineSkillName+="^��_";
					}
					if(StringUtils.isNotBlank(serviceLine)&&"���".equals(serviceLine)){
						serviceLineSkillName+="^��_";
					}
					if(i!=dcmsLoginUserList.size()-1){
						serviceLineSkillName+="|";
					}
				}
			}

		}catch (Exception e){
			System.out.println("��ѯ����"+e.getMessage());
			e.printStackTrace();

		}
		System.out.println("serviceLineSkillName:"+serviceLineSkillName);

		String skillName = "��_,��_,,��_,��_�Ϻ�";
		String skikName1[] = skillName.split(",");
		String kill="";
		for(int i=0;i<skikName1.length;i++){
			System.out.println("==============:"+skikName1[i]);
			System.out.println("==============:"+StringUtils.isNotBlank(skikName1[i]));

			if(StringUtils.isNotBlank(skikName1[i])){
			   kill+="^"+skikName1[i];
		   }
		   if(StringUtils.isNotBlank(skikName1[i])&&i!=skikName1.length-1){
		   	  kill+="|";
		   }
		}
		System.out.println("skillName:"+kill);
		String  param = "sd_call_result:{\"clresutid\":\"687996CD-319B-4541-A39A-0039904D5136\",\"sessionid\":\"7375897095883980837\",\"entid\":\"1003\",\"campaiginid\":\"U2019022510450859D5D\",\"campaigntype\":1,\"custid\":\"9\",\"custphone\":\"13752338568\",\"callresult\":2,\"callresultdesc\":\"DestBusy\",\"begintime\":\"2019-02-25 10:46:26\",\"dialtimes\":146,\"ivrtimes\":0,\"altertime\":146,\"transferagenttime\":0,\"transferqueuetime\":0,\"transferalteringtime\":0,\"dialnumber\":1,\"surveyresult\":\"\",\"isfinish\":1,\"agentid\":\"\",\"exdata1\":\"\",\"exdata2\":\"\",\"exdata3\":\"\",\"exdata4\":\"\",\"exdata5\":\"\",\"exdata6\":\"\",\"exdata7\":\"\",\"exdata8\":\"\",\"exdata9\":\"\",\"exdata10\":\"\"}\n";
		String  param1 = "sd_call_result:{\"clresutid\":\"22CB75EE-EABA-446B-ADE0-E64B9E5E5136\",\"sessionid\":\"7375897095229669384\",\"entid\":\"1003\",\"campaiginid\":\"U2019022510450859D5D\",\"campaigntype\":1,\"custid\":\"6\",\"custphone\":\"19922526145\",\"callresult\":5,\"callresultdesc\":\"DestUnknown\",\"begintime\":\"2019-02-25 10:45:49\",\"dialtimes\":137,\"ivrtimes\":0,\"altertime\":137,\"transferagenttime\":0,\"transferqueuetime\":0,\"transferalteringtime\":0,\"dialnumber\":1,\"surveyresult\":\"\",\"isfinish\":1,\"agentid\":\"\",\"exdata1\":\"\",\"exdata2\":\"\",\"exdata3\":\"\",\"exdata4\":\"\",\"exdata5\":\"\",\"exdata6\":\"\",\"exdata7\":\"\",\"exdata8\":\"\",\"exdata9\":\"\",\"exdata10\":\"\"}\n";

		Map<String, SessionDetail> result = new SessionDetailService().computeSessionDetail(param);
		Map<String, SessionDetail> result1 =new SessionDetailService().computeSessionDetail(param);

		if(result.isEmpty()|| null==result.get(param.substring(0,param.indexOf(":")))){
			System.out.println("111");
		}else{
			int index = Math.abs(result.get(param.substring(0,param.indexOf(":"))).getSession_id().hashCode()) % 3;
			System.out.println(result.get(param.substring(0,param.indexOf(":"))).getSession_id());

			System.out.println("111"+index);

		}


		if(result1.isEmpty()|| null==result1.get(param.substring(0,param.indexOf(":")))){
			System.out.println("111");
		}else{
			int index = Math.abs(result1.get(param.substring(0,param.indexOf(":"))).getSession_id().hashCode()) % 3;
			System.out.println(result1.get(param.substring(0,param.indexOf(":"))).getSession_id());

			System.out.println("111"+index);
		}
		System.out.println("param:"+param.substring(0,param.indexOf(":")));
		System.out.println("param1:"+param1.substring(0,param.indexOf(":")));

		String session_id = "7375897095883980839";
		System.out.println(session_id.hashCode()%3);

		int index = Math.abs(session_id.hashCode()) % 3;
		System.out.println(index);

		System.out.println("7375897095883980837".hashCode()%3);
		System.out.println("7375897095883980839".hashCode()%3);
		System.out.println("7375897095883980838".hashCode()%3);


		System.out.println("7375897095263223844".hashCode()%3);
		System.out.println("7375897095229669385".hashCode()%3);
		System.out.println("7375897095883980837".hashCode()%3);
		System.out.println("7375897096118861862".hashCode()%3);
		System.out.println("7375897096269856779".hashCode()%3);
		System.out.println("7375897096873836585".hashCode()%3);

		System.out.println("7375897096873836585".hashCode()%3);
		System.out.println("7375897096873836585".hashCode()%3);


	}

}
