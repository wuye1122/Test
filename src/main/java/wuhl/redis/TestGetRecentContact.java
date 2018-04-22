package wuhl.redis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class TestGetRecentContact {

	/**
	 * @author wuhl
	 * void
	 */
	//�ϴ���ϵ��  ���������ϵ�˺���ϯ״̬ ����·�ɵ���ϯ
	public String getState(List<MongoRecentAgentInfo> list,HashMap<String,Long> map){
		List<String> list1=new ArrayList<String>();

		List<String> list2=new ArrayList<String>();
		 
		String agentId="";
		System.out.println(list.size());
		System.out.println(map.size());

		//�������ϵ��
		if(list.size()!=0){
			for(MongoRecentAgentInfo po:list){
				if(Integer.valueOf(po.getScore())<=3){
					list1.add(po.getAgentId());
				}else{
					list2.add(po.getAgentId());
				}
			}
			
			System.out.println(list1.size());
			System.out.println(list2.size());
			System.out.println("����ģ�"+list1.toString());
			System.out.println("������ģ�"+list2.toString());

			//��������� 
			if(list1.size()!=0){
				if(map.size()!=0){
					System.out.println("list1.size!=0  map.size!=0 ======");
					System.out.println("dsa:"+map.toString());

					for(String l:list1){
						if(map.get(l)!=null){
						 agentId=l;//��ȡδ��ϵ������ϵ��
						 break;
						}
					}					
				}else{
					System.out.println("list1.size!=0  map.size=0 ======");

					return "��ϯæ��ȴ���";
				//	System.out.println("��ϯæ��ȴ���");
				}
			
			}
			
			if(StringUtils.isNotEmpty(agentId)){
				System.out.println("�������������ϵ����ϯid:"+agentId);
				return agentId;

			}else{
				if(map.size()!=0){
					Set<Entry<String, Long>> set=map.entrySet();
					for(Entry<String, Long> s:set){
					  if(!list2.contains(s.getKey())){
						  agentId=s.getKey();//��ȡδ��ϵ������ϵ��
						  break;
					  }	  
					}
					//��ȡһ����ϵ
					if(StringUtils.isNotEmpty(agentId)){
						System.out.println("��������δ��ϵ����ϯid:"+agentId);
						return agentId;

					}else{
						// System.out.println(map.keySet());
						 Set<String> s=	map.keySet();
						 Object[] o=s.toArray();

						System.out.println("�������һ��������ϯ:"+o[0]);  	
						return o[0].toString();

					}
									
				}else{
					return "��ϯæ��ȴ���";

				}
			}
	
		}else{
			if(map.size()!=0){
				// System.out.println(map.keySet());
				 Set<String> s=	map.keySet();
				 Object[] o=s.toArray();
				System.out.println("û�������ϵ���������"+o[0]);  	
				return o[0].toString();

			}else{
				System.out.println("��ϯæ��ȴ���");
				return "��ϯæ��ȴ���";

			}
				
		}
		
	}

	//test IVR��ȡagentId	
	public void testIvrAgent(){
		long start = System.currentTimeMillis();
		//String param7="entId=8989&skillGroupName=������������1&remoteUrl=BTL:45020036";

				String ent="0101212777";
				String skill="1000006750";
				//��ѯ�����ϵ��
				Test mongodb=new Test();
				List<MongoRecentAgentInfo> list= mongodb.getMongoRecentCon(ent,"recent_contacts","TEL:88822248","���ϱ���");
			
				
				//��ѯ��ϯ״̬
				ZsetRedisHelper redis=new ZsetRedisHelper();
				HashMap<String,Long> map=redis.getAgentStatus(ent, skill);

				TestGetRecentContact test=new TestGetRecentContact();
		         String s= test.getState(list, map);	

		       System.out.println("��ѯ��ϯ״̬��ʱ��:(" + (System.currentTimeMillis() - start)
						+ ")ms");
	}
	
	public void getDateBy5(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

	    String  starta="2017-11-21 00:02:00";
		
				try {
					int start = (int)(sdf.parse(starta).getTime()/1000);
					
					int end =start+86400;//����ʱ���
					
				/*	System.out.println("��ѯ��ҵ��ϸ��ʼʱ�� ʱ���"+start);
					System.out.println("��ѯ��ҵ��ϸ����ʱ�� ʱ���"+end);
					

					System.out.println("��ѯ��ҵ��ϸ��ʼʱ�� ʱ������"+stampToDate(start+"000"));
					System.out.println("��ѯ��ҵ��ϸ����ʱ�� ʱ������"+stampToDate(end+"000"));*/
		    
		    for(int i=start;i<end;i+=600){
		    	int end1=i+600;
		
				System.out.print("ʱ�����Ӧʱ�����ڣ�"+stampToDate(String.valueOf(i+"000")));
				System.out.print("ʱ�����"+String.valueOf(end1+"000"));
                Date d= new Date(Long.valueOf(i+"000"));
				System.out.println("ʱ��date��"+d);				
/*                System.out.println(d.getMinutes());
*/                Calendar calendar = Calendar.getInstance();
              //  calendar.setTime(d);
                long l=Long.valueOf(i+"000");
       
/*					System.out.print("ʱ�����Ӧʱ�����ڣ�"+stampToDate(String.valueOf(end1+"000")));
*/			    }
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//��ʼʱ���
	
	}
	
	//��ʱ���ת��ʱ��
	public static String stampToDate(String s){
			String res;
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long lt = new Long(s);
			Date date = new Date(lt);
			res = simpleDateFormat.format(date);
			return res;
		}
		
	//��ȡkeyʧЧʱ��� 5 10����ʧЧ
	public void getExpire(String ms){
		Calendar c = Calendar.getInstance();
		// c.setTimeInMillis(Long.valueOf("1513821096000"));
		c.setTimeInMillis(Long.valueOf(ms + "000"));
		// System.out.println("======="+c.getTimeInMillis());
		// System.out.println(c.getTime());
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int i = c.get(Calendar.MINUTE);
		int addMinBy5 = 5 - i % 5;
		int addMiNBy10 = 10 - i % 10;
		// System.out.println("���ӵ�5���ӣ�"+addMinBy5+"���ӵ�ʱ��"+addMinBy5*60);
		// System.out.println("���ӵ�10���ӣ�"+addMiNBy10+"���ӵ�ʱ��"+addMiNBy10*60);

		Long by5 = (Long.valueOf(ms) + addMinBy5 * 60);
		Long by10 = (Long.valueOf(ms) + addMiNBy10 * 60);

		// System.out.println(by5);
		// System.out.println(by10);

		/*
		 * int j=i%5==0?i:i+(5-i%5); System.out.println("=="+j); int
		 * z=i%10==0?i:i+(10-i%10); System.out.println("--"+z);
		 */
		/*
		 * for(int i=1;i<=59;i++){ int j=i%5==0?i:i+(5-i%5);
		 * System.out.println("=="+j); int z=i%10==0?i:i+(10-i%10);
		 * System.out.println("--"+z);
		 * 
		 * 
		 * }
		 */
		// ��ȡ��
		int year = c.get(Calendar.YEAR);
		// ��ȡ�·ݣ�0��ʾ1�·�
		int month = c.get(Calendar.MONTH) + 1;
		// ��ȡ��ǰ����
		int day = c.get(Calendar.DAY_OF_MONTH);
		// ��ȡ������С����
		int first = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		// ��ȡ�����������
		int last = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		// ��ȡ��ǰСʱ
		int time = c.get(Calendar.HOUR_OF_DAY);
		// ��ȡ��ǰ����
		int min = c.get(Calendar.MINUTE);
		// ��ȡ��ǰ��
		int sec = c.get(Calendar.SECOND);

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String curDate = s.format(c.getTime()); // ��ǰ����
		// System.out.println("��ǰʱ�䣺" + year + "-" + month + "-" + day + " " +
		// time + ":" + min + ":" + sec);
		System.out.println(curDate);
		System.out.println(String.valueOf(by5));
		System.out.println(String.valueOf(by10));

		System.out.println(stampToDate(String.valueOf(by5) + "000"));
		System.out.println(stampToDate(String.valueOf(by10) + "000"));

	}
	
	//��ȡkey 10����ʧЧ
	public String getTenExpire(String ms){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.valueOf(ms + "000"));
		int i = c.get(Calendar.MINUTE);
		int addMiNBy10 = 10 - i % 10;
		// System.out.println("���ӵ�10���ӣ�"+addMiNBy10+"���ӵ�ʱ��"+addMiNBy10*60);

		Long by10 = (Long.valueOf(ms) + addMiNBy10 * 60);

		// System.out.println(by10);


		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String curDate = s.format(c.getTime()); // ��ǰ����
	
		System.out.println(curDate);
		System.out.println(String.valueOf(by10));

		System.out.println(stampToDate(String.valueOf(by10) + "000"));
		return String.valueOf(by10);

	}
	
	//��ȡkey 5����ʧЧ
	public String getFiveFExpire(String ms){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.valueOf(ms + "000"));
		int i = c.get(Calendar.MINUTE);
		int addMinBy5 = 5 - i % 5;
		// System.out.println("���ӵ�5���ӣ�"+addMinBy5+"���ӵ�ʱ��"+addMinBy5*60);

		Long by5 = (Long.valueOf(ms) + addMinBy5 * 60);

		// System.out.println(by5);

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String curDate = s.format(c.getTime()); // ��ǰ����

		System.out.println(curDate);
		System.out.println(String.valueOf(by5));
		System.out.println(stampToDate(String.valueOf(by5) + "000"));
		return String.valueOf(by5);

	}	
	
	//��ȡkey ����ʧЧ
	public String getDayExpire(String ms){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.valueOf(ms + "000"));

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		String curDate = s.format(c.getTime()); // ��ǰ����
		System.out.println(curDate);
		c.add(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		System.out.println(s.format(c.getTime()));
		String netDay = String.valueOf(c.getTimeInMillis());

		String nextDay=netDay.substring(0, netDay.length() - 3);
		return nextDay;

	}	
	
	//��ȡkey ����ʧЧ
	public String getMonthExpire(String ms){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.valueOf(ms + "000"));

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		String curDate = s.format(c.getTime()); // ��ǰ����
		System.out.println(curDate);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		System.out.println(s.format(c.getTime()));
		String netDay = String.valueOf(c.getTimeInMillis());
		System.out.println(netDay.substring(0, netDay.length() - 3));
		String nextMonth=netDay.substring(0, netDay.length() - 3);
		return nextMonth;

	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//test IVR��ȡagentId
	//  new TestGetRecentContact().testIvrAgent();
		int a=5985;
		System.out.println(a%5);
		System.out.println(a/50);

		//����ͷ�� ���ƺ���
		//new ZsetRedisHelper().limitCall();
		

		//����ͷ�� ���ƺ��� ʱ�����Ӧʱ��
	//	new TestGetRecentContact().getDateBy5();
		
		//��ȡkey��ʧЧʱ�䣺��һ��
	/*	String ms="1513836662";
		new TestGetRecentContact().getDayExpire(ms);
        System.out.println("===============");
		
		//��ȡkey��ʧЧʱ�䣺 5 10����
		String ms5="1513836662";
		new TestGetRecentContact().getFiveFExpire(ms5);
        System.out.println("===============");

       //��ȡkey��ʧЧʱ�䣺 5 10����
		String ms10 = "1513836662";
		System.out.println(new TestGetRecentContact().getTenExpire(ms10));
		System.out.println("===============");
              
        //��ȡkey��ʧЧʱ�䣺 ��һ����
        String ms6="1508566262";
		new TestGetRecentContact().getMonthExpire(ms6);
        System.out.println("===============");*/
	
        
        /*	int start=1513836002;
		int end=1513839600;
		for(int i=start;i<=end;i=i+60){
			new TestGetRecentContact().getExpire(String.valueOf(i));

		}*/
	    

	}

}
