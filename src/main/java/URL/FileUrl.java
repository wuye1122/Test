package URL;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUrl {

	/**
	 * @author wuhl
	 * void 
	 * web���̵�һ��Ŀ¼�ṹ
	 * url: src-->source ���Դ������ļ���
	 * 
	 */
	private final static String FILE_PATH = System.getProperty("Test");
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		    System.out.println("java.version: "+System.getProperty("java.version"));
		    System.out.println("java.class.path: "+System.getProperty("java.class.path"));
		    System.out.println("file.separator: "+System.getProperty("file.separator"));
		//    System.out.println("line.separator: "+System.getProperty("line.separator"));
		 //   System.out.println("path.separator: "+System.getProperty("path.separator"));
		    //��ȡ��ǰ��Ŀ��·�����ַ�����
		    //���·����ȡ relativelyPath
		    String relativelyPath=System.getProperty("user.dir");
		    System.out.println("1���·����ȡ��"+relativelyPath);
		    System.out.println("\\һ��б����ת���ַ��� "+relativelyPath+"\\save");
		    System.out.println("/һ��б����ת���ַ��� "+relativelyPath+"/save");

		    System.out.println("FILE_PATH��"+FILE_PATH);


		 NumberFormat nf = NumberFormat.getNumberInstance();
		              nf.setMaximumFractionDigits(2);
		      		DecimalFormat df=new DecimalFormat(".##");

		  /*  for(int i=0;i<144;i++){	
		    	System.out.println("�����"+df.format((Math.random()*3000+1)));
		    }*/
   
		    
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

		    String  starta="2017-11-21 00:00:00";
			
					try {
						int start = (int)(sdf.parse(starta).getTime()/1000);
						
						int end =start+86400;//����ʱ���
						
					/*	System.out.println("��ѯ��ҵ��ϸ��ʼʱ�� ʱ���"+start);
						System.out.println("��ѯ��ҵ��ϸ����ʱ�� ʱ���"+end);
						

						System.out.println("��ѯ��ҵ��ϸ��ʼʱ�� ʱ������"+stampToDate(start+"000"));
						System.out.println("��ѯ��ҵ��ϸ����ʱ�� ʱ������"+stampToDate(end+"000"));*/
			    
			    for(int i=start;i<end;i+=600){
			    	int end1=i+600;
					//10���ӵ�ʱ���  ת��������
	          /*      System.out.println("ʱ�����"+String.valueOf(i+"000"));
					System.out.println("ʱ���������"+String.valueOf(end1+"000"));*/
					System.out.print("ʱ�����Ӧʱ�����ڣ�"+stampToDate(String.valueOf(i+"000")));
					System.out.print("ʱ�����"+String.valueOf(end1+"000"));
                    Date d= new Date(Long.valueOf(i+"000"));
					System.out.println("ʱ��date��"+d);				
                    System.out.println(d.getMinutes());
                   
/*					System.out.print("ʱ�����Ӧʱ�����ڣ�"+stampToDate(String.valueOf(end1+"000")));
*/			    }
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//��ʼʱ���
		
		    
	}
	//��ʱ������س�����
	public static String stampToDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

}
