package URL;

public class StringU {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	String a="slave.ids=slave-1@10.130.29.41:8080,slave-2@127.0.0.1:8087,slave-3@127.0.0.1:8087";
	            String [] arr= a.split(",");
	           System.out.println("��ӡ���飺"+arr.length);

	            String [] arrUrl=new String[arr.length];
	            //���з�Ƭ��Ϣ��������
	            for(int i=0;i<arr.length;i++){
	            	System.out.println("��ӡ������ַ��"+arr[i]);
	            	System.out.println("��ӡ������ַ��"+arr[i].split("@")[1]);
	                arrUrl[i]=arr[i].split("@")[1];
	            }
	            System.out.println(arrUrl.length);
	}

}
